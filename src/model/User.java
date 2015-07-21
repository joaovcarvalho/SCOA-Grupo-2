
package model;


public class User {

    private int id;
    private String cpf;
    private String password;
    private Type type;

    public User(String cpf, String password, Type type) {
        this.cpf = cpf;
        this.password = password;
        this.type = type;
    }

    public User(int id, String cpf, String password, Type type) {
        this.id = id;
        this.cpf = cpf;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    
    
    
    
    
    
    
}
