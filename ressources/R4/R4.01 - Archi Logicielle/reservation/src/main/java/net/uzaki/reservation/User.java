package net.uzaki.reservation;

public class User {
    protected String id;
    protected String name;
    protected String pwd;
    protected String mail;

    public User(){}

    public User(String id, String name, String pwd, String mail){
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String toString(){
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
