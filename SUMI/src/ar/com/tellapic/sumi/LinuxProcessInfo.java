/**
 * 
 */
package ar.com.tellapic.sumi;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionButton;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;
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
public class LinuxProcessInfo  implements ProcessInfo {
    private Icon icon = new ImageIcon(LinuxProcessInfo.class.getResource("/icons/processor.png"));
    private Icon propertyIcon = new ImageIcon(LinuxProcessInfo.class.getResource("/icons/property.png"));
    private String name;
    private int pid;
    
    public LinuxProcessInfo(String name, int pid) {
        this.pid = pid;
        this.name = name;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = new TellapicNode(this, icon);
        TellapicNode nameNode = new TellapicNode("Command", propertyIcon, new DefaultTellapicNodeActionLabel(String.valueOf(getProcessName()), false));
        TellapicNodeAction killAction = new DefaultTellapicNodeActionButton(new KillProcessAction());
        rootNode.addAction(killAction);
        rootNode.add(nameNode);
        return rootNode;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getProcessName()
     */
    @Override
    public String getProcessName() {
        return name;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getProcessId()
     */
    @Override
    public int getProcessId() {
        return pid;
    }
    
    @Override
    public String toString() {
        return String.valueOf(pid);
    }
    
    private class KillProcessAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public KillProcessAction() {
            putValue(AbstractAction.SMALL_ICON, new ImageIcon(KillProcessAction.class.getResource("/icons/cancel.png")));
            putValue(AbstractAction.NAME, "Kill");
        }

        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            TellapicNodeAction action = (TellapicNodeAction) e.getSource();
            ProcessInfo processInfo = (ProcessInfo) action.getNode().getUserObject();
            int option = JOptionPane.showConfirmDialog(null, "Are you sure to kill process "+processInfo.getProcessId()+"?", "Killing Process", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.CANCEL_OPTION)
                return;
            try {
                
                Runtime.getRuntime().exec("kill -9 "+processInfo.getProcessId());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
