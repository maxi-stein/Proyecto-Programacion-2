package menues;

import holding.Consola;

public class MenuABMCAreasMercado extends Menu {
    public MenuABMCAreasMercado(){
        super();
        agregarAccion(1,new MenuCrearAreaDeMercado());
        agregarAccion(2,new MenuModificarAreaDeMercado());
        agregarAccion(3,new MenuEliminarAreaDeMercado());
        agregarAccion(4,new MenuSalir());
    }
    @Override
    public void ejecutar() {
        int opcion = 0;
        while (opcion>4 || opcion<1) {
            System.out.printf("1- Crear Area de Mercado \t2- Modificar Area registrada" +
                    "\n3- Eliminar Area \t4- Salir \n");
            opcion = Consola.leerEntero();
        }
        ejecutarAccion(opcion);
    }
    private void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}

