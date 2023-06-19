package menues;

import holding.BaseDeDatosSingleton;
import holding.Ciudad;
import holding.Consola;

import java.util.HashMap;

public class MenuEliminarCiudad implements CapazDeEjecutarAccionMenu{
    private static BaseDeDatosSingleton bd;
    public MenuEliminarCiudad(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la ciudad a elminar");
        System.out.println("**No se podran eliminar ciudades en uso por Empresas**");
        bd.listarCiudades();
        HashMap<Integer, Ciudad> ciudades = bd.obtenerCiudades();
        int opcion = 0;
        while(opcion<1 || opcion>ciudades.size()){
            opcion = Consola.leerEntero();
        }
        Ciudad ciudadSeleccionada = ciudades.get(opcion);
        if(!bd.ciudadEnUso(ciudadSeleccionada)){
            bd.eliminarCiudad(opcion);
            System.out.println("Ciudad "+ciudadSeleccionada.getNombre()+ " eliminada!");
        }
        else{
            System.out.println("Imposible eliminar la ciudad! La misma esta siendo utilizada por:");
            bd.listarEmpresasQueUtilizanCiudad(ciudadSeleccionada);
        }
    }
}
