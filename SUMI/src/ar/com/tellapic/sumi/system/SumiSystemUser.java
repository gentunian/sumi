/**
 * 
 */
package ar.com.tellapic.sumi.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ar.com.tellapic.sumi.SumiUser;
import ar.com.tellapic.sumi.treetable.TellapicNode;

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
public class SumiSystemUser extends SumiUser {

//    private String home = System.getProperty("user.home");
    
    /**
     * @param id<
     * @param name
     * @param visible
     * @param remote
     * @param selected
     */
    public SumiSystemUser() {
        super(0, System.getProperty("user.name"), false, false, false);
        Process child;
        try {
            child = Runtime.getRuntime().exec("id -u "+getName());
            BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));
            setUserId(Integer.parseInt(in.readLine()));
            in.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * (non-Javadoc)
     * @see ar.com.tellapic.sumi.SumiUser#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = super.getObjectRootNode();
//        TellapicNode homeNode = new TellapicNode("Home", getPropertyIcon(), new DefaultTellapicNodeActionLabel(home, false));
        
        SystemUserInfo userInfo = SystemServices.getServices().getUserInfo(getName());
        
        rootNode.add(userInfo.getObjectRootNode());
        return rootNode;
    }
}
