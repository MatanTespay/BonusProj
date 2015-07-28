/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import init.ComboItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.ListDataEvent;

/**
 *
 * @author asus
 */
public final class QueryCombobox extends DefaultComboBoxModel {
    /*
     the combobox of the model
     */

    private final JComboBox<ComboItem> myCmb;
    /*
     my father combobox
     */
    private final JComboBox<ComboItem> fatherCmb;
    /*
     the combo item selected
     */
    private ComboItem selectedItem;
    /*
     the statement that fills the combobox - 1st col = key, 2nd col = value
     */
    private final PreparedStatement st;
    /*
     the class of they ComboItem's key
     */
    private final Class keyClass;
    /*
     the items of the model
     */
    private ArrayList<ComboItem> items;
    /**
     * button the need to correspond to the comboBox state
     */
    private Collection<JButton> buttons;
    /*
     combobox that depends on another combobox
     */

    /**
     *
     * @param cmb the value of cmb
     * @param fatherCmb the value of fatherCmb
     * @param keyClass the value of keyClass
     * @param stWhere the value of stWhere
     * @throws java.sql.SQLException
     */
    public QueryCombobox(JComboBox<ComboItem> cmb, JComboBox<ComboItem> fatherCmb, Class keyClass, PreparedStatement stWhere) throws SQLException {
        this.myCmb = cmb;
        this.fatherCmb = fatherCmb;
        this.st = stWhere;
        this.keyClass = keyClass;
        this.items = new ArrayList<>();

        this.fatherCmb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryCombobox fatherModel = (QueryCombobox) fatherCmb.getModel();
                    ComboItem selectedFatherItem = (ComboItem) fatherModel.getSelectedItem();

                    //ComboItem selectedFatherItem = (ComboItem) getSelectedItem();
                    Object key = selectedFatherItem.getKey();
                    String value = selectedFatherItem.getLabel();

                    HelperClass.setPSParamByType(st, key, value);

//                    if (keyClass.equals(Integer.class)) {
//                        st.setInt(1, (Integer) key);
//                    } else if (keyClass.equals(String.class)) {
//                        st.setString(1, value);
//                    } else if (keyClass.equals(Short.class)) {
//                        st.setString(1, value);
//                    }
                    //update this combo after father changes
                    fill();

                    if (items.size() > 0) {
                        myCmb.setEnabled(true);
                        
                    } else {
                        myCmb.setEnabled(false);
                    }

                } catch (SQLException ex) {

                }
            }
        });
        try {
            QueryCombobox fatherModel = (QueryCombobox) fatherCmb.getModel();
            ComboItem selectedFatherItem = (ComboItem) fatherModel.getSelectedItem();
            Object key = selectedFatherItem.getKey();
            String value = selectedFatherItem.getLabel();

            HelperClass.setPSParamByType(st, key, value);

//            if (fatherModel.getKeyClass().equals(Integer.class)) {
//                st.setInt(1, (int) key);
//            } else if (fatherModel.getKeyClass().equals(String.class)) {
//                st.setString(1, value);
//            } else if (fatherModel.getKeyClass().equals(Short.class)) {
//                st.setString(1, value);
//            }
        } catch (SQLException ex) {

        }
        fill();

        if (items.size() > 0) {
            selectedItem = items.get(0);
        }
    }

    public void bindButtons(Collection<JButton> buttons) {
        this.buttons = buttons;
    }

    public JComboBox<ComboItem> getFatherCmb() {
        return fatherCmb;
    }

    /*
     combobox that is based dirctly to a DB
     */
    public QueryCombobox(JComboBox<ComboItem> cmb, Class keyClass, PreparedStatement st) throws SQLException {
        this.myCmb = cmb;
        this.keyClass = keyClass;
        this.st = st;

        this.fatherCmb = null;
        this.items = new ArrayList<>();
        fill();

        if (items.size() > 0) {
            selectedItem = items.get(0);
        }
    }

    public void fill() throws SQLException {
        if (st == null) {
            return;
        }

        ArrayList<ComboItem> tempOldItems = new ArrayList<>();
        ResultSet rs;

        try {
            items.clear();
            rs = st.executeQuery();

            int keyColumn = 1;
            int valueColumn = (rs.getMetaData().getColumnCount() == 1) ? 1 : 2;

            while (rs.next()) {
                Object key = utils.HelperClass.getObjectFromResultSet(rs, getKeyClass().getSimpleName(), keyColumn);;
                String value = rs.getObject(valueColumn).toString();
                addElement(new ComboItem(key, value));
            }
            

        } catch (SQLException ex) {
            // rollback
            items = tempOldItems;
            throw new SQLException(ex);
        }
    }

    @Override
    public void addElement(Object object) {
        items.add((ComboItem) object);
        Collections.sort(items);
        int index = getIndexOf(object);
        fireIntervalAdded(this, index, index);
        setSelectedItem(object);
//        if (items.size() == 1 && items == null) {
//            setSelectedItem(object);
//        }
    }

    @Override
    public void removeElementAt(int index) {
        int selected = getIndexOf(selectedItem);
        if (selected == index) // choose a new selected item
        {
            if (selected > 0) {
                setSelectedItem(getElementAt(selected - 1));
            } else {
                setSelectedItem(getElementAt(selected + 1));
            }
        }
        items.remove(index);
        fireIntervalRemoved(this, index, index);
        Collections.sort(items);
    }

    @Override
    public void insertElementAt(Object object, int index) {
        items.add(index, (ComboItem) object);
        fireIntervalAdded(this, index, index);
        Collections.sort(items);
    }

    @Override
    public void removeElement(Object object) {
        int index = getIndexOf(object);
        if (index != -1) {
            removeElementAt(index);
        }
        Collections.sort(items);
    }

    @Override
    public void removeAllElements() {
        selectedItem = null;
        int size = getSize();
        if (size > 0) {
            items.clear();
            fireIntervalRemoved(this, 0, size - 1);
        }
    }

    @Override
    public int getSize() {
        return items.size();
    }

    /**
     * Sets the selected item for the model and sends a {@link ListDataEvent} to
     * all registered listeners. The start and end index of the event is set to
     * -1 to indicate the model's selection has changed, and not its contents.
     *
     * @param object the new selected item (<code>null</code> permitted).
     */
    @Override
    public void setSelectedItem(Object object) {
        // No item is selected and object is null, so no change required.
        if (selectedItem == null && object == null) {
            return;
        }

        // object is already selected so no change required.
        if (selectedItem != null && selectedItem.equals(object)) {
            return;
        }

        // Simply return if object is not in the list.
        if (object != null && getIndexOf(object) == -1) {
            return;
        }

        // Here we know that object is either an item in the list or null.
        // Handle the three change cases: selectedItem is null, object is
        // non-null; selectedItem is non-null, object is null;
        // selectedItem is non-null, object is non-null and they're not
        // equal.
        int selectedIndex = getIndexOf(object);
        selectedItem = (ComboItem) getElementAt(selectedIndex);
        fireContentsChanged(this, -1, -1);
    }

    /**
     * Returns the selected item.
     *
     * @return The selected item (possibly <code>null</code>).
     */
    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Returns the element at the specified index in the model's item list.
     *
     * @param index the element index.
     *
     * @return The element at the specified index in the model's item list, or
     * <code>null</code> if the <code>index</code> is outside the bounds of the
     * list.
     */
    @Override
    public Object getElementAt(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    /**
     * Returns the index of the specified element in the model's item list.
     *
     * @param object the element.
     *
     * @return The index of the specified element in the model's item list.
     */
    @Override
    public int getIndexOf(Object object) {
        for (ComboItem item : items) {
            if (item.equals(object)) {
                return items.indexOf(item);
            }
        }
        return -1;
    }

    /**
     * @return the keyClass
     */
    public Class getKeyClass() {
        return keyClass;
    }

//    private void handleSelectionAndButtons() {
//
//        if (getSize() > 0) {
//
//            if (buttons != null) {
//                for (JButton button : buttons) {
//                    button.setEnabled(true);
//                }
//            }
//        } else {
//
//            if (buttons != null) {
//                for (JButton button : buttons) {
//                    button.setEnabled(false);
//                }
//            }
//        }
//
//    }
}
