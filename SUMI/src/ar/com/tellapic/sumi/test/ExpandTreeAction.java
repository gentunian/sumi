/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JToggleButton;

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
public class ExpandTreeAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private TellapicTreeTable treetable;

	/**
	 * 
	 * @param name
	 * @param icon
	 */
	public ExpandTreeAction(TellapicTreeTable treetable) {
		this.treetable = treetable;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton button = (JToggleButton) e.getSource();
		if (button.isSelected()) {
			treetable.expandAll();
		} else {
			treetable.collapseAll();
		}
	}

}
