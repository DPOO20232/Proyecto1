package usuario;
import inventario.Sede;

public class Personal {
    private Usuario usuario;
    private String tipoPersonal;
    private Sede sede;

    public Personal(Usuario usuario, String tipoPersonal, Sede sede) {
        this.usuario = usuario;
        this.tipoPersonal = tipoPersonal;
        this.sede = sede;
    }
    
}
