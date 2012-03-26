/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;

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
public class DefaultTellapicButtonRenderer extends AbstractTellapicRenderer {

    protected JButton button;

    /**
     * 
     */
    public DefaultTellapicButtonRenderer() {
        super();
        component = new JPanel();
        button = new JButton();
        JPanel panel = (JPanel) component;
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
        panel.add(button);
        panel.setToolTipText("ASF");
    }

    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicAbstractRenderer#configureRenderer(java.awt.Component, ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, java.lang.Object, boolean, boolean)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, Object value, boolean isSelected, boolean hasFocus) {
        AbstractAction swingAction = action.getAbstractAction();
        button.setAction(swingAction);
        String toolTip = (String) swingAction.getValue(AbstractAction.SHORT_DESCRIPTION);
        ((JComponent)component).setToolTipText(toolTip);
        button.setHideActionText(true);
        Icon icon = (Icon) swingAction.getValue(AbstractAction.SMALL_ICON);
        int w = 0;
        if (icon != null) {
            w = icon.getIconWidth() + button.getIconTextGap();
        }
//        
//        FontMetrics tmpMetrics = button.getFontMetrics(button.getFont());
//        Rectangle2D tmpR2D = tmpMetrics.getStringBounds(action.getNodeActionName(), button.getGraphics());
//
//        int tmpWidth = (int)tmpR2D.getWidth() + w;
//
//        // We need to take into consideration borders and padding!
//        Insets insets = button.getInsets();
//        Dimension dim = new Dimension(tmpWidth + insets.left + insets.right, table.getRowHeight() - 1);
        Dimension dim = new Dimension(w, table.getRowHeight() - 1);

        button.setPreferredSize(dim);
        button.setMinimumSize(dim);
        button.setMaximumSize(dim);
    }
}
