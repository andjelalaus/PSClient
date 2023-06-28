/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.util;

/**
 *Enum koji ima tri opcije za prikaz forme
 * @author Andjy
 */
public enum FormMode {
    /**
     * Prikaz forme u kom su sve vrednosti prazne i unose se rucno
     * i zatim je moguce sacuvati objekat
     */
    FORM_ADD, 
    /**
     * Prikaz forme gde sve vrednosti mogu da se menjaju ali ne moze da se doda kao nov objekat
     * vec kao postojeci izmenjen
     */
    FORM_EDIT, 
    /**
     * Prikaz forme gde nista ne moze da se menja ali sve moze da se vidi i eventualno obrise objekat
     */
    FORM_VIEW
}
