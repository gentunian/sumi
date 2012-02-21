/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.awt.Color;

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
public class DefaultTellapicNodeActionColor extends DefaultAbstractTellapicNodeAction {
	
	/**
	 * 
	 * @param node
	 * @param action
	 */
	public DefaultTellapicNodeActionColor(TellapicNode node, AbstractAction action, Color data, boolean editable) {
		super(node, action, TellapicTreeTable.DEFAULT_COLOR_EDITOR_KEY, TellapicTreeTable.DEFAULT_COLOR_RENDERER_KEY, editable);
		setValue(data);
	}
	
	/**
	 * 
	 * @param action
	 */
	public DefaultTellapicNodeActionColor(AbstractAction action, Color data, boolean editable) {
		this(null, action, data, editable);
	}

	/**
	 * 
	 */
	public DefaultTellapicNodeActionColor(String name, Color data, boolean editable) {
		super(name, TellapicTreeTable.DEFAULT_COLOR_EDITOR_KEY, TellapicTreeTable.DEFAULT_COLOR_RENDERER_KEY, editable);
		setValue(data);
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getData()
	 */
	@Override
	public Object getData() {
		return getValue();
	}
}
