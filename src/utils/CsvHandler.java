/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import init.MainClass;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.opencsv.CSVReader;
import init.MyTableModel;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Matan
 */
public class CsvHandler {

    public static String SCHEMA_NAME = null;
    //insert qeury with colunms keys
    String SQL_INSERT_WITH_COLS = "INSERT INTO ${table}(${keys}) VALUES(${values})";
    //insert qeury without colunms keys
    String SQL_INSERT = "INSERT INTO ${table} VALUES(${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";
    private char seprator;
    private final JComponent holder;

    /**
     * Public constructor to build CSVLoader object with Connection details. The
     * connection is closed on success or failure.
     *
     * @param connection
     */
    public CsvHandler(JComponent holder) {
        this.holder  = holder;
        //Set default separator
        this.seprator = ',';
    }

    public static void PrintTablesMetaData(String nameOfTable) {

        //create and setup your database and get db connection
        try {

            DatabaseMetaData metaData = MainClass.con.getMetaData();

            String tableType[] = {"TABLE"};

            StringBuilder builder = new StringBuilder();

            ResultSet result = metaData.getTables(null, SCHEMA_NAME, null, tableType);
            while (result.next()) {
                String tableName = result.getString(3);
                if (!tableName.toLowerCase().equals(nameOfTable.toLowerCase())) {
                    continue;
                }

                builder.append(tableName).append("( ");
                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                ResultSetMetaData columnsMetaData = columns.getMetaData();
                boolean isAout = columnsMetaData.isAutoIncrement(1);
                while (columns.next()) {

                    String columnName = columns.getString(4);
                    builder.append(columnName);
                    builder.append(",");
                }
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append(" )");
                builder.append("\n");
                builder.append("----------------");
                builder.append("\n");

            }

            System.out.println(builder.toString());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Parse CSV file using OpenCSV library and load in given database table.
     *
     * @param csvFile Input CSV file
     * @param tableName Database table name to import data
     * @param truncateBeforeLoad Truncate the table before inserting new
     * records.
     * @throws Exception
     */
    public DefaultTableModel loadCSV(String csvFile, String tableName,
            boolean truncateBeforeLoad) throws Exception {
        boolean isAutoInc = false; //check if key of table is Auto Increment
        boolean isFirstRowCol = false; //check if first row of file are culomns definition or row data.
        boolean withIdentityCol = true;
        ArrayList<String> cols = new ArrayList<>();
        int numOfCol = 1;
        String queryColCount = "SELECT\n"
                + "     name\n"
                + "FROM\n"
                + "     [LondonU2].sys.columns\n"
                + "WHERE\n"
                + "     object_id = OBJECT_ID('" + tableName + "')";
        PreparedStatement pcount = MainClass.con.prepareCall(queryColCount);
        ResultSet rsCount = pcount.executeQuery();
        while (rsCount.next()) {
            cols.add(rsCount.getString(numOfCol));
        }
        if (!cols.isEmpty()) {
            numOfCol = cols.size();
        }
        //<editor-fold defaultstate="collapsed" desc="get meta data of table from DB">
        DatabaseMetaData metaData = MainClass.con.getMetaData();
        String tableType[] = {"TABLE"};
        ResultSet result = metaData.getTables(null, SCHEMA_NAME, null, tableType);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="check if auto Increment is set to key table and get the column key name">
        while (result.next()) {
            String nameOfTable = result.getString(3);
            if (!tableName.toLowerCase().equals(nameOfTable.toLowerCase())) {
                continue;
            }
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                //get col type as string
                isAutoInc = columns.getString(6).contains("identity");
                break;
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Init CSVReader for reading file">
        CSVReader csvReader = null;
        if (null == MainClass.con) {
            throw new Exception("Not a valid connection.");
        }
        try {

            csvReader = new CSVReader(new FileReader(csvFile), this.seprator);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occured while executing file. "
                    + e.getMessage());
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="get first row from file and check if it contains column names">
        String[] firstRow = csvReader.readNext();

        withIdentityCol = (numOfCol == firstRow.length);

        String[] firstRowWithoutCol = new String[firstRow.length - 1];
        if (null == firstRow) {
            throw new FileNotFoundException(
                    "An ecror in given CSV file."
                    + "Please check the CSV file format.");
        } else {

            if (isAutoInc) {
                System.arraycopy(firstRow, 1, firstRowWithoutCol, 0, firstRow.length - 1);
            }

            if (firstRow[0].startsWith("﻿")) {
                firstRow[0] = ((String) firstRow[0]).substring(1);
            }

            if (cols.contains(firstRow[0])) {
                isFirstRowCol = true;
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Set params holder for query">
        String questionmarks;
        String query;
        if (!isAutoInc) {
            //table is not with identity'
            questionmarks = StringUtils.repeat("?,", firstRow.length);
            questionmarks = (String) questionmarks.subSequence(0, questionmarks.length() - 1);
        } else {
            //table is with identity'
            if (withIdentityCol) {
                questionmarks = StringUtils.repeat("?,", firstRow.length - 1);

            } else {
                questionmarks = StringUtils.repeat("?,", firstRow.length);
            }

            questionmarks = (String) questionmarks.subSequence(0, questionmarks.length() - 1);
        }

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Set insert query">
        if (isFirstRowCol) {
            query = SQL_INSERT_WITH_COLS.replaceFirst(TABLE_REGEX, tableName);
            if (!isAutoInc) {
                // table is not with identity' 
                query = query.replaceFirst(KEYS_REGEX, StringUtils.join(firstRow, ","));
            } else {
                // table is with identity'
                if (withIdentityCol) {
                    query = query.replaceFirst(KEYS_REGEX, StringUtils.join(firstRowWithoutCol, ","));
                } else {
                    query = query.replaceFirst(KEYS_REGEX, StringUtils.join(firstRow, ","));
                }
            }

            query = query.replaceFirst(VALUES_REGEX, questionmarks);
        } else {
            query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
            query = query.replaceFirst(VALUES_REGEX, questionmarks);
        }
        //</editor-fold>

        //for testing
        System.out.println("Query: " + query);

        String[] nextLine;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = MainClass.con;
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);

//            //check if user wants to delete old data befor insert new data
//            if (truncateBeforeLoad) {
//                //delete data from table before loading csv
//                con.createStatement().execute("DELETE FROM " + tableName);
//            }
            final int batchSize = 1000;
            int count = 0;
//            Date date = null;
//            Integer number;
//            Double d;

            Vector<Vector<Object>> insertdData = new Vector<>();
            Vector<Object> row;
            if (!isFirstRowCol) {
                row = addRowToBatch(firstRow, ps, isAutoInc, withIdentityCol,cols.size());
                if (row != null) {
                    insertdData.add(row);
                }
            }

            while ((nextLine = csvReader.readNext()) != null) {

//                if (null != nextLine) {
//                    int index = 1;
//                    for (int i = 0; i < nextLine.length; i++) {
//                        //if key col is auto increment skip key col from file
//                        if (isAutoInc && i == 0) {
//                            continue;
//                        }
//
//                        //<editor-fold defaultstate="collapsed" desc="fix the invalid char that fuckedUp everything for two days">
//                        nextLine[i] = nextLine[i].trim();
//                        if (nextLine[i].startsWith("﻿")) {
//                            nextLine[i] = nextLine[i].substring(1);
//                        }
//                        //</editor-fold>
//
//                        //<editor-fold defaultstate="collapsed" desc="Convert the string from file to correct type and add to statement">
//                        date = ConvertUtil.convertToDate(nextLine[i]);
//                        if (null != date) {
//                            Timestamp time = new Timestamp(date.getTime());
//                            ps.setTimestamp(index++, time);
//                            //ps.setDate(index++, new java.sql.Date(date.getTime()));
//                        } else if ((number = ConvertUtil.convertToInt(nextLine[i])) != null) {
//
//                            ps.setInt(index++, number);
//                        } else if ((d = ConvertUtil.convertToDouble(nextLine[i])) != null) {
//
//                            ps.setDouble(index++, d);
//                        } else {
//                            ps.setString(index++, nextLine[i]);
//                        }
//                        //</editor-fold>
//                    }
//                    ps.addBatch();
//                }
                row = addRowToBatch(nextLine, ps, isAutoInc, withIdentityCol,cols.size());
                if (row != null) {
                    insertdData.add(row);
                }

                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            con.commit();
//            JOptionPane.showMessageDialog(null,
//                    "data has been imported successfully",
//                    "INFORMATION MESSAGE",
//                    JOptionPane.INFORMATION_MESSAGE);

            int ok = JOptionPane.showInternalConfirmDialog(holder,
                    "Data has been imported successfully", "INFORMATION MESSAGE", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                
            }
            Vector<String> colNames = new Vector<>(cols);
            if (insertdData.elementAt(0).size()== 4) {
                colNames.removeElementAt(0);
            }
            //Collections.copy(colNames, cols);
            DefaultTableModel model = new DefaultTableModel(insertdData ,colNames);
            return model;

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw new Exception(
                    "Error occured while loading data from file to database."
                    + e.getMessage());
        } finally {
            if (null != ps) {
                ps.close();
            }

            csvReader.close();
        }
    }

    /**
     *
     * @param nextLine the value of nextLine
     * @param ps the value of ps
     * @param isAutoInc the value of isAutoInc
     * @param withKeyCol the value of withKeyCol
     * @return the java.lang.Object[]
     * @throws SQLException
     */
        private Vector<Object> addRowToBatch(String[] nextLine, PreparedStatement ps, Boolean isAutoInc, boolean withKeyCol,int count) throws SQLException {
        Date date = null;
        Integer number;
        Double d;
        Vector<Object> row = null;
        if (null != nextLine) {

            //size of row to save
            int size;
            int typeIdx;
            if (withKeyCol && !isAutoInc) {
                size = count;
                typeIdx = count-1;
            }
            else if(withKeyCol && isAutoInc){
                size = count-1;
                typeIdx = count-1;
            }
            else{
                //with key without autoInc..
                size = count -1;
                typeIdx = count -2;
            }
            
            //index of siteType column according to inserted values from csv
            
            row = new Vector<>(size);

            //if the site type is nut Museum dont add the row to table
            if (!nextLine[typeIdx].equals("Museum")) {
                return null;
            }

            //int index = 1;
            for (int i = 0; i < nextLine.length; i++) {
                
                if (isAutoInc && withKeyCol && i == 0) {
                   row.add("Auto ID");
                   continue;
                }
                else {
                    //if key col is auto increment skip key col from file
                    row.add(nextLine[i]);
                }

                //<editor-fold defaultstate="collapsed" desc="fix the invalid char that fuckedUp everything for two days">
                nextLine[i] = nextLine[i].trim();
                if (nextLine[i].startsWith("﻿")) {
                    nextLine[i] = nextLine[i].substring(1);
                }
                        //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Convert the string from file to correct type and add to statement">
                date = ConvertUtil.convertToDate(nextLine[i]);
                if (null != date) {
                    Timestamp time = new Timestamp(date.getTime());
                    if (withKeyCol) {
                       ps.setTimestamp(i, time); 
                    }
                    else{
                        ps.setTimestamp(i+1, time);
                    }
                    
                    //ps.setDate(index++, new java.sql.Date(date.getTime()));
                } else if ((number = ConvertUtil.convertToInt(nextLine[i])) != null) {
                    if (withKeyCol) {
                       ps.setInt(i, number); 
                    }
                    else{
                        ps.setInt(i+1, number);
                    }
                } else if ((d = ConvertUtil.convertToDouble(nextLine[i])) != null) {

                     if (withKeyCol) {
                       ps.setDouble(i, d); 
                    }
                    else{
                        ps.setDouble(i+1, d);
                    }
                     
                } else {
                    if (withKeyCol) {
                       ps.setString(i, nextLine[i]);
                    }
                    else{
                        ps.setString(i+1, nextLine[i]);
                    }
                    
                    
                }

                //row.add(nextLine[i]);
                //</editor-fold>
            }

            ps.addBatch();
        }

        return row;
    }

    public char getSeprator() {
        return seprator;
    }

    public void setSeprator(char seprator) {
        this.seprator = seprator;
    }

    public void exportSiteExitDistanceToCsv(String table, String name) {

        FileWriter fw;
        try {
            Statement st = MainClass.con.createStatement();
                    //con.createStatement();

            //this query gets all the tables in your database(put your db name in the query)
            ResultSet res = null;
            //rst.executeQuery("SELECT Table_Name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'Base table'");

            //path to the folder where you will save your csv files
            String filename = name;

            String q = "SELECT  s.*, [stationID],st.name as 'Station Name' ,[distance] "
                    + "  FROM [LondonU2].[dbo].[tblSiteFromExit] sfe join tblSite s on sfe.siteID = s.ID"
                    + "  join tblStation st on st.ID = sfe.stationID"
                    + "  order by s.name,sfe.distance";
            //select all data from table
            res = st.executeQuery(q);

            //colunm count is necessay as the tables are dynamic and we need to figure out the numbers of columns
            int colunmCount = getColumnCount(res);

            try {
                fw = new FileWriter(filename, false);

                //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                for (int i = 1; i <= colunmCount; i++) {
                    fw.append(res.getMetaData().getColumnName(i).toLowerCase());
                    if (i != colunmCount) {
                        fw.append(",");
                    }

                }

                fw.append(System.getProperty("line.separator"));

                while (res.next()) {
                    for (int i = 1; i <= colunmCount; i++) {

                        //you can update it here by using the column type but i am fine with the data so just converting 
                        //everything to string first and then saving
                        if (res.getObject(i) != null) {
                            String data = res.getObject(i).toString();
                            fw.append(data);
                            if (i != colunmCount) {
                                fw.append(",");
                            }
                        } else {
                            String data = "null";
                            fw.append(data);
                            if (i != colunmCount) {
                                fw.append(",");
                            }
                        }

                    }
                    //new line entered after each row
                    fw.append(System.getProperty("line.separator"));
                }

                fw.flush();
                fw.close();

                JOptionPane.showMessageDialog(null,
                        "data has been exported successfully",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null,
                        "File error ",
                        "ERROR MESSAGE",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null,
                    "SQLException information",
                    "ERROR MESSAGE",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void exportTableDataToCsv(String table, String name) {
        //usual database connection part
        Connection con = null;

        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        FileWriter fw;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LondonU2;user=TOM;password=1234;");
            Statement st = con.createStatement();

            //this query gets all the tables in your database(put your db name in the query)
            ResultSet res = st.executeQuery("SELECT Table_Name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'Base table'");

            //Preparing List of table Names
            List<String> tableNameList = new ArrayList<>();
            while (res.next()) {
                tableNameList.add(res.getString(1));
            }

            //path to the folder where you will save your csv files
            String filename = name;

            //star iterating on each table to fetch its data and save in a .csv file
            for (String tableName : tableNameList) {
                if (table.equals(tableName)) {

                    int k = 0;

                    int j = 1;

                    System.out.println(tableName);

                    List<String> columnsNameList = new ArrayList<String>();

                    //select all data from table
                    res = st.executeQuery("select * from " + tableName);

                    //colunm count is necessay as the tables are dynamic and we need to figure out the numbers of columns
                    int colunmCount = getColumnCount(res);

                    try {
                        fw = new FileWriter(filename);

                        //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                        for (int i = 1; i <= colunmCount; i++) {
                            fw.append(res.getMetaData().getColumnName(i));
                            fw.append(",");

                        }

                        fw.append(System.getProperty("line.separator"));

                        while (res.next()) {
                            for (int i = 1; i <= colunmCount; i++) {

                                //you can update it here by using the column type but i am fine with the data so just converting 
                                //everything to string first and then saving
                                if (res.getObject(i) != null) {
                                    String data = res.getObject(i).toString();
                                    fw.append(data);
                                    if (i != 5) {
                                        fw.append(",");
                                    }
                                } else {
                                    String data = "null";
                                    fw.append(data);
                                    if (i != 5) {
                                        fw.append(",");
                                    }
                                }

                            }
                            //new line entered after each row
                            fw.append(System.getProperty("line.separator"));
                        }

                        fw.flush();
                        fw.close();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

            con.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load JDBC driver");
            e.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("SQLException information");
        }
    }

    //to get no of columns in result set
    public static int getColumnCount(ResultSet res) throws SQLException {
        return res.getMetaData().getColumnCount();
    }

    //to get numbers of rows in a result set 
    public static int getRowCount(ResultSet res) throws SQLException {
        res.last();
        int numberOfRows = res.getRow();
        res.beforeFirst();
        return numberOfRows;
    }

}
