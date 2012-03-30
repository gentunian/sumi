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
                in = linuxCommand.runCmd("echo ~"+userName);
                String home = in.readLine();
                userInfo = new LinuxUserInfo(Integer.parseInt(uid), userName, home, Integer.parseInt(gid), groups);
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
                String name = columns[1].split(" ")[0];
                LinuxProcess process = new LinuxProcess(Integer.parseInt(columns[0].trim()), name.replaceAll("\\/.*\\/", "") , columns[1]);
                processes.add(process);
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
            in = linuxCommand.runCmd("lsof -i -a -p "+pid+"| awk \'{print $1\" \"$2\" \"$4\" \"$5\" \"$8\" \"$9}\'");

            String line = in.readLine();
            while ((line = in.readLine()) != null) {
                String[] columns = line.split(" ");
                LinuxSocket socket = new LinuxSocket(
                        columns[0],
                        Integer.parseInt(columns[1]),
                        Integer.parseInt(columns[2].replaceAll("[urw]", "").trim()),
                        columns[2].charAt(columns[2].length()-1),
                        columns[3],
                        columns[4],
                        columns[5]
                        );
                sockets.add(socket);
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

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#killProcess(int)
     */
    @Override
    public void killProcess(int pid) {
        try {
            linuxCommand.runCmd("kill -9 "+pid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#reniceProcess(int)
     */
    @Override
    public void reniceProcess(int pid) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.Services#closeSocket(int)
     */
    @Override
    public void closeSocket(int fd) throws UnsupportedOperationException {
        
    }
}
