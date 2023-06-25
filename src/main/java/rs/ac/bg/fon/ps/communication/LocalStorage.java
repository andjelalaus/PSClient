/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vuk
 */
public class LocalStorage {
    
    private static HashMap<String,Object> hashMap;
    private static LocalStorage instance;
    
    private LocalStorage(){
        hashMap = new HashMap<>();
    }
    
    
    public static LocalStorage getInstance(){
        if(instance==null){
            instance = new LocalStorage();
        }
        return instance;
    }
    
    public void addItemsInHashMap(String key, Object value){
        hashMap.put(key, value);
    }
    
    public Object getItemFromHashMap(String key){
        if(hashMap.containsKey(key)){
            return hashMap.get(key);
        }
        return null;
    }
    
    
    public void removeItemFromHashMap(String key){
        hashMap.remove(key);
    }
    
    
}
