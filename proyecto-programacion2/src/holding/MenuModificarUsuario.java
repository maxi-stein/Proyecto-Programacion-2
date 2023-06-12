package holding;

import java.util.HashMap;
import java.util.Map;

public class MenuModificarUsuario implements CapazDeEjecutarAccionMenu{
    private HashMap<Integer,Usuario> usuarios;

    @Override
    public void ejecutar(BaseDatosHolding bd) {
        Admin adminTemporal = new Admin();
        usuarios = new HashMap<>();
        usuarios.put(1,new Admin());
        usuarios.put(2,new Vendedor());
        usuarios.put(3,new Asesor());
        System.out.println("Elija el tipo de usuario a modificar");
        listarTiposDeUsuario();
        Integer key = 1;
        do{
            key = Consola.leerEntero();
        }while (key<1 || key>usuarios.size());
        if(key == 1){
            modificarAdmin(bd);
        }
        else if(key == 2){
            modificarVendedor(bd);
        }
        else{
            modificarAsesor(bd);
        }

    }
    public void modificarAdmin(BaseDatosHolding bd){
        System.out.println("Elija el admin a modificar");
        HashMap<Integer,Usuario> adminsDelSistema = bd.listarUsuarios(new Admin());
        Integer keyAdmin = 0;
        do {
            keyAdmin = Consola.leerEntero();
            if(!adminsDelSistema.containsKey(keyAdmin)){
                System.out.println("Ingrese un valor correcto.");
            }
        } while (!adminsDelSistema.containsKey(keyAdmin));
        Admin adminAModificar = (Admin) adminsDelSistema.get(keyAdmin);
        System.out.println("Seleccione la opcion a modificar: ");
        System.out.println("1 - Nombre "+'\t'+"2 - Contraseña");
        System.out.println("3 - Direccion"+'\t'+"4 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>4){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
        switch (opcion){
            case 1:
                String nombre = Consola.leerString();
                adminAModificar.setNombre(nombre);
                break;
            case 2:
                String pass = Consola.leerString();
                adminAModificar.setPass(pass);
                break;
            case 3:
                String direccion = Consola.leerString();
                adminAModificar.setDireccion(direccion);
                break;
        }
    }
    public void modificarVendedor(BaseDatosHolding bd){
        //seleccionar el vendedor
        System.out.println("Elija el Vendedor a modificar");
        HashMap<Integer,Usuario> vendedoresDelSistema = bd.listarUsuarios(new Vendedor());
        Integer keyVendedor = 0;
        do {
            keyVendedor = Consola.leerEntero();
            if(!vendedoresDelSistema.containsKey(keyVendedor)){
                System.out.println("Ingrese un valor correcto.");
            }
        } while (!vendedoresDelSistema.containsKey(keyVendedor));
        Vendedor vendedorAModificar = (Vendedor) vendedoresDelSistema.get(keyVendedor);
        System.out.println("Seleccione la opcion a modificar: ");
        System.out.println("1 - Nombre "+'\t'+"2 - Contraseña");
        System.out.println("3 - Direccion"+'\t'+"4 - Cambiar Empresa de Trabajo");
        int opcion = 0;
        while(opcion<1 || opcion>4){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
        switch (opcion){
            case 1:
                String nombre = Consola.leerString();
                vendedorAModificar.setNombre(nombre);
                break;
            case 2:
                String pass = Consola.leerString();
                vendedorAModificar.setPass(pass);
                break;
            case 3:
                String direccion = Consola.leerString();
                vendedorAModificar.setDireccion(direccion);
                break;
            case 4:
                System.out.println("Elija la empresa a la cual cambiar: ");
                HashMap<Integer,Empresa> empresas = bd.listarEmpresas();
                Integer keyEmpresa = 0;
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                //actualizo la empresas vieja
                vendedorAModificar.getEmpresaTrabajo().traspasoDeVendedor(vendedorAModificar,keyEmpresa,bd);
                break;
            case 5:
                break;
        }
    }
    public void modificarAsesor(BaseDatosHolding bd){
        //seleccionar el asesor
        System.out.println("Elija el Asesor a modificar");
        HashMap<Integer,Usuario> asesoresDelSistema = bd.listarUsuarios(new Asesor());
        Integer keyAsesor = 0;
        do {
            keyAsesor = Consola.leerEntero();
            if(!asesoresDelSistema.containsKey(keyAsesor)){
                System.out.println("Ingrese un valor correcto.");
            }
        } while (!asesoresDelSistema.containsKey(keyAsesor));
        Asesor asesorAModificar = (Asesor) asesoresDelSistema.get(keyAsesor);
        System.out.println("Seleccione la opcion a modificar: ");
        System.out.println("1 - Nombre "+'\t'+"2 - Contraseña");
        System.out.println("3 - Direccion"+'\t'+"4 - Titulacion");
        System.out.println("5 - Agregar Area de Mercado Cubierto"+'\t'+"6 - Eliminar Area de Mercado Cubierto");
        System.out.println("7 - Vincular a una Empresa"+'\t'+"8 - Desvincular de una Empresa");
        System.out.println("9 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>9){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
        switch (opcion){
            case 1:
                String nombre = Consola.leerString();
                asesorAModificar.setNombre(nombre);
                break;
            case 2:
                String pass = Consola.leerString();
                asesorAModificar.setPass(pass);
                break;
            case 3:
                String direccion = Consola.leerString();
                asesorAModificar.setDireccion(direccion);
                break;
            case 4:
                String titulacion = Consola.leerString();
                asesorAModificar.setTitulacion(titulacion);
                break;
            case 5:
                HashMap <Integer,AreasMercado> areas = bd.listarAreasDeMercado();
                Integer keyArea = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > areas.size());

                //verifico si el area agregada ya esta en el asesor
                if(asesorAModificar.asesoraElArea(areas.get(keyArea))){
                    System.out.println("Area ya cubierta, no se aceptan duplicados.");
                }
                else{
                    asesorAModificar.agregarAreaMercadoCubierto(areas.get(keyArea));
                }
                break;
            case 6:
                HashMap<Integer,AreasMercado> areasCubiertas = asesorAModificar.listarAreasMercadoCubiertas();
                Integer keyAreaCubierta = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > areasCubiertas.size());
                asesorAModificar.eliminarAreaMercadoCubierto(keyAreaCubierta);
                break;
            case 7:

                break;
        }
    }
    public void listarTiposDeUsuario(){
        for (Map.Entry<Integer, Usuario> user : usuarios.entrySet()) {
            Integer key = user.getKey();
            Usuario value = user.getValue();
            System.out.println(key + " " + value + '\n');
        }
    }
}
