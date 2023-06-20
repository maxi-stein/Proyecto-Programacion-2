package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;

public class MenuModificarUsuario implements CapazDeEjecutarAccionMenu{
    @Override
    public void ejecutar() {
        System.out.println("Elija el usuario a modificar");
        BaseDeDatosSingleton.listarUsuarios();
        int key = elegirUsuario();
        BaseDeDatosSingleton.modificarUsuario(key);
    }
    private int elegirUsuario(){
        int key;
        do{
            key = Consola.leerEntero();
        }while(!BaseDeDatosSingleton.contieneUsuario(key));
        return key;
    }
}
