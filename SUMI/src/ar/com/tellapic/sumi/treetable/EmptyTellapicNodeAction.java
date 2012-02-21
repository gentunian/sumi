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
public class EmptyTellapicNodeAction extends DefaultAbstractTellapicNodeAction {

	public EmptyTellapicNodeAction(TellapicNode node) {
		super("", TellapicTreeTable.NO_EDITOR, TellapicTreeTable.DEFAULT_LABEL_RENDERER_KEY, false);
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#getValue()
	 */
	@Override
	public Object getValue() {
		return "";
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#performAction()
	 */
	@Override
	public void performAction() {
		// Nothing here...
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getData()
	 */
	@Override
	public Object getData() {
		return "";
	}
}
