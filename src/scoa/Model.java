/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoãoVitor
 */
public abstract class Model {
  protected static Connection connect = null;
  protected static Statement statement = null;
  protected PreparedStatement preparedStatement = null;
  protected static ResultSet resultSet = null;
  protected static String table = null;
  
  protected String getModelName(){ return null; };
  
  public ArrayList<Object> all(){
      
      if(getModelName() == null){
          System.err.println("Table name not defined! Override the method getModelName");
      }
      
      try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/scoa?"
              + "user=scoauser&password=scoauser");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      ResultSet resultSet = statement
          .executeQuery("select * from scoa."+getModelName());
      
      ArrayList<Object> results = new ArrayList<Object>();
      while (resultSet.next()) {
        // It is possible to get the columns via name
        // also possible to get the columns via the column number
        // which starts at 1
        // e.g. resultSet.getSTring(2);

        Object result = dataToObject(resultSet);
        results.add(result);
      }
      
      return results;
      
      } catch (Exception e) {
          System.err.println(e.toString());
      } finally {
        close();
      } 

      return new ArrayList<Object>();
  }
  
  
  protected abstract Object dataToObject(ResultSet resultSet) throws SQLException;
  protected abstract HashMap<String, String> objectToData();
  
  public void save(){
 
  }
  
  // You need to close the resultSet
  protected static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
}
