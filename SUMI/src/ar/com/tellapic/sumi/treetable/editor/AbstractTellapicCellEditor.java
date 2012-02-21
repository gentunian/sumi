/**
 * 
 */
package ar.com.tellapic.sumi.treetable.editor;

import javax.swing.AbstractCellEditor;

import ar.com.tellapic.sumi.treetable.TellapicNodeAction;

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
public abstract class AbstractTellapicCellEditor extends AbstractCellEditor {

	private static final long serialVersionUID = 1L;
	
	protected TellapicNodeAction action;
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.AbstractCellEditor#stopCellEditing()
	 */
	@Override
	public boolean stopCellEditing() {
		if (action != null)
			action.performAction();
		return super.stopCellEditing();
	}
}
