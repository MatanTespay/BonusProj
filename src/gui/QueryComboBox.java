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
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
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

    public QueryComboBox(JComboBox cmb, String sqlQuery, Class keyClass,
            String keyColumn, String valueColumn, Object variable) {
        this.cmb = cmb;
        this.sqlQuery = sqlQuery;
        this.keyClass = keyClass;
        this.keyColumn = keyColumn;
        this.valueColumn = valueColumn;
        this.variable = variable;
    }

    public QueryComboBox(JComboBox cmb, String sqlQuery, Class keyClass,
            String keyColumn, String valueColumn) {
        this(cmb, sqlQuery, keyClass, keyColumn, valueColumn, null);
    }

    public void fill() {
        ResultSet rs;
        TreeSet<ComboItem> items = new TreeSet<>();
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
                items.add(new ComboItem(key, value));
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
