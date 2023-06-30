/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.componenet.table;

import java.time.LocalDateTime;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;


/**
 * Model za prikaz tabele predstava
 * @author Andjy
 */
public class ShowsTableModel extends AbstractTableModel{
    /**
     * imena kolona su id name place time capacity
     */
    private final String[] columnNames= {"ID","Name","Place","Time","Capacity"};
    /**
     * lista predstavi koja ce se prikazivati na tabeli
     */
    private final List<Predstava> shows;
/**
 * Konstruktor klase koji setuje listu
 * @param shows lista predstavi
 */
    public ShowsTableModel(List<Predstava> shows) {
        this.shows = shows;
    }

    @Override
    public String getColumnName(int column) {
        if (column>columnNames.length) return "n/a";
        return columnNames[column]; 
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Predstava predstava = shows.get(rowIndex);
        switch(columnIndex){
            case 0:
                predstava.setId((Integer)value);
                break;
            case 1:
                predstava.setNaziv(String.valueOf(value));
                break;
            case 2:
                predstava.setMesto(String.valueOf(value));
                break;
                
            case 3:
                predstava.setVreme((LocalDateTime) value);
                break;
            case 4:
                predstava.setKapacitet((Integer)value);
                
        }
    }
    
    @Override
    public int getRowCount() {
        if (shows==null) return 0;
        return shows.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Predstava pr = shows.get(rowIndex);
        switch(columnIndex){
            case 0: return pr.getPredstavaId();
            case 1: return pr.getNaziv();
            case 2: return pr.getMesto();
            case 3: return pr.getVreme();
            case 4: return pr.getKapacitet();
            default:
                return "n/a";
        }
    }
/**
 * Metoda koja dodaju predstavu na listu predstavi.
 * @param product predstava koja se dodaje
 */
    public void addProduct(Predstava product) {
        shows.add(product);
        //fireTableDataChanged();
        fireTableRowsInserted(shows.size()-1, shows.size()-1);
    }
/**
 * vrati predstavu u tom redu
 * @param row iz kog treba da se vrati
 * @return predstavu u zadatom redu
 */
    public Predstava getProductAt(int row) {
        return shows.get(row);
    }
    
}
