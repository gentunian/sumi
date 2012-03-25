/**
 * 
 */
package ar.com.tellapic.sumi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;

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
public class SystemSumiUser extends SumiUser {

    private String home = System.getProperty("user.home");
    
    /**
     * @param id<
     * @param name
     * @param visible
     * @param remote
     * @param selected
     */
    public SystemSumiUser() {
        super(0, System.getProperty("user.name"), false, false, false);
        Process child;
        try {
            child = Runtime.getRuntime().exec("id -u "+getName());
            BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));
            setUserId(Integer.parseInt(in.readLine()));
            in.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUser#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = super.getObjectRootNode();
        TellapicNode homeNode = new TellapicNode("Home", getPropertyIcon(), new DefaultTellapicNodeActionLabel(home, false));
        UsersInfo usersInfo = Services.getUsersInfo();
        // Get files info
        TellapicNode filesRootNode = new TellapicNode("Files", new ImageIcon(SystemSumiUser.class.getResource("/icons/folder.png")));
        for(FileInfo file : usersInfo.getFiles(getName())) {
            if (!file.getName().equals("")) {
                filesRootNode.add(file.getObjectRootNode());
            }
        }
        // Get sockets info
        TellapicNode socketsRootNode = new TellapicNode("Sockets", new ImageIcon(SystemSumiUser.class.getResource("/icons/socket.png")));
        for(SocketInfo socket : usersInfo.getSockets(getName())) {
            if (!socket.getName().equals("")) {
                socketsRootNode.add(socket.getObjectRootNode());
            }
        }
        // Get processes info
        TellapicNode processesRootNode = new TellapicNode("Processes", new ImageIcon(SystemSumiUser.class.getResource("/icons/processor.png")));
        for(ProcessInfo process : usersInfo.getProcesses(getName())) {
            if (!process.getProcessName().equals("")) {
                processesRootNode.add(process.getObjectRootNode());
            }
        }
        
        rootNode.add(filesRootNode);
        rootNode.add(socketsRootNode);
        rootNode.add(processesRootNode);
        rootNode.add(homeNode);
        return rootNode;
    }
}
