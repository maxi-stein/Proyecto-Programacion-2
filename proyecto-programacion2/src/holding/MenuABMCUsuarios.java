package holding;

import java.util.HashMap;

public class MenuABMCUsuarios implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,CapazDeEjecutarAccionMenu> acciones;
    public MenuABMCUsuarios(){
        acciones = new HashMap<>();
        acciones.put(1,new MenuCrearObjeto());
        acciones.put(2,new MenuModificarUsuario());
        acciones.put(3,new MenuEliminarUsuario());
        acciones.put(4,new MenuVendedorCaptado());
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

    public void ejecutarAccion(int key){
        Usuario usuario = null;
        acciones.get(key).ejecutar(usuario);
    }
}
