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
        while (opcion>4 || opcion<1) {
            System.out.printf("1-Crear Empresa \t2-Modificar Empresa" +
                    "\n3-Eliminar Empresa \t4-Salir \n");
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

