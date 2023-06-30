/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.communication;


import java.util.HashMap;


/**
 *Klasa za cuvanje hashmapa
 * ima opciju dodavanja i izvlacenja objekata u hsah mapama
 * @author Andjy
 */
public class LocalStorage {
    /**
     * objekat klase hash mape koji prvi parametar ima string drugi object
     */
    private static HashMap<String,Object> hashMap;
    /**
     * instanca date klase
     */
    private static LocalStorage instance;
    /**
     * bezparametarski konstruktor koji inicijalizuje hashmapu
     */
    private LocalStorage(){
        hashMap = new HashMap<>();
    }
    
    /**
     * Metoda za vracanje instance klase
     * pravi novu ukoliko je postojeca null
     * @return instancu date klase
     */
    public static LocalStorage getInstance(){
        if(instance==null){
            instance = new LocalStorage();
        }
        return instance;
    }
    /**
     * Metoda za dodavanje stringa i objekta u hash mapu
     * 
     * @param key je u string formatu naziv pod kojim cemo cuvati dati objekat
     * @param value je objekat koji zelimo da cuvamo
     */
    public void addItemsInHashMap(String key, Object value){
        hashMap.put(key, value);
    }
    /**
     * Metoda za vracanje objekta iz hash mape
     * 
     * @param key je u string formatu naziv pod kojim zleimo da nadjemo dati objekat
     * @return objekat klase Object
     */
    public Object getItemFromHashMap(String key){
        if(hashMap.containsKey(key)){
            return hashMap.get(key);
        }
        return null;
    }
    
     /**
     * Metoda za brisanje objekta iz hash mape
     * nalazi se pomocu key vrednosti
     * @param key je u string formatu naziv pod kojim zleimo da nadjemo dati objekat za brisanje
     * 
     */
    public void removeItemFromHashMap(String key){
        hashMap.remove(key);
    }
    
    
}
