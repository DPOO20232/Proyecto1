package usuario;

import java.util.List;

public class Usuario {
    private String login;
    private String password;
    private static List<Usuario> credenciales;
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public boolean iniciarSesion(String login, String password) {
        boolean retorno = false;
        for (Usuario i : credenciales) {
            if (i.login.equals(login) && i.password.equals(password)) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String newpassword) {
        this.password = newpassword;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
}
