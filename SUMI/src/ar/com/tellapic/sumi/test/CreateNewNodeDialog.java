/**
 * 
 */
package ar.com.tellapic.sumi.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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
public class CreateNewNodeDialog extends JDialog {
    public int EXIT_STATUS = 0;

    private static final long serialVersionUID = 1L;

    private static final String DATA_COLUMN_TYPE_COMPONENT = "DCTC";
    private final Action newDataAction       = new NewColumnDataAction();
    private final Action removeDataAction    = new RemoveColumnDataAction();
    private final Action cancelDialogAction  = new CancelDialogAction();
    private final Action okDialogAction      = new OkDialogAction();
    private final Action newChildAction      = new NewChildAction();
    private final Action setActionTypeAction = new SetActionTypeAction();

    private final JPanel contentPanel = new JPanel();
    private JButton newChildButton = new JButton("New button");
    private JTextField userField;
    private FormLayout layout;
    private int rowOffset;

    /* This list seems necessary for the FormLayout */
    /* as you need to remove the components from the */
    /* layout BEFORE removing a row. */
    /* I didn't find any get method for getting */
    /* the components currently laid out. */
    /* So the list has a component list. */
    /* Each index in the outer list is a layout row. */
    private List<List<Component>> componentsList;

    private DialogInfo data;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CreateNewNodeDialog dialog = new CreateNewNodeDialog(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param treeTable 
     */
    public CreateNewNodeDialog(String parentName /*GumiNode parent*/) {
        String str = "New Node Information";
        if (parentName != null)
            str += " for Node " + parentName;
        setTitle(str);
        initComponents();
        componentsList  = new ArrayList<List<Component>>();
        data = new DialogInfo();
    }

    /**
     */
    private void initComponents() {
        setModal(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(CreateNewNodeDialog.class.getResource("/icons/user.png")));
        setBounds(100, 100, 441, 318);
        setMinimumSize(new Dimension(450,300));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        /* Create the form layout */
        layout = new FormLayout(new ColumnSpec[] {
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,},
                new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,});

        /* Set the form layout in the content pane */
        contentPanel.setLayout(layout);

        /* Create a label to show a tip */
        JLabel lblTipUseBinds = new JLabel("Tip: use binds Ctrl+N and Ctrl+R to add or remove column data respectively.");
        lblTipUseBinds.setFont(new Font("DejaVu Sans", Font.ITALIC, 10));
        lblTipUseBinds.setIcon(new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/information-balloon.png")));

        /* Add the label to the view */
        contentPanel.add(lblTipUseBinds, "2, 2, 6, 1");

        /* Create a label for the user field */
        JLabel lblUserName = new JLabel("User name:");

        /* Add the label to the view */
        contentPanel.add(lblUserName, "2, 4, right, default");

        /* Create the user field */
        userField = new JTextField();
        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                newChildButton.setEnabled(userField.getText().length() > 0);
                //				if (node == null) {
                //					node = new GumiNode(userField.getText());
                //					if (parentNode != null)
                //						parentNode.add(node);
                //				}
                //				else
                //					node.setUserObject(userField.getText());
            }
        });
        userField.setColumns(10);

        /* Add the user field to the view */
        contentPanel.add(userField, "4, 4, fill, default");

        /* Create the button for adding column data */

        /* Add the button to the view */

        /* Create a separator */
        JButton button = new JButton();
        button.setHideActionText(true);
        button.setAction(newDataAction);
        contentPanel.add(button, "8, 4");

        JSeparator separator = new JSeparator();

        /* Add the separator to the view */
        contentPanel.add(separator, "2, 6, 5, 1");

        /* Create the Button Panel */
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.setLayout(new FormLayout(new ColumnSpec[] {
                ColumnSpec.decode("max(4dlu;pref)"),
                ColumnSpec.decode("100px"),
                ColumnSpec.decode("4dlu:grow"),
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
                FormFactory.PREF_COLSPEC,
                FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
                new RowSpec[] {
                FormFactory.LINE_GAP_ROWSPEC,
                RowSpec.decode("28px"),}));

        /* Configure the button for adding childs. Sets its state to disabled */
        newChildButton.setAction(newChildAction);
        newChildButton.setEnabled(false);

        /* Add the new child button to the button panel */
        buttonPane.add(newChildButton, "2, 2, left, top");

        /* Create the OK dialog button */
        JButton okButton = new JButton("OK");
        okButton.setAction(okDialogAction);
        okButton.setActionCommand("OK");

        /* Add the OK dialog button to the button panel */
        buttonPane.add(okButton, "4, 2, left, top");

        /* Create the Cancel dialgo button */
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setAction(cancelDialogAction);
        cancelButton.setActionCommand("Cancel");

        /* Add the cancel dialog button to the button panel */
        buttonPane.add(cancelButton, "6, 2, right, top");

        /* Assign keyboards bindings */
        createKeyboardMappings();

        /* Request focus */
        addComponentListener(new ComponentAdapter(){
            public void componentShown(ComponentEvent e) {
                userField.requestFocus();
            }
        });
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblUserName, userField, button, buttonPane, okButton, cancelButton}));
        /* Final configurations */
        getRootPane().setDefaultButton(okButton);
        rowOffset = layout.getRowCount();
    }

    /**
     * 
     */
    private void createKeyboardMappings() {
        ActionMap actionMap = rootPane.getActionMap();
        InputMap inputMap   = rootPane.getInputMap();
        /**/
        actionMap.put(okDialogAction.getValue(Action.NAME), okDialogAction);
        actionMap.put(cancelDialogAction.getValue(Action.NAME), cancelDialogAction);
        actionMap.put(removeDataAction.getValue(Action.NAME), removeDataAction);
        actionMap.put(newDataAction.getValue(Action.NAME), newDataAction);
        /**/
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), newDataAction.getValue(Action.NAME));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelDialogAction.getValue(Action.NAME));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), removeDataAction.getValue(Action.NAME));
        /**/
        rootPane.setActionMap(actionMap);
        rootPane.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMap);
    }

    /**
     * 
     */
    private void createRow() {
        /* Gets the component count for key-value pairs */
        int cc = componentsList.size();

        /* Appends rows in the layout for later use */
        layout.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
        layout.appendRow(FormFactory.DEFAULT_ROWSPEC);

        /* Gets the last row in the layout */
        int lastRow = layout.getRowCount();

        /* Creates a label for the data column field */
        JLabel dataLabel = new JLabel("Column "+(cc+1)+":");

        /* Adds the label into the layout */
        contentPanel.add(dataLabel, "2, "+String.valueOf(lastRow)+", right, center");

        /* Creates the data column field */
        JTextField dataField = new JTextField();

        /* Adds the data column field into the layout */
        contentPanel.add(dataField, "4, "+String.valueOf(lastRow)+", fill, center");

        /* Creates a button for removing data column fields */
        JButton removeDataButton = new JButton();
        removeDataButton.setAction(removeDataAction);
        removeDataButton.setHideActionText(true);

        /* Adds the button to the layout */
        contentPanel.add(removeDataButton, "8, "+String.valueOf(lastRow)+", center, bottom");

        /* Creates a button for setting the data column type */
        JButton typeButton = new JButton();
        typeButton.setAction(setActionTypeAction);
        typeButton.setHideActionText(true);

        /* Adds the button to the layout */
        contentPanel.add(typeButton, "6, "+String.valueOf(lastRow)+", center, bottom");

        /* Call pack for repainting and rearranging widgets */
        pack();

        List<Component> list = new ArrayList<Component>();
        list = new ArrayList<Component>();
        list.add(dataField);
        list.add(removeDataButton);
        list.add(dataLabel);
        list.add(typeButton);
        JLabel defaultType = new JLabel();
        defaultType.setName(DATA_COLUMN_TYPE_COMPONENT);
        list.add(defaultType);
        componentsList.add(list);
        dataField.requestFocus();
    }

    /**
     * 
     */
    private void removeRow(int index) {
        List<Component> components = componentsList.remove(index);
        for(Component component : components)
            contentPanel.remove(component);
        components.removeAll(components);

        for(List<Component> c : componentsList) {
            for(Component label : c) {
                if (label instanceof JLabel)
                    ((JLabel)label).setText("Column "+(componentsList.indexOf(c)+1));
            }
        }

        int rowIndex = rowOffset + 1 + index * 2;
        layout.removeRow(rowIndex);
        layout.removeRow(rowIndex);

        if (!componentsList.isEmpty()) {
            List<Component> c = componentsList.get(componentsList.size() - 1);
            for(Component component : c) {
                if (component instanceof JTextField)
                    component.requestFocus();
            }
        }

        repaint();
        pack();
    }

    /**
     * 
     * @return
     */
    public String getNodeName() {
        return userField.getText();
    }

    /**
     * 
     * @return
     */
    public DialogInfo getDialogInfo() {
        for(List<Component> components : componentsList) {
            String type = null;
            String value = null;
            for(Component component : components) {
                String name = component.getName();
                if (name != null && name.equals(DATA_COLUMN_TYPE_COMPONENT)) {
                    if (component instanceof JLabel) {
                        type = "text";
                    } else if (component instanceof JComboBox) {
                        type = "combo";
                    } else if (component instanceof JCheckBox) {
                        type = "boolean";
                    } else if (component instanceof JButton) {
                        type = "Button";
                    }
                } else if (component instanceof JTextField) {
                    value = ((JTextField)component).getText();
                }
            }
            data.addColumnData(type, value);
        }
        data.setNodeName(userField.getText());

        return data;
    }

    /**
     * 
     * @param source
     * @return
     */
    private int getComponentRowIndex(Component source) {
        for(List<Component> components : componentsList) {
            if (components.indexOf(source) != -1) {
                return componentsList.indexOf(components);
            }
        }
        return -1;
    }

    /**
     * 
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
    private class CancelDialogAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public CancelDialogAction() {
            super("Cancel", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/cross-button.png")));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EXIT_STATUS = -1;
            dispose();
        }
    }

    /**
     * 
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
    private class OkDialogAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public OkDialogAction() {
            super("Ok", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/tick-button.png")));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<List<Component>> removeList = new ArrayList<List<Component>>();
            for(int i = 0; i < componentsList.size(); i++) {
                List<Component> components = componentsList.get(i);
                for(Component component : components) {
                    if (component instanceof JTextField && ((JTextField)component).getText().isEmpty()) {
                        removeList.add(components);
                        break; //AHAHAHHAAH!
                    }
                }
            }
            componentsList.removeAll(removeList);
            dispose();
        }
    }

    /**
     * 
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
    private class NewColumnDataAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public NewColumnDataAction() {
            super("NewColumnDataAction", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/plus-button.png")));
            putValue(SHORT_DESCRIPTION, "Adds a new child field for this user (Ctrl+N).");
        }
        public void actionPerformed(ActionEvent e) {
            createRow();
        }
    }

    /**
     * 
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
    private class RemoveColumnDataAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public RemoveColumnDataAction() {
            super("RemoveColumnDataAction", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/minus-button.png")));
            putValue(SHORT_DESCRIPTION, "Removes this data field (Ctrl+R).");
        }
        public void actionPerformed(ActionEvent e) {
            Component object = (Component) e.getSource();
            int index = 0;
            if (!(e.getSource() instanceof JButton)) {
                object =  KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                if (!(object instanceof JTextField))
                    return;
            }

            index = getComponentRowIndex(object);

            for(List<Component> components : componentsList) {
                int i = components.indexOf(object);
                if (i != -1) {
                    index = componentsList.indexOf(components);
                    break; // AHAHHAHAAH!
                }
            }
            removeRow(index);
        }
    }

    /**
     * 
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
    private class NewChildAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public NewChildAction() {
            super("Add child", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/user--plus.png")));
            putValue(SHORT_DESCRIPTION, "Some short description");
        }
        public void actionPerformed(ActionEvent e) {
            CreateNewNodeDialog dialog = new CreateNewNodeDialog(userField.getText());
            dialog.setVisible(true);

            if (EXIT_STATUS < 0)
                return;

            DialogInfo child = dialog.getDialogInfo();
            data.addChild(child);
        }
    }

    /**
     * 
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
    private class SetActionTypeAction extends AbstractAction implements ItemListener{
        private static final long serialVersionUID = 1L;
        public SetActionTypeAction() {
            super("Set Type", new ImageIcon(CreateNewNodeDialog.class.getResource("/icons/property.png")));
            putValue(SHORT_DESCRIPTION, "Set the type");
        }

        public void actionPerformed(ActionEvent e) {
            Component source = (Component) e.getSource();
            JPopupMenu menu = new JPopupMenu();
            ButtonGroup group = new ButtonGroup();
            JCheckBoxMenuItem labelItem = new JCheckBoxMenuItem("text");
            JCheckBoxMenuItem comboItem = new JCheckBoxMenuItem("combo");
            JCheckBoxMenuItem checkItem = new JCheckBoxMenuItem("boolean");
            JCheckBoxMenuItem buttonItem = new JCheckBoxMenuItem("Button");
            labelItem.addItemListener(this);
            comboItem.addItemListener(this);
            checkItem.addItemListener(this);
            buttonItem.addItemListener(this);
            group.add(labelItem);
            group.add(comboItem);
            group.add(checkItem);
            group.add(buttonItem);
            menu.add(labelItem);
            menu.add(comboItem);
            menu.add(checkItem);
            menu.add(buttonItem);
            menu.show(source, 10, 10);

            @SuppressWarnings("unused")
            int index = getComponentRowIndex(source);


        }

        /* (non-Javadoc)
         * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            //			Component         component = null;
            JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
            JPopupMenu        menu = (JPopupMenu) source.getParent();
            int index = getComponentRowIndex(menu.getInvoker());
            List<Component> list = componentsList.get(index);

            if (e.getStateChange() == ItemEvent.DESELECTED)
                return;

            String type = source.getText();
            String value = "";
            for(Component component : list)
                if (component instanceof JTextField)
                    value = ((JTextField)component).getText();
            data.addColumnData(type, value);

            //			if (source.getText().equals("text")) {
            //				component = new JLabel();
            //			} else if (source.getText().equals("combo")) {
            //				component = new JComboBox();
            //			} else if (source.getText().equals("boolean")) {
            //				component = new JCheckBox();
            //			} else if (source.getText().equals("Button")) {
            //				component = new JButton();
            //			}
            //			component.setName(DATA_COLUMN_TYPE_COMPONENT);

            boolean done = false;
            int i = 0;
            for(i = 0; !done && i < list.size(); i++) {
                String name = list.get(i).getName();
                done = (name != null && name.equals(DATA_COLUMN_TYPE_COMPONENT));
                if (done)
                    list.remove(i);	
            }

            //			list.add(component);
        }
    }
}
