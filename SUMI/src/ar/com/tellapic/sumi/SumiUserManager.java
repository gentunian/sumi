/**
 * 
 */
package ar.com.tellapic.sumi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

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
public class SumiUserManager extends Observable implements SumiUserManagerInterface, SumiUserManagerState, Observer {
    
    public static final String USER_ADDED = "ADD";
    public static final String USER_REMOVED = "REMOVE";
    
    private ArrayList<SumiUser> users;
    private long sid;
    
    private static class Holder {
        private static final SumiUserManager INSTANCE = new SumiUserManager();
    }
    
    private SumiUserManager() {
        users = new ArrayList<SumiUser>();
        sid = 0;
    }
    
    /**
     * 
     * @return
     */
    public static SumiUserManager getInstance() {
        return Holder.INSTANCE;
    }
    
    
    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SumiUser) {
            SumiUser user = (SumiUser) o;
            if (arg instanceof String) {
                Object property = user.getProperty((String) arg);
                if (property != null) {
                    
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUser(java.lang.String)
     */
    @Override
    public List<SumiUser> getUsers(String propertyName, Object propertyValue) {
        List<SumiUser> rUsers = new ArrayList<SumiUser>();

        for(int i = 0; i < users.size(); i++) {
            SumiUser u = users.get(i);
            if (u.getProperty(propertyName) != propertyValue) 
                rUsers.add(u);
        }
        
        return rUsers;
    }
    
    
    public List<SumiUser> getUsers(Map<String, Object> properties) {
        List<SumiUser> rUsers = new ArrayList<SumiUser>();
        
        for(int i = 0; i < users.size(); i++) {
            SumiUser u = users.get(i);
            Set<Entry<String, Object>> s1 = u.getProperties().entrySet();
            Set<Entry<String, Object>> s2 = properties.entrySet();
            
            if (s1.containsAll(s2))
                rUsers.add(u);
        }
        
        return rUsers;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUsers()
     */
    @Override
    public List<SumiUser> getUsers() {
        return users;
    }
    
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUser(java.lang.String)
     */
    @Override
    public SumiUser getUser(String name) {
        List<SumiUser> u = getUsers(SumiUser.PROPERTY_NAME, name);
        return (u.size() > 0)? u.get(0) : null; // We don't allow users to have same name
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUser(int)
     */
    @Override
    public SumiUser getUser(long id) {
        List<SumiUser> u = getUsers(SumiUser.PROPERTY_ID, id);
        return (u.size() > 0)? u.get(0) : null; // We don't allow users to have same ID
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerState#getUser(int, java.lang.String)
     */
    @Override
    public SumiUser getUser(long id, String userName) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put(SumiUser.PROPERTY_ID, id);
        m.put(SumiUser.PROPERTY_NAME, userName);

        List<SumiUser> u = getUsers(m);
        return (u.size() > 0)? u.get(0) : null;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#addUser(ar.com.tellapic.sumi.SumiUser)
     */
    @Override
    public boolean addUser(SumiUser user) {
        if (getUser(user.getUserId(), user.getName()) != null)
            return false;
        
        boolean added = users.add(user);

        if (added) {
            user.addObserver(this);
            setChanged();
            notifyObservers(new Object[]{USER_ADDED, user});
        }

        return added;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#delUser(java.lang.String)
     */
    @Override
    public boolean delUser(String userName) {
        SumiUser user = getUser(userName);
        return delUser(user);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#delUser(int)
     */
    @Override
    public boolean delUser(long id) {
        SumiUser user = getUser(id);
        return delUser(user);
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#delUser(ar.com.tellapic.sumi.SumiUser)
     */
    @Override
    public boolean delUser(SumiUser user) {
        boolean removed = users.remove(user);
        
        if (removed) {
            user.deleteObserver(this);
            setChanged();
            notifyObservers(new Object[]{USER_REMOVED, user});
        }
        
        return removed;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#createUser(java.lang.String, int)
     */
    @Override
    public SumiUser createUser(String userName, long id) {
        SumiUser user = new SumiUser(id, userName);
        return (addUser(user))? user : null;
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUserManagerInterface#createUser(java.lang.String, int)
     */
    @Override
    public SumiUser createUser(String userName) {
        SumiUser u = null;
        do {
            u = new SumiUser(sid, userName);
            sid++;
            System.out.println(u);
        }
        while (!addUser(u));
        return u;
    }
}
