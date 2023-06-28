/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.ps.validator;



/**
 * Interfejs IValidator koji sluzi za koriscenje radi validacija podataka koji su stigli od korisnika
 * da bi mogle da se izvrse sistemske operacije neophodno ih je proveriti metodom validate
 * @author Andjy
 */
public interface IValidator {
     /**
     * Metoda proverava da li je email u formatu emai-a, da je prosledjena vrednost koja treba da bude broj zaista broj
     * da ocena moze biti u rasponu od 1 do 10 zakljucno sa tim vrednostima
     * nijedan string ne sme biti prazan ili null
     * popust mora biti od 0 do 100 uzimajuci te vrednosti u obzir
     * tekstualni podaci moraju imati samo slova i ne smeju biti duzi od 100 karaktera
     * @param value koja predstavlja String koji je dobijen kao vrednost atributa koji poseduje objekat nad kojim se vrsi sistemska operacija
     * @throws ValidatorException baca se ukoliko neki od navedenih uslova nije ispunjen
     */
    void validate(String value) throws ValidatorException;
}
