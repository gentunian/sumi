///**
// * 
// */
//package ar.com.tellapic.sumi.test;
//
//import java.awt.Component;
//
//import javax.swing.JLabel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
//import ar.com.tellapic.sumi.treetable.TellapicNodeAction;
//import ar.com.tellapic.sumi.treetable.renderer.DefaultTellapicLabelRenderer;
//
///**
// *   Copyright (c) 2010 Sebastián Treu.
// *
// *   This program is free software; you can redistribute it and/or modify
// *   it under the terms of the GNU General Public License as published by
// *   the Free Software Foundation; version 2 of the License.
// *
// *   This program is distributed in the hope that it will be useful,
// *   but WITHOUT ANY WARRANTY; without even the implied warranty of
// *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// *   GNU General Public License for more details.
// *
// * @author
// *         Sebastian Treu 
// *         sebastian.treu(at)gmail.com
// *
// */
//public class ItemsRenderer extends DefaultTellapicLabelRenderer {
//
//	private static final long serialVersionUID = 1L;
//	private JLabel label;
//	private JScrollPane pane;
//	
//	public ItemsRenderer() {
//		label = new JLabel();
//		pane = new JScrollPane(label);
//	}
//	
//	@Override
//	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//		JScrollPane s = (JScrollPane) table.getCellEditor(row, column).getTableCellEditorComponent(table, value, isSelected, row, column);
//		int hsv = s.getHorizontalScrollBar().getValue();
//		int vsv = s.getVerticalScrollBar().getValue();
//		pane.getHorizontalScrollBar().setValue(hsv);
//		pane.getVerticalScrollBar().setValue(vsv);
//		return pane;
//	}
//	
//	/* (non-Javadoc)
//	 * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
//	 */
//	@Override
//	public void configureRenderer(TellapicNodeAction action, JTable table, boolean isSelected) {
//		
//	}
//
//    /* (non-Javadoc)
//     * @see ar.com.tellapic.sumi.treetable.renderer.TellapicTableCellRenderer#configureRenderer(java.awt.Component, ar.com.tellapic.sumi.treetable.TellapicNodeAction, javax.swing.JTable, boolean)
//     */
//    @Override
//    public void configureRenderer(Component component,
//            TellapicNodeAction action, JTable table, Object value, boolean isSelected, boolean hasFocus) {
//        // TODO Auto-generated method stub
//        String[] data = (String[]) action.getData();
//		String html = "<html>Items:<br>";
//		for(int i = 0; i < data.length; i++)
//			html +="<html><li>"+data[i];
//		label.setText(html);
//    }
//}
