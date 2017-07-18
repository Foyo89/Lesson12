/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lesson11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author RENT
 */
public class JDBCTutorial {
    
    private static String url = "jdbc:mysql://localhost/books_db?serverTimezone=CET&useSSL=false";
    private static String userName = "root";
    private static String password = "root";
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        DBConnectionManager dbConnectionManager = new DBConnectionManager(url, userName, password);
        Connection connection = dbConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM users";
        preparedStatement = connection.prepareStatement(sql);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        System.out.println(firstName+ " "+ lastName);
        }
    }
    
}
