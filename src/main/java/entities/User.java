package entities;

public class User {
    private int id_user;
    private String nomuser;
    private String prenomuser;
    private String AdrUser;
    private String EmailUsr;
    private String password;
    private int Numtel;
    private String Role;

    public User() {
    }

    public User(int id_user, String nomuser, String prenomuser, String adrUser, String emailUsr,String password, int numtel, String role) {
        this.id_user = id_user;
        this.nomuser = nomuser;
        this.prenomuser = prenomuser;
        AdrUser = adrUser;
        EmailUsr = emailUsr;
        Numtel = numtel;
        this.password = password;
        Role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getPrenomuser() {
        return prenomuser;
    }

    public void setPrenomuser(String prenomuser) {
        this.prenomuser = prenomuser;
    }

    public String getAdrUser() {
        return AdrUser;
    }

    public void setAdrUser(String adrUser) {
        AdrUser = adrUser;
    }

    public String getEmailUsr() {
        return EmailUsr;
    }

    public void setEmailUsr(String emailUsr) {
        EmailUsr = emailUsr;
    }

    public int getNumtel() {
        return Numtel;
    }

    public void setNumtel(int numtel) {
        Numtel = numtel;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
