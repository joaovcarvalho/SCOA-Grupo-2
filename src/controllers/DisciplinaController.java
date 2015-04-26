/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.KeyNotExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.InsereDisciplinaView;
import scoa.Controller;
import scoa.Request;
import scoa.View;

/**
 *
 * @author JoãoVitor
 */
public class DisciplinaController extends Controller {
    public void insert(Request request){
        View.make(new InsereDisciplinaView());
   }
    
   public void store(Request request){
        try {
            System.out.println(request.input("nome"));
        } catch (KeyNotExistsException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
