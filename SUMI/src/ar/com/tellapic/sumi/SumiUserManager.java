/**
 * 
 */
package ar.com.tellapic.sumi;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

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
public class SumiUserManager extends TellapicTreeTableModel implements SumiUserManagerInterface, SumiUserManagerState, Observer {

    /* The users list */
    private ArrayList<SumiUser> users;

    /**
     * Constructor: Creates an empty uses list.
     */
    public SumiUserManager() {
        super();
        users = new ArrayList<SumiUser>();
    }

    /**
     * 
     * @param user
     */
    private void cleanRemovedUser(SumiUser user) {
        System.out.println("Child count: "+getRoot().getChildCount());
        user.deleteObserver(this);
        TellapicNode removedNode = getNodeForUser(user);
        removeNodeFromParent(removedNode);
        //TODO: Check if node was removed (e.g. has no more references)
        System.out.println("Child count: "+getRoot().getChildCount());
    }
    
    /**
     * 
     * @param user
     */
    private void arrangeAddedUser(SumiUser user, List<TellapicNodeAction> actions) {
        user.addObserver(this);

        /* Create the node that will wrap the object */
//        TellapicNode node = new TellapicNode(user, new ImageIcon(SumiUserManager.class.getResource("/icons/user.png")));
        TellapicNode node = user.getObjectRootNode();
        node.addTellapicNodeAddActionListener(nodeChangeListener);

        /* As we know that we are adding a SumiUser, build the preferred actions... */
        /* Should we put this code here? */
//        TellapicNodeAction visibilityAction = new DefaultTellapicNodeActionCheckBox(new ToggleVisibilityAction());
//        actions.add(visibilityAction);

        if (actions != null)
            for(TellapicNodeAction nodeAction : actions)
                node.addAction(nodeAction);

        /* And finally, put the node into the tree model */
        TellapicNode insertAtNode = (TellapicNode) getRoot();
        int idx = insertAtNode.getChildCount();
        insertNodeInto(node, insertAtNode, idx);
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerInterface#addUser(ar.com.tellapic.gumi.SumiUser, java.util.List)
     */
    @Override
    public boolean addUser(SumiUser user, List<TellapicNodeAction> actions) {
        boolean added = users.add(user);

        if (added)
            arrangeAddedUser(user, actions);

        return added;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerInterface#delUser(java.lang.String)
     */
    @Override
    public boolean delUser(String name) {
        SumiUser user = getUser(name);
        
        return delUser(user);
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerInterface#delUser(int)
     */
    @Override
    public boolean delUser(int id) {
        SumiUser user = getUser(id);

        return delUser(user);
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerInterface#delUser(ar.com.tellapic.gumi.SumiUser)
     */
    @Override
    public boolean delUser(SumiUser user) {
        boolean removed = users.remove(user);
        
        if (removed)
            cleanRemovedUser(user);
        
        return removed;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerState#getUsers()
     */
    @Override
    public List<SumiUser> getUsers() {
        return users;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerState#getUser(java.lang.String)
     */
    @Override
    public SumiUser getUser(String userName) {
        SumiUser user  = null;
        int i;

        for(i = 0; i < users.size() && !users.get(i).getName().equals(userName); i++);

        if (i < users.size())
            user = users.get(i);

        return user;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.SumiUserManagerState#getUser(int)
     */
    @Override
    public SumiUser getUser(int id) {
        SumiUser user = null;
        int i;
        for(i = 0; i < users.size() && users.get(i).getUserId() != id; i++);

        if (i < users.size())
            user = users.get(i);

        return user;
    }


    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unused")
    public void update(Observable o, Object arg) {
        Object[] params = (Object[])arg;
        SumiUser user = (SumiUser) o;
        int userIndex = users.indexOf(user);
        //TODO: some work
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUser(boolean)
     */
    @Override
    public List<SumiUser> getUsers(boolean remoteUser) {
        return users;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#setUserVisible(boolean, boolean)
     */
    @Override
    public void setRemoteUsersVisibility(boolean visible) {
        for(SumiUser user : users)
            if (user.isRemote())
                user.setVisible(visible);
    }
    
    
    /**
     * 
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
    @SuppressWarnings("unused")
    private class ToggleVisibilityAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ToggleVisibilityAction() {
            putValue(ACTION_COMMAND_KEY, "Visibility");
            putValue(NAME, "Visibility");
        }

        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            TellapicNodeAction action = (TellapicNodeAction) e.getSource();
            TellapicNode node = action.getNode();
            SumiUser user = (SumiUser) node.getUserObject();
            boolean oldValue = user.isVisible();
            user.setVisible(!oldValue);
        }
    }
}