/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import init.MainClass;
import java.sql.Connection;
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

/**
 *
 * @author Matan
 */
public class CsvHandler {

    public static String SCHEMA_NAME = null;
   String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";
    private char seprator;

    /**
     * Public constructor to build CSVLoader object with Connection details. The
     * connection is closed on success or failure.
     *
     * @param connection
     */
    public CsvHandler() {

        //Set default separator
        this.seprator = ',';
    }

    public static void PrintTablesMetaData() {

        //create and setup your database and get db connection
        try {

            DatabaseMetaData metaData = MainClass.con.getMetaData();

            String tableType[] = {"TABLE"};

            StringBuilder builder = new StringBuilder();

            ResultSet result = metaData.getTables(null, SCHEMA_NAME, null, tableType);
            while (result.next()) {
                String tableName = result.getString(3);

                builder.append(tableName).append("( ");
                ResultSet columns = metaData.getColumns(null, null, tableName, null);

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
    public void loadCSV(String csvFile, String tableName,
            boolean truncateBeforeLoad) throws Exception {

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

        String[] headerRow = csvReader.readNext();
        if (null == headerRow) {
            throw new FileNotFoundException(
                    "No columns defined in given CSV file."
                    + "Please check the CSV file format.");
        }       
        
        String questionmarks = StringUtils.repeat("?,", headerRow.length);
        questionmarks = (String) questionmarks.subSequence(0, questionmarks.length() - 1);
 
        String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
        query = query.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
        query = query.replaceFirst(VALUES_REGEX, questionmarks);
 
        //for testing
        System.out.println("Query: " + query);

        String[] nextLine;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = MainClass.con;
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);

            if (truncateBeforeLoad) {
                //delete data from table before loading csv
                con.createStatement().execute("DELETE FROM " + tableName);
            }

            final int batchSize = 1000;
            int count = 0;
            Date date = null;
            while ((nextLine = csvReader.readNext()) != null) {

                if (null != nextLine) {
                    int index = 1;
                    for (String string : nextLine) {
                        date = DateUtil.convertToDate(string);
                        if (null != date) {
                            Timestamp time = new Timestamp(date.getTime());
                            ps.setTimestamp(index++, time);
                            //ps.setDate(index++, new java.sql.Date(date.getTime()));
                        } else {
                            string = string.trim();
                            ps.setString(index++, string);
                        }
                    }
                    ps.addBatch();
                }

                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            con.commit();
            JOptionPane.showMessageDialog(null,
                        "data has been imported successfully",
                        "INFORMATION MESSAGE",
                        JOptionPane.INFORMATION_MESSAGE);
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
        if (1 == 1) {
            return;
        }
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
