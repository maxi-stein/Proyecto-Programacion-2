package holding;

import java.io.Serializable;

public class AreasMercado implements CapazDeSerBloqueado, Serializable {
    private String nombre;
    private String descripcion;
    private boolean bloqueado;
    public AreasMercado(String nombre, String descripcion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return nombre + "-" +  descripcion + '\n' + "*".repeat(25);
    }
    @Override
    public void setBloqueo(boolean valor) {
        bloqueado = valor;
    }
    @Override
    public boolean estaBloqueado() {
        return bloqueado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
