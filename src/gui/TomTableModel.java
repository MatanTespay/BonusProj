/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import utils.HelperClass;

/**
 *
 * @author asus
 */
public class TomTableModel extends AbstractTableModel {

    private final ArrayList<Column> columns;
    private final PreparedStatement addStatement;
    private final PreparedStatement fillStatement;
    private ArrayList<Object[]> rows;
    private Object[] lastRowRemoved;

    /**
     * used when table has default data from DB
     *
     * @param columns
     * @param addStatement
     * @param fillStatement
     */
    public TomTableModel(ArrayList<Column> columns, PreparedStatement addStatement, PreparedStatement fillStatement) {
        this.columns = columns;
        this.fillStatement = fillStatement;
        this.addStatement = addStatement;

        rows = new ArrayList<>();
        fillTable();
    }
    /*
    used when table starts empty
    */
    public TomTableModel(ArrayList<Column> columns, PreparedStatement addStatement) {
        this(columns, addStatement, null);
    }

    private Object[] makeRow(ResultSet rs) {
        Object[] rowToAdd = new Object[getColumnCount()];
        Object fieldToAdd;
        String colSqlName;
        try {
            for (int col = 0; col < getColumnCount(); col++) {
                colSqlName = columns.get(col).getSqlColumnName();
                switch (getColumnClass(col).getSimpleName()) {
                    case "Integer":
                        fieldToAdd = rs.getInt(colSqlName);
                        break;
                    case "String":
                        fieldToAdd = rs.getString(colSqlName);
                        break;
                    case "Double":
                        fieldToAdd = rs.getDouble(colSqlName);
                        break;
                    case "Date":
                        fieldToAdd = rs.getString(colSqlName);
                        break;
                    case "Boolean":
                        fieldToAdd = rs.getBoolean(colSqlName);
                        break;
                    default:
                        fieldToAdd = null;
                        System.out.println("Add another class");
                }
                rowToAdd[col] = fieldToAdd;
            }
            return rowToAdd;

        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addRow(Object key) {
        try {
            if (key instanceof Integer) {
                addStatement.setInt(1, (Integer) key);
            } else if (key instanceof String) {
                addStatement.setString(1, key.toString());
            }

            ResultSet rs = addStatement.executeQuery();
            rs.next();
            rows.add(makeRow(rs));
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);

        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeRow(Object key) {
        for (int i = 0; i < rows.size(); i++) {
            if (key.toString().equals(rows.get(i)[0].toString())) {
                lastRowRemoved = rows.get(i);
                rows.remove(rows.get(i));
                fireTableRowsDeleted(i, i);
            }
        }
    }

    public void fillTable() {
        try {
            if (fillStatement == null){
                return;
            }
            ResultSet rs = fillStatement.executeQuery();
            while (rs.next()) {
                rows.add(makeRow(rs));
                fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
            }

            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < rows.size()
                && columnIndex >= 0 && columnIndex < columns.size()) {
            return rows.get(rowIndex)[columnIndex];
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col).getName();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columns.get(columnIndex).getcClass().getSimpleName().equals("Date")) {
            return String.class;
        }
        return columns.get(columnIndex).getcClass();
    }

    /**
     * @return the lastRowRemoved
     */
    public Object[] getLastRowRemoved() {
        return lastRowRemoved;
    }
}
