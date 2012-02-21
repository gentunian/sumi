/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import javax.swing.AbstractAction;



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
public interface TellapicNodeAction {

	/**
	 * 
	 * @param node
	 */
	public void setNode(TellapicNode node);
	
	/**
	 * 
	 */
	public String getNodeActionName();
	
	/**
	 * 
	 * @return
	 */
	public TellapicNode getNode();
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(Object value);
	
	/**
	 * 
	 * @return
	 */
	public Object getValue();

	/**
	 * @return
	 */
	public String getRendererKey();
	
	/**
	 * 
	 * @return
	 */
	public String getEditorKey();

	/**
	 * 
	 */
	public void performAction();
	
	/**
	 * 	 
	 * @return
	 */
	public Object getData();
	
	/*
	 * 
	 */
	public AbstractAction getAbstractAction();

	/**
	 * @return
	 */
	public boolean isEditable();
	
}
