package usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login;
    private String password;
    private static List<Usuario> credenciales;
    private static List<Cliente> clientes;
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
    public static void addClientes(Cliente cliente){
        if (clientes==null){
            clientes= new ArrayList<Cliente>();
        }
        clientes.add(cliente);
    }
    public static List<Cliente> getClientes(){ return clientes;}
    public static Cliente assignCliente(int cedula){
        Cliente retorno=null;
        for(Cliente i: Usuario.getClientes()){
            if (i.getNumeroCedula()==cedula){
                retorno=i;
            }
        }
        return retorno;
    }
}
