package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;
import holding.Empresa;

import java.util.HashMap;

public class MenuEliminarEmpresa implements CapazDeEjecutarAccionMenu{
    private static BaseDeDatosSingleton bd;
    public MenuEliminarEmpresa() {
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la Empresa a Eliminar");
        System.out.println("**No se podran eliminar Empresas con registros de Usuarios**");
        bd.listarEmpresas();
        HashMap<Integer, Empresa> empresas = bd.obtenerEmpresas();
        int opcion = 0;
        while(opcion<1 || opcion>empresas.size()){
            opcion = Consola.leerEntero();
        }
        Empresa empresaSeleccionada = empresas.get(opcion);
        if(!empresaSeleccionada.tieneTrabajadores()){
            empresaSeleccionada.eliminarRegistrosAreaDeMercado(); //eliminamos los registros para que la empresa bloqueada no obstruye la eliminación de un AreaDeMercado
            bd.eliminarEmpresa(opcion);
            System.out.println("Empresa "+empresaSeleccionada.getNombre()+ " eliminada!");
        }
        else{
            System.out.println("Imposible eliminar la empresa! Contiene empleados activos.");
        }
    }
}
