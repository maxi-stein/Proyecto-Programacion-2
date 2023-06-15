package holding;

import java.util.HashMap;

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
        int key = 1;
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
        int keyAdmin = 0;
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
        int keyVendedor = 0;
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
                int keyEmpresa = 0;
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
        int keyAsesor = 0;
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

        HashMap<Integer,Empresa> empresas = bd.listarEmpresas();
        int keyEmpresa = 0;

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
                int keyArea = 0;
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
                int keyAreaCubierta = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > areasCubiertas.size());
                asesorAModificar.eliminarAreaMercadoCubierto(keyAreaCubierta);
                break;
            case 7:
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                if(bd.usuarioAsesoraAEmpresa(asesorAModificar,empresas.get(keyEmpresa))){
                    System.out.println("El usuario ya asesora dicha empresa!");
                }
                else{
                    System.out.println("Ingrese la fecha de inicio dd/mm/aaaa:");
                    empresas.get(keyEmpresa).agregarAsesor(asesorAModificar,Consola.leerFecha());
                }
                break;
            case 8:
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                if(!bd.usuarioAsesoraAEmpresa(asesorAModificar,empresas.get(keyEmpresa))){
                    empresas.get(keyEmpresa).eliminarAsesor(asesorAModificar);
                    System.out.println("El asesor " + asesorAModificar.toString() + " se desvinculo exitosamente de la empresa " +
                            empresas.get(keyEmpresa).getNombre());
                }
            default:
                break;
        }
    }
    public void listarTiposDeUsuario(){
        for(int i=1;i<=usuarios.size();i++){
            if(usuarios.get(i) != null){
                System.out.println(i+" - "+ usuarios.get(i).getClass().getSimpleName());
            }

        }
    }
}
