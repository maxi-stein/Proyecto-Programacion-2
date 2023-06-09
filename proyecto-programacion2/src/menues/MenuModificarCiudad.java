package menues;

import holding.BaseDeDatosSingleton;
import holding.Ciudad;
import holding.Consola;
import holding.Pais;

import java.util.HashMap;

public class MenuModificarCiudad implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer, Ciudad> ciudades;
    private HashMap<Integer, Pais> paises;
    public MenuModificarCiudad(){
        ciudades = BaseDeDatosSingleton.obtenerCiudades();
        paises = BaseDeDatosSingleton.obtenerPaises();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la ciudad a modificar:");
        BaseDeDatosSingleton.listarCiudades();
        int keyCiudad = seleccionarCiudad(ciudades);
        System.out.println("Determine que atributo modificar:");
        System.out.print("1 - Nombre "+"\t2- Pais Origen \n"+
                "3 - Salir\n");
        int opcion = 0;
        while(opcion<1 || opcion>3){
            opcion = Consola.leerEntero();
        }
        Ciudad ciudadSeleccionada = ciudades.get(opcion);
        switch (opcion){
            case 1:
                boolean yaExiste = false;
                while(!yaExiste){
                    System.out.println("Determine el nuevo nombre:");
                    String nombre = Consola.leerString();
                    ciudadSeleccionada.setNombre(nombre);
                    for(int i=1;i<=ciudades.size();i++){
                        //evaluo si la ciudad es igual a otra ciudad dentro del mismo pais
                        if(ciudadSeleccionada.esIgual(ciudades.get(i)) && ciudadSeleccionada.tieneAlPais(ciudades.get(i).getPaisOrigen()) ){
                            System.out.println("Ese nombre ya le pertenece a otra ciudad del pais"+ciudadSeleccionada.getPaisOrigen().getNombre());
                            System.out.println("Intente de nuevo.");
                        }
                    }
                }

                System.out.println("Nombre modificado!"+ciudades.get(keyCiudad));
                break;
            case 2:
                System.out.println("Seleccione el pais:");
                BaseDeDatosSingleton.listarPaises();
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
