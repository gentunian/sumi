/**
 * 
 */
package ar.com.tellapic.sumi.renderer;

import javax.swing.JTable;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractComboRenderer;

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
public class NodeActionComboRenderer extends TellapicAbstractComboRenderer {

	/**
	 * 
	 */
	public NodeActionComboRenderer() {
		super();
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.gumi.treetable.TellapicNodeAction)
	 */
	@Override
	public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
		Object[] data = (Object[]) action.getData();
		
		combo.removeAllItems();
		for(int i = 0; i < data.length; i++)
			combo.addItem(data[i]);
		
		String labelName = action.getNodeActionName();
		label.setText(labelName+(labelName.length() > 0?":":""));
	}
}
