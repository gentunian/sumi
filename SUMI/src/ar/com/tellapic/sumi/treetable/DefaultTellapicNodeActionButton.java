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
public class DefaultTellapicNodeActionButton extends DefaultAbstractTellapicNodeAction {

    /**
     * 
     * @param node
     * @param action
     */
    public DefaultTellapicNodeActionButton(TellapicNode node, AbstractAction action) {
        super(node, action, TellapicTreeTable.DEFAULT_BUTTON_EDITOR_KEY, TellapicTreeTable.DEFAULT_BUTTON_RENDERER_KEY, true);
        setValue("pimba");
    }

    /**
     * 
     * @param node
     * @param action
     */
    public DefaultTellapicNodeActionButton(AbstractAction action) {
        this(null, action);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeAction#getData()
     */
    @Override
    public Object getData() {
        return null;
    }
}
