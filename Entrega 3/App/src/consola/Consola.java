package consola;
import inventario.Inventario;
public class Consola {
    public static void MenuInicial(){
    //1) crear usuario tipo cliente
    //2) iniciar sesion   
    //admin (rf 1-14) (nico)
    //admin local(15-17)
    //cliente - personalatencion(19-20)
    //personalatencion(en reserva->21,25-27)
    //personalTecnico(20)
    //cliente (24-27)
    ;
    }
    public static void NuevoCliente(){
    //josé
    }
    public static void main(String[] args)
	{
    Inventario.loadSistema();
    MenuInicial();
    
	}
}
