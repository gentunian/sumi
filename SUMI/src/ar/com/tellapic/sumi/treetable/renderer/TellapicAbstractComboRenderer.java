/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
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
public abstract class TellapicAbstractComboRenderer implements TellapicTableCellRenderer {

	protected JPanel     panel;
	protected JLabel     label;
	protected JComboBox  combo;
	protected SpringLayout layout;
	
	
	/**
	 * 
	 */
	public TellapicAbstractComboRenderer() {
		panel = new JPanel();
		label = new JLabel();
		combo = new JComboBox();
		layout = new SpringLayout(); 
		layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, combo, 1, SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.SOUTH, combo, 0, SpringLayout.SOUTH, panel);
		layout.putConstraint(SpringLayout.NORTH, combo, 0, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, combo, 0, SpringLayout.EAST, panel);
		panel.setLayout(layout);
		panel.add(label);
		panel.add(combo);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
			panel.setForeground(table.getSelectionForeground());
		} else {
			panel.setBackground(table.getBackground());
			panel.setForeground(table.getForeground());
		}
		
		
		try {
			TreePath path = ((TellapicTreeTable)table).getPathForRow(row);
			TellapicNode node = (TellapicNode) path.getLastPathComponent();
			TellapicNodeAction action = node.getActionAt(column);
			configureRenderer(action, table, isSelected);
		} catch(NullPointerException e) {
			// Do nothing as we need to return the component either...
		}
		combo.setSelectedItem(value);
		return panel;
	}
 
}
