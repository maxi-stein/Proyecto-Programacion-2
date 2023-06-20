package holding;

import java.io.Serializable;

public abstract class Usuario implements CapazDeSerBloqueado, Serializable {
    private int codigoUsuario;
    private String nombre;
    private String direccion;
    private String pass;
    private Boolean bloqueado;
    public Usuario( String nombre, String direccion, String pass) {
        this.codigoUsuario = BaseDeDatosSingleton.getInstance().obtenerCodigoMayor()+1;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pass = pass;
        this.bloqueado = false;
    }
    public Usuario(){
        codigoUsuario=BaseDeDatosSingleton.getInstance().obtenerCodigoMayor()+1;
    }
    public abstract void proceder();
    public abstract void mostrarInfo();
    public void setBloqueo(boolean valor){
        this.bloqueado = valor;
    }
    public boolean estaBloqueado(){
        return bloqueado;
    }
    public void modificar(){
        System.out.println("Seleccione la opcion a modificar: ");
        System.out.println("1 - Nombre "+'\t'+"2 - Contraseña"+'\t'+"3 - Direccion");
    }
    public void pedirDatosBasicos(){
        System.out.println("Ingrese el nombre: ");
        this.nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        this.direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        this.pass = Consola.leerString();
    }
    public boolean validarPass(String pass){
        return this.pass.equals(pass);
    }
    public void mostrarCredenciales(){
        System.out.println("Código Usuario: "+ codigoUsuario);
        System.out.println("Nombre y direc: " +nombre + direccion );
        System.out.println("Contraseña: "+ pass);
    }
    public int getCodigoUsuario() {
        return codigoUsuario;
    }
    public String getNombre() {
        return nombre;
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
    public void setCodigoUsuario(int codigo){
        this.codigoUsuario = codigo;
    }
    @Override
    public String toString() {
        return String.format("%s - COD:%d - %s \n",nombre,codigoUsuario,direccion);
    }
}
