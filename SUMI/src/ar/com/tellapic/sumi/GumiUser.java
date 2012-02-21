package ar.com.tellapic.sumi;

import java.util.Observable;

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
public class GumiUser extends Observable {
	
	public static final int REMOTE_CHANGED     = -10;
	public static final int SELECTION_CHANGED  = -9;
	public static final int VISIBILITY_CHANGED = -8;
	public static final int NAME_SET           = -7;
	public static final int USER_ID_SET        = -6;
	
	private int              userId;
	private String           name;
	private boolean          visible;
	private boolean          selected;
	private boolean          remote;

	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param visible
	 * @param remote
	 * @param selected
	 */
	public GumiUser(int id, String name, boolean visible, boolean remote, boolean selected) {
		this.userId   = id;
		this.name     = name;
		this.visible  = visible;
		this.remote   = remote;
		this.selected = selected;
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 */
	public GumiUser(int id, String name) {
		this(id, name, true, false, false);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param visible
	 */
	public GumiUser(int id, String name, boolean visible) {
		this(id, name, visible, false, false);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param visible
	 * @param remote
	 */
	public GumiUser(int id, String name, boolean visible, boolean remote) {
		this(id, name, visible, remote, false);
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
		setChanged();
		notifyObservers(new Object[]{USER_ID_SET});
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers(new Object[]{NAME_SET});
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		if (this.visible != visible)
			setChanged();
		
		this.visible = visible;
		notifyObservers(new Object[]{VISIBILITY_CHANGED});
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		if (this.selected != selected)
			setChanged();
		
		this.selected = selected;
		notifyObservers(new Object[]{SELECTION_CHANGED});
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param remote the remote to set
	 */
	public void setRemote(boolean remote) {
		if (this.remote != remote)
			setChanged();
		
		this.remote = remote;
		notifyObservers(new Object[]{REMOTE_CHANGED});
	}
	
	/**
	 * @return the remote
	 */
	public boolean isRemote() {
		return remote;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
