/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

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
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame(null);
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
    public MainFrame(TellapicTreeTableModel model) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(this.contentPane);
        
        JMenuBar        menuBar = new JMenuBar();
        JMenu              menu = new JMenu("Tools");
        menu.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                LookAndFeelMenu lnfMenu = new LookAndFeelMenu();
                lnfMenu.addLookAndFeelListener(new LookAndFeelListener(){
                    @Override
                    public void lookAndFeelChanged() {
                        SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        pack();
                    }
                });
                ((JMenu)e.getSource()).removeAll();
                ((JMenu)e.getSource()).add(lnfMenu);
            }
        });
        
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

}
