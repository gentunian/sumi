/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.GumiUser;
import ar.com.tellapic.sumi.GumiUserManager;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionColor;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCombo;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNodeAction;

/**
 *   Copyright (c) 2010 Sebastián Treu.
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; version 2 of the License.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 * @author
 *         Sebastian Treu 
 *         sebastian.treu(at)gmail.com
 *
 */
public class TestCaseAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GumiUserManager model;
	private Color[] colorArray = new Color[] {
			Color.red,
			Color.blue,
			Color.black,
			Color.yellow,
			Color.cyan,
			Color.gray,
			Color.green,
			Color.magenta,
			Color.orange
	};

	/**
	 * 
	 */
	public TestCaseAction(GumiUserManager model) {
		super("Test Case" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/flask.png")));
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		List<GumiUser> users = createUsers(40);
		
		for(int i = 0; i < users.size(); i++) {
			if (i < 10)
				model.addUser(users.get(i), createLabelActions(3));
			else if (i >= 10 && i < 20)
				model.addUser(users.get(i), createComboActions(2));
			else if (i >=20 && i < 30)
				model.addUser(users.get(i), createCheckboxActions(4));
			else
				model.addUser(users.get(i), createColorActions(5));
		}
	}
	
	private List<GumiUser> createUsers(int howMany) {
		List<GumiUser> list = new ArrayList<GumiUser>();
		for(int i = 0; i < howMany; i++) {
			GumiUser user = new GumiUser(0, "user"+i);
			list.add(user);
		}
		return list;
	}
	
	private List<TellapicNodeAction> createLabelActions(int howMany) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();
		for(int i = 0; i < howMany; i++) {
			TellapicNodeAction nodeAction = new DefaultTellapicNodeActionLabel("labelAction"+i, true);
			actions.add(nodeAction);
		}
		return actions;
	}
	
	private List<TellapicNodeAction> createComboActions(int howMany) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();
		for(int i = 0; i < howMany; i++) {
			TellapicNodeAction nodeAction = new DefaultTellapicNodeActionCombo("comboAction"+i, new String[] {"comboValue1", "comboValu2", "comboValu3"});
			actions.add(nodeAction);
		}
		return actions;
	}
	
	private List<TellapicNodeAction> createCheckboxActions(int howMany) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();
		for(int i = 0; i < howMany; i++) {
			TellapicNodeAction nodeAction = new DefaultTellapicNodeActionCheckBox("checkbox"+i);
			actions.add(nodeAction);
		}
		return actions;
	}
	
	private List<TellapicNodeAction> createColorActions(int howMany) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();
		for(int i = 0; i < howMany; i++) {
			TellapicNodeAction nodeAction = new DefaultTellapicNodeActionColor("color"+i, colorArray [i%10], i%2==0);
			actions.add(nodeAction);
		}
		return actions;
	}
}
