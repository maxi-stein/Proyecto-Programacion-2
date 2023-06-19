package menues;

import holding.Consola;

public class MenuABMCEmpresas extends Menu {

    public MenuABMCEmpresas(){
        super();
        agregarAccion(1,new MenuCrearEmpresa());
        agregarAccion(2,new MenuModificarEmpresa());
        agregarAccion(3,new MenuEliminarEmpresa());
    }
    @Override
    public void ejecutar() {
        int opcion = 0;
        while (opcion>5 || opcion<1) {
            System.out.printf("1-Crear Usuario \t2-Modificar Usuario \t3-Eliminar Usuario" +
                    "\n4-Agregar Vendedor Captado \n5-Salir \n");
            opcion = Consola.leerEntero();
        }
        if(opcion!=5){
            ejecutarAccion(opcion);
        }
    }

    private void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}

