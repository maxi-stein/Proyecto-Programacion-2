package holding;

public class MenuPrincipalAdmin extends MenuSuper {


    public MenuPrincipalAdmin() {
        super();
        agregarAccion(1,new MenuMostrarInformacion());
        agregarAccion(2,new MenuABMCUsuarios());
        agregarAccion(3,new MenuABMCEmpresas());
        agregarAccion(4,new MenuABMCAreasMercado());
        agregarAccion(5,new MenuABMCUbicaciones());
    }
    @Override
    public void ejecutar(BaseDatosHolding bd) {
        int opcion = 0;
        while (opcion>6 || opcion<1) {
            System.out.printf("1-Mostrar datos del Administrador \t2-ABCM Usuarios" +
                    "\n3-ABCM Empresas \t4-ABCM Areas de Mercado "+
                    "\n5-ABCM Ciudad/Pais \t6-Salir");
            opcion = Consola.leerEntero();
        }
        if(opcion!=6){
            ejecutarAccion(opcion,bd);
        }
    }

    public void ejecutarAccion(int key,BaseDatosHolding bd){
        getAccion(key).ejecutar(bd);
    }
}
