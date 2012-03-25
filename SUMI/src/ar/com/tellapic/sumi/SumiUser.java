package ar.com.tellapic.sumi;

import java.util.Observable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface;

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
public class SumiUser extends Observable implements TellapicNodeCreatorInterface {

    public static final int REMOTE_CHANGED     = -10;
    public static final int SELECTION_CHANGED  = -9;
    public static final int VISIBILITY_CHANGED = -8;
    public static final int NAME_SET           = -7;
    public static final int USER_ID_SET        = -6;

    private int              userId;
    private String           name;
    private boolean          visible;
    private boolean          selected;
    private boolean          remote;
    private Icon             icon;
    private Icon             propertyIcon;
    
    private ToggleUserVisibilityNodeAction toggleVisibilityAction = new ToggleUserVisibilityNodeAction();
    private UserSelectedStateNodeAction    selectedStateAction = new UserSelectedStateNodeAction();

    /**
     * 
     * @param id
     * @param name
     * @param visible
     * @param remote
     * @param selected
     */
    public SumiUser(int id, String name, boolean visible, boolean remote, boolean selected) {
        this.userId   = id;
        this.name     = name;
        this.visible  = visible;
        this.remote   = remote;
        this.selected = selected;
        this.icon = new ImageIcon(SumiUser.class.getResource("/icons/user.png"));
        this.propertyIcon = new ImageIcon(SumiUser.class.getResource("/icons/property.png"));
    }

    /**
     * 
     * @param id
     * @param name
     */
    public SumiUser(int id, String name) {
        this(id, name, true, false, false);
    }

    /**
     * 
     * @param id
     * @param name
     * @param visible
     */
    public SumiUser(int id, String name, boolean visible) {
        this(id, name, visible, false, false);
    }

    /**
     * 
     * @param id
     * @param name
     * @param visible
     * @param remote
     */
    public SumiUser(int id, String name, boolean visible, boolean remote) {
        this(id, name, visible, remote, false);
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
        setChanged();
        notifyObservers(new Object[]{USER_ID_SET});
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers(new Object[]{NAME_SET});
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        if (this.visible != visible)
            setChanged();

        this.visible = visible;
        notifyObservers(new Object[]{VISIBILITY_CHANGED});
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        if (this.selected != selected)
            setChanged();

        this.selected = selected;
        notifyObservers(new Object[]{SELECTION_CHANGED});
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param remote the remote to set
     */
    public void setRemote(boolean remote) {
        if (this.remote != remote)
            setChanged();

        this.remote = remote;
        notifyObservers(new Object[]{REMOTE_CHANGED});
    }

    /**
     * @return the remote
     */
    public boolean isRemote() {
        return remote;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectNodes()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = new TellapicNode(this, getIcon());
        TellapicNode isVisibleNode = new TellapicNode("Visible", getPropertyIcon(), toggleVisibilityAction);
        TellapicNode isRemoteNode = new TellapicNode("Remote", getPropertyIcon(), new DefaultTellapicNodeActionCheckBox("", false));
        TellapicNode nameNode = new TellapicNode("Name", getPropertyIcon(), new DefaultTellapicNodeActionLabel(name, false));
        TellapicNode idNode = new TellapicNode("Id", getPropertyIcon(), new DefaultTellapicNodeActionLabel(String.valueOf(userId), false));
        TellapicNode isSelectedNode = new TellapicNode("Selected", getPropertyIcon(), selectedStateAction);
        // Add the nodes to the root node...
        rootNode.add(isSelectedNode);
        rootNode.add(isRemoteNode);
        rootNode.add(isVisibleNode);
        rootNode.add(nameNode);
        rootNode.add(idNode);
        // Configure nodes...
        isVisibleNode.setTooltipText("User visibility property");
        isSelectedNode.setTooltipText("User selected property");
        nameNode.setTooltipText("User name property");
        idNode.setTooltipText("User id property");
        isRemoteNode.setTooltipText("User remote property");
        // Return the root
        return rootNode;
    }

    /**
     * @return
     */
    public Icon getIcon() {
        return icon;
    }
    
    /**
     * 
     * @param icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * @return
     */
    public Icon getPropertyIcon() {
        return propertyIcon;
    }
    
    /**
     * 
     * @param icon
     */
    public void setPropertyIcon(Icon icon) {
        this.propertyIcon = icon;
    }
    
}
