/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
public class DualImageRenderer extends TellapicAbstractPanelRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel firstImage;
	private JLabel secondImage;
	private boolean vertical;
	
	/**
	 * 
	 */
	public DualImageRenderer(boolean vertical) {
		super();
		this.vertical = vertical;
		configureLabelsAndLayout();
	}
	
	/**
	 * 
	 */
	private void configureLabelsAndLayout() {
		firstImage = new JLabel();
		secondImage = new JLabel();
		firstImage.setHorizontalAlignment(JLabel.HORIZONTAL);
		firstImage.setVerticalAlignment(JLabel.CENTER);
		secondImage.setHorizontalAlignment(JLabel.HORIZONTAL);
		secondImage.setVerticalAlignment(JLabel.CENTER);
		firstImage.setBorder(BorderFactory.createLineBorder(Color.black));
		secondImage.setBorder(BorderFactory.createLineBorder(Color.black));
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.NORTH, firstImage, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, firstImage, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, secondImage, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, secondImage, 0, SpringLayout.WEST, this);
		if (vertical) {
			layout.putConstraint(SpringLayout.WEST, firstImage, 0, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.SOUTH, firstImage, 0, SpringLayout.VERTICAL_CENTER, this);
			layout.putConstraint(SpringLayout.NORTH, secondImage, 0, SpringLayout.VERTICAL_CENTER, this);
			layout.putConstraint(SpringLayout.EAST, secondImage, 0, SpringLayout.EAST, this);
		} else {
			layout.putConstraint(SpringLayout.SOUTH, firstImage, 0, SpringLayout.SOUTH, this);
			layout.putConstraint(SpringLayout.NORTH, secondImage, 0, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.EAST, secondImage, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout.putConstraint(SpringLayout.WEST, firstImage, 0, SpringLayout.HORIZONTAL_CENTER, this);
		}

		setLayout(layout);
		add(firstImage);
		add(secondImage);
	}

	/**
	 * 
	 * @param v
	 */
	public void setVertical(boolean v) {
		this.vertical = v;
		removeAll();
		configureLabelsAndLayout();
		repaint();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
	 */
	@Override
	public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
		String[] data = (String[])action.getData();
		firstImage.setIcon(new ImageIcon(DualImageRenderer.class.getResource(data[0])));
		secondImage.setIcon(new ImageIcon(DualImageRenderer.class.getResource(data[1])));
	}

}
