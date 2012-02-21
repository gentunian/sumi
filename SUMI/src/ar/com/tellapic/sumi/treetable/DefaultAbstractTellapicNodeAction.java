/**
 * 
 */
package ar.com.tellapic.sumi.treetable;

import java.awt.event.ActionEvent;

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
public abstract class DefaultAbstractTellapicNodeAction implements TellapicNodeAction {

	protected AbstractAction  action;	
	protected String          rendererKey;
	protected String          editorKey;
	protected TellapicNode    node;
	protected Object          value;
	protected String          name;
	protected boolean         editable;
	
	/**
	 * 
	 * @param node
	 * @param action
	 * @param eKey
	 * @param rKey
	 * @param editable
	 */
	public DefaultAbstractTellapicNodeAction(TellapicNode node, AbstractAction action, String eKey, String rKey, boolean editable) {
		if (eKey == null || rKey == null)
			throw new IllegalArgumentException("eKey and rKey must not be null.");
		
		this.name = "";
		if (action != null) {
			this.name = (String) action.getValue(AbstractAction.NAME);
			if (this.name == null)
				throw new IllegalArgumentException("actions must have AbstractAction.NAME property set");
		}
		this.action= action;
		this.editorKey = eKey;
		this.rendererKey = rKey;
		this.node = node;
		this.editable = editable;
	}
	
	/**
	 * 
	 * @param action
	 * @param eKey
	 * @param rKey
	 * @param editable
	 */
	public DefaultAbstractTellapicNodeAction(AbstractAction action, String eKey, String rKey, boolean editable) {
		this(null, action, eKey, rKey, editable);
	}
	
	/**
	 * 
	 * @param name
	 * @param eKey
	 * @param rKey
	 * @param editable
	 */
	public DefaultAbstractTellapicNodeAction(String name, String eKey, String rKey, boolean editable) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null.");
		
		if (eKey == null || rKey == null)
			throw new IllegalArgumentException("eKey and rKey must not be null.");
		
		this.name = name;
		this.editorKey = eKey;
		this.rendererKey = rKey;
		this.node = null;
		this.action = null;
		this.editable = editable;
	}
	
	/**
	 * 
	 */
	public void setNode(TellapicNode node) {
		this.node = node;
	}
	
	/**
	 * @return 
	 * 
	 */
	public TellapicNode getNode() {
		return node;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#getRendererComponent()
	 */
	@Override
	public String getRendererKey() {
		return rendererKey;
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#getEditor()
	 */
	@Override
	public String getEditorKey() {
		return editorKey;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.TellapicNodeAction#performAction()
	 */
	@Override
	public void performAction() {
		if (action == null)
			return;
		ActionEvent e = new ActionEvent(this, 0, (String)action.getValue(AbstractAction.ACTION_COMMAND_KEY));
		action.actionPerformed(e);
	}
	
	/**
	 * @return the action
	 */
	public AbstractAction getAbstractAction() {
		return action;
	}
	
	
	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getValue()
	 */
	@Override
	public Object getValue() {
		return value;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#getNodeActionName()
	 */
	@Override
	public String getNodeActionName() {
		return name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.tellapic.gumi.treetable.TellapicNodeAction#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return editable;
	}
}
