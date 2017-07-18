/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lesson11;

import java.io.Serializable;

/**
 *
 * @author RENT
 */
public class User implements Serializable {
    
    public String name;
    public String lastName;

    User(Object readObject) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    
    }

    User() {
        name = "";
        lastName = "";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
