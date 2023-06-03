package holding;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;

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
                case 2:
                    mostrarABMUsuario();
                    break;
                case 3:
                    mostrarABMEmpresas();
                    break;
                case 4:
                    mostrarABMAreasMercado();
                    break;
                case 5:
                    crearCiudad();
                    break;
                case 6:
                    break;
            }
            if(opcion != 6) opcion = mostrarMenu();
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
                    "\n3-ABM Empresas \t4-ABM Areas de Mercado "+
                    "\n5-Crear Ciudad - Pais \t6-Salir");
            opcion = Consola.leerEntero();
        }
        return opcion;
    }
    public void mostrarABMUsuario(){
        int opcion = 0;
        while (opcion>11 || opcion<1) {
            System.out.printf("1-Crear Administrador \t2-Modificar Administrador \t3-Eliminar Administrador" +
                    "\n4-Agregar Vendedor \t5-Agregar Vendedor Captado \t6-Modificar Vendedor \t7-Eliminar Vendedor" +
                    "\n8-Agregar Asesor \t9-Modificar Asesor \t10-Eliminar Asesor" +
                    "\n11-Salir");
            opcion = Consola.leerEntero();
        }
        switch (opcion){
            case 1:
                String nombre;
                String direccion;
                int pass;
                Admin ad;

                System.out.println("Ingrese Nombre: ");
                nombre = Consola.leerString();
                System.out.println("Ingrese Dirección: ");
                direccion = Consola.leerString();
                System.out.println("Ingrese Contraseña: ");
                pass = Consola.leerEntero();
                ad = new Admin(nombre, direccion,pass);
                System.out.println("* USUARIO CREADO *");
                ad.mostrarCredenciales();
                ad.mostrarInformacion();
                //guardar en BD
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
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
        }
        if(opcion!= 11) mostrarABMUsuario();
    }

    public void mostrarABMEmpresas(){
        int opcion = 0;
        while (opcion>13 || opcion<1) {
            System.out.printf("1-Crear Empresa \t2-Modificar Empresa \t3-Eliminar Empresa" +
                    "\n4-Agregar Vendedor \t5-Despedir Vendedor "+
                    "\n6-Agregar Asesor \t7-Despedir Asesor" +
                    "\n8-Agregar Area de Mercado \t9-Eliminar Area de Mercado" +
                    "\n10-Agregar Ciudad \t11-Quitar Ciudad \t12-Asignar Sede" +
                    "\n13-Salir");
            opcion = Consola.leerEntero();
        }

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
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
        }
        if(opcion!= 13) mostrarABMEmpresas();
    }

    public void crearCiudad(){
        System.out.println("Elegir el Pais origen:");
        //implementacion faltante
    }

    public void mostrarABMAreasMercado(){
        int opcion = 0;
        while (opcion>6 || opcion<1) {
            System.out.printf("1-Crear Area Mercado \t2-Modificar Area Mercado" +
                    "\n3-Salir");
            opcion = Consola.leerEntero();
        }

        switch (opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        if(opcion != 3) mostrarABMAreasMercado();
    }
}
