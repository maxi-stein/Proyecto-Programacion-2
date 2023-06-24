package menues;

import holding.*;

import java.util.HashMap;

public class MenuModificarEmpresa implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer, Ciudad> ciudades;
    private  HashMap<Integer, Empresa> empresas;
    private HashMap<Integer,Ciudad> ciudadesDeEmpresa;
    private HashMap<Integer, AreasMercado> areas;
    public MenuModificarEmpresa(){
        ciudades = BaseDeDatosSingleton.obtenerCiudades();
        empresas = BaseDeDatosSingleton.obtenerEmpresas();
        areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
    }
    @Override
    public void ejecutar() {
        System.out.println("Seleccione la empresa a modificar");
        BaseDeDatosSingleton.listarEmpresas();
        int keyEmpresa = 0;
        while (keyEmpresa < 1 || keyEmpresa>empresas.size()){
            keyEmpresa = Consola.leerEntero();
        }
        Empresa empresaSeleccionada = empresas.get(keyEmpresa);

        System.out.println("Seleccione el atributo a modificar:");
        System.out.print("1 - Nombre \t"+"2 - Fecha de Entrada \t"+"3 - Facturacion Anual \n");
        System.out.print("4 - Asignar Sede \t"+"5 - Agregar Ciudad \t"+"6 - Eliminar Ciudad \n");
        System.out.print("7 - Agregar Area de Mercado \t"+"8 - Eliminar Area de Mercado \n");
        System.out.println("9 - Salir");
        int opcion = 0;
        while (opcion<1 || opcion>9){
            opcion = Consola.leerEntero();
        }
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre");
                empresaSeleccionada.setNombre(Consola.leerString());
                break;
            case 2:
                System.out.println("Ingrese la fecha de entrada:");
                empresaSeleccionada.setFechaEntrada(Consola.leerFecha());
                break;
            case 3:
                System.out.println("Ingrese la facturacion Anual:");
                empresaSeleccionada.setFacturacionAnual(Consola.leerDouble());
                break;
            case 4:
                System.out.println("Seleccione la ciudad para convertirla en Sede:");
                BaseDeDatosSingleton.listarCiudades();
                int keyCiudad = 0;
                while(keyCiudad<1 || keyCiudad>ciudades.size()){
                    keyCiudad = Consola.leerEntero();
                }
                empresaSeleccionada.seleccionarSede(ciudades.get(keyCiudad));
                break;
            case 5:
                System.out.println("Seleccione la Ciudad a agregar");
                BaseDeDatosSingleton.listarCiudades();
                int opcionCiudad = 0;
                while (opcionCiudad<1 || opcionCiudad>ciudades.size()){
                    opcionCiudad = Consola.leerEntero();
                }
                empresaSeleccionada.agregarCiudad(opcionCiudad);
            case 6:
                BaseDeDatosSingleton.listarCiudadesDeEmpresa(keyEmpresa);
                int opcionEmpresa = 0;
                ciudadesDeEmpresa = BaseDeDatosSingleton.obtenerCiudadesDeEmpresa(keyEmpresa);
                while (opcionEmpresa<1 || opcionEmpresa>ciudadesDeEmpresa.size()){
                    opcionEmpresa = Consola.leerEntero();
                }
                empresaSeleccionada.eliminarCiudad(ciudadesDeEmpresa.get(opcionEmpresa));
            case 7:
                System.out.println("Seleccione el area de mercado a agregar");
                BaseDeDatosSingleton.listarAreasDeMercado();
                int opcionAreaMercado = 0;
                while (opcionAreaMercado<1 || opcionAreaMercado>areas.size()){
                    opcionAreaMercado = Consola.leerEntero();
                }
                //empresaSeleccionada.agregarAreaMercado(areas.get(opcionAreaMercado));
                BaseDeDatosSingleton.agregarAreaMercadoAEmpresa(keyEmpresa, areas.get(opcionAreaMercado));
                break;
            case 8:
                System.out.println("Seleccione el area de mercado a eliminar");
                empresaSeleccionada.listarAreasDeMercado();
                HashMap<Integer, AreasMercado> areasMercadoAEliminar = empresaSeleccionada.obtenerAreasDeMercado();
                int opcionAreaMercadoAEliminar = 0;
                while (opcionAreaMercadoAEliminar<1 || opcionAreaMercadoAEliminar>areasMercadoAEliminar.size()){
                    opcionAreaMercadoAEliminar = Consola.leerEntero();
                }
                empresaSeleccionada.eliminarAreaMercado(areasMercadoAEliminar.get(opcionAreaMercadoAEliminar));
                break;
            default:
                break;
        }
    }
}
