/**
 * 
 */
package ar.com.tellapic.sumi.treetable.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreePath;

import ar.com.tellapic.sumi.treetable.TellapicNode;
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
public class DefaultTellapicComboBoxCellEditor extends AbstractTellapicCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;

	private JComboBox editor;
	/**
	 * 
	 */
	public DefaultTellapicComboBoxCellEditor() {
		editor = new JComboBox();
		editor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				stopCellEditing();
			}
		});
		editor.addPopupMenuListener(new PopupMenuListener(){
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				stopCellEditing();
			}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		return editor.getSelectedItem();
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
			Object[] data = (Object[]) action.getData();
			editor.removeAllItems();
			for(int i = 0; i < data.length; i++)
				editor.addItem(data[i]);
		} catch(NullPointerException e) {
			// Do nothing...
		}

		editor.setSelectedItem(value);
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
