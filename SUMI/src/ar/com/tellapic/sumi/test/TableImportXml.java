/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

import ar.com.tellapic.sumi.GumiUser;
import ar.com.tellapic.sumi.GumiUserManager;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionColor;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCombo;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;
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
public class TableImportXml extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Document dom;
	private DocumentTraversal dt;
	private GumiUserManager model;
	
	/**
	 * @param model 
	 * 
	 */
	public TableImportXml(GumiUserManager model) {
		super("Import XML" ,new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/table-import.png")));
		putValue(AbstractAction.SHORT_DESCRIPTION, "Import table from XML file");
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parseXmlFile();
		parseDocument();
	}
	
	/**
	 * from some tutorial 
	 */
	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			

			DocumentBuilder db = dbf.newDocumentBuilder();

			dom = db.parse(TableImportXml.class.getResourceAsStream("testtree.xml")); 
			
			dt = (DocumentTraversal) dom;
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void parseDocument(){
		Element element = dom.getDocumentElement();
		TreeWalker tw = dt.createTreeWalker(element, NodeFilter.SHOW_ELEMENT, null, false);
		Node node = tw.getRoot().getFirstChild();
		TellapicNode parentNode = (TellapicNode) model.getRoot();
		test(node, parentNode, "TellapicNode");
	}
	
	/**
	 * 
	 * @param node
	 * @param parent
	 * @param elemName
	 */
	private void test(Node node, TellapicNode parent, String elemName) {
		while(node != null) {
			if (node instanceof Element && node.getNodeName().equals(elemName)) {
				TellapicNode childNode = getTellapicNode((Element) node);
				model.insertNodeInto(childNode, parent, parent.getChildCount());
				test(node.getFirstChild(), childNode, elemName);
			}
			node = node.getNextSibling();
		}
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	private TellapicNode getTellapicNode(Element element) {
		List<TellapicNodeAction> actions = new ArrayList<TellapicNodeAction>();

		NodeList nl = element.getElementsByTagName("TellapicNodeAction");
		if(nl != null) {
			for(int i = 0; i < nl.getLength(); i++) {
				
				Element el = (Element)nl.item(i);
				if (el.getParentNode().equals(element))
					actions.add(getTellapicNodeAction(el));
			}
		}

		String name = element.getAttribute("name");
		String iconPath = element.getAttribute("icon_src");
		int id = Integer.parseInt(element.getAttribute("id"));
		GumiUser user = new GumiUser(id, name);
		TellapicNode node = new TellapicNode(user, new ImageIcon(TableImportXml.class.getResource(iconPath)));
		for(TellapicNodeAction action : actions)
			node.addAction(action);
		return node;
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	private TellapicNodeAction getTellapicNodeAction(Element element) {
		String name = element.getAttribute("name");
		String type = element.getAttribute("type");
		String editable = element.getAttribute("editable");
		boolean editableValue = editable.length() == 0 ? true: Boolean.parseBoolean(editable);
		TellapicNodeAction action = null;
		
		if (type.equals("boolean")) {
			action = new DefaultTellapicNodeActionCheckBox(name);
		} else if (type.equals("combo")) {
			Object[] data = getComboData(element); 
			action = new DefaultTellapicNodeActionCombo(name, data);
		} else if (type.equals("color")) {
			action = new DefaultTellapicNodeActionColor(name, getColorData(element), editableValue);
		} else if (type.equals("text")) {
			
			
			action = new DefaultTellapicNodeActionLabel(name, editableValue);
		}
		
		return action;
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	private Color getColorData(Element element) {
		Color color = Color.white;
		NodeList nl = element.getElementsByTagName("ColorData");
		if(nl != null) {
			Element el = (Element)nl.item(0);
			color = Color.decode(el.getFirstChild().getNodeValue());
		}
		return color;
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	private Object[] getComboData(Element element) {
		Object[] data = null;
		NodeList nl = element.getElementsByTagName("ComboData");
		if(nl != null) {
			data = new Object[nl.getLength()];
			for(int i = 0; i < nl.getLength(); i++) {
				Element el = (Element)nl.item(i);
				data[i] = el.getFirstChild().getNodeValue();
			}
		}
		return data;
	}
}
