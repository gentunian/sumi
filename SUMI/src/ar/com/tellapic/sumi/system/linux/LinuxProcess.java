/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import java.util.ArrayList;

import ar.com.tellapic.sumi.system.FileInfo;
import ar.com.tellapic.sumi.system.Process;
import ar.com.tellapic.sumi.system.SocketInfo;
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
public class LinuxProcess implements Process {
    private String pName;
    private String command;
    private int pid;

    private ArrayList<SocketInfo> sockets;
    private ArrayList<FileInfo>   files;
    
    public LinuxProcess(int pid, String pName, String command) {
        this.pid = pid;
        this.pName = pName;
        this.command = command;
        this.sockets = SystemServices.getServices().getSockets(pid);
        this.files   = SystemServices.getServices().getFiles(pid);
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

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.linux.Process#kill()
     */
    @Override
    public void kill() {
        SystemServices.getServices().killProcess(getProcessId());
        
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.linux.Process#renice(int)
     */
    @Override
    public void renice(int newPriority) {
        // TODO Auto-generated method stub
        
    }
}
