package holding;

public class MenuABMCUsuarios extends MenuSuper{

    public MenuABMCUsuarios(){
        super();
        agregarAccion(1,new MenuCrearUsuario());
        agregarAccion(2,new MenuModificarUsuario());
        agregarAccion(3,new MenuEliminarUsuario());
        agregarAccion(4,new MenuVendedorCaptado());
    }
    @Override
    public void ejecutar(BaseDatosHolding bd) {
        int opcion = 0;
        while (opcion>5 || opcion<1) {
            System.out.printf("1-Crear Usuario \t2-Modificar Usuario \t3-Eliminar Usuario" +
                    "\n4-Agregar Vendedor Captado \n5-Salir \n");
            opcion = Consola.leerEntero();
        }
        if(opcion!=5){
            ejecutarAccion(opcion,bd);
        }
    }

    public void ejecutarAccion(int key,BaseDatosHolding bd){
        getAccion(key).ejecutar(bd);
    }

}
