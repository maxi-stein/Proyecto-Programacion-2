package holding;

import menues.MenuPrincipalNoAdmin;

import java.util.HashMap;

public class Asesor extends Usuario{
    private String titulacion;
    private HashMap<Integer,AreasMercado> mercadosCubiertos;
    private int cantidadMercadosCubiertos;

    public Asesor(String nombre, String direccion, String pass, String titulacion) {
        super(nombre, direccion, pass);
        this.titulacion = titulacion;
        mercadosCubiertos = new HashMap<>();
        cantidadMercadosCubiertos = 0;
    }
    public Asesor(){
        super();
        mercadosCubiertos = new HashMap<>();
        cantidadMercadosCubiertos = 0;
    }
    @Override
    public void proceder() {
        MenuPrincipalNoAdmin mpa = new MenuPrincipalNoAdmin();
        mpa.ejecutar();
    }
    @Override
    public void mostrarInfo() {
        super.mostrarCredenciales();
        System.out.println("Titulacion: "+titulacion);
        System.out.println("Mercados cubiertos:");
        listarMercadosCubiertos();
        System.out.println();
        System.out.println("Empresas asesoradas:");
        BaseDeDatosSingleton.listarEmpresasDeAsesor(this);
    }
    @Override
    public void modificar() {
        super.modificar();
        System.out.println("4 - Titulacion");
        System.out.println("5 - Agregar Area de Mercado Cubierto"+'\t'+"6 - Eliminar Area de Mercado Cubierto");
        System.out.println("7 - Vincular a una Empresa"+'\t'+"8 - Desvincular de una Empresa");
        System.out.println("9 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>9){
            opcion = Consola.leerEntero();
        }
        BaseDeDatosSingleton.listarEmpresas();
        HashMap<Integer,Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
        int keyEmpresa = 0;

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
            case 4:
                String titulacion = Consola.leerString();
                setTitulacion(titulacion);
                break;
            case 5:
                BaseDeDatosSingleton.listarAreasDeMercado();
                HashMap <Integer,AreasMercado> areas = BaseDeDatosSingleton.obtenerAreasDeMercado();
                int keyArea = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > areas.size());

                //verifico si el area agregada ya esta en el asesor
                if(asesoraElArea(areas.get(keyArea))){
                    System.out.println("Area ya cubierta, no se aceptan duplicados.");
                }
                else{
                    agregarAreaMercadoCubierto(areas.get(keyArea));
                }
                break;
            case 6:
                listarMercadosCubiertos();
                int keyAreaCubierta = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > cantidadMercadosCubiertos);
                eliminarAreaMercadoCubierto(keyAreaCubierta);
                break;
            case 7:
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                if(BaseDeDatosSingleton.usuarioAsesoraAEmpresa(this,empresas.get(keyEmpresa))){
                    System.out.println("El usuario ya asesora dicha empresa!");
                }
                else{
                    System.out.println("Ingrese la fecha de inicio dd/mm/aaaa:");
                    empresas.get(keyEmpresa).agregarAsesor(this,Consola.leerFecha());
                }
                break;
            case 8:
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                if(!BaseDeDatosSingleton.usuarioAsesoraAEmpresa(this,empresas.get(keyEmpresa))){
                    empresas.get(keyEmpresa).eliminarAsesor(this);
                    System.out.println("El asesor " + getNombre() + " se desvinculo exitosamente de la empresa " +
                            empresas.get(keyEmpresa).getNombre());
                }
            default:
                break;
        }
    }

    public void listarMercadosCubiertos(){
        for(var merc : mercadosCubiertos.entrySet()){
            System.out.println("-"+merc.getValue());
        }
    }
    public void agregarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.put(cantidadMercadosCubiertos+1,area);
        cantidadMercadosCubiertos++;
    }
    private void eliminarAreaMercadoCubierto(int keyArea){
        mercadosCubiertos.remove(keyArea);
    }
    public boolean asesoraElArea(AreasMercado area){
        return mercadosCubiertos.entrySet().contains(area);
    }

    @Override
    public String toString() {
        return "Asesor{" +
                "titulacion='" + titulacion + '\'' +
                ", mercadosCubiertos=" + mercadosCubiertos +
                ", cantidadMercadosCubiertos=" + cantidadMercadosCubiertos +
                '}';
    }
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
}
