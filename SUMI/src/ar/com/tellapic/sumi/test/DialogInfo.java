/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
public class DialogInfo {
	
	private String                          nodeName;
	private List<Map.Entry<String, String>> columnData;
	private DialogInfo                      child;
	
	/**
	 * 
	 */
	public DialogInfo() {
		this(null);
	}
	
	/**
	 * 
	 * @param name
	 */
	public DialogInfo(String name) {
		nodeName = name;
		columnData = new ArrayList<Map.Entry<String,String>>();
		child = null;
	}
	
	/**
	 * 
	 * @param type
	 * @param value
	 */
	public void addColumnData(String type, String value) {
		columnData.add(new AbstractMap.SimpleEntry<String, String>(type, value));
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Entry<String, String>> getColumnData() {
		return columnData;
	}
	
	/**
	 * 
	 * @param child
	 */
	public void addChild(DialogInfo child) {
		System.out.println("Adding child "+child+" to parent "+nodeName);
		this.child = child;
	}

	/**
	 * 
	 * @return
	 */
	public DialogInfo getChild() {
		return child;
	}
	
	/**
	 * @param text
	 */
	public void setNodeName(String text) {
		nodeName = text;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNodeName() {
		return nodeName;
	}
}
