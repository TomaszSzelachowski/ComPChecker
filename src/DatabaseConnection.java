

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom
 */
import java.sql.DriverManager;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseConnection {

    public static Connection establishConnection() {    //This method connects to the database and returns the connection.

        try {
 
            String host = "jdbc:mysql://213.104.129.95:3306/ComPChecker";   //Location of mySQL server
            String uName = "root";          
            String uPass = "root";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            System.out.println("Connected database successfully...");

            return con;
        } catch (SQLException err) {
            System.out.println(err.getMessage());   //Prints out SQL error 
            return null;
        }
    }
    
    public void closeConnection(Connection con){
    
        try{
        con.close();
        }catch(SQLException err){
        
        
        }
    
    }

}
