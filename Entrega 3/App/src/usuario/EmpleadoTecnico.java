package usuario;

import inventario.Sede;

public class EmpleadoTecnico extends personal{
    public EmpleadoTecnico(String login, String password, String tipoPersonal, Sede sede) {
        /**
         * Crea un nuevo empleado técnico con la información de inicio de sesión, tipo de personal y sede especificados.
         *
         * @param login       El nombre de usuario para iniciar sesión.
         * @param password    La contraseña para iniciar sesión.
         * @param tipoPersonal El tipo de personal que identifica al empleado técnico.
         * @param sede        La sede a la que está asignado el empleado técnico.
         */
        super(login, password, tipoPersonal, sede);
        
    }
    
}
