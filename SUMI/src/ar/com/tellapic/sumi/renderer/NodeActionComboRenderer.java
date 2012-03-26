/**
 * 
 */
package ar.com.tellapic.sumi.renderer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
import ar.com.tellapic.sumi.treetable.renderer.AbstractTellapicRenderer;

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
public class NodeActionComboRenderer extends AbstractTellapicRenderer {

    private JLabel       label;
    private JComboBox    combo;

    /**
     * 
     */
    public NodeActionComboRenderer() {
        super();
        component = new JPanel();
        label = new JLabel();
        combo = new JComboBox();
        SpringLayout layout = new SpringLayout(); 
        layout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, component);
        layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, component);
        layout.putConstraint(SpringLayout.WEST, combo, 1, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.SOUTH, combo, 0, SpringLayout.SOUTH, component);
        layout.putConstraint(SpringLayout.NORTH, combo, 0, SpringLayout.NORTH, component);
        layout.putConstraint(SpringLayout.EAST, combo, 0, SpringLayout.EAST, component);
        ((JPanel)component).setLayout(layout);
        ((JPanel)component).add(label);
        ((JPanel)component).add(combo);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractRenderer#configureRenderer(java.awt.Component, ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, java.lang.Object, boolean, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, Object value,
            boolean isSelected, boolean hasFocus) {

        Object[] data = (Object[]) action.getData();

        combo.removeAllItems();
        for(int i = 0; i < data.length; i++)
            combo.addItem(data[i]);

        String labelName = action.getNodeActionName();
        label.setText(labelName+(labelName.length() > 0?":":""));
        combo.setSelectedItem(value);
    }
}
