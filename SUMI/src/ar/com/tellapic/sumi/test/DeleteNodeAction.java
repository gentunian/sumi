/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.SumiUser;
import ar.com.tellapic.sumi.SumiUserManager;
import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicTreeTableModel;


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
public class DeleteNodeAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    private TellapicTreeTableModel model;

    /**
     * @param model 
     * @param tree
     */
    public DeleteNodeAction(TellapicTreeTableModel model) {
        super("Delete User" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/minus-button.png")));
        putValue(SHORT_DESCRIPTION, "Deletes the selected user.");
        this.model = model;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (model == null)
            return;
        
        for(TellapicNode node : model.getSelectedNodes()) {
            SumiUser user = (SumiUser) node.getUserObject();
            SumiUserManager.getInstance().delUser(user);
        }
    }
}

