package usuario;

public class Usuario {
    private String login;
    private String password;

    public void iniciarSesion(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setPassword(String newpassword) {
        this.password = newpassword;
    }

    private void checkPassword(String loign, String password) {
        
    }
}
