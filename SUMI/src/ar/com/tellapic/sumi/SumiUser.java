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

    /* Constants for property changes */
    public static final int SELECTION_CHANGED  = -9;
    public static final int VISIBILITY_CHANGED = -8;
    public static final int NAME_SET           = -7;
    public static final int USER_ID_SET        = -6;

    /* Basic information a SumiUser will hold */
    private int              userId;
    private String           name;
    private boolean          visible;
    private boolean          selected;

    /* Icons */
    private Icon             icon;
    private Icon             propertyIcon;
    
    
    private ToggleUserVisibilityNodeAction toggleVisibilityAction = new ToggleUserVisibilityNodeAction("");
    private UserSelectedStateNodeAction    selectedStateAction    = new UserSelectedStateNodeAction("");

    /**
     * 
     * @param id
     * @param name
     * @param visible
     * @param remote
     * @param selected
     */
    public SumiUser(int id, String name, boolean visible, boolean remote, boolean selected) {
        setUserId(id);
        setName(name);
        setVisible(visible);
        setSelected(selected);
        setIcon(new ImageIcon(SumiUser.class.getResource("/icons/user.png")));
        setPropertyIcon(new ImageIcon(SumiUser.class.getResource("/icons/property.png")));
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
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
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectNodes()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        // Create the root node
        TellapicNode rootNode = new TellapicNode(this, getIcon());
        
        // Create the node for the visible property that will hold
        // the action 'toggleVisibilityAction'
        TellapicNode isVisibleNode = new TellapicNode(
                "Visible",
                getPropertyIcon(),
                toggleVisibilityAction
                );
        
        // Create the node for the name property. This will hold a default label action.
        TellapicNode nameNode = new TellapicNode(
                "Name",
                getPropertyIcon(),
                new DefaultTellapicNodeActionLabel(
                        name,
                        false // we will not edit this field
                        )
                );
        
        // Create the node for the id property and a default label action.
        TellapicNode idNode = new TellapicNode(
                "Id",
                getPropertyIcon(),
                new DefaultTellapicNodeActionLabel(
                        String.valueOf(userId),
                        false // we will not edit this field
                        )
                );
        
        // Lastly, the selected node with a custom action: 'selectedStateAction'
        TellapicNode isSelectedNode = new TellapicNode(
                "Selected",
                getPropertyIcon(),
                selectedStateAction
                );
        
        // Add the nodes to the root node...
        rootNode.add(isSelectedNode);
        rootNode.add(isVisibleNode);
        rootNode.add(nameNode);
        rootNode.add(idNode);
        
        // Configure nodes...[OPTIONAL AND NOT FULLY TESTED]
        isVisibleNode.setTooltipText("User visibility property");
        isSelectedNode.setTooltipText("User selected property");
        nameNode.setTooltipText("User name property");
        idNode.setTooltipText("User id property");
        
        // Return the root
        return rootNode;
    }
    
    // How will the model know if we are selected?
    // There are better approachs maybe, this is straighforward.
    private class UserSelectedStateNodeAction extends DefaultTellapicNodeActionCheckBox {

        // False == Not editable action, just shows information with a checkbox
        public UserSelectedStateNodeAction(String text) {
            super(text, false); // We don't want the CheckBox to be editable
        }
        
        @Override
        public Object getValue() {
            return isSelected();
        }
    }
    
    // We will provide a way to interact with this user visibility property.
    // This is another straightforward example of using @see ar.com.tellapic.gumi.TellapicNodeAction
    private class ToggleUserVisibilityNodeAction extends DefaultTellapicNodeActionCheckBox {
        
        // False == Not editable action, just shows information with a checkbox
        public ToggleUserVisibilityNodeAction(String text) {
            super(text, true); // We want the CheckBox to be editable
        }
        
        /* (non-Javadoc)
         * @see ar.com.tellapic.gumi.TellapicNodeAction#setValue(java.lang.Object)
         */
        @Override
        public void setValue(Object value) {
            // Avoid halting in vain if for some reason the model is doing something wrong
            // Log to standard output instead
            if (value instanceof Boolean)
                setVisible((Boolean) value);
            else
                System.out.println("[WRN!!] Value "+value+" should be a Boolean instance.");
        }

        /* (non-Javadoc)
         * @see ar.com.tellapic.gumi.TellapicNodeAction#getValue()
         */
        @Override
        public Object getValue() {
            return isVisible();
        }
    }
}
