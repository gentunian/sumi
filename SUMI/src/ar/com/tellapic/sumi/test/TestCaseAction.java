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

import ar.com.tellapic.sumi.SumiUser;
import ar.com.tellapic.sumi.SumiUserManager;
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
	private SumiUserManager model;
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
	public TestCaseAction(SumiUserManager model) {
		super("Test Case" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/flask.png")));
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		List<SumiUser> users = createUsers(40);
		model.addUser(users.get(0), createTest(3));
		model.addUser(users.get(1), createTest(3));
        
//		for(int i = 0; i < users.size(); i++) {
//			if (i < 10) {
//				List<TellapicNodeAction> l = createLabelActions(1);
//				l.addAll(createComboActions(1));
//				l.addAll(createColorActions(2));
//				model.addUser(users.get(i), l);
//			}
//			else if (i >= 10 && i < 20)
//				model.addUser(users.get(i), createComboActions(2));
//			else if (i >=20 && i < 30)
//				model.addUser(users.get(i), createCheckboxActions(4));
//			else
//				model.addUser(users.get(i), createColorActions(5));
//		}
	}
	
	private List<SumiUser> createUsers(int howMany) {
		List<SumiUser> list = new ArrayList<SumiUser>();
		for(int i = 0; i < howMany; i++) {
			SumiUser user = new SumiUser(0, "user"+i);
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
	
	private List<TellapicNodeAction> createTest(int howMany) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();
		TellapicNodeAction a1 = new OrderIssuedNodeAction("", new String[]{"SO-123","12/12/12"}, false);
		TellapicNodeAction a2 = new ItemsNodeAction("", new String[]{"item1","item2","item3"}, false);
		TellapicNodeAction a3 = new DualImageNodeAction(new SomeSwingAction());
		actions.add(a1);
		actions.add(a2);
		actions.add(a3);
		return actions;
	}
}
