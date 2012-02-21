/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;


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
public class DefaultTellapicCellEditorListener  implements CellEditorListener {

	/* (non-Javadoc)
	 * @see javax.swing.event.CellEditorListener#editingStopped(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void editingStopped(ChangeEvent e) {
		TellapicNodeAction action = (TellapicNodeAction) e.getSource();
		action.performAction();
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.CellEditorListener#editingCanceled(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void editingCanceled(ChangeEvent e) {}

}
