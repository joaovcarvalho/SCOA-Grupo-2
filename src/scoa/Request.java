/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoa;

import exceptions.KeyNotExistsException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoãoVitor
 */
public class Request {
    private HashMap fields;
    private Controller controller;
    private String action;
    
    public Request(Controller controller, String action){
        this.fields = new HashMap();
        this.controller = controller;
        this.action = action;
    }
    
    public void send(){
        try {
            Method method = controller.getClass().getMethod(action, Request.class);    
            method.invoke(controller, this);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } catch( Exception ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public void addInput(String key, Object value){
        this.fields.put(key, value);
    }
    
    public Object input(String key) throws KeyNotExistsException{
        if(this.fields.containsKey(key)){
            return this.fields.get(key);
        }else{
            throw new KeyNotExistsException("Key is not set.");
        }
    }
    
    
    
    
}
