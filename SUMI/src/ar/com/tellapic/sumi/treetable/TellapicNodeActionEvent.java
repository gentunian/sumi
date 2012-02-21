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
public class TellapicNodeActionEvent {
	public static final int ADD_ACTION    = 0;
	public static final int REMOVE_ACTION = 1;
	
	private TellapicNode       source;
	private TellapicNodeAction action;
	private long               when;
	private int                whatAction;
	
	public TellapicNodeActionEvent(TellapicNode src, TellapicNodeAction action, int whatAction) {
		if (whatAction != ADD_ACTION && whatAction != REMOVE_ACTION)
			throw new IllegalArgumentException("Wrong whatAction argument.");
		if (src == null)
			throw new IllegalArgumentException("Source cannot be null.");
		if (action == null)
			throw new IllegalArgumentException("Action cannot be null");
		
		this.whatAction = whatAction;
		this.when = System.currentTimeMillis();
		this.source = src;
		this.action = action;
	}
	
	/**
	 * @return the source
	 */
	public TellapicNode getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(TellapicNode source) {
		this.source = source;
	}
	/**
	 * @return the action
	 */
	public TellapicNodeAction getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(TellapicNodeAction action) {
		this.action = action;
	}

	/**
	 * @return the when
	 */
	public long getWhen() {
		return when;
	}

	/**
	 * @return the whatAction
	 */
	public int getWhatAction() {
		return whatAction;
	}
}