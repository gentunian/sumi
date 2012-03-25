/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreePath;

import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.TellapicTreeTable;
import ar.com.tellapic.sumi.treetable.editor.AbstractTellapicCellEditor;

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
public class ItemsListEditor extends AbstractTellapicCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane pane;
	private JLabel label;
	
	public ItemsListEditor() {
		label = new JLabel();
		pane = new JScrollPane(label);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		return "";
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		try {
			TreePath path = ((TellapicTreeTable)table).getPathForRow(row);
			TellapicNode node = (TellapicNode) path.getLastPathComponent();
			TellapicNodeAction action = node.getActionAt(column);
			String[] data = (String[]) action.getData();
			String html = "<html>Items:<br>";
			for(int i = 0; i < data.length; i++)
				html +="<html><li>"+data[i];
			label.setText(html);
		} catch(NullPointerException e) {
			System.out.println("Renderer could not be configured");
			e.printStackTrace();
		}
//		JScrollPane pane = (JScrollPane) table.getCellRenderer(row, column).getTableCellRendererComponent(table, value, isSelected, true, row, column);
//		pane.setViewportView(renderer);
//		TellapicAbstractPanelRenderer renderer = (TellapicAbstractPanelRenderer) pane.getViewport().getView();
		
		return pane;
	}

}
