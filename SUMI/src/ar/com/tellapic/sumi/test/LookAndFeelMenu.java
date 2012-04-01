/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

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
public class LookAndFeelMenu extends JMenu implements ItemListener {
    private static final long serialVersionUID = 1L;
    private LookAndFeelListener listener;

    public LookAndFeelMenu() {
        super("Look and Feel");
        String currentLnF = UIManager.getLookAndFeel().getName();
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(info.getName());
            item.setName(info.getClassName());
            item.addItemListener(this);
            add(item);
            item.setSelected(info.getName().equals(currentLnF));
        }
    }
    
    /**
     * 
     * @param lnfName
     */
    public void setLookAndFeel(String lnfName) {
        try {
            UIManager.setLookAndFeel(lnfName);
        } catch (UnsupportedLookAndFeelException e) {
            //fallback
        } catch (ClassNotFoundException e) {
            //fallback
        } catch (InstantiationException e) {
            //fallback
        } catch (IllegalAccessException e) {
            //fallback
        }
        if (listener != null)
            listener.lookAndFeelChanged();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object item = e.getItem();
        setLookAndFeel(((JMenuItem)item).getName());
    }

    /**
     * @param lookAndFeelListener
     */
    public void addLookAndFeelListener(LookAndFeelListener l) {
        listener = l;
    }
}
