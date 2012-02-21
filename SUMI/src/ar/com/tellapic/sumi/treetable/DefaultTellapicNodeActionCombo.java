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
public class DefaultTellapicNodeActionCombo extends DefaultAbstractTellapicNodeAction {

	private Object[] data;
	
	/**
	 * 
	 * @param node
	 * @param action
	 */
	public DefaultTellapicNodeActionCombo(TellapicNode node, AbstractAction action, Object[] data) {
		super(node, action, TellapicTreeTable.DEFAULT_COMBO_EDITOR_KEY, TellapicTreeTable.DEFAULT_COMBO_RENDERER_KEY, true);
		initData(data);
	}
	
	/**
	 * 
	 * @param action
	 */
	public DefaultTellapicNodeActionCombo(AbstractAction action, Object[] data) {
		this(null, action, data);
	}

	/**
	 * 
	 */
	public DefaultTellapicNodeActionCombo(String name, Object[] data) {
		super(name, TellapicTreeTable.DEFAULT_COMBO_EDITOR_KEY, TellapicTreeTable.DEFAULT_COMBO_RENDERER_KEY, true);
		initData(data);
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getData()
	 */
	@Override
	public Object getData() {
		return data;
	}
	
	private void initData(Object[] data) {
		if (data == null)
			throw new IllegalArgumentException("data must not be null");
		this.data = data;
		if (data.length > 0)
			setValue(data[0]);
	}
}