/**
 * 
 */
package ar.com.tellapic.sumi;

import javax.swing.AbstractAction;

import ar.com.tellapic.sumi.treetable.DefaultAbstractTellapicNodeAction;
import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicTreeTable;

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
public class ToggleUserVisibilityNodeAction extends DefaultAbstractTellapicNodeAction {

    /**
     * 
     */
    public ToggleUserVisibilityNodeAction() {
        this(null, null);
    }

    /**
     * @param node
     * @param action
     */
    public ToggleUserVisibilityNodeAction(TellapicNode node, AbstractAction action) {
        super(node, action, TellapicTreeTable.DEFAULT_CHECKBOX_EDITOR_KEY, TellapicTreeTable.DEFAULT_CHECKBOX_RENDERER_KEY, true);
    }

    /**
     * 
     * @param action
     */
    public ToggleUserVisibilityNodeAction(AbstractAction action) {
        this(null, action);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.TellapicNodeAction#setValue(java.lang.Object)
     */
    @Override
    public void setValue(Object value) {
        System.out.println(ToggleUserVisibilityNodeAction.class.getSimpleName()+" set value "+value);
        SumiUser user = (SumiUser) node.getParent().getUserObject();
        user.setVisible((Boolean) value);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.TellapicNodeAction#getValue()
     */
    @Override
    public Object getValue() {
        SumiUser user = (SumiUser) node.getParent().getUserObject();
        return user.isVisible();
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getData()
     */
    @Override
    public Object getData() {
        return node.getUserObject();
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#isEditable()
     */
    @Override
    public boolean isEditable() {
        return true;
    }
}
