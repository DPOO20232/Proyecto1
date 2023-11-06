package modelo;

public class Admin extends Usuario{

    public Admin(String login, String password) {
        /**
         * Constructor de la clase Admin.
         *
         * @param login    Nombre de usuario para iniciar sesión como administrador.
         * @param password Contraseña para iniciar sesión como administrador.
         */
        super(login, password);
    }
    
}
