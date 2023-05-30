package holding;

public class AreasMercado implements CapazDeMostrarSuInformacion{
    private String nombre;
    private String descripcion;

    public AreasMercado(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.printf("%s - %s",nombre,descripcion);
    }
}
