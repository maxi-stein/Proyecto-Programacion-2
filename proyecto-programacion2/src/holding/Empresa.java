package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empresa implements CapazDeMostrarSuInformacion{

    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private ArrayList<Pais> ubicaciones;
    private ArrayList<AREAS_MERCADO> areasMercado;

    public Empresa(String nombre, LocalDate fechaEntrada, double facturacionAnual,Pais pais,Ciudad ciudad) {
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.facturacionAnual = facturacionAnual;
        ubicaciones = new ArrayList<>();
        ubicaciones.add(pais);
        pais.agregarCiudad(ciudad);
        areasMercado = new ArrayList<>();
    }

    public String displayInfo(){
        return String.format("%s \n",nombre);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(nombre + "Fecha de entrada: "+fechaEntrada);
        System.out.println("    - Facturacion anual: $"+facturacionAnual);
        System.out.println("    - Ubicaciones: ");
        for(Pais pais : ubicaciones){
            System.out.println("        *"+pais);
        }
    }

    public String getNombre(){
        return nombre;
    }

}