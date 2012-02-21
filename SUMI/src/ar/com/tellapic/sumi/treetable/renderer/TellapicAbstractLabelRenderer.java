/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Component;

import javax.swing.JLabel;
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
public abstract class TellapicAbstractLabelRenderer extends JLabel implements TellapicTableCellRenderer {
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 */
	public TellapicAbstractLabelRenderer() {
		super();
		setOpaque(true);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		TreePath treePath = ((TellapicTreeTable)table).getPathForRow(row);
		TellapicNode node = (TellapicNode) treePath.getLastPathComponent();
		if (column == 0) {
			setIcon(node.getIcon());
			setText(node.getUserObject().toString());
		} else {
			TellapicNodeAction action = node.getActionAt(column);
			configureRenderer(action, table, isSelected);
			setText(value.toString());
			setIcon(null);
		}
		
		if (isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}
		return this;
	}
}
