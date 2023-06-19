package holding;

import menues.MenuPrincipalAdmin;

public class Admin extends Usuario {
    public Admin(String nombre, String direccion, String pass) {
        super(nombre, direccion, pass);
    }
    public Admin(){
        super();
    }
    @Override
    public void proceder() {
        MenuPrincipalAdmin mp = new MenuPrincipalAdmin();
        mp.ejecutar();
    }

    @Override
    public void modificar() {
        super.modificar();
        int opcion = 0;
        while(opcion<1 || opcion>4){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
        switch (opcion){
            case 1:
                String nombre = Consola.leerString();
                setNombre(nombre);
                break;
            case 2:
                String pass = Consola.leerString();
                setPass(pass);
                break;
            case 3:
                String direccion = Consola.leerString();
                setDireccion(direccion);
                break;
        }
    }
}
