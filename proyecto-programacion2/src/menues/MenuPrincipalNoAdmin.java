package menues;

import holding.Consola;

public class MenuPrincipalNoAdmin extends Menu {
    @Override
    public void ejecutar() {
        agregarAccion(1,new MenuMostrarInformacion());
        System.out.print("1- Mostrar Informacion \t2- Salir\n");
        int opcion = 0;
        while (opcion<1 || opcion>2){
            opcion = Consola.leerEntero();
        }
        if(opcion==1){
            ejecutarAccion(opcion);
        }
    }
    public void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}
