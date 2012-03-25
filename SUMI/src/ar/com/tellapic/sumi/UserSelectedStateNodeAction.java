/**
 * 
 */
package ar.com.tellapic.sumi;

import ar.com.tellapic.sumi.treetable.DefaultTellapicNodeActionCheckBox;

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
public class UserSelectedStateNodeAction extends DefaultTellapicNodeActionCheckBox {

    public UserSelectedStateNodeAction() {
        super("", false);
    }
    
    @Override
    public Object getValue() {
        if (node != null) {
            SumiUser user = (SumiUser) node.getParent().getUserObject();
            return user.isSelected();
        } else
            return super.getValue();
    }
//    This action is not editable. This will never happen
//    @Override
//    public void setValue(Object value) {
//        if (node != null) {
//            SumiUser user = (SumiUser) node.getParent().getUserObject();
//            user.setSelected((Boolean) value);
//        } else
//            super.setValue(value);
//    }
}
