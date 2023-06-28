/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.validator.components;

import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;
/**
 * Klasa OcenaValidatoror koja implementira interfejs IValidator i njegovu metodu validate
 * Proverava da li ocena je u formi broja i da nije manji od 1 ili veci od 10
 * @author Andjy
 */
public class OcenaValidator implements IValidator{

    @Override
    public void validate(String value) throws ValidatorException {
        Integer ocena = Integer.parseInt(value);
        
        if(ocena<1 || ocena>10){
            throw new ValidatorException("Ocena mora biti u intervalu od 1 do 10");
        }
    }
    
}
