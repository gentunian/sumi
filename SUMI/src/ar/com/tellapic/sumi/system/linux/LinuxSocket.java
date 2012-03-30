/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import ar.com.tellapic.sumi.system.Socket;
import ar.com.tellapic.sumi.system.SystemServices;

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
public class LinuxSocket implements Socket {

    private String pName;
    private int pid;
    private String type;
    private String protocol;
    private String name;
    private int fd;
    private String fdMode;
    
    public LinuxSocket(String pName, int pid, int fd, char fdMode, String type, String protocol, String name) {
        this.pName = pName;
        this.pid = pid;
        this.type = type;
        this.protocol = protocol;
        this.name = name;
        this.fd = fd;
        if (fdMode == 'r')
            this.fdMode = "Read Only";
        else if (fdMode == 'w')
            this.fdMode = "Write Only";
        else if (fdMode == 'u')
            this.fdMode = "Read/Write";
        else
            this.fdMode = "Unknown";
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

    /**
     * 
     */
    public String toString() {
        return name;
    }
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Socket#close()
     */
    @Override
    public void close() {
        SystemServices.getServices().closeSocket(fd);
    }
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SocketInfo#getFileDescriptor()
     */
    @Override
    public int getFileDescriptor() throws UnsupportedOperationException {
        return fd;
    }
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SocketInfo#getFileDescriptorMode()
     */
    @Override
    public String getFileDescriptorMode() throws UnsupportedOperationException {
        return fdMode;
    }
}
