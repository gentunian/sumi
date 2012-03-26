/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ar.com.tellapic.sumi.system.FileInfo;
import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionLabel;
import ar.com.tellapic.sumi.treetable.TellapicNode;

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
public class LinuxFileInfo implements FileInfo {
    private String name;
    private Icon icon;
    private String owner;
    private Icon propertyIcon = new ImageIcon(LinuxFileInfo.class.getResource("/icons/property.png"));
    
    public LinuxFileInfo(String name) {
        this.name = name;
        this.icon = new ImageIcon(LinuxFileInfo.class.getResource("/icons/file.png"));
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.FileInfo#getIcon()
     */
    @Override
    public Icon getIcon() {
        return icon;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.FileInfo#getOwner()
     */
    @Override
    public String getOwner() {
        return owner;
    }
    
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.FileInfo#getName()
     */
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }

    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.treetable.TellapicNodeCreatorInterface#getObjectRootNode()
     */
    @Override
    public TellapicNode getObjectRootNode() {
        TellapicNode rootNode = new TellapicNode(this, getIcon());
        TellapicNode nameNode = new TellapicNode("File Name", propertyIcon, new DefaultTellapicNodeActionLabel(getName(), false));
        rootNode.add(nameNode);
        return rootNode;
    }
}
