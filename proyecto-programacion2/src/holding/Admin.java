package holding;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }

    @Override
    public void proceder() {
        int opcion = mostrarMenu();
        while(opcion != 9){
            switch (opcion){
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                         
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    break;
            }
            if(opcion != 9) opcion = mostrarMenu();
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Datos de Usuario: "+ super.toString());
    }
    @Override
    public int mostrarMenu() {
        int opcion = 0;
        while (opcion>6 || opcion<1) {
            System.out.printf("1-Mostrar datos del Administrador \t2-ABM Usuarios" +
                    "\n3-ABM Empresas \t4-ABM Ciudad y Pais" +
                    "\n5-ABM Areas de Mercado \t6-Salir");
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
    public int mostrarAbmUsuario(){
        int opcion = 0;
        while (opcion>6 || opcion<1) {
            System.out.printf("1-Crear Administrador \t2-Modificar Administrador \t3-Eliminar Administrador" +
                    "\n4-Agregar Vendedor \t5-Modificar Vendedor \t6-Eliminar Vendedor" +
                    "\n7-Agregar Asesor \t8-Modificar Asesor \t9-Eliminar Asesor");
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
}
