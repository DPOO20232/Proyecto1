package usuario;

import inventario.Sede;

public class AdminLocal extends Personal {
    public AdminLocal(String login, String password, String tipoPersonal, Sede sede) {
        super(login, password, tipoPersonal, sede);
        
    }
}