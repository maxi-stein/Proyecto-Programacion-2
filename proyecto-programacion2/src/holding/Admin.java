package holding;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }

    @Override
    public void proceder() {
        MenuPrincipalAdmin mp = new MenuPrincipalAdmin();
        mp.ejecutar();
    }
    public void crearCiudad(){
        System.out.println("Elegir el Pais origen:");
        //implementacion faltante
    }

    public void crearVendedor(Vendedor v){

    }

}
