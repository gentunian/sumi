/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import ar.com.tellapic.sumi.treetable.TellapicTreeTable;
import ar.com.tellapic.sumi.treetable.TellapicTreeTableModel;

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
public class TreeTableFrame extends JInternalFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TreeTableFrame frame = new TreeTableFrame(new TellapicTreeTable());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TreeTableFrame(TellapicTreeTable tree) {
        super("Tree Frame", true, true, true, true);
        setBounds(100, 100, 450, 300);
        JToolBar toolbar = new JToolBar();
        add(toolbar, BorderLayout.NORTH);
        toolbar.add(new InsertUserAction());
        toolbar.add(new DeleteNodeAction((TellapicTreeTableModel) tree.getTreeTableModel()));
        toolbar.addSeparator();
        JToggleButton expandCollapse = new JToggleButton();
        expandCollapse.setAction(new ExpandTreeAction(tree));
        expandCollapse.setHideActionText(true);
        expandCollapse.setIcon(new ImageIcon(TreeTableFrame.class.getResource("/icons/eye.png")));
        toolbar.add(expandCollapse);
        JToggleButton gridToggle = new JToggleButton();
        gridToggle.setAction(new ToggleGridAction(tree));
        gridToggle.setHideActionText(true);
        gridToggle.setSelected(false);
        toolbar.add(gridToggle);
        JToggleButton headersToggle = new JToggleButton();
        headersToggle.setAction(new ToggleHeadersAction(tree));
        headersToggle.setHideActionText(true);
        headersToggle.setSelected(true);
        toolbar.add(headersToggle);
        add(new JScrollPane(tree));
        setPreferredSize(new Dimension(400,400));
        setVisible(true);
        pack();
//        toolbar.addSeparator();
//        toolbar.add(new TableImportXml(tree.getTreeTableModel()));
//        toolbar.add(new TableExportXml(tree.getTreeTablemodel()));
    }

}
