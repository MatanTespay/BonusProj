/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import init.ComboItem;
import init.MainClass;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import utils.E_Airports;
import utils.HelperClass;
import core.Flight;
import exceptions.AddStopToFlightExeption;

/**
 *The class represents the screen which enable users to add {@link core.StepIn } to 
  {@link core.Flight }

 * @author Matan
 */
public class AddStop extends MyInternalFrame {

    /**
     * Creates new form AddStop
     *
     * @param title
     * @param type
     */
    public AddStop(String title, String type) {
        super(title, type);
        initComponents();
        fillFlightsCombo();
        btnOk.setToolTipText(utils.Constants.saveButtonToolTip);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbFlights = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        lblSource = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDestination = new javax.swing.JLabel();
        cbStops = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        btnOk.setText("Submit");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Stop:");

        cbFlights.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFlightsActionPerformed(evt);
            }
        });

        jLabel3.setText("Source:");

        jLabel4.setText("Destination:");

        jLabel1.setText("Select Flight:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(cbFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSource, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbStops, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbFlights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblSource, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbStops, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:

        E_Airports stop;
        if (cbFlights.getModel().getSize() <= 0 || cbStops.getModel().getSize() <= 0) {
            shoMissingDataMsg("Flights or airports are missing");
            return;
        }

        int flightNumber = Integer.parseInt((String)((ComboItem) cbFlights.getSelectedItem()).getKey());
        stop = E_Airports.valueOf((String)((ComboItem) cbStops.getSelectedItem()).getKey());

        try {
            MainClass.getIfly().addStepInToFlight(flightNumber, stop);
            JOptionPane.showInternalConfirmDialog(this, "Saved changes", "Confirmation", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            cbStops.removeItem(stop);
            setIflyState(false);
        } catch (AddStopToFlightExeption ex) {
            JOptionPane.showInternalConfirmDialog(this, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void cbFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFlightsActionPerformed
        // TODO add your handling code here:
        fillStopsCombo();
    }//GEN-LAST:event_cbFlightsActionPerformed

    /**
     * fill {@link #cbStops}
     */
    private void fillStopsCombo() {

        int flightNumber = -1;
        if (cbFlights.getModel().getSize() > 0) {
            flightNumber = Integer.parseInt((String)((ComboItem) cbFlights.getSelectedItem()).getKey());
        }

        Flight selectFlight = MainClass.getIfly().getFlights().get(flightNumber);
        ArrayList<E_Airports> list = new ArrayList<E_Airports>();
        if (selectFlight != null) {
            list.add(selectFlight.getSource());
            list.add(selectFlight.getDestination());
            lblSource.setText(selectFlight.getSource().getCity());
            lblDestination.setText(selectFlight.getDestination().getCity());
        }
        HelperClass.fillAirportsCombo(this, cbStops, list);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox cbFlights;
    private javax.swing.JComboBox cbStops;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDestination;
    private javax.swing.JLabel lblSource;
    // End of variables declaration//GEN-END:variables

    
    /**
     * fill {@link #cbFlights}
     */
    private void fillFlightsCombo() {
        HelperClass.fillFlightsCombo(this, cbFlights, false);
        fillStopsCombo();
    }
}
