package holding;

public abstract class Usuario {
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

    public boolean validarNombre(String nombre){
        return this.nombre == nombre ? true : false;
    }
    public boolean validarPass(String pass){
        return this.pass == pass ? true : false;
    }

    public int getCodigo(){
        return this.codigoUsuario;
    }
}
