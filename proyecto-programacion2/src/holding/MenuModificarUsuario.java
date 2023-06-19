package holding;

import java.util.HashMap;

public class MenuModificarUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;
    @Override
    public void ejecutar() {
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        System.out.println("Elija el usuario a modificar");
        bd.listarUsuarios();
        int key = elegirUsuario();
        bd.modificarUsuario(key);
    }
    private int elegirUsuario(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        int key;
        do{
            key = Consola.leerEntero();
        }while(!bd.contieneUsuario(key));
        return key;
    }
}
