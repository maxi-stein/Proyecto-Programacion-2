package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;
import holding.Pais;

import java.util.HashMap;

public class MenuModificarPais implements CapazDeEjecutarAccionMenu {
    private static BaseDeDatosSingleton bd;
    public MenuModificarPais(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione el pais a modificar:");
        bd.listarPaises();
        HashMap<Integer, Pais> paises = bd.obtenerPaises();
        int keyPais = seleccionarPais(paises);
        System.out.println("Determine que atributo modificar:");
        System.out.print("1 - Nombre '\t'"+" 2- PBI \t"+"3 - Numero de habitantes\n"+
                "4 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>3){
            opcion = Consola.leerEntero();
        }
        Pais paisSeleccionado = paises.get(opcion);
        switch (opcion){
            case 1:
                System.out.println("Determine el nuevo nombre:");
                String nombre = Consola.leerString();
                paisSeleccionado.setNombre(nombre);
                System.out.println("Nombre modificado!"+paisSeleccionado);
                break;
            case 2:
                System.out.println("Determine el PBI:");
                double pbi = Consola.leerDouble();
                paisSeleccionado.setPbi(pbi);
                System.out.println("PBI modificado!"+paisSeleccionado);
                break;
            case 3:
                System.out.println("determine el numero de habitantes:");
                int numHab = Consola.leerEntero();
                paisSeleccionado.setNumHabitantes(numHab);
                System.out.println("Numero de habitantes modificado!"+paisSeleccionado);
                break;
            default:
                break;
        }
    }
    private int seleccionarPais(HashMap<Integer, Pais> paises){
        int opcion = 0;
        while (opcion<1 || opcion>paises.size()){
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
}
