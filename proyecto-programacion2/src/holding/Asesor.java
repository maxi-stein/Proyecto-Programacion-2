package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Asesor extends Usuario{
    private String titulacion;
    private LocalDate fechaInicio;
    private ArrayList<AREAS_MERCADO> mercadosCubiertos;

    public Asesor(String nombre, String direccion, String pass, String titulacion, LocalDate fechaInicio) {
        super(nombre, direccion, pass);
        this.titulacion = titulacion;
        this.fechaInicio = fechaInicio;
    }
}
