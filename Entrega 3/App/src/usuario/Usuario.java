package usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login;
    private String password;
    private static List<Usuario> credenciales;
    private static List<Cliente> ListaClientes;
    private static List<Licencia> ListaLicencias;
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
    //no tiene sentido permitir cambio de nombre de usuario
    public void setPassword(String newpassword) {
        this.password = newpassword;
    }
        public boolean checkCredenciales(String login, String password) {
        boolean retorno = false;
        for (Usuario i : credenciales) {
            if (i.getLogin().equals(login) && i.getPassword().equals(password)) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
    public static List<Cliente> getListaClientes(){ return ListaClientes;}
    public static List<Licencia> getListaLicencias(){ return ListaLicencias;}
    public static void addCliente(Cliente cliente){
        if (ListaClientes==null){
            ListaClientes= new ArrayList<Cliente>();
        }
        ListaClientes.add(cliente);
    }
    public static void addLicencia(Licencia licencia){
        if (ListaLicencias==null){
            ListaLicencias= new ArrayList<Licencia>();
        }
        ListaLicencias.add(licencia);
    }
    public static Cliente assignCliente(int cedula){
        Cliente retorno=null;
        for(Cliente i: Usuario.getListaClientes()){
            if (i.getNumeroCedula()==cedula){
                retorno=i;
            }
        }
        return retorno;
    }
    public static Licencia assignLicencia(int num_licencia){
        Licencia retorno=null;
        for(Licencia i: Usuario.getListaLicencias()){
            if (i.getNumeroLicencia()==num_licencia){
                retorno=i;
                break;
            }
        }
        return retorno;
    }
}
