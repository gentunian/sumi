/**
 * 
 */
package ar.com.tellapic.sumi.treetable.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * This is an example taken from JAVA Tutorials showing how to use editors/renderers in GUMI
 * ------------------------------------------------------------------------------------------
 * 
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
public class DefaultTellapicColorCellEditor extends AbstractTellapicCellEditor implements TableCellEditor, ActionListener {
	private static final long serialVersionUID = 1L;
	
	Color currentColor;
	JButton button;
	JColorChooser colorChooser;
	JDialog dialog;
	protected static final String EDIT = "edit";

	public DefaultTellapicColorCellEditor() {
		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);

		//Set up the dialog that the button brings up.
		colorChooser = new JColorChooser();
		dialog = JColorChooser.createDialog(button,
				"Pick a Color",
				true,  //modal
				colorChooser,
				this,  //OK button handler
				null); //no CANCEL button handler
	}

	public void actionPerformed(ActionEvent e) {
		if (EDIT.equals(e.getActionCommand())) {
			//The user has clicked the cell, so
			//bring up the dialog.
			button.setBackground(currentColor);
			colorChooser.setColor(currentColor);
			dialog.setVisible(true);

			fireEditingStopped(); //Make the renderer reappear.

		} else { //User pressed dialog's "OK" button.
			currentColor = colorChooser.getColor();
		}
	}

	//Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() {
		return currentColor;
	}

	//Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table,
			Object value,
			boolean isSelected,
			int row,
			int column) {
		currentColor = (Color)value;
		return button;
	}
}
