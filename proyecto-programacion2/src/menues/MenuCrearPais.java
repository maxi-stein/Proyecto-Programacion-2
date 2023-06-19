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
        String nombre = Consola.leerString().toUpperCase();
        System.out.println("Determine el PBI del pais");
        double pbi = Consola.leerDouble();
        System.out.println("Ingrese el numero de habitantes");
        int numHabitantes = Consola.leerEntero();
        Pais p = new Pais(nombre,pbi,numHabitantes);
        if(bd.paisYaExiste(p)){
            System.out.println("EL pais "+p.getNombre()+" ya existe!");
        }
        else{
            bd.agregarPais(p);
            System.out.println("Se agregó el pais: \n"+ p.toString());
        }
    }
}
