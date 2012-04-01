/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import ar.com.tellapic.sumi.SumiUser;

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
public class TellapicTreeTableModel extends DefaultTreeTableModel {
	private TableColumnModel                   tcm;
	
	public TellapicTreeTableModel(TableColumnModel tcm) {
		root = new TellapicNode("root", null);
		this.tcm = tcm;
	}

	public TellapicTreeTableModel() {
		this(null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.DefaultTreeTableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		if (tcm == null) {
			return getMaximumColumnNumber((TellapicNode) getRoot(), 1);
		}
		else
			return tcm.getColumnCount();
	}
	
	/**
	 * 
	 * @param node
	 * @param max
	 * @return
	 */
	private int getMaximumColumnNumber(TellapicNode node, int max) {
		if (node.getChildCount() == 0) {
			int k = (getRoot().equals(node)?0:1);
			return node.getActionCount()+k;
		}
		else {
			int count = node.getChildCount();
			for(int i = 0; i < count; i++) {
				int value =  getMaximumColumnNumber((TellapicNode) node.getChildAt(i), max);
				if (max <= value)
					max = value;
			}
			return max;
		}
	}
	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.TreeTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) {
		//System.out.println("Column name for column: "+arg0);
		return "Column"+String.valueOf(arg0);
	}

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.TreeTableModel#getValueAt(java.lang.Object, int)
	 */
	@Override
	public Object getValueAt(Object node, int columnIndex) {
		//System.out.println("ci: "+columnIndex);
		return ((TellapicNode)node).getValueAt(columnIndex);
	}

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.treetable.TreeTableModel#isCellEditable(java.lang.Object, int)
	 */
	@Override
	public boolean isCellEditable(Object node, int columnIndex) {
		if (columnIndex == 0)
			return false;
		
		return ((TellapicNode) node).getActionAt(columnIndex).isEditable();
	}

	/**
	 * Gets the TellapicNode wrapped around a SumiUser object.
	 * @param user the user for which we want the desired node
	 * @return the node wrapped around user if found or null if user is not managed by this model.
	 */
	public TellapicNode getNodeForUser(SumiUser user) {
		boolean found = false;
		TellapicNode root = (TellapicNode) getRoot();
		int count = root.getChildCount();
		int i = 0;
		for(i = 0; i < count && !found; i++) {
			SumiUser cmpUser = (SumiUser) getValueAt(root.getChildAt(i), 0);
			found = cmpUser.equals(user);
			
		}
		if (found)
			return (TellapicNode) root.getChildAt(i-1);
		else
			return null;

	}
	
	/**
	 * 
	 * @return
	 */
	public List<TellapicNode> getSelectedNodes() {
	    return getSelectedNodesForParent((TellapicNode) getRoot());
	}
	
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public List<TellapicNode> getSelectedNodesForParent(TellapicNode parent) {
	    List<TellapicNode> selectedNodes = new ArrayList<TellapicNode>();
	    
	    for(int i = 0; i < parent.getChildCount(); i++) {
	        TellapicNode child = (TellapicNode) parent.getChildAt(i);
	        selectedNodes.addAll(getSelectedNodesForParent(child));
	        if (child.isSelected())
	            selectedNodes.add(child);
	    }
	    return selectedNodes;
	}
}
