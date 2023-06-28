/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.componenet.table;

import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;

/**
 * Klasa koja predstavlja model za tabelu rstavki rezervacija koje se unose, gledaju
 * @author andjy
 */
public class ReservationsTableModel extends AbstractTableModel {
/**
 * objekat klase Rezervacija
 */
    private final Rezervacija rezervacija;
    /**
     * imena kolona id predstava discount number of seats and watched
     */
    private final String[] columnNames = new String[]{"id", "Predstava","Discount","Number of seats","Watched"};
/**
 * konstruktor klase gde se setuje rezervacija
 * @param rezervacija objekat koji zelimo da prikazemo
 */
    public ReservationsTableModel(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    @Override
    public int getRowCount() {
        if (rezervacija != null) {
            return rezervacija.getStavke().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRezervacije item = rezervacija.getStavke().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getStavkaId();
            case 1:
                return item.getPredstavaId().getNaziv();
            case 2:
                return item.getPopust();
            case 3:
                return item.getBrojSedista();
            case 4:
                return item.isGledato();
        
            default:
                return "n/a";
        }
    }
/**
 * Metoda za dodavanje stavki u rezervaciju
 * @param predstava predstavlja predstavu koju korisnik zeli da gleda
 * @param quantity koliko mesta zeli za datu predstavu
 * @param discount koliki popust ostvaruje
 * @param watched  da li je vec gledao predstavu
 */
    public void addInvoiceItem(Predstava predstava, Integer quantity, Integer discount,boolean watched) {
        StavkaRezervacije item = new StavkaRezervacije();
        item.setRezervacijaId(rezervacija);
        item.setStavkaId(rezervacija.getStavke().size() + 1);
        item.setPredstavaId(predstava);
        item.setPopust(discount);
        item.setBrojSedista(quantity);
        item.setGledato(watched);
        rezervacija.getStavke().add(item);
        fireTableRowsInserted(rezervacija.getStavke().size() - 1, rezervacija.getStavke().size() - 1);
    }
/**
 * metoda koja izbacuje stavku iz rezervacije
 * @param rowIndex oznacava red u kom se nalazi stavka za izbacivanje
 */
    public void removeInvoiceItem(int rowIndex) {
        StavkaRezervacije item = rezervacija.getStavke().get(rowIndex);
        rezervacija.getStavke().remove(rowIndex);
        //setOrderNumbers();
        fireTableRowsDeleted(rezervacija.getStavke().size() - 1, rezervacija.getStavke().size() - 1);
    }
/**
 * da se lepo setuju id od stavke rezrvacije
 */
    private void setOrderNumbers() {
        int orderNo = 0;
        for (StavkaRezervacije item : rezervacija.getStavke()) {
            item.setStavkaId(++orderNo);
        }
    }
/**
 * vracanje rezervacije
 * @return rezervacija
 */
    public Rezervacija getInvoice() {
        return rezervacija;
    }

}
