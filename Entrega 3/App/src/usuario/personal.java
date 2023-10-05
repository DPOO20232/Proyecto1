package usuario;
import inventario.Sede;

public class Personal extends Usuario{
    private String tipoPersonal;
    private Sede sede;
    private static Usuario credencialAdmin;
    public Personal(String login, String password, String tipoPersonal, Sede sede) {
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
        if ((login.equals(Personal.credencialAdmin.getLogin()))&&(password.equals(Personal.credencialAdmin.getPassword()))){
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
