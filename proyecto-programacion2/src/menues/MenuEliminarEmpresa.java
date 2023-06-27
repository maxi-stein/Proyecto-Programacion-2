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
        System.out.println("Seleccione la Empresa a Eliminar - 0 para cancelar - ");
        System.out.println("**No se podran eliminar Empresas con registros de Usuarios**");
        BaseDeDatosSingleton.listarEmpresas();
        int opcion = 0;
        opcion = Consola.leerEntero();
        while(opcion<0 || opcion>empresas.size()){
            System.out.println("Valor incorrecto - Ingrese nuevamente:");
            opcion = Consola.leerEntero();
        }
        if(opcion != 0){
            Empresa empresaSeleccionada = empresas.get(opcion);
            if(!empresaSeleccionada.tieneTrabajadores()){
                empresaSeleccionada.eliminarRegistrosAreaDeMercado(); //eliminamos los registros para que la empresa bloqueada no obstruye la eliminación de un AreaDeMercado
                BaseDeDatosSingleton.eliminarEmpresa(opcion);
                System.out.println("Empresa "+empresaSeleccionada.getNombre()+ " eliminada!");
            }
            else{
                System.out.println("Imposible eliminar la empresa! Contiene los siguiente empleados activos:");
                empresaSeleccionada.listarTrabajadores();
            }
        }else {
            System.out.println("* Proceso cancelado *");
        }

    }
}
