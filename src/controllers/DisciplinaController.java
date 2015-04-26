/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.KeyNotExistsException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Disciplina;
import views.*;
import scoa.Controller;
import scoa.Model;
import scoa.Request;
import scoa.View;

/**
 *
 * @author JoãoVitor
 */
public class DisciplinaController extends Controller {
    
    public void index(Request request){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Cálculo I");
        disciplina.save();
        
        ArrayList<Object> resultados = new Disciplina().all();
        View.make(new listaDisciplinasView(resultados));
    }
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
