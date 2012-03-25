/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.event.EventListenerList;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;


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
public class TellapicNode extends AbstractMutableTreeTableNode {

    private List<TellapicNodeAction>    actions;
    private EventListenerList           listenerList;
    private Icon                        icon;
    private boolean                     selected;
    private String                      alternativeName;
    private String                      tooltipText;
    
    /* This list is used as a memory list for every new registered listener */
    /* New listeners will have the posibility to receive each action event */
    /* received for this node */
    private List<TellapicNodeActionEvent>   actionEventList;
    private String rendererKey = TellapicTreeTable.DEFAULT_LABEL_RENDERER_KEY;

    /**
     * 
     * @param obj
     */
    public TellapicNode(Object obj, Icon icon) {
        super(obj);
        this.icon = icon;
        actions  = new ArrayList<TellapicNodeAction>();
        actionEventList = new ArrayList<TellapicNodeActionEvent>();
        listenerList = new EventListenerList();
        setTooltipText(obj.toString());
    }

    /**
     * 
     * @param obj
     * @param icon
     * @param action
     */
    public TellapicNode(Object obj, Icon icon, TellapicNodeAction action) {
        this(obj, icon);
        addAction(action);
    }

    /**
     * 
     * @param obj
     * @param icon
     * @param actions
     */
    public TellapicNode(Object obj, Icon icon, TellapicNodeAction[] actions) {
        this(obj, icon);
        if (actions != null && actions.length > 0) {
            for(int i = 0; i < actions.length; i++)
                addAction(actions[i]);
        }
    }

    /**
     * 
     * @param obj
     * @param icon
     * @param actions
     */
    public TellapicNode(Object obj, Icon icon, List<TellapicNodeAction> actions) {
        this(obj, icon);
        for(TellapicNodeAction action : actions)
            addAction(action);
    }
    
    /**
     * 
     * @param aName
     */
    public void setAlternativeName(String aName) {
        alternativeName = aName;
    }
    
    /**
     * 
     * @return
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * 
     * @param action
     */
    public void removeAction(TellapicNodeAction action) {
        actions.remove(action);
        Object[] listeners = listenerList.getListenerList();
        TellapicNodeActionEvent e = new TellapicNodeActionEvent(this, action, TellapicNodeActionEvent.REMOVE_ACTION);
        actionEventList.add(e);
        for (int i = listeners.length-2; i>=0; i-=2)
            if (listeners[i] == TellapicNodeRemoveActionListener.class)
                ((TellapicNodeRemoveActionListener) listeners[i+1]).actionRemoved(e);
    }

    /**
     * 
     * @param action
     */
    public void addAction(TellapicNodeAction action) {
        if (action == null)
            throw new IllegalArgumentException("action could not be null");
        action.setNode(this);
        actions.add(action);
        Object[] listeners = listenerList.getListenerList();
        TellapicNodeActionEvent e = new TellapicNodeActionEvent(this, action, TellapicNodeActionEvent.ADD_ACTION);
        actionEventList.add(e);
        for (int i = listeners.length-2; i>=0; i-=2)
            if (listeners[i] == TellapicNodeAddActionListener.class)
                ((TellapicNodeAddActionListener) listeners[i+1]).actionAdded(e);
    }

    /**
     * 
     * @param l
     */
    public void addTellapicNodeRemoveActionListener(TellapicNodeRemoveActionListener l) {
        for(TellapicNodeActionEvent event : actionEventList)
            if (event.getWhatAction() == TellapicNodeActionEvent.REMOVE_ACTION)
                l.actionRemoved(event);
        listenerList.add(TellapicNodeRemoveActionListener.class, l);
    }

    /**
     * 
     * @param l
     */
    public void removeTellapicNodeRemoveActionListner(TellapicNodeRemoveActionListener l) {
        listenerList.remove(TellapicNodeRemoveActionListener.class, l);
    }

    /**
     * 
     * @param l
     */
    public void addTellapicNodeAddActionListener(TellapicNodeAddActionListener l) {
        for(TellapicNodeActionEvent event : actionEventList)
            if (event.getWhatAction() == TellapicNodeActionEvent.ADD_ACTION)
                l.actionAdded(event);
        listenerList.add(TellapicNodeAddActionListener.class, l);
    }

    /**
     * 
     * @param l
     */
    public void removeTellapicNodeAddActionListner(TellapicNodeAddActionListener l) {
        listenerList.remove(TellapicNodeAddActionListener.class, l);
    }

    /**
     * 
     * @param index
     * @return
     */
    public TellapicNodeAction getActionAt(int column) {
        int index = column - 1;
        if (index >= 0 && index < actions.size())
            return actions.get(index);
        return new EmptyTellapicNodeAction(this);
    }

    /**
     * 
     * @return
     */
    public List<TellapicNodeAction> getActions() {
        return actions;
    }

    //	/**
    //	 * 
    //	 * @return
    //	 */
    //	public TellapicTreeTableCellRenderer getRenderer() {
    //		return renderer;
    //	}

    /* (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.TreeTableNode#getValueAt(int)
     */
    @Override
    public Object getValueAt(int column) {
        if (column == 0)
            return getUserObject();

        return getActionAt(column).getValue();
    }

    /*
     * (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode#setValueAt(java.lang.Object, int)
     */
    @Override
    public void setValueAt(Object value, int column) {
        if (column == 0) {
            // It is supposed that TellapicNode values shouldn't be editable.
            // Maybe for future uses...
        }
        getActionAt(column).setValue(value);
    }

    /* (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.TreeTableNode#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return getActionCount()+1;
    }

    /**
     * @return
     */
    public int getActionCount() {
        return actions.size();
    }

    /**
     * @param column
     * @return
     */
    public String getRendererKeyForColumn(int column) {
        if (column == 0)
            return rendererKey ;
        else
            return getActionAt(column).getRendererKey();
    }

    /**
     * @param column
     * @return
     */
    public Object getEditorKeyForColumn(int column) {
        if (column == 0)
            return rendererKey ;
        else
            return getActionAt(column).getEditorKey();
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        System.out.println("Node "+this+" selected: "+selected);
    }
    
    /*
     * (non-Javadoc)
     * @see org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode#toString()
     */
    @Override
    public String toString() {
        if (alternativeName != null)
            return alternativeName;
        return super.toString();
    }

    /**
     * @return the tooltipText
     */
    public String getTooltipText() {
        return tooltipText;
    }

    /**
     * @param tooltipText the tooltipText to set
     */
    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
    }
}
