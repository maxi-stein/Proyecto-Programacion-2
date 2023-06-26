package holding;

import menues.MenuPrincipalAdmin;

import java.util.HashMap;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }
    public Admin(){
        super();
    }
    @Override
    public void proceder() {
        MenuPrincipalAdmin mp = new MenuPrincipalAdmin();
        mp.ejecutar();
    }
    @Override
    public void mostrarInfo() {
        super.mostrarCredenciales();
        System.out.println("Empresas registradas:");
        HashMap<Integer,Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
        for(int i=1;i<=empresas.size();i++){
            System.out.println(empresas.get(i).toString());
        }
        System.out.println("Usuarios registrados: ");
        HashMap<Integer,Usuario> usuarios = BaseDeDatosSingleton.obtenerUsuarios();
        for(int i=1;i<=usuarios.size();i++){
            if(usuarios.get(i).noEstaBloqueado()){
                System.out.println(usuarios.get(i).toString());
            }
        }
        System.out.println("Las areas de mercado registradas son: ");
        HashMap<Integer,AreasMercado> areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
        for(int i = 1;i<=areas.size();i++){
            if(areas.get(i).noEstaBloqueado()){
                System.out.println(areas.get(i).getNombre());
            }
        }
        System.out.println("Las ciudades registradas son: ");
        HashMap<Integer,Ciudad> ciudades = BaseDeDatosSingleton.obtenerCiudades();
        for(int i=1;i<=ciudades.size();i++){
            System.out.println(ciudades.get(i).toString());
        }
    }
    @Override
    public void modificar() {
        super.modificar();
        int opcion = 0;
        while(opcion<1 || opcion>4){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
        switch (opcion){
            case 1:
                String nombre = Consola.leerString();
                setNombre(nombre);
                break;
            case 2:
                String pass = Consola.leerString();
                setPass(pass);
                break;
            case 3:
                String direccion = Consola.leerString();
                setDireccion(direccion);
                break;
        }
    }
}
