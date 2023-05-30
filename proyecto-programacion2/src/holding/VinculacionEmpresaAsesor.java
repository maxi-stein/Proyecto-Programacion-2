package holding;

import java.time.LocalDate;

public class VinculacionEmpresaAsesor {

    private Asesor asesor;
    private LocalDate fechaInicio;

    public VinculacionEmpresaAsesor(Asesor asesor, LocalDate fechaInicio) {
        this.asesor = asesor;
        this.fechaInicio = fechaInicio;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
