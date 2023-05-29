package holding;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }

    @Override
    public void proceder() {

    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Datos de Usuario: "+ toString());
    }

    @Override
    public int leerNum() {
        return 0;
    }

    @Override
    public int mostrarMenu() {
        System.out.printf("1-Mostrar datos de Usuario \n2-Crear Administrador \n3-Crear Vendedor \n4-Crear Asesor" +
                "\n5-Crear Empresa \n6-Agregar Pais al listado \n7- Agregar Ciudad al Listado");
        return 0;
    }
}
