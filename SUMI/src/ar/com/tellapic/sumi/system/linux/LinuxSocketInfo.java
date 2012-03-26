/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.system.SocketInfo;
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
public class LinuxSocketInfo implements SocketInfo {

    private String pName;
    private int pid;
    private String type;
    private String protocol;
    private String name;
    private Icon icon = new ImageIcon(LinuxSocketInfo.class.getResource("/icons/socket.png"));
    private Icon propertyIcon = new ImageIcon(LinuxSocketInfo.class.getResource("/icons/property.png"));
    
    public LinuxSocketInfo(String pName, int pid, String type, String protocol, String name) {
        this.pName = pName;
        this.pid = pid;
        this.type = type;
        this.protocol = protocol;
        this.name = name;
    }
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getType()
     */
    @Override
    public String getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getProcessName()
     */
    @Override
    public String getProcessName() {
        return pName;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getProcessId()
     */
    @Override
    public int getProcessId() {
        return pid;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getName()
     */
    @Override
    public String getName() {
        return name;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getProtocol()
     */
    @Override
    public String getProtocol() {
        return protocol;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SocketInfo#getIcon()
     */
    @Override
    public Icon getIcon() {
        return icon;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = new TellapicNode(this, getIcon());
        TellapicNode pNameNode = new TellapicNode("Process Name", propertyIcon, new DefaultTellapicNodeActionLabel(getProcessName(), false));
        TellapicNode pidNode = new TellapicNode("PID", propertyIcon, new DefaultTellapicNodeActionLabel(String.valueOf(getProcessId()), false));
        TellapicNode protocolNode = new TellapicNode("Protocol", propertyIcon, new DefaultTellapicNodeActionLabel(getProtocol(), false));
        TellapicNode typeNode = new TellapicNode("Type", propertyIcon, new DefaultTellapicNodeActionLabel(getType(), false));

        rootNode.add(pNameNode);
        rootNode.add(pidNode);
        rootNode.add(typeNode);
        rootNode.add(protocolNode);
        
        return rootNode;
    }
    
    /**
     * 
     */
    public String toString() {
        return name;
    }
}
