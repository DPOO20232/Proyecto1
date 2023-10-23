package usuario;
import java.util.List;
import inventario.Sede;
import java.util.ArrayList;
public class personal extends Usuario{
    private String tipoPersonal;
    private Sede sede;
    private static Admin credencialAdmin;
    private static List<personal> credencialesPersonal;
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
    public String getTipoPersonal(){
        return this.tipoPersonal;
    }
    public static Admin getCredendialAdmin(){
        return credencialAdmin;
    }
    public static void setAdmin(Admin admin){
        credencialAdmin=admin;
    }
    public static boolean checkLoginAdmin(String login,String password){
        if ((login.equals(credencialAdmin.getLogin()))&&(password.equals(credencialAdmin.getPassword()))){
            return true;
        }
        else{
            return false;
        }
    }
    public static personal checkLoginPersonal(String login, String password){
        personal retorno=null;
        for (personal i: getCredencialesPersonal()){
        if ((login.equals(i.getLogin()))&&(password.equals(i.getPassword()))){
            retorno=i;
            break;
        }}
        return retorno;
    }
    public String tipoPersonal(){
        return this.tipoPersonal;
    }
    public Sede getSede(){
        return this.sede;
    }
    public static void addCredencialesPersonal(personal personal){
        if (credencialesPersonal==null){
            credencialesPersonal= new ArrayList<personal>();
        }
        credencialesPersonal.add(personal);


    }
    public static List<personal> getCredencialesPersonal(){
        return credencialesPersonal;
    }
    public static void printRegistroEmpleados(Sede sede){
        String inicio="> ";
        int cantidadPersonalAtencion=0;
        int cantidadPersonalTecnico=0;
        String key1 = "0 empleados de atención. ";
        String key2 = "0 empleados técnicos. ";
        String value1 = "";
        String value2 = "";
    
        if (sede.getAdminLocal() != null) {
            inicio =inicio+ "1 Administrador Local: " + sede.getAdminLocal().getLogin() + ". ";
        } else {
            inicio =inicio+ "0 Administradores Locales. ";
        }
    
        for (personal i : sede.getPersonalSede()) {
            String tipo=i.getTipoPersonal();
            String login=i.getLogin();
            if (tipo.equals("EmpleadoAtencion")) {
                cantidadPersonalAtencion+=1;
                key1 = cantidadPersonalAtencion + " Empleado(s) de atención: ";
                value1 =value1+ login + ", ";
            } else if (i.getTipoPersonal().equals("EmpleadoTecnico")) {
                cantidadPersonalTecnico+=1;
                key2 =cantidadPersonalTecnico + " Empleado(s) técnico(s): ";
                value2 =value2+ login + ", ";
            }
        }
    
        System.out.println(inicio + key1 + value1.substring(0, value1.length() - 2) +". "+ key2 + value2.substring(0, value2.length() - 2) + ".");
    }

}
