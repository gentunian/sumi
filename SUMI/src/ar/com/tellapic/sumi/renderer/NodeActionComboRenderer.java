/**
 * 
 */
package ar.com.tellapic.sumi.renderer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractPanelRenderer;

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
public class NodeActionComboRenderer extends TellapicAbstractPanelRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel       label;
	private JComboBox    combo;
	
	/**
	 * 
	 */
	public NodeActionComboRenderer() {
		super();
		label = new JLabel();
		combo = new JComboBox();
		SpringLayout layout = new SpringLayout(); 
		layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, combo, 1, SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.SOUTH, combo, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, combo, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, combo, 0, SpringLayout.EAST, this);
		setLayout(layout);
		add(label);
		add(combo);
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
