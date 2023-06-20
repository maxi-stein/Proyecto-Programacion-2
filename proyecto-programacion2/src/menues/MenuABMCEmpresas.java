package menues;

import holding.Consola;

public class MenuABMCEmpresas extends Menu {

    public MenuABMCEmpresas(){
        super();
        agregarAccion(1,new MenuCrearEmpresa());
        agregarAccion(2,new MenuModificarEmpresa());
        agregarAccion(3,new MenuEliminarEmpresa());
        agregarAccion(4,new MenuSalir());
    }
    @Override
    public void ejecutar() {
        int opcion = 0;
        while (opcion>4 || opcion<1) {
            System.out.printf("1- Crear Empresa \t2- Modificar Empresa" +
                    "\n3- Eliminar Empresa \t4- Salir \n");
            opcion = Consola.leerEntero();
        }
        ejecutarAccion(opcion);
    }
    private void ejecutarAccion(int key){
        getAccion(key).ejecutar();
    }
}

