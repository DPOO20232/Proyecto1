package usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login;
    private String password;
    private static List<Usuario> credenciales;
    private static List<Cliente> ListaClientes;
    private static List<Licencia> ListaLicencias;
    private static List<String> ListaNombresLogins;
    private static List<Integer> ListaNumCedulas;
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
    public static List<String> getListaNombresLogins(){ return ListaNombresLogins;}
    public static List<Integer> getListaNumCedulas(){ return ListaNumCedulas;}

    public static void addCliente(Cliente cliente){
        if (getListaClientes()==null){
            ListaClientes= new ArrayList<Cliente>();
        }
        ListaClientes.add(cliente);
    }
    public static void addLicencia(Licencia licencia){
        if (getListaLicencias()==null){
            ListaLicencias= new ArrayList<Licencia>();
        }
        ListaLicencias.add(licencia);
    }
    public static void addNombreLogin(String nom){
        if (getListaNombresLogins()==null){
            ListaNombresLogins= new ArrayList<String>();
        }
        ListaNombresLogins.add(nom);
    }
    public static void addNumCedulas(int cedula){
        if (getListaNumCedulas()==null){
            ListaNumCedulas= new ArrayList<Integer>();
        }
        ListaNumCedulas.add(cedula);
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
    public static Cliente checkLoginCliente(String login, String password){
        Cliente retorno=null;
        for (Cliente i: getListaClientes()){
        if ((login.equals(i.getLogin()))&&(password.equals(i.getPassword()))){
            retorno=i;
            break;
        }}
        return retorno;
    }
    public static boolean checkNombresLogins(String login){
        boolean encontrado=false;
        for(String i: getListaNombresLogins()){
            if (i.equals(login)){encontrado=true;break;}
        }
        return encontrado;
    }
    public static boolean checkCedulas(int cedula){
        boolean retorno=false;
        for (int i: getListaNumCedulas()){
            if (i==cedula){
                retorno=true;
                break;
            }
        }
        return retorno;
    }
    public static boolean checkLicencia(int num_licencia){
        boolean retorno=false;
        for(Licencia i: getListaLicencias()){
            if(i.getNumeroLicencia()==num_licencia){
                retorno=true;
                break;
            }
        }
        return retorno;
    }
    public static boolean checkVencimientoLicencia(int num_licencia){
        for (Licencia i: getListaLicencias()){
            
        }
    }
}
