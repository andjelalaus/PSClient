/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.validator;

/**
 * Klasa ValidatorException koja nasledjuje klasu Exception
 * Poseduje konstruktor koji baca izuzetak sa  porukom koja mu je prosledjena
 * @author Andjy
 */
public class ValidatorException extends Exception{
      /**
     * Konstruktor u kom se poziva super metoda klase Exception
     * @param message poruka koja se baca kao izuzetak
     */
    public ValidatorException(String message){
        super(message);
    }
}
