/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import ar.com.tellapic.sumi.SumiUserManager;
import ar.com.tellapic.sumi.renderer.NodeActionCheckBoxRenderer;
import ar.com.tellapic.sumi.renderer.NodeActionComboRenderer;
import ar.com.tellapic.sumi.system.SumiSystemUser;
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
    static JFrame frame;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("SUMI");
                final TellapicTreeTable tree = new TellapicTreeTable();
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
                
//                tree.setRowHeight(90);
//                tree.registerRendererComponent("DualImageRenderer", new DualImageRenderer(true));
//                tree.registerRendererComponent("ItemsRenderer", new ItemsRenderer());
//                tree.registerRendererComponent("OrderIssuedRenderer", new OrderIssuedRenderer());
//                tree.registerEditorComponent("ItemsListEditor", new ItemsListEditor());
//                tree.registerEditorComponent("TickButtonEditor", new TickButtonEditor());
                //				tree.addMouseWheelListener(new MouseWheelListener(){
                //					
                //					@Override
                //					public void mouseWheelMoved(MouseWheelEvent e) {
                //						Point p = e.getPoint();
                //						boolean v = tree.editCellAt(tree.rowAtPoint(p), tree.columnAtPoint(p));
                //					}
                //				});
                //				
                JToolBar toolbar = new JToolBar();
                TellapicTreeTableModel model = new SumiUserManager();
                tree.setTreeTableModel(model);

                frame.add(toolbar, BorderLayout.NORTH);
//                toolbar.add(new InsertUserAction((SumiUserManager) model));
//                toolbar.add(new DeleteNodeAction((SumiUserManager) model));
//                toolbar.addSeparator();
//                JToggleButton expandCollapse = new JToggleButton();
//                expandCollapse.setAction(new ExpandTreeAction(tree));
//                expandCollapse.setHideActionText(true);
//                expandCollapse.setIcon(new ImageIcon(Main.class.getResource("/icons/eye--minus.png")));
//                toolbar.add(expandCollapse);
//                JToggleButton gridToggle = new JToggleButton();
//                gridToggle.setAction(new ToggleGridAction(tree));
//                gridToggle.setHideActionText(true);
//                gridToggle.setSelected(false);
//                toolbar.add(gridToggle);
//                JToggleButton headersToggle = new JToggleButton();
//                headersToggle.setAction(new ToggleHeadersAction(tree));
//                headersToggle.setHideActionText(true);
//                headersToggle.setSelected(true);
//                toolbar.add(headersToggle);
//                toolbar.addSeparator();
//                toolbar.add(new TestCaseAction((SumiUserManager) model));
//                toolbar.addSeparator();
//                toolbar.add(new TableImportXml((SumiUserManager) model));
//                toolbar.add(new TableExportXml((SumiUserManager) model));
//                toolbar.addSeparator();
                JButton lnfButton = new JButton();
                PopupLookAndFeelMenu lnfMenu = new PopupLookAndFeelMenu();
                lnfMenu.addLookAndFeelListener(new LookAndFeelListener(){
                    @Override
                    public void lookAndFeelChanged() {
                        SwingUtilities.updateComponentTreeUI(frame);
                        frame.pack();
                    }
                });
                lnfButton.setAction(lnfMenu);
                toolbar.add(lnfButton);
                frame.setPreferredSize(new Dimension(800,600));
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new JScrollPane(tree));
                frame.setVisible(true);
                ((SumiUserManager)model).addUser(new SumiSystemUser(), null);
                //model.insertNodeInto(new SumiNode(new String("asdf")), (SumiNode) model.getRoot(), 0);
            }
        });
    }
}
