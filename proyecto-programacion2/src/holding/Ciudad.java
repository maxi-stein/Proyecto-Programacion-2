package holding;

import java.io.Serializable;

/**
 * Esta clase detalla las ciudades en las cuales las empresas desarrollaran actividades o bien las utilizaran
 * como sede fiscal
 */
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

    /**
     * Evalua si esta ciudad pertenece a un determinado Pais
     * @param p Pais a evaluar
     * @return boolean del resultado
     */
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