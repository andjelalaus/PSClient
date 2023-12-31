/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.LocalStorage;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.view.form.componenet.table.ShowsTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 * Forma za prikaz svih predstavi sa zadatim pojmom za trazenje
 * po nazivu treba traziti predstave
 * @author andelalausevic
 */
public class FrmViewShows extends javax.swing.JDialog {

    /**
     *objekat klase LocalStorage koji sluzi za slanje predstave kada se izabere
     */
    private LocalStorage localStorage = LocalStorage.getInstance();
    /**
 * Konstruktor forme za prikaz predstava.
 *
 * @param parent Roditeljski frame.
 * @param modal Modalnost forme.
 */
    public FrmViewShows(java.awt.Frame parent, boolean modal) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnDetails = new javax.swing.JButton();
        lblSaerch = new javax.swing.JLabel();
        txtNameOfShow = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form for view shows");

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Naziv", "Mesto", "Vreme", "Kapacitet"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        lblSaerch.setText("Search by name of show:");

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSaerch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNameOfShow, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaerch)
                    .addComponent(txtNameOfShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetails)
                    .addComponent(jButton1))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Metoda koja se izvršava prilikom klika na dugme "Detalji".
 * Ako je selektovana neka predstava u tabeli, otvara se forma za prikaz detalja te predstave.
 * Selektovana predstava se dodaje u lokalno skladište pod ključem "Predstava".
 * Otvara se instanca forme FrmPredstava u režimu prikaza (FormMode.FORM_VIEW).
 * 
 * @param evt Događaj klika na dugme
 */
    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        // TODO...
        if(getTblProducts().getSelectedRow()!=-1){
            
            Predstava pr=((ShowsTableModel)getTblProducts().getModel()).getProductAt(getTblProducts().getSelectedRow());
            localStorage.addItemsInHashMap("Predstava",pr);
            FrmPredstava forma=new FrmPredstava(null, rootPaneCheckingEnabled, FormMode.FORM_VIEW);
            forma.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(this,"Sistem ne moze da ucita predstavu, verovatno niste selektovali zeljenu","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnDetailsActionPerformed
/**
 * Metoda koja se izvršava prilikom klika na dugme "Pretraga".
 * Pretražuje predstave na osnovu unetog imena predstave.
 * Ako se pronađu predstave sa unetim imenom, prikazuju se u tabeli.
 * U suprotnom, prikazuje se poruka da sistem nije pronašao predstave.
 * 
 * @param evt Događaj klika na dugme
 */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
        
         List<Predstava> shows;
         
        try {
            List<Predstava> showsToShow=new ArrayList<>();
            shows = Communication.getInstance().getAllShows();
            for(Predstava pr:shows){
                if(pr.getNaziv().equalsIgnoreCase(getTxtNameOfShow().getText().trim())){
                    showsToShow.add(pr);
                    System.out.println(pr);
                }
            }
            if(showsToShow.isEmpty()){
            JOptionPane.showMessageDialog(this,"Sistem ne moze da nadje predstave po zadatoj vrednosti");
            }
            else{
            JOptionPane.showMessageDialog(this,"Sistem je nasao predstave sa unetim imenom");
            ShowsTableModel ptm = new ShowsTableModel(showsToShow);
            //getTblPanel().setVisible(true);
            getTblProducts().setModel(ptm);
            //getTblProducts().setVisible(true);
            getBtnDetails().setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed
/**
 * Metoda koja se izvršava prilikom klika na dugme "Cancel".
 * Zatvara trenutni prozor.
 * 
 * @param evt Događaj klika na dugme
 */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSaerch;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtNameOfShow;
    // End of variables declaration//GEN-END:variables
  /**
 * Metoda koja vraća dugme "btnDetails".
 * 
 * @return dugme "btnDetails"
 */
    public JButton getBtnDetails() {
        return btnDetails;
    }
    /**
 * Metoda koja vraća tekstualno polje "txtNameOfShow".
 * 
 * @return Tekstualno polje "txtNameOfShow"
 */
    public JTextField getTxtNameOfShow() {
        return txtNameOfShow;
    }
    /**
 * Metoda koja vraća oznaku "lblSearch".
 * 
 * @return Oznaka "lblSearch"
 */
     public JLabel getLblSearch() {
        return lblSaerch;
    }
/**
 * Metoda koja vraća tabelu "tblProducts".
 * 
 * @return Tabela "tblProducts"
 */
    public JTable getTblProducts() {
        return tblProducts;
    }
/**
 * Metoda koja se izvršava prilikom klika na dugme "Details".
 * Dodaje akcioni prisluskivac za dugme "btnDetails".
 * 
 * @param actionListener Akcioni slušač koji se dodaje
 */
    public void getBtnDetailsAddActionListener(ActionListener actionListener) {
        btnDetails.addActionListener(actionListener);
    }
    /**
 * Metoda koja vraća panel za tabelu "tblProducts".
 * 
 * @return Panel za tabelu "tblProducts"
 */
     public JScrollPane getTblPanel() {
        return jScrollPane1;
    }
/**
 * Metoda koja se izvršava prilikom inicijalizacije prozora.
 * Priprema prikaz prozora.
 */
    private void prepareView() {
       getTblPanel().setVisible(true);
       getBtnDetails().setEnabled(false);
        
    }

    
}
