/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.util.HashMap;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableModel;

import ar.com.tellapic.sumi.treetable.renderer.TellapicHierarchicalCellRenderer;
import ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer;



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
public class TellapicTreeTable extends JXTreeTable {
	
	public static final String DEFAULT_LABEL_RENDERER_KEY    = "DefaultLabelRenderer";
	public static final String DEFAULT_COMBO_RENDERER_KEY    = "DefaultComboRenderer";
	public static final String DEFAULT_BUTTON_RENDERER_KEY   = "DefaultButtonRenderer";
	public static final String DEFAULT_CHECKBOX_RENDERER_KEY = "DefaultCheckboxRenderer";
	public static final String DEFAULT_COLOR_RENDERER_KEY    = "DefaultColorRenderer";
	
	public static final String DEFAULT_BUTTON_EDITOR_KEY     = "DefaultButtonEditor";
	public static final String DEFAULT_TEXTFIELD_EDITOR_KEY  = "DefaultTextFieldEditor";
	public static final String DEFAULT_CHECKBOX_EDITOR_KEY   = "DefaultCheckboxEditor";
	public static final String DEFAULT_COMBO_EDITOR_KEY      = "DefaultEditorEditor";
	public static final String DEFAULT_COLOR_EDITOR_KEY      = "DefaultColorEditor";

	public static final String NO_EDITOR = "NoEditor";
	
	private static final long serialVersionUID = 1L;
	
	
	private HashMap<String, TellapicTableCellRenderer> renderersMap;
	private HashMap<String, TableCellEditor>   editorsMap;
	
	/**
	 * 
	 */
	public TellapicTreeTable() {
		super();
		setColumnMargin(1);
		setRowMargin(1);
		setToggleClickCount(1);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateColumnsFromModel(true);
		setTreeCellRenderer(new TellapicHierarchicalCellRenderer());
		renderersMap = new HashMap<String, TellapicTableCellRenderer>();
		editorsMap   = new HashMap<String, TableCellEditor>();
		
		addDefaultRenderers();
		addDefaultEditors();
	}

	private void addDefaultRenderers() {
		
	}
	
	private void addDefaultEditors() {
		
	}
	
	/**
	 * 
	 * @param key
	 * @param editor
	 */
	public void registerEditorComponent(String key, TableCellEditor editor) {
		editorsMap.put(key, editor);
	}
	
	/**
	 * 
	 * @param key
	 * @param renderer
	 */
	public void registerRendererComponent(String key, TellapicTableCellRenderer renderer) {
		renderersMap.put(key, renderer);
	}
	
	@Override
	public void setTreeTableModel(TreeTableModel treeModel) {
		super.setTreeTableModel(treeModel);
		treeModel.addTreeModelListener(new DefaultTellapicTreeModelListener());
//		((TellapicTreeTableModel) treeModel).addTellapicNodeAddActionListener(new DefaultTellapicNodeAddActionListener());
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		if (isHierarchical(column))
			return super.getCellRenderer(row, column);
		
		TreePath treePath = getPathForRow(row);
		TellapicNode node = (TellapicNode) treePath.getLastPathComponent();
		TellapicNodeAction action = node.getActionAt(column);
		TellapicTableCellRenderer renderer = renderersMap.get(action.getRendererKey());
//		renderer.configureRenderer(action);
		return renderer;
	}

	@Override
	public TableCellEditor getCellEditor(int row, int column) {
		if (isHierarchical(column))
			return super.getCellEditor();
		TreePath path = getPathForRow(row);
		TellapicNode node = (TellapicNode) path.getLastPathComponent();
		//return node.getActionAt(column).getEditor();
		return editorsMap.get(node.getEditorKeyForColumn(column));
	}

	/**
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
	private class DefaultTellapicTreeModelListener implements TreeModelListener {
		@Override
		public void treeNodesChanged(TreeModelEvent e) {}
		
		@Override
		public void treeNodesInserted(TreeModelEvent e) {
			createDefaultColumnsFromModel();
		}

		@Override
		public void treeNodesRemoved(TreeModelEvent e) {
			createDefaultColumnsFromModel();
		}

		@Override
		public void treeStructureChanged(TreeModelEvent e) {
			createDefaultColumnsFromModel();
		}
	}
	
	
	/**
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
//	private class TellapicDefaultCellEditor extends DefaultCellEditor {
//		private static final long serialVersionUID = 1L;
//		/**/
//		private TellapicNodeAction action;
//		
//		/**
//		 * @param checkBox
//		 */
//		public TellapicDefaultCellEditor(JCheckBox checkBox) {
//			super(checkBox);
//		}
//
//		/**
//		 * @param jTextField
//		 */
//		public TellapicDefaultCellEditor(JTextField jTextField) {
//			super(jTextField);
//		}
//		
//		/**
//		 * @param jComboBox
//		 */
//		public TellapicDefaultCellEditor(JComboBox jComboBox) {
//			super(jComboBox);
//		}
//
//		@Override
//		public Component getTreeCellEditorComponent(JTree tree,
//				Object value,
//				boolean isSelected,
//				boolean expanded,
//				boolean leaf,
//				int row) {
//			return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
//		}
//
//		@Override
//		public Component getTableCellEditorComponent(JTable table,
//				Object value,
//				boolean isSelected,
//				int row,
//				int column) {
//			Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
//			TreePath path = getPathForRow(row);
//			TellapicNode node = (TellapicNode) path.getLastPathComponent();
//			action = node.getActionAt(column);
//			System.out.println("action: "+action+" row: "+row+" col: "+column);
//			return c;
//		}
//		
//		@Override
//		protected void fireEditingStopped() {
////			if (action == null)
////				return;
//			// Guaranteed to return a non-null array
//			Object[] listeners = listenerList.getListenerList();
//			// Process the listeners last to first, notifying
//			// those that are interested in this event
//			for (int i = listeners.length-2; i>=0; i-=2)
//				if (listeners[i]==CellEditorListener.class)
//					((CellEditorListener)listeners[i+1]).editingStopped(new ChangeEvent(action));
//		}
//		
//	}
}
