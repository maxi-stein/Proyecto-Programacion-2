package holding;

import java.io.Serializable;
import java.time.LocalDate;

public class VinculacionEmpresaAsesor implements Serializable {

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

    public boolean contiene(Asesor a){
        return a.getCodigoUsuario() == asesor.getCodigoUsuario();
    }

}
