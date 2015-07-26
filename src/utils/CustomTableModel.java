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
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author asus
 */
public class CustomTableModel extends AbstractTableModel {

    private final JTable table;
    private final ArrayList<Column> columns;
    private final PreparedStatement fillStatement;
    private Collection<JButton> buttons;
    private ArrayList<Object[]> rows;

    // for combobox binding
    private int keyColumn;
    private int valueColumn;

    /*
     used when table starts empty
     */
    public CustomTableModel(JTable table, ArrayList<Column> columns, PreparedStatement fillStatement) {
        this.table = table;
        this.columns = columns;
        this.fillStatement = fillStatement;
        this.rows = new ArrayList<>();
        this.buttons = null;

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
                try {

                    model.fill();
                    for (int row = e.getFirstRow(); row < e.getLastRow(); row++) {
                        key = getValueAt(row, keyColumn);
                        value = getValueAt(row, valueColumn).toString();
                        item = new ComboItem(key, value);
                        model.removeElement(item);
                    }
                    cmb.setSelectedIndex(0);
                } catch (SQLException ex) {
                    System.err.println("combobox model fill failed");
                }

            }
        });
    }

    public void bindButtons(Collection<JButton> buttons) {
        this.buttons = buttons;
    }

    private Object[] makeRowFromStatement(ResultSet rs) throws SQLException {
        Object[] rowToAdd = new Object[getColumnCount()];
        Object fieldToAdd;
        String colSqlName;

        for (int col = 0; col < getColumnCount(); col++) {
            colSqlName = columns.get(col).getSqlColumnName();
            if (colSqlName.equals("#")) {
                fieldToAdd = rs.getRow() + 1;
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
    }

    public boolean fillTable() throws SQLException {

        if (fillStatement == null) {
            return false;
        }
        ArrayList<Object[]> tempRows = new ArrayList<>();

        // retrieve the results and add them to the table
        ResultSet rs = fillStatement.executeQuery();
        while (rs.next()) {
            tempRows.add(makeRowFromStatement(rs));
        }
        rows = tempRows;
        fireTableRowsInserted(0, rows.size());

        int lastRowIndex = table.getRowCount() - 1;
        boolean tableNotEmpty = lastRowIndex != -1;
        if (tableNotEmpty) {
            table.setRowSelectionInterval(lastRowIndex, lastRowIndex);
        }

        if (buttons != null) {
            for (JButton button : buttons) {
                button.setEnabled(tableNotEmpty);
            }
        }

        return true;
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
}
