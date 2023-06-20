package menues;

import holding.Consola;

public class MenuABMCAreasMercado extends Menu {
    public MenuABMCAreasMercado(){
        super();
        agregarAccion(1,new MenuCrearAreaDeMercado());
        agregarAccion(2,new MenuModificarAreaDeMercado());
        agregarAccion(3,new MenuEliminarAreaDeMercado());
    }
    @Override
    public void ejecutar() {
        System.out.println("menu mercado sin implementar");

        int opcion = 0;
        while (opcion>4 || opcion<1) {
            System.out.printf("1- Crear Area de Mercado \t2- Modificar Area registrada" +
                    "\n3- Eliminar Area \t4-S alir \n");
            opcion = Consola.leerEntero();
        }
        if(opcion!=4){
            ejecutarAccion(opcion);
        }
    }
    private void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}

