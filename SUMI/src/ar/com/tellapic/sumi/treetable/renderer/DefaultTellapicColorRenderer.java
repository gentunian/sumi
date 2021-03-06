/**
 * 
 */
package ar.com.tellapic.sumi.treetable.renderer;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;

/**
 * This is an example taken from JAVA tutorials at:
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * 
 * To show how you can adapt or write a custom renderer for GUMI.
 * 
 */

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class DefaultTellapicColorRenderer extends AbstractTellapicRenderer {

    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public DefaultTellapicColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        component = new JLabel();
        ((JLabel)component).setOpaque(true);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.gumi.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.gumi.treetable.TellapicNodeAction)
     */
    @Override
    public void configureRenderer(TellapicNodeAction action, JTable table, Object value, boolean isSelected, boolean hasFocus) {
        Color newColor = (Color)action.getData();
        ((JLabel)component).setBackground(newColor);
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2,5,2,5, table.getSelectionBackground());
                }
                ((JLabel)component).setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5, table.getBackground());
                }
                ((JLabel)component).setBorder(unselectedBorder);
            }
        }

        ((JLabel)component).setToolTipText("RGB value: " + newColor.getRed() + ", " + newColor.getGreen() + ", "+ newColor.getBlue());
    }
}
