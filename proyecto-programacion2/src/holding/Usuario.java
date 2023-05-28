package holding;

public abstract class Usuario implements CapazDeVisualizarMenu,CapazDeLeerEnteros {
    private static int CONTADOR = 0;
    private int codigoUsuario;
    private String nombre;
    private String direccion;
    private String pass;

    public Usuario( String nombre, String direccion, String pass) {
        CONTADOR++;
        this.codigoUsuario = CONTADOR;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pass = pass;
    }

    public abstract void proceder();

    public abstract void mostrarInformacionUsuario();

    public boolean validarNombre(String nombre){
        return this.nombre.equals(nombre);
    }
    public boolean validarPass(String pass){
        return this.pass.equals(pass);
    }

    @Override
    public String toString() {
        return String.format("%s - COD:%d - %s \n",nombre,codigoUsuario,direccion);
    }
}
