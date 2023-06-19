package menues;

import holding.*;

import java.time.LocalDate;
import java.util.HashMap;

public class MenuCrearEmpresa implements CapazDeEjecutarAccionMenu{
    private BaseDeDatosSingleton bd;
    private HashMap<Integer, Ciudad> ciudades;
    private  HashMap<Integer, Empresa> empresas;
    HashMap<Integer, AreasMercado> areasMercado;
    public MenuCrearEmpresa(){
        bd = BaseDeDatosSingleton.getInstance();
        ciudades = bd.obtenerCiudades();
        empresas = bd.obtenerEmpresas();
        areasMercado = bd.obtenerAreasDeMercado();
    }
    @Override
    public void ejecutar() {
        System.out.println("Indique el nombre de la Empresa: ");
        String nombreEmpresa = Consola.leerString();
        System.out.println("Indique la Facturación Anual de la Empresa");
        Double factAnual = Consola.leerDouble();
        System.out.println("Ingrese la Fecha de Inicio de la Empresa - dd/mm/aaaa -:");
        LocalDate fechaInicio = Consola.leerFecha();

        Empresa emp = new Empresa(nombreEmpresa, fechaInicio , factAnual);

        System.out.println("Seleccione la Sede de la Empresa:");
        bd.listarCiudades();
        int keyCiudad = 0;
        while(keyCiudad<1 || keyCiudad>ciudades.size()){
            keyCiudad = Consola.leerEntero();
        }
        emp.agregarCiudad(keyCiudad);
        emp.seleccionarSede(ciudades.get(keyCiudad));

        System.out.println("Seleccione Área de Mercado:");
        bd.listarAreasDeMercado();
        int opcAreaMercado=0;
        while(opcAreaMercado<1 || opcAreaMercado>areasMercado.size()){
            opcAreaMercado = Consola.leerEntero();
        }
         emp.agregarCiudad(opcAreaMercado);
        bd.agregarEmpresa(emp);
        System.out.println("Empresa Registrada Correctamente");
    }
}
