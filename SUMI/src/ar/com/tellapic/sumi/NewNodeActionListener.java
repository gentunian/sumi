/**
 * 
 */
package ar.com.tellapic.sumi;

import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicNodeActionEvent;
import ar.com.tellapic.sumi.treetable.TellapicNodeAddActionListener;

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
 *
 */
public class NewNodeActionListener implements TellapicNodeAddActionListener {

//	private JXTreeTable treeTable;
//	private GumiRenderer renderer;
	
	public NewNodeActionListener() {
//		this.treeTable = treeTable;
//		renderer = new GumiRenderer((GumiTreeTableModel) treeTable.getTreeTableModel());
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.GumiNodeAddActionListener#actionAdded(ar.com.tellapic.gumi.GumiNodeActionEvent)
	 */
	@Override
	public void actionAdded(TellapicNodeActionEvent e) {
//		TreeTableModel  model = treeTable.getTreeTableModel();
//		TableColumnModel  tcm = treeTable.getColumnModel();
//		TellapicNode       source = e.getSource();

		/* First column is indexed with 0 in the JXTreeTable. And, */
		/* Second column is indexed with 1, and so on...*/
		
		/* Below is the code needed to add a new column, if the new action */
		/* index exceeds the current table space. */
//
//		for(int i = model.getColumnCount(); i < 1 + source.getActionCount(); i++) {
//			TableColumn column = new TableColumn(i);
//			System.out.println("Index i: "+i);
//			column.setHeaderValue(model.getColumnName(i));
//			tcm.addColumn(column);
//			/* actionAdded() should be called after an 'action' node has */
//			/* been added to 'source' node. */
//			column.setCellRenderer(renderer);
//		}
	}
}
