package holding;

public class AreasMercado{
    private String nombre;
    private String descripcion;

    public AreasMercado(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre + "-" +  descripcion + '\n' + "*".repeat(25) + '\n';
    }
}
