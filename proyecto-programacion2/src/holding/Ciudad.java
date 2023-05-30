package holding;

public class Ciudad implements CapazDeTenerSede {
    private String nombre;
    private boolean desarrollaActividad;
    private boolean esSede;
    public Ciudad(String nombre, boolean desarrollaActividad)
    {
        this.nombre = nombre;
        this.desarrollaActividad = desarrollaActividad;
        this.esSede = false;
    }

    @Override
    public void hacerSede() {
        esSede = true;
    }

    @Override
    public void eliminarSede() {
        esSede = false;
    }
}
