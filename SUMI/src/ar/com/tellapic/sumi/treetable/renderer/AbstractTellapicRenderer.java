/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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
public abstract class AbstractTellapicRenderer implements TableCellRenderer {
    protected TellapicNodeAction action;
    protected Component component;

    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            component.setBackground(table.getSelectionBackground());
            component.setForeground(table.getSelectionForeground());
        } else {
            component.setBackground(table.getBackground());
            component.setForeground(table.getForeground());
        }

        if (action != null)
            configureRenderer(action, table, value, isSelected, hasFocus);

        return component;
    }
    
    /**
     * 
     * @param action
     */
    public void setTellapicNodeAction(TellapicNodeAction action) {
        this.action = action;
    }
    /**
     * 
     * @param component
     * @param action
     * @param table
     * @param isSelected
     */
    public abstract void configureRenderer(TellapicNodeAction action, JTable table, Object value, boolean isSelected, boolean hasFocus);
}
