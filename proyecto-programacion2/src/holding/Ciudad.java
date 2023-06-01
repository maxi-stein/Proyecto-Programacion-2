package holding;

public class Ciudad {
    private String nombre;

    private Pais paisOrigen;

    public Ciudad(String nombre, Pais paisOrigen) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    public boolean esIgual(Ciudad c){
        return (nombre.equals(c.nombre)) && paisOrigen==c.paisOrigen;
    }

    @Override
    public String toString() {
        return nombre +"," + paisOrigen;
    }
}