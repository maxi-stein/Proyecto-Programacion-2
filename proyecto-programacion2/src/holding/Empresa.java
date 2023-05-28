package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empresa{

    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private ArrayList<Pais> ubicaciones;
    private ArrayList<AREAS_MERCADO> areasMercado;

    public Empresa(String nombre, LocalDate date,double facturacionAnual) {
        this.nombre = nombre;
        this.fechaEntrada = date;
        this.facturacionAnual = facturacionAnual;
    }

    public String displayInfo(){
        return String.format("%s \n",nombre);
    }

    public String getNombre(){
        return nombre;
    }

}