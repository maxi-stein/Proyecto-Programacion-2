package holding;

import java.util.HashMap;

public class MenuCrearUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;
    @Override
    public void ejecutar() {
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        int opcion = elegirOpcion();
        FactoryUsuarios.crearUsuario(opcion);
    }
    private int elegirOpcion(){
        System.out.println("Seleccione el tipo de usuario a crear: ");
        int opcion=1;
        do {
            System.out.println("1 - Admin");
            System.out.println("2 - Vendedor");
            System.out.println("3 - Asesor");
            opcion = Consola.leerEntero();
        }while (opcion<1 || opcion>3);
        return opcion;
    }
}
