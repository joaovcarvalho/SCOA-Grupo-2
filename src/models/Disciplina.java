/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import scoa.Model;

/**
 *
 * @author JoãoVitor
 */
public class Disciplina extends Model {
    
    private String id;
    private String nome;

    public Disciplina(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Disciplina() {
    }
    
    protected String getModelName(){ return "disciplinas"; }
    
    protected Object dataToObject(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        String id = resultSet.getString("id");
        String nome = resultSet.getString("nome");
        return new Disciplina(id, nome);
    }
    
    protected HashMap<String, String> objectToData(){
        HashMap<String, String> properties = new HashMap<String,String>();
        properties.put("id", this.id);
        properties.put("nome", this.nome);
        
        return properties;
    }
    
    public void save(){
        
        try{
          

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                .getConnection("jdbc:mysql://localhost/scoa?"
                    + "user=scoauser&password=scoauser");

            // PreparedStatements can use variables and are more efficient

            preparedStatement = Model.connect
                .prepareStatement("insert into scoa.disciplinas values( default, ?);");
            // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
            // Parameters start with 1


            preparedStatement.setString(1, this.nome);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          close();
        } 

    }
    
    public void update(){
        
        try{
          

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                .getConnection("jdbc:mysql://localhost/scoa?"
                    + "user=scoauser&password=scoauser");

            // PreparedStatements can use variables and are more efficient

            preparedStatement = Model.connect
                .prepareStatement("UPDATE scoa.disciplinas set nome = ? WHERE id = ?;");
            // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
            // Parameters start with 1


            preparedStatement.setString(1, this.nome);
            preparedStatement.setString(2, this.id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          close();
        } 

    }

    @Override
    public String toString() {
        return id + " | " + nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
