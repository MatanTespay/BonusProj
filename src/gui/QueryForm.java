/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static init.MainClass.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import utils.HelperClass;
import utils.Queries;

/**
 *
 * @author asus
 */
public class QueryForm extends MyInternalFrame {

    /**
     * Creates new form QueryForm from result of the import result
     * @param  model
     * @param title
     * @param type
     */
    public QueryForm(String title, String type , DefaultTableModel model){
        super(title, type);
        initComponents();
        tblResult.setModel(model);
        
    }
    /**
     * Creates new form QueryForm
     *
     * @param title
     * @param type
     */
    public QueryForm(String title, String type) {
        super(title, type);
        initComponents();
        try {
            Statement st = con.createStatement();
            ResultSet rs;

            switch (title) {
                case "Remote Sites":
                    rs = st.executeQuery(Queries.QUERY1);
                    break;
                case "Unused Paper Cards":
                    rs = st.executeQuery(Queries.QUERY2);
                    break;
                case "Main Stations":
                    rs = st.executeQuery(Queries.QUERY3);
                    break;
                case "Most Active Stations":
                    rs = st.executeQuery(Queries.QUERY4);
                    break;
                case "Cards That Have Activity Issues":
                    Statement st1 = con.createStatement();
                    st1.execute(Queries.USE_LONDON2);
                    st1.execute(Queries.IF_EXISTS_endDates);
                    st1.execute(Queries.USE_LONDON2);
                    st1.execute(Queries.SET_ANSI_NULLS_ON);
                    st1.execute(Queries.SET_QUOTED_IDENTIFIER_ON);
                    st1.execute(Queries.CREATE_VIEW_END_DATES);
                    rs = st.executeQuery(Queries.QUERY5);
                    break;
                case "Very Active Oyster Cards":
                    rs = st.executeQuery(Queries.QUERY6);
                    break;
                case "Profitable Deposit Years":
                    Statement st2 = con.createStatement();
                    st2.execute(Queries.USE_LONDON2);
                    st2.execute(Queries.IF_EXISTS_numOfCardsForPrice);
                    st2.execute(Queries.USE_LONDON2);
                    st2.execute(Queries.SET_ANSI_NULLS_ON);
                    st2.execute(Queries.SET_QUOTED_IDENTIFIER_ON);
                    st2.execute(Queries.CREATE_VIEW_numOfCardsForPrice);

                    Statement st3 = con.createStatement();
                    st3.execute(Queries.USE_LONDON2);
                    st3.execute(Queries.IF_EXISTS_yearsWithMoreThan5000);
                    st3.execute(Queries.USE_LONDON2);
                    st3.execute(Queries.SET_ANSI_NULLS_ON);
                    st3.execute(Queries.SET_QUOTED_IDENTIFIER_ON);
                    st3.execute(Queries.CREATE_VIEW_yearsWithMoreThan5000);

                    Statement st4 = con.createStatement();
                    st4.execute(Queries.USE_LONDON2);
                    st4.execute(Queries.IF_EXISTS_NumOfOysterRides);
                    st4.execute(Queries.USE_LONDON2);
                    st4.execute(Queries.SET_ANSI_NULLS_ON);
                    st4.execute(Queries.SET_QUOTED_IDENTIFIER_ON);
                    st4.execute(Queries.CREATE_VIEW_numOfOysterRides);

                    rs = st.executeQuery(Queries.QUERY7);
                    break;
                default:
                    rs = null;
            }
            tblResult.setModel(HelperClass.buildTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(QueryForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblResult;
    // End of variables declaration//GEN-END:variables
}
