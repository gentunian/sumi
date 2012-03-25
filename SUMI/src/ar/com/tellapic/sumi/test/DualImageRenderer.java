/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class DualImageRenderer extends TellapicAbstractPanelRenderer {
    private static final long serialVersionUID = 1L;
    private JLabel labelImage;
    private JButton buttonImage;
    
    @SuppressWarnings("unused")
    private boolean vertical;

    /**
     * 
     */
    public DualImageRenderer(boolean vertical) {
        super();
        this.vertical = vertical;
        //configureLabelsAndLayout();
        
        labelImage = new JLabel();
        buttonImage = new JButton();
        labelImage.setHorizontalAlignment(JLabel.HORIZONTAL);
        labelImage.setVerticalAlignment(JLabel.CENTER);
        buttonImage.setHorizontalAlignment(JLabel.HORIZONTAL);
        buttonImage.setVerticalAlignment(JLabel.CENTER);
        if (!vertical) {
            setLayout(new GridLayout(0, 2));
            add(labelImage, BorderLayout.EAST);
            add(buttonImage, BorderLayout.WEST);
        } else { 
            setLayout(new GridLayout(2, 0));
            add(labelImage, BorderLayout.NORTH);
            add(buttonImage, BorderLayout.SOUTH);
        }
    }

    /**
     * 
     */
//    private void configureLabelsAndLayout() {
//        firstImage = new JLabel();
//        secondImage = new JButton();
//        firstImage.setHorizontalAlignment(JLabel.HORIZONTAL);
//        firstImage.setVerticalAlignment(JLabel.CENTER);
//        secondImage.setHorizontalAlignment(JLabel.HORIZONTAL);
//        secondImage.setVerticalAlignment(JLabel.CENTER);
//        firstImage.setBorder(BorderFactory.createLineBorder(Color.black));
//        secondImage.setBorder(BorderFactory.createLineBorder(Color.black));
//        SpringLayout layout = new SpringLayout();
//        layout.putConstraint(SpringLayout.NORTH, firstImage, 0, SpringLayout.NORTH, this);
//        layout.putConstraint(SpringLayout.EAST, firstImage, 0, SpringLayout.EAST, this);
//        layout.putConstraint(SpringLayout.SOUTH, secondImage, 0, SpringLayout.SOUTH, this);
//        layout.putConstraint(SpringLayout.WEST, secondImage, 0, SpringLayout.WEST, this);
//        if (vertical) {
//            layout.putConstraint(SpringLayout.WEST, firstImage, 0, SpringLayout.WEST, this);
//            layout.putConstraint(SpringLayout.SOUTH, firstImage, 0, SpringLayout.VERTICAL_CENTER, this);
//            layout.putConstraint(SpringLayout.NORTH, secondImage, 0, SpringLayout.VERTICAL_CENTER, this);
//            layout.putConstraint(SpringLayout.EAST, secondImage, 0, SpringLayout.EAST, this);
//        } else {
//            layout.putConstraint(SpringLayout.SOUTH, firstImage, 0, SpringLayout.SOUTH, this);
//            layout.putConstraint(SpringLayout.NORTH, secondImage, 0, SpringLayout.NORTH, this);
//            layout.putConstraint(SpringLayout.EAST, secondImage, 0, SpringLayout.HORIZONTAL_CENTER, this);
//            layout.putConstraint(SpringLayout.WEST, firstImage, 0, SpringLayout.HORIZONTAL_CENTER, this);
//        }
//        add(firstImage);
//        add(secondImage);
//        setLayout(layout);
//    }

    /**
     * 
     * @param v
     */
    public void setVertical(boolean v) {
        this.vertical = v;
        removeAll();
        //configureLabelsAndLayout();
        repaint();
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
        String[] data = (String[])action.getData();
        labelImage.setIcon(new ImageIcon(DualImageRenderer.class.getResource(data[0])));
        buttonImage.setIcon(new ImageIcon(DualImageRenderer.class.getResource(data[1])));
    }
    
    public JButton getButtonImage() {
        return buttonImage;
    }
    
    public JLabel getLabelImage() {
        return labelImage;
    }
}
