/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author JoãoVitor
 */
public class DataAccessObject {
    private static boolean status = false;
    private static Connection connection = null;

    //Método de Conexão
    public static java.sql.Connection initConnection() {
        try {
            // Driver JDBC padrão
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados
            String server = "localhost";    //Caminho do servidor do BD
            String port = "3306";   //Porta utilizada pelo BD
            String db_name = "scoa";       //Nome do seu banco de dados, schema
            String url = "jdbc:mysql://" + server + ":" + port + "/" + db_name;
            String user = "root";        //Nome do usuário de seu BD
            String password = "1234";      //Senha do usuario
            connection = DriverManager.getConnection(url, user, password);

            //Teste de conexão  
            status = (connection != null);
            return connection;
            
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("Driver not found!");
            return null;
        } catch (SQLException e) {  //Não conseguindo se conectar ao banco
            System.out.println("Not possible connect to database.");
            return null;
        }
    }

    //Método que retorna o status da conexão
    public static boolean getStatus() {
        return status;
    }

    //Método para fechar a conexão
    public static boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false; 
        }
    }

    //Método para reiniciar a conexão
    public static boolean reiniciaConexao() {        
        try {
            closeConnection();
            connection = initConnection();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
}
