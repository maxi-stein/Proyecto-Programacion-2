package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;

public class MenuEliminarUsuario implements CapazDeEjecutarAccionMenu{
    @Override
    public void ejecutar() {
        BaseDeDatosSingleton.listarUsuarios();
        int key = elegirUsuario();
        BaseDeDatosSingleton.eliminarUsuario(key);
    }
    private int elegirUsuario(){
        int key;
        do{
            key = Consola.leerEntero();
        }while(!BaseDeDatosSingleton.contieneUsuario(key));
        return key;
    }
}
