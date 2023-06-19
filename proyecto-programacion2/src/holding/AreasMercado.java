package holding;

public class AreasMercado implements CapazDeSerBloqueado{
    private static int CONTADOR = 0;
    private int codArea;
    private String nombre;
    private String descripcion;
    private boolean bloqueado;
    public AreasMercado(String nombre, String descripcion) {
        CONTADOR++;
        codArea = CONTADOR;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return nombre + "-" +  descripcion + '\n' + "*".repeat(25) + '\n';
    }
    @Override
    public void setBloqueo(boolean valor) {
        bloqueado = valor;
    }
    @Override
    public boolean estaBloqueado() {
        return bloqueado;
    }
}
