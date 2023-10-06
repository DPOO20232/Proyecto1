package usuario;

import inventario.Sede;

public class EmpleadoAtencion extends Personal{
    public EmpleadoAtencion(String login, String password, String tipoPersonal, Sede sede) {
        super(login, password, tipoPersonal, sede);
        
    }
}
