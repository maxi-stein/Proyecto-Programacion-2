package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empresa{

    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private ArrayList<Pais> ubicaciones;
    private ArrayList<AREAS_MERCADO> areaMercado;

    public Empresa(String nom, LocalDate date,double facturacionAnual) {
        this.nombre = nom;
        this.fechaEntrada = date;
        this.facturacionAnual = facturacionAnual;
    }

}