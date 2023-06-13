package holding;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }
    public Admin(){
        super();
    }
    @Override
    public void proceder(BaseDatosHolding bd) {
        MenuPrincipalAdmin mp = new MenuPrincipalAdmin();
        mp.ejecutar(bd);
    }
    public void crearCiudad(){
        System.out.println("Elegir el Pais origen:");
        //implementacion faltante
    }

    public void agregarUsuario(Usuario u,BaseDatosHolding bd){
        bd.agregarUsuario(u);
    }
}
