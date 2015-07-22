/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import init.ComboItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.INSERT;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author asus
 */
public class CustomTableModel extends AbstractTableModel {

    private final JTable table;
    private final ArrayList<Column> columns;
    private final PreparedStatement addStatement;
    private final PreparedStatement fillStatement;
    private final boolean isAddRowFromDB;
    private ArrayList<Object[]> rows;
    private Object[] lastRowRemoved;

    // for combobox binding
    private int keyColumn;
    private int valueColumn;

    /**
     * used when table has default data from DB
     *
     * @param table
     * @param columns
     * @param addStatement
     * @param fillStatement
     */
    public CustomTableModel(JTable table, ArrayList<Column> columns, PreparedStatement addStatement, PreparedStatement fillStatement) {
        this.table = table;
        this.columns = columns;
        this.fillStatement = fillStatement;
        this.addStatement = addStatement;
        this.isAddRowFromDB = (addStatement != null);
        this.rows = new ArrayList<>();

        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    /*
     combobox that depends on a Jtable
     */

    public void bindComboBox(JComboBox cmb, int keyColumn, int valueColumn) {

        this.keyColumn = keyColumn;
        this.valueColumn = valueColumn;

        addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                QueryCombobox model = (QueryCombobox) cmb.getModel();
                Object key;
                String value;
                ComboItem item;

                if (e.getType() == INSERT) {
                    key = getValueAt(e.getFirstRow(), keyColumn);
                    value = getValueAt(e.getFirstRow(), valueColumn).toString();
                    item = new ComboItem(key, value);
                    model.removeElement(item);

                } else if (e.getType() == DELETE) {
                    key = getLastRowRemoved()[keyColumn];
                    value = getLastRowRemoved()[valueColumn].toString();
                    item = new ComboItem(key, value);
                    model.addElement(item);
                }
            }
        });
    }
    /*
     used when table starts empty
     */

    public CustomTableModel(JTable table, ArrayList<Column> columns, PreparedStatement addStatement) {
        this(table, columns, addStatement, null);
    }

    private Object[] makeRowFromStatement(ResultSet rs) {
        Object[] rowToAdd = new Object[getColumnCount()];
        Object fieldToAdd;
        String colSqlName;
        try {
            for (int col = 0; col < getColumnCount(); col++) {
                colSqlName = columns.get(col).getSqlColumnName();
                if (colSqlName.equals("#")) {
                    fieldToAdd = rs.getRow();
                } else {
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
                }
                rowToAdd[col] = fieldToAdd;
            }
            return rowToAdd;

        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Object[] makeRowFromInput(Object[] vars) {
        for (Object var : vars) {
            if (var == null) {
                return null;
            }
        }

        Object[] newRow = new Object[columns.size()];
        int colIndex;

        if (columns.get(0).getSqlColumnName().equals("#")) {
            // auto number to first col
            if (vars.length != columns.size() - 1) {
                return null;
            }
            newRow[0] = rows.size();
            colIndex = 1;
        } else {
            if (vars.length != columns.size()) {
                return null;
            }
            colIndex = 0;
        }

        for (Object var : vars) {
            newRow[colIndex++] = var;
        }
        return newRow;
    }

    private PreparedStatement setVarToStatement(PreparedStatement st, int index, Object var) {
        try {
            switch (var.getClass().getSimpleName()) {
                case "Integer":
                    addStatement.setInt(index, (Integer) var);
                    break;
                case "Double":
                    addStatement.setDouble(index, (Double) var);
                    break;
                case "Boolean":
                    addStatement.setBoolean(index, (Boolean) var);
                case "String":
                case "Date": /* need to check if works on Date too*/

                    addStatement.setString(index, var.toString());
                    break;
                default:
                    System.out.println("need to add another type");
            }
            return st;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void addRow(Object var) {
        try {
            if (addStatement != null) {
                // add row from DB
                setVarToStatement(addStatement, 1, var);
                ResultSet rs = addStatement.executeQuery();
                rs.next();
                rows.add(makeRowFromStatement(rs));

                fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
                table.setRowSelectionInterval(rows.size() - 1, rows.size() - 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRow(Object[] vars) {
        try {
            if (addStatement != null) {
                // add row from DB
                for (int i = 0; i < vars.length; i++) {
                    if (vars[i] != null) {
                        setVarToStatement(addStatement, i, vars[i]);
                    }
                }
                ResultSet rs = addStatement.executeQuery();
                rs.next();
                rows.add(makeRowFromStatement(rs));
            } else {
                // add row from input
                rows.add(makeRowFromInput(vars));
            }
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
            table.setRowSelectionInterval(rows.size() - 1, rows.size() - 1);

        } catch (SQLException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeRow(Object key) {
        for (int i = 0; i < rows.size(); i++) {
            if (key.toString().equals(rows.get(i)[0].toString())) {
                lastRowRemoved = rows.remove(i);
                fireTableRowsDeleted(i, i);
                if (i != 1) {
                    table.setRowSelectionInterval(i - 1, i - 1);
                }
                break;
            }
        }
    }
    /*
     they keys should be the table's left columns in the same order
     NOT INCLUDING  when 1st column is #
     */

    public void removeRow(int rowToRemove) {
        if (rowToRemove != -1) {
            lastRowRemoved = rows.remove(rowToRemove);
            fireTableRowsDeleted(rowToRemove, rowToRemove);
            if (rowToRemove != 0) {
                table.setRowSelectionInterval(rowToRemove - 1, rowToRemove - 1);
            }
        }
    }

    public void fillTable() {
        try {
            if (fillStatement == null) {
                return;
            }
            ResultSet rs = fillStatement.executeQuery();
            while (rs.next()) {
                rows.add(makeRowFromStatement(rs));
                fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
            }

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
