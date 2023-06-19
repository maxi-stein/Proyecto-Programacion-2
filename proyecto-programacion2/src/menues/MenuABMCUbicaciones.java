package menues;

import holding.Consola;

public class MenuABMCUbicaciones extends Menu {
    public MenuABMCUbicaciones(){
        super();
        agregarAccion(1,new MenuCrearCiudad());
        agregarAccion(2,new MenuModificarCiudad());
        agregarAccion(3,new MenuEliminarCiudad());
        agregarAccion(4,new MenuCrearPais());
        agregarAccion(5,new MenuModificarPais());
        //agregarAccion(6,new MenuEliminarPais());
    }
    @Override
    public void ejecutar() {
        int opcion = 0;
        while (opcion>7 || opcion<1) {
            System.out.printf("1-Crear Ciudad \t2-Modificar Ciudad \t3-Eliminar Ciudad" +
                    "\n4-Crear Pais \t5-Modificar Pais \t6 - Eliminar Pais" +
                    "\n7 - Salir");
            opcion = Consola.leerEntero();
        }
        if(opcion!=7){
            ejecutarAccion(opcion);
        }
    }
    private void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}
