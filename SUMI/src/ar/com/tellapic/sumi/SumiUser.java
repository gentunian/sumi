package ar.com.tellapic.sumi;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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
public class SumiUser extends Observable implements PropertyChangeListener {

    public static final String PROPERTY_NAME     = "Name";
    public static final String PROPERTY_VISIBLE  = "Visible";
    public static final String PROPERTY_ID       = "ID";
    public static final String PROPERTY_ICON     = "Icon";
    public static final String PROPERTY_SELECTED = "Selected";
    
    private HashMap<String, Object> properties;
    
    private Icon             propertiesIcon = new ImageIcon(SumiUser.class.getResource("/icons/property.png"));

    /**
     * 
     * @param sid
     * @param name
     * @param visible
     * @param selected
     */
    public SumiUser(long sid, String name, boolean visible, boolean selected) {
        properties = new HashMap<String, Object>();
        setUserId(sid);
        setName(name);
        setSelected(selected);
        setVisible(visible);
        setIcon(new ImageIcon(SumiUser.class.getResource("/icons/user.png")));
    }

    /**
     * 
     * @param sid
     * @param name
     */
    public SumiUser(long sid, String name) {
        this(sid, name, true, false);
    }

    /**
     * 
     * @param id
     * @param name
     * @param visible
     */
    public SumiUser(long id, String name, boolean visible) {
        this(id, name, visible, false);
    }

    /**
     * 
     * @param key
     * @param newValue
     */
    public void setProperty(String key, Object newValue) {
        Object oldValue = properties.get(key);
        if (oldValue != newValue) {
            System.out.println("Setting property "+key+" to "+newValue);
            properties.put(key, newValue);
            propertyChange(new PropertyChangeEvent(this, key, oldValue, newValue));
        }
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public Object getProperty(String key) {
        return properties.get(key);
    }
    
    /**
     * @param sid the userId to set
     */
    public void setUserId(long sid) {
        setProperty(PROPERTY_ID, sid);
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return (Long) getProperty(PROPERTY_ID);
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        setProperty(PROPERTY_NAME, name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return (String) getProperty(PROPERTY_NAME);
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        setProperty(PROPERTY_SELECTED, selected);
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return (Boolean) getProperty(PROPERTY_SELECTED);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (String) getProperty(PROPERTY_NAME);
    }

    
    /**
     * @return
     */
    public Icon getIcon() {
        return (Icon) getProperty(PROPERTY_ICON);
    }
    
    /**
     * 
     * @param icon
     */
    public void setIcon(Icon icon) {
        setProperty(PROPERTY_ICON, icon);
    }
    
    /**
     * 
     * @param p
     * @return
     */
    public boolean matchProperties(SumiUser user) {
        Set<Entry<String, Object>> set1 = properties.entrySet();
        Set<Entry<String, Object>> set2 = user.getProperties().entrySet();
        
        return set1.containsAll(set2);
    }

    /**
     * @return
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setChanged();
        notifyObservers(evt.getPropertyName());
    }

    /**
     * @return
     */
    public Icon getPropertiesIcon() {
        return propertiesIcon;
    }

    /**
     * @return
     */
    public boolean isVisible() {
        return (Boolean) getProperty(PROPERTY_VISIBLE);
    }
    
        /**
     * @param visible
     */
    private void setVisible(boolean visible) {
        setProperty(PROPERTY_VISIBLE, visible);
    }

//    /*
//     * (non-Javadoc)
//     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectNodes()
//     */
//    @Override
//    public TellapicNode getObjectRootNode() {
//        // Create the root node
//        TellapicNode rootNode = new TellapicNode(this, getIcon());
//        
//        // Create the node for the name property. This will hold a default label action.
//        TellapicNode nameNode = new TellapicNode(
//                "Name",
//                propertyIcon,
//                new DefaultTellapicNodeActionLabel(
//                        getName(),
//                        false // we will not edit this field
//                        )
//                );
//        
//        // Create the node for the id property and a default label action.
//        TellapicNode idNode = new TellapicNode(
//                "Id",
//                propertyIcon,
//                new DefaultTellapicNodeActionLabel(
//                        String.valueOf(getUserId()),
//                        false // we will not edit this field
//                        )
//                );
//        
//        // Lastly, the selected node with a custom action: 'selectedStateAction'
//        TellapicNode isSelectedNode = new TellapicNode(
//                "Selected",
//                propertyIcon,
//                selectedStateAction
//                );
//        
//        // Add the nodes to the root node...
//        rootNode.add(isSelectedNode);
//        rootNode.add(nameNode);
//        rootNode.add(idNode);
//        
//        // Configure nodes...[OPTIONAL AND NOT FULLY TESTED]
//        isSelectedNode.setTooltipText("User selected property");
//        nameNode.setTooltipText("User name property");
//        idNode.setTooltipText("User id property");
//        
//        // Return the root
//        return rootNode;
//    }
    
//    // How will the model know if we are selected?
//    // There are better approachs maybe, this is straighforward.
//    private class UserSelectedStateNodeAction extends DefaultTellapicNodeActionCheckBox {
//
//        // False == Not editable action, just shows information with a checkbox
//        public UserSelectedStateNodeAction(String text) {
//            super(text, false); // We don't want the CheckBox to be editable
//        }
//        
//        @Override
//        public Object getValue() {
//            return isSelected();
//        }
//    }
}
