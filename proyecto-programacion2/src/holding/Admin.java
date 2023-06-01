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
        System.out.println("Datos de Usuario: "+ super.toString());
    }

    @Override
    public int mostrarMenu() {
        int opcion = 0;
        while (opcion>8 || opcion<1) {
            System.out.printf("1-Mostrar datos de Usuario \t2-Crear Administrador " +
                    "\n3-Crear Vendedor \t4-Crear Asesor" +
                    "\n5-Crear Empresa \t6-Asignar Vendedor Captado " +
                    "\n7- Crear Ciudad \t8-Crear Pais");
            opcion = leerNum();
            if(opcion>8 || opcion<1) System.out.println("Opcion incorrecta, intente de nuevo.");
        }
        return opcion;
    }
    public int leerNum(){
        String numero = Consola.leerString();
        return Integer.parseInt(numero);
    }
}
