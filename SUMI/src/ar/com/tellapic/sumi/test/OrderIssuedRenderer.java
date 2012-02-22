/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTable;

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
public class OrderIssuedRenderer extends TellapicAbstractPanelRenderer {

	private static final long serialVersionUID = 1L;
	private static final String ORDER_STR = "<html>Order ID:<br><html>";
	private static final String ISSUED_STR = "<html>Issued Time:<br><html>";
	
	private JLabel order;
	private JLabel issued;
	
	public OrderIssuedRenderer() {
		super();
		order = new JLabel();
		issued = new JLabel();
		setLayout(new GridLayout(2,0));
		order.setHorizontalAlignment(JLabel.HORIZONTAL);
		issued.setHorizontalAlignment(JLabel.HORIZONTAL);
		add(order); 
		add(issued);
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
	 */
	@Override
	public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
		String[] data = (String[])action.getData();
		order.setText(ORDER_STR+data[0]);
		issued.setText(ISSUED_STR+data[1]);
	}
}
