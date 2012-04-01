/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ar.com.tellapic.sumi.SumiUser;
import ar.com.tellapic.sumi.SumiUserManager;
import ar.com.tellapic.sumi.UserManagerTreeTableModel;
import ar.com.tellapic.sumi.renderer.NodeActionCheckBoxRenderer;
import ar.com.tellapic.sumi.renderer.NodeActionComboRenderer;
import ar.com.tellapic.sumi.treetable.TellapicTreeTable;
import ar.com.tellapic.sumi.treetable.TellapicTreeTableModel;
import ar.com.tellapic.sumi.treetable.editor.DefaultTellapicButtonCellEditor;
import ar.com.tellapic.sumi.treetable.editor.DefaultTellapicCheckBoxCellEditor;
import ar.com.tellapic.sumi.treetable.editor.DefaultTellapicColorCellEditor;
import ar.com.tellapic.sumi.treetable.editor.DefaultTellapicComboBoxCellEditor;
import ar.com.tellapic.sumi.treetable.renderer.DefaultTellapicButtonRenderer;
import ar.com.tellapic.sumi.treetable.renderer.DefaultTellapicColorRenderer;
import ar.com.tellapic.sumi.treetable.renderer.DefaultTellapicLabelRenderer;

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
public class Main {
//    static JFrame frame;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                // Add a user
                SumiUserManager.getInstance().addUser(new SumiUser(0, "seba"));
                
                // Create the model
                TellapicTreeTableModel model = new UserManagerTreeTableModel();
                
                // Create the tree view and set its model
                TellapicTreeTable tree  = new TellapicTreeTable();
                tree.setTreeTableModel(model);
                
                // Put the tree in an internal frame
                TreeTableFrame treeTableFrame = new TreeTableFrame(tree);
                
                // Create the main window frame
                MainFrame      frame = new MainFrame(model);
                JDesktopPane desktop = new JDesktopPane();
                frame.getContentPane().add(desktop);
                desktop.add(treeTableFrame);

                // Configure the tree view and register renderers/editors
                tree.setFillsViewportHeight(true);
                
                tree.registerRendererComponent(TellapicTreeTable.DEFAULT_COLOR_RENDERER_KEY, new DefaultTellapicColorRenderer(true));
                tree.registerRendererComponent(TellapicTreeTable.DEFAULT_CHECKBOX_RENDERER_KEY, new NodeActionCheckBoxRenderer());
                tree.registerRendererComponent(TellapicTreeTable.DEFAULT_BUTTON_RENDERER_KEY, new DefaultTellapicButtonRenderer());
                tree.registerRendererComponent(TellapicTreeTable.DEFAULT_LABEL_RENDERER_KEY,  new DefaultTellapicLabelRenderer());
                tree.registerRendererComponent(TellapicTreeTable.DEFAULT_COMBO_RENDERER_KEY, new NodeActionComboRenderer());
                
                tree.registerEditorComponent(TellapicTreeTable.DEFAULT_COLOR_EDITOR_KEY, new DefaultTellapicColorCellEditor());
                tree.registerEditorComponent(TellapicTreeTable.DEFAULT_TEXTFIELD_EDITOR_KEY, new DefaultCellEditor(new JTextField()));
                tree.registerEditorComponent(TellapicTreeTable.DEFAULT_CHECKBOX_EDITOR_KEY,  new DefaultTellapicCheckBoxCellEditor());
                tree.registerEditorComponent(TellapicTreeTable.DEFAULT_COMBO_EDITOR_KEY,  new DefaultTellapicComboBoxCellEditor());
                tree.registerEditorComponent(TellapicTreeTable.DEFAULT_BUTTON_EDITOR_KEY,  new DefaultTellapicButtonCellEditor());
                
                frame.setPreferredSize(new Dimension(800,600));
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
