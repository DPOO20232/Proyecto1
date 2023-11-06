package modelo;

public class AdminLocal extends personal {
    public AdminLocal(String login, String password, String tipoPersonal, Sede sede) {
        /**
         * Constructor de la clase AdminLocal.
         *
         * @param login       Nombre de usuario para iniciar sesión como administrador local.
         * @param password    Contraseña para iniciar sesión como administrador local.
         * @param tipoPersonal Tipo de personal (puede representar el rol del administrador local).
         * @param sede        Sede a la que está asociado el administrador local.
         */
        super(login, password, tipoPersonal, sede);
        
    }
}
