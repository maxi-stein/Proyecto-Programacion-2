package holding;

import java.util.HashMap;

public class MenuEliminarUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;
    @Override
    public void ejecutar() {
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        bd.listarUsuarios();
        int key = elegirUsuario();
        bd.eliminarUsuario(key);
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
