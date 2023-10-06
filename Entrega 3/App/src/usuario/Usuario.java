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

    private String getLogin(){
        return this.login;
    }
    private String getPassword(){
        return this.password;
    }
    //no tiene sentido permitir cambio de nombre de usuario
    public void setPassword(String newpassword) {
        this.password = newpassword;
    }
        public boolean checkCredenciales(String login, String password) {
        boolean retorno = false;
        for (Usuario i : credenciales) {
            if (i.getLogin().equals(login) && i.getPassword().equals(password)) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
}
