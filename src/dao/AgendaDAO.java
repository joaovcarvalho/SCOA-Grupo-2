/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataAccessObject.closeConnection;
import static dao.DataAccessObject.getConnection;
import static dao.DataAccessObject.initConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agenda;
import model.Professor;
import model.Room;
import model.Subject;

/**
 *
 * @author JoãoVitor
 */
public class AgendaDAO extends DataAccessObject{
    public static ArrayList<Agenda> getAgendasByClassId(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM agendas WHERE id_class = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        ArrayList<Agenda> agendas = new ArrayList<>();
        try{
            while (rs.next()) {
                Date date = rs.getDate("date");
                int id_agenda = rs.getInt("id");
                String description = rs.getString("description");
                model.Class myClass = ClassDAO.getClassById(rs.getInt("id_class"));
                Agenda a = new Agenda(id, description, date, myClass);
 
                agendas.add(a);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return agendas;
    }
}
