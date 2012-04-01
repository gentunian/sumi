/**
 * 
 */
package ar.com.tellapic.sumi.system;

import ar.com.tellapic.sumi.SumiUser;


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
public class SumiSystemUser extends SumiUser {
    
    // We adapt this class to a _real_ user system class
    private SystemUserInfo userInfo;
    
    /**
     * @param id<
     * @param name
     * @param visible
     * @param remote
     * @param selected
     */
    public SumiSystemUser(String userName) {
        super(0, userName);
        userInfo = SystemServices.getServices().getUserInfo(userName);
        this.setUserId(userInfo.getUserId());
    }
    
//    /*
//     * (non-Javadoc)
//     * @see ar.com.tellapic.sumi.SumiUser#getObjectRootNode()
//     */
//    @Override
//    public TellapicNode getObjectRootNode() {
//        // Retrieve the node from our parent
//        TellapicNode rootNode = super.getObjectRootNode();
//        
//        // We add the home directory node
//        TellapicNode homeNode = new TellapicNode("Home", getPropertyIcon(), new DefaultTellapicNodeActionLabel(userInfo.getUserHomePath(), false));
//        rootNode.add(homeNode);
//        
//        // We collect the processes in use *NOW* (this is for the example, it will not auto-update)
//        // and put them in a TellapicNode.
//        Icon processIcon = new ImageIcon(SumiSystemUser.class.getResource("/icons/processor.png"));
//        TellapicNode processesRootNode = new TellapicNode("Processes", processIcon);
//        processesRootNode.addAction(new DefaultTellapicNodeActionCombo(new SortByName(), new String[]{"name", "pid"}));
//        for(ProcessInfo processInfo : userInfo.getProcesses()) {
//            TellapicNode processNode = new TellapicNode(processInfo, processIcon);
//
//            String pid = String.valueOf(processInfo.getProcessId());
//            
//            // Create a default action that shows info in a JLabel
//            TellapicNodeAction pidInfo  = new DefaultTellapicNodeActionLabel(pid, false);
//            TellapicNodeAction nameInfo = new DefaultTellapicNodeActionLabel(processInfo.getProcessCommand(), false);
//            
//            // Create the nodes that will hold properties and actions
//            TellapicNode pidNode  = new TellapicNode("PID", getPropertyIcon(), pidInfo);
//            TellapicNode nameNode = new TellapicNode("Command", getPropertyIcon(), nameInfo);
//            
//            // Add the info-nodes to its parent
//            processNode.add(pidNode);
//            processNode.add(nameNode);
//            
//            // We collect the sockets in use *NOW* (this is for the example, it will not auto-update)
//            // and put them in a TellapicNode.
//            Icon socketIcon = new ImageIcon(SumiSystemUser.class.getResource("/icons/socket.png"));
//            TellapicNode socketsRootNode = new TellapicNode("Sockets", socketIcon);
//            for(SocketInfo socketInfo : processInfo.getSockets()) {
//                TellapicNode socketNode     = new TellapicNode(socketInfo, socketIcon);
//                
//                pid = String.valueOf(socketInfo.getProcessId());
//                String fd = String.valueOf(socketInfo.getFileDescriptor());
//                // Create info actions.
//                TellapicNodeAction socketNameInfo = new DefaultTellapicNodeActionLabel(socketInfo.getProcessName(), false);
//                TellapicNodeAction socketPidInfo  = new DefaultTellapicNodeActionLabel(pid, false);
//                TellapicNodeAction socketFDInfo   = new DefaultTellapicNodeActionLabel(fd, false);
//                TellapicNodeAction socketModeInfo = new DefaultTellapicNodeActionLabel(socketInfo.getFileDescriptorMode(), false);
//                TellapicNodeAction socketProtInfo = new DefaultTellapicNodeActionLabel(socketInfo.getProtocol(), false);
//                TellapicNodeAction socketTypeInfo = new DefaultTellapicNodeActionLabel(socketInfo.getType(), false);
//                // Create the nodes
//                TellapicNode socketNameNode = new TellapicNode("Process Name", getPropertyIcon(), socketNameInfo);
//                TellapicNode socketFdNode   = new TellapicNode("File Descriptor", getPropertyIcon(), socketFDInfo);
//                TellapicNode socketModeNode = new TellapicNode("File Descriptor Mode", getPropertyIcon(), socketModeInfo);
//                TellapicNode socketPidNode  = new TellapicNode("PID", getPropertyIcon(), socketPidInfo);
//                TellapicNode socketTypeNode = new TellapicNode("Protocol", getPropertyIcon(), socketTypeInfo);
//                TellapicNode socketProtNode  = new TellapicNode("Type", getPropertyIcon(), socketProtInfo);
//                // Add the nodes to its parend
//                socketNode.add(socketNameNode);
//                socketNode.add(socketPidNode);
//                socketNode.add(socketFdNode);
//                socketNode.add(socketModeNode);
//                socketNode.add(socketProtNode);
//                socketNode.add(socketTypeNode);
//                //TODO: create action for closing sockets
//                
//                // Add the parent to the root
//                socketsRootNode.add(socketNode);
//            }
//            // Create a TellapicNodeAction for killing the process
//            TellapicNodeAction killAction = new DefaultTellapicNodeActionButton(new KillProcessAction());
//            processNode.addAction(killAction);
//            processNode.add(socketsRootNode);
//            processesRootNode.add(processNode);
//        }
//        rootNode.add(processesRootNode);
//        return rootNode;
//    }

//    // Create a kill linux-process SWING action
//    // TODO: extend the process interface
//    private class KillProcessAction extends AbstractAction {
//        private static final long serialVersionUID = 1L;
//
//        public KillProcessAction() {
//            Icon icon = new ImageIcon(KillProcessAction.class.getResource("/icons/cancel.png"));
//            putValue(AbstractAction.SMALL_ICON, icon); 
//            putValue(AbstractAction.NAME, "Kill");
//            putValue(AbstractAction.SHORT_DESCRIPTION, "Kill process");
//        }
//
//        /* (non-Javadoc)
//         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//         */
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            TellapicNodeAction action = (TellapicNodeAction) e.getSource();
//            if (action.getNode().getUserObject() instanceof Process) {
//                Process process = (Process) action.getNode().getUserObject();
//                int option = JOptionPane.showConfirmDialog(
//                        null,
//                        "Are you sure to kill process "+process.getProcessId()+"?",
//                        "Killing Process",
//                        JOptionPane.OK_CANCEL_OPTION,
//                        JOptionPane.QUESTION_MESSAGE
//                        );
//                if (option == JOptionPane.CANCEL_OPTION)
//                    return;
//                // Kill 'em all
//                process.kill();
//            }
//        }
//    }
}
