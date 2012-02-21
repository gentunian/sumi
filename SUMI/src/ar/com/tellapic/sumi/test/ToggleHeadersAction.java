/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXTreeTable;

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
public class ToggleHeadersAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private JXTreeTable treeTable;
	private JTableHeader header;
	
	public ToggleHeadersAction(JXTreeTable tree) {
		super("Toggle grid" ,new ImageIcon(ToggleHeadersAction.class.getResource("/icons/edit-heading.png")));
		treeTable = tree;
		putValue(SHORT_DESCRIPTION, "Show/hide the tree grid");
		header = treeTable.getTableHeader();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton button = (JToggleButton) e.getSource();
		header.setVisible(button.isSelected());
	}

}
