package holding;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }

    @Override
    public void proceder() {

    }

    @Override
    public void mostrarInformacionUsuario() {
        System.out.println("Datos de Usuario: "+ toString());
    }

    @Override
    public int leerNum() {
        return 0;
    }

    @Override
    public int mostrarMenu() {
        return 0;
    }
}
