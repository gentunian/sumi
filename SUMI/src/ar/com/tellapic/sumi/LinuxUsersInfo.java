/**
 * 
 */
package ar.com.tellapic.sumi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


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
public class LinuxUsersInfo implements UsersInfo {

    private String[] shellCmd = new String[] {
      "/bin/sh",
      "-c",
      ""
    };
    
    private BufferedReader cmdResult;
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getFiles(java.lang.String)
     */
    @Override
    public ArrayList<FileInfo> getFiles(String userName) {
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();
        try {
            runCmd("lsof -u "+userName+" | awk \'{print $9}\' | sort -n");
            String line = null;
            while ((line = cmdResult.readLine()) != null) {
                LinuxFileInfo fileInfo = new LinuxFileInfo(line);
                files.add(fileInfo);
            }
            cmdResult.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getFiles(int)
     */
    @Override
    public void getFiles(int uid) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcessesId(java.lang.String)
     */
    @Override
    public void getProcessesId(String userName) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcessesId(int)
     */
    @Override
    public void getProcessesId(int uid) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcessesNames(java.lang.String)
     */
    @Override
    public void getProcessesNames(String userName) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcessesNames(int)
     */
    @Override
    public void getProcessesNames(int uid) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcesses(java.lang.String)
     */
    @Override
    public ArrayList<ProcessInfo> getProcesses(String userName) {
        ArrayList<ProcessInfo> processes = new ArrayList<ProcessInfo>();
        try {
            runCmd("ps -opid,cmd -u "+userName);

            String line = cmdResult.readLine();
            while ((line = cmdResult.readLine()) != null) {
                String[] columns = line.trim().split(" ", 2);
                LinuxProcessInfo processInfo = new LinuxProcessInfo(columns[1], Integer.parseInt(columns[0].trim()));
                processes.add(processInfo);
            }
            cmdResult.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcesses(int)
     */
    @Override
    public void getProcesses(int uid) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getSockets(java.lang.String)
     */
    @Override
    public ArrayList<SocketInfo> getSockets(String userName) {
        ArrayList<SocketInfo> sockets = new ArrayList<SocketInfo>();
        try {
            runCmd("lsof -i | awk \'{print $1\" \"$2\" \"$5\" \"$8\" \"$9}\'");

            String line = cmdResult.readLine();
            while ((line = cmdResult.readLine()) != null) {
                String[] columns = line.split(" ");
                LinuxSocketInfo socketInfo = new LinuxSocketInfo(columns[0], Integer.parseInt(columns[1]), columns[2], columns[3], columns[4]);
                sockets.add(socketInfo);
            }
            cmdResult.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sockets;
    }
    
    private void runCmd(String cmd) throws IOException {
        shellCmd[2] = cmd;
        Process p = Runtime.getRuntime().exec(shellCmd);
        cmdResult = new BufferedReader(new InputStreamReader(p.getInputStream()));
    }
}
