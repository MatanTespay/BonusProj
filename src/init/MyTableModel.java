/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * The class represents customized DefaultTableModel enables use of ArrayList in
 * the tables used in the system
 *
 * @author Matan
 */
public class MyTableModel extends DefaultTableModel {

    private String[] columnNames;
    //private Object[][] data;
    private int[] enabledCells;
    private ArrayList<Object[]> data;

    /**
     *
     * @param columnNames
     * @param data
     * @param cells
     */
    public MyTableModel(String[] columnNames, ArrayList<Object[]> data, int[] cells) {
        this.columnNames = columnNames;
        this.data = data;
        enabledCells = cells;
    }

    public MyTableModel(String[] columnNames, ArrayList<Object[]> data) {
        this(columnNames, data, new int[0]);
    }

    public MyTableModel() {
        this(0, 0);
    }

    public MyTableModel(int rowCount, int columnCount) {
        this(newCols(columnCount), rowCount);
    }

    public MyTableModel(String[] columnNames) {
        this(columnNames, 0);
    }

    public MyTableModel(String[] columnNames, int roCount) {
        this(columnNames, newData(roCount));
    }

    private static String[] newCols(int size) {
        String[] v = new String[size];
        return v;
    }

    private static ArrayList<Object[]> newData(int size) {
        ArrayList<Object[]> v = new ArrayList<>(size);
        return v;
    }

    @Override
    public int getRowCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col] != null ? columnNames[col] : "";
    }

    @Override
    public Object getValueAt(int row, int col) {
        //return data[row][col];
        if (data != null &&  row != -1 && col != -1 && data.get(row) != null) {
            Object[] rowData = data.get(row);
            Object v = rowData[col];
            return v;
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {

        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Object[] row = data.get(rowIndex);
            if (row[c] != null) {
                return getValueAt(rowIndex, c).getClass();
            }
        }
        return String.class;

        //return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (enabledCells == null) {
            return false;
        }

        for (int i = 0; i < enabledCells.length; i++) {
            if (enabledCells[i] == col) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addRow(Object[] rowData) {
        if (data != null) {
            data.add(rowData);
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }
    }

    @Override
    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
        for (int i = 0; i < data.size(); i++) {
            setValueAt((i + 1), i, 0);
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        // change made here
        if (data != null && data.get(row) != null) {
            Object[] rowData = data.get(row);
            rowData[col] = value;
            fireTableCellUpdated(row, col);
        }
    }    
}
