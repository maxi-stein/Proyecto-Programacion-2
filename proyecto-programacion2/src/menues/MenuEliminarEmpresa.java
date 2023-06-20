package menues;

import holding.BaseDeDatosSingleton;
import holding.Consola;
import holding.Empresa;

import java.util.HashMap;

public class MenuEliminarEmpresa implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer, Empresa> empresas;
    public MenuEliminarEmpresa() {
        empresas = BaseDeDatosSingleton.obtenerEmpresas();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la Empresa a Eliminar");
        System.out.println("**No se podran eliminar Empresas con registros de Usuarios**");
        BaseDeDatosSingleton.listarEmpresas();
        int opcion = 0;
        while(opcion<1 || opcion>empresas.size()){
            opcion = Consola.leerEntero();
        }
        Empresa empresaSeleccionada = empresas.get(opcion);
        if(!empresaSeleccionada.tieneTrabajadores()){
            empresaSeleccionada.eliminarRegistrosAreaDeMercado(); //eliminamos los registros para que la empresa bloqueada no obstruye la eliminación de un AreaDeMercado
            BaseDeDatosSingleton.eliminarEmpresa(opcion);
            System.out.println("Empresa "+empresaSeleccionada.getNombre()+ " eliminada!");
        }
        else{
            System.out.println("Imposible eliminar la empresa! Contiene empleados activos.");
        }
    }
}
