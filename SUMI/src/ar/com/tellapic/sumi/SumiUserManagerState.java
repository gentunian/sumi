/**
 * 
 */
package ar.com.tellapic.sumi;

import java.util.List;
import java.util.Map;

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
public interface SumiUserManagerState {

    /**
     * 
     * @param id
     * @param userName
     * @return
     */
    public SumiUser getUser(long id, String userName);
    
    /**
     * 
     * @param properties
     * @return
     */
    public List<SumiUser> getUsers(Map<String, Object> properties);
    
    /**
     * 
     * @param name
     * @return
     */
    public SumiUser getUser(String name);
    
    /**
     * 
     * @param id
     * @return
     */
    public SumiUser getUser(long id);
    
    /**
     * Retrieves an user.
     * 
     * @param userName The user name of the user to be retrieved.
     * @return The user with name userName or null if no such user exists.
     */
    public List<SumiUser> getUsers(String propertyName, Object propertyValue);

    /**
     * Get the list of users.
     * 
     * @return the list of users.
     */
    public List<SumiUser> getUsers();

}
