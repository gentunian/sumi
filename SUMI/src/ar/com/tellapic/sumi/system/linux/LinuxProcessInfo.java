/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ar.com.tellapic.sumi.system.FileInfo;
import ar.com.tellapic.sumi.system.ProcessInfo;
import ar.com.tellapic.sumi.system.SocketInfo;
import ar.com.tellapic.sumi.system.SumiSystemUser;
import ar.com.tellapic.sumi.system.SystemServices;
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
    private String pName;
    private String command;
    private int pid;

    private ArrayList<SocketInfo> sockets;
    private ArrayList<FileInfo>   files;
    
    public LinuxProcessInfo(int pid, String pName, String command) {
        this.pid = pid;
        this.pName = pName;
        this.command = command;
        this.sockets = SystemServices.getServices().getSockets(pid);
        this.files   = SystemServices.getServices().getFiles(pid);
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = new TellapicNode(this, icon);
        TellapicNode pidNode = new TellapicNode("PID", propertyIcon, new DefaultTellapicNodeActionLabel(String.valueOf(getProcessId()), false));
        TellapicNode nameNode = new TellapicNode("Command", propertyIcon, new DefaultTellapicNodeActionLabel(String.valueOf(getProcessCommand()), false));
        TellapicNodeAction killAction = new DefaultTellapicNodeActionButton(new KillProcessAction());
        
        TellapicNode filesNode = new TellapicNode("Files", new ImageIcon(SumiSystemUser.class.getResource("/icons/folder.png")));
//        for(FileInfo file : files) {
//            if (!file.getName().equals("")) {
//                filesRootNode.add(file.getObjectRootNode());
//            }
//        }
        
        // Get sockets info
        TellapicNode socketsNode = new TellapicNode("Sockets", new ImageIcon(SumiSystemUser.class.getResource("/icons/socket.png")));
        for(SocketInfo socket : sockets) {
            socketsNode.add(socket.getObjectRootNode());
        }
        
        // Add action to kill process
        rootNode.addAction(killAction);
        
        // Add nodes to root
        rootNode.add(nameNode);
        rootNode.add(pidNode);
        rootNode.add(filesNode);
        rootNode.add(socketsNode);
        return rootNode;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getProcessName()
     */
    @Override
    public String getProcessName() {
        return pName;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getProcessId()
     */
    @Override
    public int getProcessId() {
        return pid;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getProcessCommand()
     */
    @Override
    public String getProcessCommand() {
        return command;
    }
    
    @Override
    public String toString() {
        return pName;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getFiles()
     */
    @Override
    public ArrayList<FileInfo> getFiles() {
        return   files;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.ProcessInfo#getSockets()
     */
    @Override
    public ArrayList<SocketInfo> getSockets() {
        return sockets;
    }
    
    /**
     * 
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
    private class KillProcessAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public KillProcessAction() {
            putValue(AbstractAction.SMALL_ICON, new ImageIcon(KillProcessAction.class.getResource("/icons/cancel.png")));
            putValue(AbstractAction.NAME, "Kill");
            putValue(AbstractAction.SHORT_DESCRIPTION, "Kill process");
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
