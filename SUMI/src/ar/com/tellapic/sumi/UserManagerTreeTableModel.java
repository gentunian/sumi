/**
 * 
 */
package ar.com.tellapic.sumi;

import java.util.Observable;
import java.util.Observer;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
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
public class UserManagerTreeTableModel extends TellapicTreeTableModel implements Observer {


    private SumiUserManager userManager = SumiUserManager.getInstance();
    
    public UserManagerTreeTableModel() {
        userManager.addObserver(this);
        for(SumiUser user : userManager.getUsers()) {
            wrapUser(user);
        }
    }

    /**
     * 
     * @param user
     */
    private void cleanRemovedUser(SumiUser user) {
        user.deleteObserver(this);
        TellapicNode removedNode = getNodeForUser(user);
        removeNodeFromParent(removedNode);
    }
    
    /**
     * 
     * @param user
     */
    private void wrapAddedUser(SumiUser user) {
        user.addObserver(this);

        /* Create the node that will wrap the object */
        TellapicNode node = wrapUser(user); 

        /* And finally, put the node into the tree model */
        TellapicNode insertAtNode = (TellapicNode) getRoot();
        int idx = insertAtNode.getChildCount();
        insertNodeInto(node, insertAtNode, idx);
    }

    /**
     * 
     * @return
     */
    private TellapicNode wrapUser(SumiUser user) {
        TellapicNode rootNode = new TellapicNode(user, user.getIcon());
        
        TellapicNodeAction nameInfo     = new DefaultTellapicNodeActionLabel(user.getName(), false);
        TellapicNodeAction idInfo       = new DefaultTellapicNodeActionLabel(String.valueOf(user.getUserId()), false);
        TellapicNodeAction selectedInfo = new DefaultTellapicNodeActionCheckBox("", user.isSelected(), false);
        TellapicNodeAction visibleInfo  = new DefaultTellapicNodeActionCheckBox("", user.isVisible(), true);
        
        TellapicNode nameNode     = new TellapicNode(SumiUser.PROPERTY_NAME, user.getPropertiesIcon(), nameInfo);
        TellapicNode idNode       = new TellapicNode(SumiUser.PROPERTY_ID, user.getPropertiesIcon(), idInfo);
        TellapicNode selectedNode = new TellapicNode(SumiUser.PROPERTY_SELECTED, user.getPropertiesIcon(), selectedInfo);
        TellapicNode visibleNode  = new TellapicNode(SumiUser.PROPERTY_VISIBLE, user.getPropertiesIcon(), visibleInfo);
        
        rootNode.add(nameNode);
        rootNode.add(idNode);
        rootNode.add(selectedNode);
        rootNode.add(visibleNode);
        
        return rootNode;
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SumiUserManager) {
            if (arg instanceof Object[]) {
                if (((Object[])arg)[0] instanceof String && ((Object[])arg)[1] instanceof SumiUser) {
                    String action = (String)((Object[])arg)[0];
                    SumiUser user = (SumiUser)((Object[])arg)[1];
                    if (action.equals(SumiUserManager.USER_ADDED)) {
                        wrapAddedUser(user);
                    } else if (action.equals(SumiUserManager.USER_REMOVED)) {
                        cleanRemovedUser(user);
                    }
                }
            }
        }
    }
}