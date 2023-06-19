package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;
import holding.Pais;

public class MenuCrearPais implements CapazDeEjecutarAccionMenu{
    private static BaseDeDatosSingleton bd;
    public MenuCrearPais(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Determine el nombre del Pais");
        String nombre = Consola.leerString();
        System.out.println("Determine el PBI del pais");
        double pbi = Consola.leerDouble();
        Pais p = new Pais(nombre);
    }
}
