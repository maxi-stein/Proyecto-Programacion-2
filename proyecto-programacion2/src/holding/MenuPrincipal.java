package holding;

import java.util.HashMap;

public class MenuPrincipal implements CapazDeEjecutarAccionMenu {
    private HashMap<Integer,CapazDeEjecutarAccionMenu> acciones;

    public MenuPrincipal() {
        this.acciones = new HashMap<>();
        acciones.put(1,new MenuMostrarInformacion());
        acciones.put(2,new MenuABMCUsuarios());
        acciones.put(3,new MenuABMCEmpresas());
        acciones.put(4,new MenuABMCAreasMercado());
        acciones.put(5,new MenuABMCUbicaciones());
    }

    @Override
    public void ejecutar() {
        int opcion = 0;
        while (opcion>6 || opcion<1) {
            System.out.printf("1-Mostrar datos del Administrador \t2-ABCM Usuarios" +
                    "\n3-ABCM Empresas \t4-ABCM Areas de Mercado "+
                    "\n5-ABCM Ciudad/Pais \t6-Salir");
            opcion = Consola.leerEntero();
        }
        if(opcion!=6){
            ejecutarAccion(opcion);
        }
    }

    public void ejecutarAccion(int key){
        acciones.get(key).ejecutar();
    }
}
