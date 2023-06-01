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
                    mostrarInformacion();
                    break;
                case 2: //crear admin

                    break;
                case 3: //crear vendedor
                         
                    break;
                case 4: //crear asesor

                    break;
                case 5: //crear empresa

                    break;
                case 6: //asignar vendedor captado

                    break;
                case 7: //crear ciudad

                    break;
                case 8: //crear País

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
        while (opcion>9 || opcion<1) {
            System.out.printf("1-Mostrar datos de Usuario \t2-Crear Administrador " +
                    "\n3-Crear Vendedor \t4-Crear Asesor" +
                    "\n5-Crear Empresa \t6-Asignar Vendedor Captado " +
                    "\n7- Crear Ciudad \t8-Crear Pais \t 9-Salir");
            opcion = leerNum();
            if(opcion>9 || opcion<1) System.out.println("Opcion incorrecta, intente de nuevo.");
        }
        return opcion;
    }
    public int leerNum(){
        String numero = Consola.leerString();
        return Integer.parseInt(numero);
    }
}
