/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import ar.com.tellapic.sumi.treetable.editor.DefaultTellapicButtonEditor;

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
public class TickButtonEditor extends DefaultTellapicButtonEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;
    
    public TickButtonEditor() {
        super();
    }
   
    /* (non-Javadoc)
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        DualImageRenderer dualImageRenderer = (DualImageRenderer) renderer.getTableCellRendererComponent(table, value, isSelected, true, row, column);
        DualImageRenderer clonedRenderer = (DualImageRenderer) clonePanel(dualImageRenderer);
        JButton rendererButton = clonedRenderer.getButtonImage();
        clonedRenderer.remove(rendererButton);
        editor.setIcon(rendererButton.getIcon());
        clonedRenderer.add(editor);
        if (isSelected) {
            clonedRenderer.setBackground(table.getSelectionBackground());
            clonedRenderer.setForeground(table.getSelectionForeground());
        } else {
            clonedRenderer.setBackground(table.getBackground());
            clonedRenderer.setForeground(table.getForeground());
        }
        return clonedRenderer;
    }
    
    /**
     * 
     * @param toClone
     * @return
     */
    private JComponent clonePanel(JComponent toClone) {
        JComponent c = null;
        FileOutputStream fos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("myObject.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toClone);
            oos.flush();
            oos.close();
            // Deserialize the object persisted in "myObject.ser"
            FileInputStream fis = new FileInputStream("myObject.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (JComponent) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    /* (non-Javadoc)
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    @Override
    public Object getCellEditorValue() {
        return "SomethingUseful";
    }

}
