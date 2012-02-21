/**
 * 
 */
package ar.com.tellapic.sumi.treetable.editor;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreePath;

import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicTreeTable;
import ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractCheckBoxRenderer;

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
public class DefaultTellapicCheckBoxCellEditor extends AbstractTellapicCellEditor implements TableCellEditor{

	private static final long serialVersionUID = 1L;

	private JCheckBox editor;
	
	/**
	 * 
	 */
	public DefaultTellapicCheckBoxCellEditor() {
		editor = new JCheckBox();
		editor.setSelected(true);
		editor.setOpaque(true);
		editor.setRequestFocusEnabled(false);
		editor.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				stopCellEditing();
			}
		});
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		return editor.isSelected();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		try {
			TreePath path = ((TellapicTreeTable)table).getPathForRow(row);
			TellapicNode node = (TellapicNode) path.getLastPathComponent();
			action = node.getActionAt(column);
			editor.setText(action.getNodeActionName());
		} catch(NullPointerException e) {
			// Do nothing...
		}
		
		Component renderer = table.getCellRenderer(row, column).getTableCellRendererComponent(table, value, isSelected, true, row, column);
		if (renderer instanceof TellapicAbstractCheckBoxRenderer) {
			editor.setIcon(((TellapicAbstractCheckBoxRenderer) renderer).getIcon());
			editor.setSelectedIcon(((TellapicAbstractCheckBoxRenderer) renderer).getSelectedIcon());
		}
		editor.setSelected((Boolean) value);
		if (isSelected) {
			editor.setBackground(table.getSelectionBackground());
			editor.setForeground(table.getSelectionForeground());
		} else {
			editor.setBackground(table.getBackground());
			editor.setForeground(table.getForeground());
		}
		
		return editor;
	}
}
