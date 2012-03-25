/**
 * 
 */
package ar.com.tellapic.sumi.renderer;

import javax.swing.JTable;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractButtonRenderer;

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
public class NodeActionButtonRenderer extends TellapicAbstractButtonRenderer {
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
        setAction(action.getAbstractAction());
    }
}
