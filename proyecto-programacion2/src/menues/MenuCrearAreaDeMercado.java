package menues;

import holding.AreasMercado;
import holding.BaseDeDatosSingleton;
import holding.Consola;

public class MenuCrearAreaDeMercado implements CapazDeEjecutarAccionMenu {

    private BaseDeDatosSingleton bd;
    public MenuCrearAreaDeMercado(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Ingrese Nombre del Area de Mercado:");
        String nombre = Consola.leerString();
        System.out.println("Ingrese su correspondiente Descripcion:");
        String descripcion = Consola.leerString();
        AreasMercado nuevaArea = new AreasMercado(nombre, descripcion);
        bd.agregarAreaDeMercado(nuevaArea);
    }

}

