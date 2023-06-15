package holding;

public abstract class Usuario{
    private static int CONTADOR = 0;
    private int codigoUsuario;
    private String nombre;
    private String direccion;
    private String pass;
    private Boolean bloqueado;

    public Usuario( String nombre, String direccion, String pass) {
        CONTADOR++;
        this.codigoUsuario = CONTADOR;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pass = pass;
        this.bloqueado = false;

    }

    public Usuario(){
        codigoUsuario=Integer.MIN_VALUE;
        nombre  = null;
        direccion = null;
        pass = null;
    }
    public abstract void proceder(BaseDatosHolding bd);

    public boolean validarNombre(String nombre){
        return this.nombre.equals(nombre);
    }
    public boolean validarPass(String pass){
        return this.pass.equals(pass);
    }
    public void mostrarCredenciales(){
        System.out.println("Código Usuario: "+ codigoUsuario);
        System.out.println("Contraseña: "+ pass);
    }
    @Override
    public String toString() {
        return String.format("%s - COD:%d - %s \n",nombre,codigoUsuario,direccion);
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void eliminar(){
        this.bloqueado = true;
    }

    public void desbloquear(){
        this.bloqueado = false;
    }

    public boolean estaBloqueado(){
        return bloqueado;
    }
}
