/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import ar.com.tellapic.sumi.treetable.TellapicNode;

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
public class TellapicHierarchicalCellRenderer extends JLabel implements TreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  TellapicHierarchicalCellRenderer() {
		super();
		setMinimumSize(new Dimension(9999,999));
		setMaximumSize(new Dimension(9999,999));
		setPreferredSize(new Dimension(9999,999));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree,
			Object value,
			boolean selected,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus) {

		TreePath treePath = tree.getPathForRow(row);
		if (treePath != null) {
			TellapicNode node = (TellapicNode) treePath.getLastPathComponent();
			setIcon(node.getIcon());
			setText(node.getUserObject().toString());
			setToolTipText(getText());
		}
		if (selected) {
			//setBackground(tree.getSelectionBackground());
			//setForeground(tree.getSelectionForeground());
		} else {
			setBackground(tree.getBackground());
			setForeground(tree.getForeground());
		}
		return this;
	}
}
