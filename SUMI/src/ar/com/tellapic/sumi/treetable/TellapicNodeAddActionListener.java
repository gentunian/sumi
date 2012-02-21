/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.util.EventListener;


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
 *
 * GumiNodeAddActionListener
 * -------------------------
 * 
 * This interface is used for invoking some code when a new Action Node
 * is added to a specific Node.
 * 
 * Every time an Action Node is added to a Node, eg:
 * 
 *  <code>node.addAction(actionNode)</code>
 *  
 *  this listener should execute the actionAdded method.
 */
public interface TellapicNodeAddActionListener extends EventListener {

	/**
	 * 
	 * @param action
	 */
	public void actionAdded(TellapicNodeActionEvent e);
}
