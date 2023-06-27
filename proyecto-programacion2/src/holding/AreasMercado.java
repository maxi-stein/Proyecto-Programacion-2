package holding;

import java.io.Serializable;

/**
 * Esta clase detalla todas las areas de mercado en las cuales una empresa desarrolla actividad. Asi mismo,
 * los asesores asesoran determinadas areas de mercado
 */

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
        return nombre + "-" + descripcion;
    }

    @Override
    public void setBloqueo(boolean valor) {
        bloqueado = valor;
    }

    @Override
    public boolean noEstaBloqueado() {
        return !bloqueado;
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

    /**
     * Compara el AreasMercado pasada por parametro con la instancia de este objeto
     * @param a AreasMercado a evaluar
     * @return boolean del resultado de la evaluacion
     */
    public boolean esIgual(AreasMercado a){
        return nombre.equalsIgnoreCase(a.getNombre());
    }
}
