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
        else if(opcion == 3){
            crearAsesor();
        }
        else{
            crearVendedorCaptado();
        }
    }
    private static void crearAdmin(){
        Admin a = new Admin();
        a.pedirDatosBasicos();
        a.mostrarCredenciales();
        BaseDeDatosSingleton.agregarUsuario(a);
    }
    private static void crearVendedor(){
        Vendedor v = new Vendedor();
        v.pedirDatosBasicos();
        System.out.println("Seleccione la empresa en la que trabaja: ");
        BaseDeDatosSingleton.listarEmpresas();
        HashMap<Integer, Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
        int key = 0;
        do {
            key = Consola.leerEntero();
        } while (key < 0 || key > empresas.size());
        Empresa empresaTrabajo = empresas.get(key);
        v.setEmpresaTrabajo(empresaTrabajo);
        empresaTrabajo.agregarVendedor(v);
        v.mostrarCredenciales();
        BaseDeDatosSingleton.agregarUsuario(v);
    }
    private static void crearAsesor(){
        Asesor asesor = new Asesor();
        asesor.pedirDatosBasicos();
        System.out.println("Ingrese la titulacion");
        String titulacion = Consola.leerString();
        asesor.setTitulacion(titulacion);
        System.out.println("Seleccione el area de mercado que asesora");
        BaseDeDatosSingleton.listarAreasDeMercado();
        HashMap <Integer,AreasMercado> areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
        Integer keyArea = 0;
        do {
            keyArea = Consola.leerEntero();
        } while (keyArea < 0 || keyArea > areas.size());

        //se agrega el area de mercado cubierta
        asesor.agregarAreaMercadoCubierto(areas.get(keyArea));

        //se agrega la empresa para la que trabaja y se establece VinculacionAsesorEmpresa
        System.out.println("Seleccione la empresa que asesora (0 para salir): ");
        BaseDeDatosSingleton.listarEmpresas();
        HashMap<Integer, Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
        int keyEmpresa = 0;
        boolean compatible;
        do {
            keyEmpresa = Consola.leerEntero();
            if(keyEmpresa==0){
                compatible=true;
            } else {
                compatible = empresas.get(keyEmpresa).areaCompatible(areas.get(keyArea));
                if(!compatible) {
                    System.out.println("El area de mercado no es compatible con la Empresa.");
                    System.out.println("Ingrese otra empresa o 0 para salir.");
                }
            }
        } while (keyEmpresa < 0 || keyEmpresa > empresas.size() || !compatible);
        if(keyEmpresa!=0) {
            System.out.println("Ingresa la fecha de inicio (dd/mm/aaaa): ");
            LocalDate fechaInicio = Consola.leerFecha();
            asesor.mostrarCredenciales();
            BaseDeDatosSingleton.agregarAsesorAEmpresa(keyEmpresa, asesor, fechaInicio);//se agrega el asesor a la empresa
            BaseDeDatosSingleton.agregarUsuario(asesor);//se agrega el asesor a la base de datos de usuarios
        }
    }

    private static void crearVendedorCaptado(){
        HashMap<Integer,Vendedor> vendedores = null;
        Vendedor vendedorCaptado = new Vendedor();
        vendedorCaptado.pedirDatosBasicos();
        System.out.println("Determine el vendedor maestro - 0 para Cancelar -: ");
        BaseDeDatosSingleton.listarVendedores();
        vendedores = BaseDeDatosSingleton.obtenerVendedores();
        if(!vendedores.isEmpty())
        {
            int opcion = Consola.leerEntero();
            while (!vendedores.containsKey(opcion) && opcion != 0){
                System.out.println("Ingrese una opcion correcta");
                opcion = Consola.leerEntero();
            }
            if(opcion!= 0){
                Vendedor vendedorMaestro = vendedores.get(opcion);
                vendedorMaestro.getEmpresaTrabajo().agregarVendedor(vendedorCaptado); //agrego el vendedor captado al arraylist de vendedores de la empresa
                vendedorCaptado.setEmpresaTrabajo(vendedorMaestro.getEmpresaTrabajo());//le asigno la empresa de trabajo al nuevo vendedor
                vendedorMaestro.captarVendedor(vendedorCaptado); //se asocia el vendedor captado con su vendedor maestro
                vendedorCaptado.mostrarCredenciales();
                BaseDeDatosSingleton.agregarUsuario(vendedorCaptado); //se agrega el vendedor a la base de datos
            }else{
                System.out.println("- Proceso Cancelado - ");
            }

        }else{
            System.out.println("Sin registros de Vendedores!");
        }

    }

}
