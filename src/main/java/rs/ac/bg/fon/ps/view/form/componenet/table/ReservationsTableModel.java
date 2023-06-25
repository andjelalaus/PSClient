/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.componenet.table;

import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.domain.Rezervacija;

/**
 *
 * @author Milos Milic
 */
public class ReservationsTableModel extends AbstractTableModel {

    private final Rezervacija rezervacija;
    private final String[] columnNames = new String[]{"id", "Predstava","Discount","Number of seats","Watched"};

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

    public void removeInvoiceItem(int rowIndex) {
        StavkaRezervacije item = rezervacija.getStavke().get(rowIndex);
        rezervacija.getStavke().remove(rowIndex);
        //setOrderNumbers();
        fireTableRowsDeleted(rezervacija.getStavke().size() - 1, rezervacija.getStavke().size() - 1);
    }

    private void setOrderNumbers() {
        int orderNo = 0;
        for (StavkaRezervacije item : rezervacija.getStavke()) {
            item.setStavkaId(++orderNo);
        }
    }

    public Rezervacija getInvoice() {
        return rezervacija;
    }

}
