/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.LocalStorage;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.view.form.componenet.table.ShowsTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 * Forma koja pretragom klijenta i predstave
 * vraca kartu za istog ili karte
 * @author andelalausevic
 */
public class FrmViewTickets extends javax.swing.JDialog {

   /**
     *objekat klase LocalStorage koji sluzi za slanje karte kada se izabere
     */
    private LocalStorage localStorage = LocalStorage.getInstance();
    /**
 * Konstruktor klase FrmViewTickets.
 * 
 * @param parent Roditeljski prozor
 * @param modal Modalnost prozora
 */
    public FrmViewTickets(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareView();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDetails = new javax.swing.JButton();
        lblShow = new javax.swing.JLabel();
        txtNameOfShow = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbTickets = new javax.swing.JComboBox<>();
        lblClient = new javax.swing.JLabel();
        txtNameOfClient = new javax.swing.JTextField();
        lblTicket = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form for view tickets");

        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        lblShow.setText("Search by name of show:");

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTicketsActionPerformed(evt);
            }
        });

        lblClient.setText("Search by name of client:");

        lblTicket.setText("TICKETS:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblTicket)
                            .addGap(18, 18, 18)
                            .addComponent(cbTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblClient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNameOfClient, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblShow)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNameOfShow, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnSearch))
                    .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShow)
                    .addComponent(txtNameOfShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClient)
                    .addComponent(txtNameOfClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnSearch)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTicket))
                .addGap(18, 18, 18)
                .addComponent(btnDetails)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Metoda koja se poziva prilikom klika na dugme "Detalji".
 *ukoliko je klijent dobro izabran salje se karta preko lokalnog skladista 
 * formi karta za prikaz
 * @param evt Događaj klika na dugme
 */
    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO...
        if(getCbTickets().getSelectedIndex()!=-1){
            Karta k=(Karta) getCbTickets().getSelectedItem();
            localStorage.addItemsInHashMap("Karta",k);
            System.out.println(k);
            FrmKarta forma=new FrmKarta(null, rootPaneCheckingEnabled, FormMode.FORM_VIEW);
            forma.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"System can not load ticket, please set ticket","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnDetailsActionPerformed
/**
 * Metoda koja se poziva prilikom klika na dugme "Pretraga".
 *Mora da postoji i predstava i klijent za koje postoji rezervacija odnosno karta
 * ispisuje se poruka o uspecu ili neuspehu
 * @param evt Događaj klika na dugme
 */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
       
       getBtnDetails().setVisible(true);
       getCbTickets().setVisible(true);
       getLblTicket().setVisible(true);
       
         List<Karta> tickets;
         List<Karta> ticketsToShow=new ArrayList<>();
        try {
            tickets = Communication.getInstance().getAllTickets();
            for(Karta k:tickets){
                if(k.getRezervacijaId().getKlijentId().getIme().equalsIgnoreCase(getTxtNameOfClient().getText()) && k.getStavkaId().getPredstavaId().getNaziv().equalsIgnoreCase(getTxtNameOfShow().getText())){
                    ticketsToShow.add(k);
                }
            }
            getCbTickets().setModel(new DefaultComboBoxModel(ticketsToShow.toArray()));
            getCbTickets().setSelectedIndex(-1);
            getCbTickets().setVisible(true);
            
            if(ticketsToShow.isEmpty()){
                JOptionPane.showMessageDialog(this,"Nema karata sa unetim podacima","ERROR",JOptionPane.ERROR_MESSAGE);
                getCbTickets().setVisible(false);
            }
            else{
                     JOptionPane.showMessageDialog(this,"Sistem je nasao karte sa unetim podacima");
                getCbTickets().setModel(new DefaultComboBoxModel(ticketsToShow.toArray()));
            getCbTickets().setSelectedIndex(-1);
            getCbTickets().setVisible(true);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cbTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTicketsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTicketsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<Object> cbTickets;
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblShow;
    private javax.swing.JLabel lblTicket;
    private javax.swing.JTextField txtNameOfClient;
    private javax.swing.JTextField txtNameOfShow;
    // End of variables declaration//GEN-END:variables

    /**
 * Metoda koja vraća dugme "Detalji".
 *
 * @return Dugme "Detalji"
 */
    public JButton getBtnDetails() {
        return btnDetails;
    }
    /**
 * Metoda koja vraća dugme "Pretraga".
 *
 * @return Dugme "Pretraga"
 */
     public JButton getBtnSearch() {
        return btnSearch;
    }
     /**
 * Metoda koja vraća padajuću listu "Ulaznice".
 *
 * @return Padajuća lista "Ulaznice"
 */
      public JComboBox<Object> getCbTickets() {
        return cbTickets;
    }
      /**
 * Metoda koja vraća tekstualno polje "Naziv predstave".
 *
 * @return Tekstualno polje "Naziv predstave"
 */
    public JTextField getTxtNameOfShow() {
        return txtNameOfShow;
    }
    /**
 * Metoda koja vraća tekstualno polje "Naziv klijenta".
 *
 * @return Tekstualno polje "Naziv klijenta"
 */
    public JTextField getTxtNameOfClient() {
        return txtNameOfClient;
    }
    /**
 * Metoda koja vraća oznaku "Pretraga".
 *
 * @return Oznaka "Pretraga"
 */
     public JLabel getLblSearch() {
        return lblShow;
    }
     /**
 * Metoda koja vraća oznaku "Ulaznica".
 *
 * @return Oznaka "Ulaznica"
 */
      public JLabel getLblTicket() {
        return lblTicket;
    }

  
/**
 * Metoda za dodavanje slušaoca događaja na dugme "Detalji".
 *
 * @param actionListener Slušalac događaja
 */

    public void getBtnDetailsAddActionListener(ActionListener actionListener) {
        btnDetails.addActionListener(actionListener);
    }
    /**
 * Metoda koja se izvršava prilikom inicijalizacije prozora.
 * Priprema prikaz prozora gde je dugme detalji nevidljivo
 * nevidljiv je combo za karte i label za karte
 */
    private void prepareView() {
       getBtnDetails().setVisible(false);
       getCbTickets().setVisible(false);
       getLblTicket().setVisible(false);
        
    }



}
