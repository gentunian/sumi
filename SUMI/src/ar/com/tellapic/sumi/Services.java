/**
 * 
 */
package ar.com.tellapic.sumi;

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
public final class Services {

    private static UsersInfo usersInfo;
    
    public Services() {
        usersInfo = new LinuxUsersInfo();
    }
    
    public void setUserInfoInterface(UsersInfo uii) {
        usersInfo = uii;
    }
    
    public static UsersInfo getUsersInfo() {
        if (usersInfo == null)
            usersInfo = new LinuxUsersInfo();
        return usersInfo;
    }
}
