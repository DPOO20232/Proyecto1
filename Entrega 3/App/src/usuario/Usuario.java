package usuario;

public class Usuario {
    private String login;
    private String password;

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public boolean iniciarSesion(String login, String password) {
        if (this.login.equals(login) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void setPassword(String newpassword) {
        this.password = newpassword;
    }
}
