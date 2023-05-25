package holding;

public abstract class Usuario {
    private String nombre;
    private String direccion;
    private String pass;

    public Usuario(String nombre, String direccion, String pass) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pass = pass;
    }
}
