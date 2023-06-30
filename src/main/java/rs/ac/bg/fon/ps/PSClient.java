/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps;


import rs.ac.bg.fon.ps.view.form.FrmLogin;

/**
 *Klasa iz koje se poziva main metoda
 * startuje glavna forma za logovanje
 * @author Andjy
 */
public class PSClient {
    /**
     * main metoda u kojoj se poziva forma za logovanje
     * @param args  koji nije iskoriscen
     */
    public static void main(String[] args) {
        new FrmLogin().setVisible(true);
    }
}
