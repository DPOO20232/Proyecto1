package usuario;
import inventario.Sede;

public class personal extends Usuario{
    private String tipoPersonal;
    private Sede sede;
    private static Usuario credencialAdmin;
    public personal(String login, String password, String tipoPersonal, Sede sede) {
        super(login, password);
        this.tipoPersonal = tipoPersonal;
        this.sede = sede;
    }
    public void setTipoPersonal(String tipo){
        this.tipoPersonal=tipo;
    }
        public void setSede(Sede sede){
        this.sede=sede;
    }
    public static void setAdmin(Usuario admin){
        credencialAdmin=admin;
    }
    public boolean checkAdmin(String login,String password){
        if ((login.equals(credencialAdmin.getLogin()))&&(password.equals(credencialAdmin.getPassword()))){
            return true;
        }
        else{
            return false;
        }
    }
    public String tipoPersonal(){
        return this.tipoPersonal;
    }
    public Sede getSede(){
        return this.sede;
    }
}
