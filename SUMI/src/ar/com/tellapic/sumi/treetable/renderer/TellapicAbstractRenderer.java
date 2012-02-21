/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.tree.TreePath;

import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
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
public abstract class TellapicAbstractRenderer implements TellapicTableCellRenderer {
	protected Component component;
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		TellapicNodeAction action = null;
		TreePath path = ((TellapicTreeTable)table).getPathForRow(row);
		if (path != null) {
			TellapicNode node = (TellapicNode) path.getLastPathComponent();
			action = node.getActionAt(column);
			configureRenderer(action, table, isSelected);
		}
		return component;
	}
}
