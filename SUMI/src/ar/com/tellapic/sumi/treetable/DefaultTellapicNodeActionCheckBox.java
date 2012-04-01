/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import javax.swing.AbstractAction;



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
public class DefaultTellapicNodeActionCheckBox extends DefaultAbstractTellapicNodeAction {


    public DefaultTellapicNodeActionCheckBox(String name, boolean initValue, boolean editable) {
        super(name, TellapicTreeTable.DEFAULT_CHECKBOX_EDITOR_KEY, TellapicTreeTable.DEFAULT_CHECKBOX_RENDERER_KEY, editable);
        setValue(initValue);
    }
    /**
     * 
     * @param node
     * @param action
     */
    public DefaultTellapicNodeActionCheckBox(TellapicNode node, AbstractAction action) {
        super(node, action, TellapicTreeTable.DEFAULT_CHECKBOX_EDITOR_KEY, TellapicTreeTable.DEFAULT_CHECKBOX_RENDERER_KEY, true);
        setValue(true);
    }

    /**
     * Creates an editable action by default with action {code}action{/code}.
     * @param action
     */
    public DefaultTellapicNodeActionCheckBox(AbstractAction action) {
        this(null, action);
    }

    /**
     * Creates an editable action by default with name {code}name{/code}.
     * @param name
     */
    public DefaultTellapicNodeActionCheckBox(String name) {
        this(name, true, true);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getData()
     */
    @Override
    public Object getData() {
        // TODO Auto-generated method stub
        return null;
    }
}
