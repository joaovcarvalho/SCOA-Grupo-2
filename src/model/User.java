
package model;


public class User {

    private int id;
    private String cpf;
    private String password;
    private Type type;
    private int type_id;

    public User(int id, String cpf, String password, Type type,int type_id ) {
        this.id = id;
        this.cpf = cpf;
        this.password = password;
        this.type = type;
        this.type_id = type_id;
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

    /**
     * @return the type_id
     */
    public int getType_id() {
        return type_id;
    }

    /**
     * @param type_id the type_id to set
     */
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    
    
    
    
    
    
    
    
}
