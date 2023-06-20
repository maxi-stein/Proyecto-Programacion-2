package menues;

import holding.BaseDeDatosSingleton;
import holding.Ciudad;
import holding.Consola;

import java.util.HashMap;

public class MenuEliminarCiudad implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer, Ciudad> ciudades;
    public MenuEliminarCiudad(){
        ciudades = BaseDeDatosSingleton.obtenerCiudades();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la ciudad a elminar");
        System.out.println("**No se podran eliminar ciudades en uso por Empresas**");
        BaseDeDatosSingleton.listarCiudades();
        int opcion = 0;
        while(opcion<1 || opcion>ciudades.size()){
            opcion = Consola.leerEntero();
        }
        Ciudad ciudadSeleccionada = ciudades.get(opcion);
        if(!BaseDeDatosSingleton.ciudadEnUso(ciudadSeleccionada)){
            BaseDeDatosSingleton.eliminarCiudad(opcion);
            System.out.println("Ciudad "+ciudadSeleccionada.getNombre()+ " eliminada!");
        }
        else{
            System.out.println("Imposible eliminar la ciudad! La misma esta siendo utilizada por:");
            BaseDeDatosSingleton.listarEmpresasQueUtilizanCiudad(ciudadSeleccionada);
        }
    }
}
