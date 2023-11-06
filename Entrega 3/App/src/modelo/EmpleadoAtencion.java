package modelo;

public class EmpleadoAtencion extends personal{
    public EmpleadoAtencion(String login, String password, String tipoPersonal, Sede sede) {
        /**
         * Crea un nuevo empleado de atención al cliente con la información de inicio de sesión, tipo de personal y sede especificados.
         *
         * @param login       El nombre de usuario para iniciar sesión.
         * @param password    La contraseña para iniciar sesión.
         * @param tipoPersonal El tipo de personal que identifica al empleado de atención al cliente.
         * @param sede        La sede a la que está asignado el empleado de atención al cliente.
         */
        super(login, password, tipoPersonal, sede);
        
    }
}
