/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.validator.components;

import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;



/**
 *
 * @author Andjy
 */
public class EmailValidator implements IValidator{

    @Override
    public void validate(String value) throws ValidatorException {
        if(value==null || value.isEmpty()){
            throw new ValidatorException("Polje je obavezno");
        }
           
        if(!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new ValidatorException("Neispravan unos email-a");
        }
        
    }
    
}
