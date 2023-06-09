package menues;

import holding.AreasMercado;
import holding.BaseDeDatosSingleton;
import holding.Consola;

import java.util.HashMap;

public class MenuEliminarAreaDeMercado implements CapazDeEjecutarAccionMenu {
    private HashMap<Integer, AreasMercado> areas;
    public MenuEliminarAreaDeMercado() {
        areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione el area a Eliminar");
        System.out.println("**No se podran eliminar Areas de Mercado en uso por Empresas**");
        BaseDeDatosSingleton.listarAreasDeMercado();

        int opcion = 0;
        while(opcion<1 || opcion>areas.size()){
            opcion = Consola.leerEntero();
        }
        AreasMercado areaSeleccionada = areas.get(opcion);
        if(!BaseDeDatosSingleton.areaEnUso(areaSeleccionada)){
            BaseDeDatosSingleton.eliminarAreaDeMercado(opcion);
            System.out.println("Area de Mercado "+areaSeleccionada.getNombre()+ " eliminada!");
        }
        else{
            System.out.println("Imposible eliminar el Area de Mercado! Se encuentra utilizada por:");
            BaseDeDatosSingleton.listarEmpresasQueUtilizanAreaMercado(areaSeleccionada);
        }
    }
}
