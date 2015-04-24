/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.InsereDisciplinaView;

/**
 *
 * @author JoãoVitor
 */
public class DisciplinaController {
    public void index(){
        new InsereDisciplinaView().setVisible(true);
    }
}
