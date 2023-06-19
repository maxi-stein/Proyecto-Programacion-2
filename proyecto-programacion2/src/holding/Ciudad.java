package holding;

public class Ciudad {
    private static int CONTADOR = 0;
    private int codigoCiudad;
    private String nombre;

    private Pais paisOrigen;

    public Ciudad(String nombre, Pais paisOrigen) {
        CONTADOR++;
        codigoCiudad=CONTADOR;
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

    public int getCodigoCiudad() {
        return codigoCiudad;
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