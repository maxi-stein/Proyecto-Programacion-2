package menues;

import holding.AreasMercado;
import holding.BaseDeDatosSingleton;
import holding.Consola;

import java.util.HashMap;

public class MenuModificarAreaDeMercado implements CapazDeEjecutarAccionMenu {
    private HashMap<Integer, AreasMercado> areas;
    public MenuModificarAreaDeMercado(){
        areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione el area a modificar:");
        BaseDeDatosSingleton.listarAreasDeMercado();
        int keyAreaMercado = seleccionarAreaMercado(areas);
        System.out.println("Determine que atributo modificar:");
        System.out.print("1 - Nombre \t"+" 2- Descripción"+"\n"+
                "3 - Salir\n");
        int opcion = 0;
        while(opcion<1 || opcion>3){
            opcion = Consola.leerEntero();
        }
        AreasMercado areaSeleccionada = areas.get(keyAreaMercado);
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                String nombre = Consola.leerString();
                areaSeleccionada.setNombre(nombre.toUpperCase());
                System.out.println("Nombre modificado!: "+areaSeleccionada);
                break;
            case 2:
                System.out.println("Ingrese nueva Descripción:");
                String descripcion = Consola.leerString();
                areaSeleccionada.setDescripcion(descripcion);
                System.out.println("Descripción modificada!: "+areaSeleccionada);
                break;
            default:
                break;
        }

    }
    private int seleccionarAreaMercado(HashMap<Integer, AreasMercado> areaMercado){
        int opcion = 0;
        while (opcion<1 || opcion>areaMercado.size()){
            opcion = Consola.leerEntero();
        }
        return opcion;
    }

}
