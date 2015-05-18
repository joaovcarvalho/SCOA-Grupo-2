/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import model.Type;
import model.User;

/**
 *
 * @author Amanda
 */
public class UserController {
    
    public void login(String CPF, String password){
     
       User user = new UserDAO().getUserByCPFandPassword(CPF, password);
       Type t = user.getType();
       System.out.println(user.getId());
    
    }
    
}
