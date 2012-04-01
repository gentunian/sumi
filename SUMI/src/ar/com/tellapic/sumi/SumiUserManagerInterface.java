/**
 * 
 */
package ar.com.tellapic.sumi;



/**
 *   Copyright (c) 2010 Sebastián Treu. *
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
public interface SumiUserManagerInterface {

    
    /**
     * 
     * @param userName
     * @return
     */
    public SumiUser createUser(String userName);
    
    /**
     * 
     * @param userName
     * @param id
     * @return
     */
    public SumiUser createUser(String userName, long id);
    
    /**
     * Adds an existing user to the users list. 
     * 
     * @param user the user to be added to the users list.
     * @return true if the user was succesfully added.
     */
    public boolean addUser(SumiUser user);
    /**
     * Removes the user with the specified userName.
     * 
     * @param userName The user name of the user to be removed.
     * @return true if the user was removed. False otherwise.
     */
    public boolean delUser(String userName);

    /**
     * Removes the user with the specified id.
     * 
     * @param id the id of the user to be removed.
     * @return true if the user was removed. False otherwise.
     */
    public boolean delUser(long id);

    /**
     * Removes the user from the users list.
     * 
     * @param user the user to be removed from the users list.
     * @return true if the user is succcessfully removed, false otherwise.
     */
    public boolean delUser(SumiUser user);

}
