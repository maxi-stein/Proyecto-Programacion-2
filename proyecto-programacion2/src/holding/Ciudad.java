package holding;

import java.io.Serializable;

public class Ciudad implements CapazDeSerBloqueado, Serializable {
    private String nombre;
    private Pais paisOrigen;
    private boolean bloqueado;
    public Ciudad(String nombre, Pais paisOrigen) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }
    @Override
    public void setBloqueo(boolean valor) {
        bloqueado = valor;
    }
    @Override
    public boolean noEstaBloqueado() {
        return !bloqueado;
    }
    public boolean esIgual(Ciudad c){
        return (nombre.equals(c.nombre)) && paisOrigen.getNombre().equalsIgnoreCase(c.paisOrigen.getNombre());
    }
    public boolean tieneAlPais(Pais p){
        return paisOrigen.esIgual(p);
    }
    @Override
    public String toString() {
        return nombre +"," + paisOrigen.toString();
    }
    public String getNombre() {
        return nombre;
    }
    public Pais getPaisOrigen() {
        return paisOrigen;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

}