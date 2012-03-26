/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import ar.com.tellapic.sumi.system.FileInfo;
import ar.com.tellapic.sumi.system.ProcessInfo;
import ar.com.tellapic.sumi.system.Services;
import ar.com.tellapic.sumi.system.SocketInfo;
import ar.com.tellapic.sumi.system.SystemUserInfo;

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
public class LinuxServices implements Services {

    private final LinuxCommand linuxCommand = LinuxCommand.getInstance();

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#getUserInfo(java.lang.String)
     */
    @Override
    public SystemUserInfo getUserInfo(String userName) {
        LinuxUserInfo userInfo = null;
        BufferedReader in = null;
        try {
            in = linuxCommand.runCmd("id -n -u "+userName);
            String output = in.readLine();
            if (!output.contains("No such user")) {
                in = linuxCommand.runCmd("id -n -G "+userName);
                String[] groups = in.readLine().split(" ");
                in = linuxCommand.runCmd("id -u "+userName);
                String uid = in.readLine();
                in = linuxCommand.runCmd("id -g "+userName);
                String gid = in.readLine();
                userInfo = new LinuxUserInfo(Integer.parseInt(uid), userName, Integer.parseInt(gid), groups);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#getProcesses(java.lang.String)
     */
    @Override
    public ArrayList<ProcessInfo> getProcesses(String userName) {
        ArrayList<ProcessInfo> processes = new ArrayList<ProcessInfo>();
        BufferedReader in = null;
        try {
            in = linuxCommand.runCmd("ps --sort cmd -opid,cmd -u "+userName);
            String line = in.readLine();
            while ((line = in.readLine()) != null) {
                String[] columns = line.trim().split(" ", 2);
                LinuxProcessInfo processInfo = new LinuxProcessInfo(Integer.parseInt(columns[0].trim()), columns[1].split(" ")[0], columns[1]);
                processes.add(processInfo);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#getSockets(java.lang.String)
     */
    @Override
    public ArrayList<SocketInfo> getSockets(int pid) {
        ArrayList<SocketInfo> sockets = new ArrayList<SocketInfo>();
        BufferedReader in = null;
        try {
//            in = linuxCommand.runCmd("lsof -i | awk \'{print $1\" \"$2\" \"$5\" \"$8\" \"$9}\'");
            in = linuxCommand.runCmd("lsof -i -a -p "+pid+"| awk \'{print $1\" \"$2\" \"$5\" \"$8\" \"$9}\'");

            String line = in.readLine();
            while ((line = in.readLine()) != null) {
                String[] columns = line.split(" ");
                LinuxSocketInfo socketInfo = new LinuxSocketInfo(columns[0], Integer.parseInt(columns[1]), columns[2], columns[3], columns[4]);
                sockets.add(socketInfo);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sockets;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#getFiles(int)
     */
    @Override
    public ArrayList<FileInfo> getFiles(int pid) {
        return null;
    }
}
