package holding;

import java.time.LocalDate;
import java.util.HashMap;

public class FactoryUsuarios {
    public static void crearUsuario(int opcion) {
        if(opcion == 1){
            crearAdmin();
        }
        else if(opcion == 2){
            crearVendedor();
        }
        else {
            crearAsesor();
        }
    }
    private static void crearAdmin(){
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String pass = Consola.leerString();
    }
    private static void crearVendedor(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String contrasena = Consola.leerString();
        System.out.println("Elija la empresa en la que trabaja: ");
        HashMap<Integer, Empresa> empresas = bd.listarEmpresas();
        Integer key = 0;
        do {
            key = Consola.leerEntero();
        } while (key < 0 || key > empresas.size());
        Vendedor v = new Vendedor(nombre, direccion, contrasena, LocalDate.now(), empresas.get(key));
        v.mostrarCredenciales();
        bd.agregarUsuario(v);
    }
    private static void crearAsesor(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        System.out.println("Ingrese el nombre: ");
        String nombre = Consola.leerString();
        System.out.println("Ingrese la direccion: ");
        String direccion = Consola.leerString();
        System.out.println("Ingrese la contrasenia: ");
        String contrasena = Consola.leerString();
        System.out.println("Ingrese la titulacion");
        String titulacion = Consola.leerString();
        System.out.println("Elija el area de mercado que asesora");
        HashMap <Integer,AreasMercado> areas = bd.listarAreasDeMercado();
        Integer keyArea = 0;
        do {
            keyArea = Consola.leerEntero();
        } while (keyArea < 0 || keyArea > areas.size());
        Asesor asesor = new Asesor(nombre,direccion,contrasena,titulacion);

        //se agrega el area de mercado cubierta
        asesor.agregarAreaMercadoCubierto(areas.get(keyArea));

        //se agrega la empresa para la que trabaja y se establece VinculacionAsesorEmpresa
        System.out.println("Elija la empresa que asesora: ");
        HashMap<Integer, Empresa> empresas = bd.listarEmpresas();
        int keyEmpresa = 0;
        boolean compatible;
        do {
            keyEmpresa = Consola.leerEntero();
            compatible = empresas.get(keyEmpresa).areaCompatible(areas.get(keyArea));
            if(!compatible) {
                System.out.println("El area de mercado no es compatible con la Empresa");
            }
        } while (keyEmpresa < 0 || keyEmpresa > empresas.size() || !compatible);
        System.out.println("Ingresa la fecha de inicio (dd/mm/aaaa): ");
        LocalDate fechaInicio = Consola.leerFecha();

        bd.agregarAsesorAEmpresa(keyEmpresa,asesor,fechaInicio);//se agrega el asesor a la empresa
        bd.agregarUsuario(asesor);//se agrega el asesor a la base de datos de usuarios
    }
}
