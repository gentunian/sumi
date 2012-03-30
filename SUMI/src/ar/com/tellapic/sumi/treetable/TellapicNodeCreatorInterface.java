/**
 * 
 */
package ar.com.tellapic.sumi.treetable;



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
public interface TellapicNodeCreatorInterface {

    /**
     * Gets the root node for this object
     * @return a {code}TellapicNode{/code} that its the root of this object
     */
    public TellapicNode getObjectRootNode();
}
