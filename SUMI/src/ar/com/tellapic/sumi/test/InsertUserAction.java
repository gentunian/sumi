/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.SumiUserManager;

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


    /**
     * @param tree
     */
    public InsertUserAction() {
        super("Add User" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/plus-button.png")));
        putValue(SHORT_DESCRIPTION, "Adds a new user.");
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

        SumiUserManager.getInstance().createUser(data.getNodeName());

    }
}