/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.validator.components;

import rs.ac.bg.fon.ps.validator.IValidator;
import rs.ac.bg.fon.ps.validator.ValidatorException;

/**
 *
 * @author Vuk
 */
public class TextValidator implements IValidator{

    @Override
    public void validate(String value) throws ValidatorException {
         if(value == null || value.isEmpty()){
            throw new ValidatorException("Polje je obavezno!");
        }
        
        if(!value.matches("[a-zA-Z_ ]*$")){
            throw new ValidatorException("Polje mora da sadrzi samo slova");
        }
        
        if(value.length()>100){
            throw new ValidatorException("Polje moze da sadrzi maksimalno 100 znakova");
        }
    }
    
}
