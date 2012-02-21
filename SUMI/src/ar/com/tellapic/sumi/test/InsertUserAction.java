/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.GumiUser;
import ar.com.tellapic.sumi.GumiUserManager;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCombo;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.EmptyTellapicNodeAction;
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
public class InsertUserAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private GumiUserManager userManager;

	
	/**
	 * @param tree
	 */
	public InsertUserAction(GumiUserManager userManager) {
		super("Add User" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/plus-button.png")));
		putValue(SHORT_DESCRIPTION, "Adds a new user.");
		this.userManager = userManager;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CreateNewNodeDialog dialog = new CreateNewNodeDialog(null);
		dialog.setVisible(true);
		dialog.pack();
		if (dialog.EXIT_STATUS < 0)
			return;
		DialogInfo data = dialog.getDialogInfo();
		buildNode(data);
 	}
	
	private void buildNode(DialogInfo data) {
		GumiUser user = new GumiUser(0, data.getNodeName());

		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();

		/* Gets the column data (e.g. builds GumiNodeAction) */
		for(Entry<String, String> map : data.getColumnData()) {
			String key = map.getKey();
			String value = map.getValue();
			TellapicNodeAction nodeAction = null;
			if (key.equals("text")) {
				nodeAction = new DefaultTellapicNodeActionLabel(value, true);
			} else if (key.equals("combo")) {
				String[] lblValues = value.split(":");
				if (lblValues.length > 1)
					nodeAction = new DefaultTellapicNodeActionCombo(lblValues[0], lblValues[1].split(","));
				else
					nodeAction = new DefaultTellapicNodeActionCombo("", value.split(","));
			} else if (key.equals("boolean")) {
				nodeAction = new DefaultTellapicNodeActionCheckBox(value);
			} else {
				nodeAction = new EmptyTellapicNodeAction(null);
			}
			actions.add(nodeAction);
		}

		userManager.addUser(user, actions);
	}
}