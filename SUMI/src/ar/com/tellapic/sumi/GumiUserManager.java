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
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
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
public class GumiUserManager extends TellapicTreeTableModel implements GumiUserManagerInterface, GumiUserManagerState, Observer {

    /* The users list */
    private ArrayList<GumiUser> users;

    /**
     * Constructor: Creates an empty uses list.
     */
    public GumiUserManager() {
        super();
        users = new ArrayList<GumiUser>();
    }

    /**
     * 
     * @param user
     */
    private void cleanRemovedUser(GumiUser user) {
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
    private void arrangeAddedUser(GumiUser user, List<TellapicNodeAction> actions) {
        user.addObserver(this);

        /* Create the node that will wrap the object */
        TellapicNode node = new TellapicNode(user, new ImageIcon(GumiUserManager.class.getResource("/icons/user.png")));
        node.addTellapicNodeAddActionListener(nodeChangeListener);

        /* As we know that we are adding a GumiUser, build the preferred actions... */
        /* Should we put this code here? */
        TellapicNodeAction visibilityAction = new DefaultTellapicNodeActionCheckBox(new ToggleVisibilityAction());
        actions.add(visibilityAction);

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
     * @see ar.com.tellapic.gumi.GumiUserManagerInterface#addUser(ar.com.tellapic.gumi.GumiUser, java.util.List)
     */
    @Override
    public boolean addUser(GumiUser user, List<TellapicNodeAction> actions) {
        boolean added = users.add(user);

        if (added)
            arrangeAddedUser(user, actions);

        return added;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerInterface#delUser(java.lang.String)
     */
    @Override
    public boolean delUser(String name) {
        GumiUser user = getUser(name);
        
        return delUser(user);
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerInterface#delUser(int)
     */
    @Override
    public boolean delUser(int id) {
        GumiUser user = getUser(id);

        return delUser(user);
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerInterface#delUser(ar.com.tellapic.gumi.GumiUser)
     */
    @Override
    public boolean delUser(GumiUser user) {
        boolean removed = users.remove(user);
        
        if (removed)
            cleanRemovedUser(user);
        
        return removed;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerState#getUsers()
     */
    @Override
    public List<GumiUser> getUsers() {
        return users;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerState#getUser(java.lang.String)
     */
    @Override
    public GumiUser getUser(String userName) {
        GumiUser user  = null;
        int i;

        for(i = 0; i < users.size() && !users.get(i).getName().equals(userName); i++);

        if (i < users.size())
            user = users.get(i);

        return user;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.gumi.GumiUserManagerState#getUser(int)
     */
    @Override
    public GumiUser getUser(int id) {
        GumiUser user = null;
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
    public void update(Observable o, Object arg) {
        @SuppressWarnings("unused")
        Object[] params = (Object[])arg;
        @SuppressWarnings("unused")
        GumiUser user = (GumiUser) o;
        //TODO: some work
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.GumiUserManagerState#getUser(boolean)
     */
    @Override
    public List<GumiUser> getUsers(boolean remoteUser) {
        return users;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.GumiUserManagerInterface#setUserVisible(boolean, boolean)
     */
    @Override
    public void setRemoteUsersVisibility(boolean visible) {
        for(GumiUser user : users)
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
            GumiUser user = (GumiUser) node.getUserObject();
            boolean oldValue = user.isVisible();
            user.setVisible(!oldValue);
        }
    }
}