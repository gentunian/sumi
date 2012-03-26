/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;

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
public class DefaultTellapicCheckBoxRenderer extends AbstractTellapicRenderer {

    /**
     * 
     */
    public DefaultTellapicCheckBoxRenderer() {
        component = new JCheckBox();
        ((JCheckBox)component).setOpaque(true);
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractRenderer#configureRenderer(java.awt.Component, ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, java.lang.Object, boolean, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, Object value, boolean isSelected, boolean hasFocus) {
        JCheckBox checkBox = (JCheckBox) component;
        checkBox.setSelected((Boolean) value);
        checkBox.setText(action.getNodeActionName());
        checkBox.setEnabled(action.isEditable());
    }
}
