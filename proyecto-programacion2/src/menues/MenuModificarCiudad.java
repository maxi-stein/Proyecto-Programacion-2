package menues;

import holding.BaseDeDatosSingleton;
import holding.Ciudad;
import holding.Consola;
import holding.Pais;

import java.util.HashMap;

public class MenuModificarCiudad implements CapazDeEjecutarAccionMenu{
    private static BaseDeDatosSingleton bd;
    public MenuModificarCiudad(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la ciudad a modificar:");
        bd.listarCiudades();
        HashMap<Integer, Ciudad> ciudades = bd.obtenerCiudades();
        int keyCiudad = seleccionarCiudad(ciudades);
        System.out.println("Determine que atributo modificar:");
        System.out.print("1 - Nombre '\t'"+" 2- Pais Origen \n"+
                "3 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>3){
            opcion = Consola.leerEntero();
        }
        Ciudad ciudadSeleccionada = ciudades.get(opcion);
        switch (opcion){
            case 1:
                System.out.println("Determine el nuevo nombre:");
                String nombre = Consola.leerString();
                ciudadSeleccionada.setNombre(nombre);
                System.out.println("Nombre modificado!"+ciudades.get(keyCiudad));
                break;
            case 2:
                System.out.println("Seleccione el pais:");
                bd.listarPaises();
                HashMap<Integer, Pais> paises = bd.obtenerPaises();
                int keyPais = 0;
                while(keyPais<1 || keyPais>paises.size()){
                    keyPais=Consola.leerEntero();
                }
                ciudadSeleccionada.setPaisOrigen(paises.get(keyPais));
                System.out.println("Pais origen modificado!: \n"+ciudadSeleccionada);
                break;
            default:
                break;
        }
    }
    private int seleccionarCiudad(HashMap<Integer, Ciudad> ciudades){
        int opcion = 0;
        while (opcion<1 || opcion>ciudades.size()){
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
}
