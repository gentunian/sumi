/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import java.util.ArrayList;

import ar.com.tellapic.sumi.system.ProcessInfo;
import ar.com.tellapic.sumi.system.SystemServices;
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
public class LinuxUserInfo implements SystemUserInfo {

    private int uid;
    private int gid;
    private String name;
    private String[] groups;
    private ArrayList<ProcessInfo> processes;
    private String home;
    
    /**
     * @param parseInt
     * @param userName
     * @param gid
     * @param groups
     */
    public LinuxUserInfo(int uid, String userName, String home, int gid, String[] groups) {
        this.home = home;
        this.uid = uid;
        this.name = userName;
        this.gid = gid;
        this.groups = groups;
        this.processes = SystemServices.getServices().getProcesses(userName);
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.UsersInfo#getProcesses(java.lang.String)
     */
    @Override
    public ArrayList<ProcessInfo> getProcesses() {
        return processes;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SystemUserInfo#getUserName()
     */
    @Override
    public String getUserName() {
        return name;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SystemUserInfo#getUserID()
     */
    @Override
    public int getUserId() {
        return uid;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SystemUserInfo#getUserHomePath()
     */
    @Override
    public String getUserHomePath() {
        return home;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SystemUserInfo#getGroupID()
     */
    @Override
    public int getGroupID() {
        return gid;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.system.SystemUserInfo#getGroups()
     */
    @Override
    public String[] getGroups() {
        return groups;
    }
}