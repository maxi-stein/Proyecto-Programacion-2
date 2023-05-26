package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Asesor extends Usuario{
    private String titulacion;
    private LocalDate fechaInicio;
    private ArrayList<AREAS_MERCADO> mercadosCubiertos;
    private ArrayList<Empresa> empresasAsesoradas;

    public Asesor(String nombre, String direccion, String pass, String titulacion, LocalDate fechaInicio) {
        super(nombre, direccion, pass);
        this.titulacion = titulacion;
        this.fechaInicio = fechaInicio;
    }

    @Override
    public void proceder() {

    }

    @Override
    public int mostrarMenu() {
        return 0;
    }

    @Override
    public int leerNum() {
        return 0;
    }
}
