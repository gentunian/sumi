/**
 * 
 */
package ar.com.tellapic.sumi.renderer;

import javax.swing.JLabel;
import javax.swing.JTable;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.renderer.AbstractTellapicRenderer;

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
public class NodeActionLabelRenderer extends AbstractTellapicRenderer {

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractRenderer#configureRenderer(java.awt.Component, ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, java.lang.Object, boolean, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, Object value,
            boolean isSelected, boolean hasFocus) {
        
        ((JLabel)component).setText(action.getNodeActionName());
        
    }
}
