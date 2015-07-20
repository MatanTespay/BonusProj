/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import static init.MainClass.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import utils.HelperClass;

/**
 *
 * @author asus
 */
public class QueryComboBox {

    private final JComboBox cmb;
    private final String sqlQuery;
    private final Class keyClass;
    private final String keyColumn;
    private final String valueColumn;
    private Object variable;
    private TreeSet<ComboItem> items;
    
    private JTable table;
    private int tableKeyColumn;
    private int tableValueColumn;

    public QueryComboBox(JComboBox cmb, String sqlQuery, Class keyClass,
            String keyColumn, String valueColumn, Object variable) {
        this.cmb = cmb;
        this.sqlQuery = sqlQuery;
        this.keyClass = keyClass;
        this.keyColumn = keyColumn;
        this.valueColumn = valueColumn;
        this.variable = variable;
        
        this.items = new TreeSet<>();
    }

    public QueryComboBox(JComboBox cmb, String sqlQuery, Class keyClass,
            String keyColumn, String valueColumn) {
        this(cmb, sqlQuery, keyClass, keyColumn, valueColumn, null);
    }

    public void fill() {
        ResultSet rs;
        
        try {
            if (variable != null) {
                // there is a variable
                PreparedStatement st;
                st = con.prepareStatement(sqlQuery);
                switch (variable.getClass().getSimpleName()) {
                    case "Integer":
                        st.setInt(1, (Integer) variable);
                        break;
                    case "Double":
                        st.setDouble(1, (Double) variable);
                        break;
                    case "String":
                        st.setString(1, (String) variable);
                        break;
                    case "Timestamp":
                        st.setString(1, variable.toString());
                        break;
                    default:
                        System.out.println("add another class type");
                }
                rs = st.executeQuery();
            } else {
                // no variable
                Statement st = con.createStatement();
                rs = st.executeQuery(sqlQuery);
            }

            while (rs.next()) {
                Object key;
                String value ;
                 value = rs.getObject(valueColumn).toString();
                
                switch (keyClass.getSimpleName()) {
                    case "Integer":
                        key = rs.getInt(keyColumn);
                        break;
                    case "Double":
                        key = rs.getDouble(keyColumn);
                        break;
                    case "String":
                        key = rs.getInt(keyColumn);
                        break;
                    case "Date":
                        key = rs.getString(keyColumn);
                        break;
                    default:
                        key = null;
                        System.out.println("add another class type");
                }
                this.items.add(new ComboItem(key, value));
            }
            cmb.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));

        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param variable the variable to set
     */
    public void fill(Object variable) {
        this.variable = variable;
        QueryComboBox.this.fill();
    }

    public void removeTableItems(){
        HashSet<ComboItem> tableItems = new HashSet<>();
        TableModel model = table.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object key = model.getValueAt(row, tableKeyColumn);
            String value = model.getValueAt(row, tableValueColumn).toString();
            tableItems.add(new ComboItem(key, value));
        }
        this.items.removeAll(tableItems);
        cmb.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
    }
    
    public void setTable(JTable table, int keyColumn, int valueColumn){
        this.table=table;
        this.tableKeyColumn=keyColumn;
        this.tableValueColumn=valueColumn;
        
        this.table.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                Object obj = e.getSource();
            }
        });
        
        removeTableItems();
    }
    
    public JTable getTable(){
        return table;
    }

    /**
     * @return the cmb
     */
    public JComboBox getCmb() {
        return cmb;
    }

    /**
     * @return the sqlQuery
     */
    public String getQuery() {
        return sqlQuery;
    }

    /**
     * @return the keyClass
     */
    public Class getkeyClass() {
        return keyClass;
    }

    /**
     * @return the keyColumn
     */
    public String getKeyColumn() {
        return keyColumn;
    }

    /**
     * @return the valueColumn
     */
    public String getValueColumn() {
        return valueColumn;
    }

    /**
     * @return the variable
     */
    public Object getVariable() {
        return variable;
    }
}
