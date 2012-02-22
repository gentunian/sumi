/**
 * 
 */
package ar.com.tellapic.sumi.test;

import javax.swing.AbstractAction;

import ar.com.tellapic.sumi.treetable.DefaultAbstractTellapicNodeAction;
import ar.com.tellapic.sumi.treetable.TellapicNode;
import ar.com.tellapic.sumi.treetable.TellapicTreeTable;

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
public class DualImageNodeAction extends DefaultAbstractTellapicNodeAction {

	/**
	 * @param name
	 * @param eKey
	 * @param rKey
	 * @param editable
	 */
	public DualImageNodeAction() {
		super("", TellapicTreeTable.NO_EDITOR, "DualImageRenderer", false);

	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.sumi.treetable.TellapicNodeAction#getData()
	 */
	@Override
	public Object getData() {
		return new String[] {"/icons/Warning.png", "/icons/ok.png"};
	}
}
