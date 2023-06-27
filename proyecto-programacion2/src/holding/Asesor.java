package holding;

import menues.MenuPrincipalNoAdmin;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Se encarga de asesorar a las empresas del holding que utilicen las areas de mercado de dicho asesor
 */
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

    /**
     * Muestra la informacion del usuario
     */
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

    /**
     * Permite modificar la informacion del usuario
     */
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

        HashMap<Integer,Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
        int keyEmpresa = 0;

        switch (opcion){
            case 1:
                System.out.println("Ingrese Nombre: ");
                String nombre = Consola.leerString();
                setNombre(nombre);
                break;
            case 2:
                System.out.println("Ingrese nueva Contraseña: ");
                String pass = Consola.leerString();
                setPass(pass);
                break;
            case 3:
                System.out.println("Ingrese Dirección: ");
                String direccion = Consola.leerString();
                setDireccion(direccion);
                break;
            case 4:
                System.out.println("Ingrese Titulación: ");
                String titulacion = Consola.leerString();
                setTitulacion(titulacion);
                break;
            case 5:
                System.out.println("Seleccione Area de Mercado:\n");
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
                System.out.println("Seleccione Area de Mercado:\n");
                this.listarMercadosCubiertos();
                int keyAreaCubierta = 0;
                do {
                    keyArea = Consola.leerEntero();
                } while (keyArea < 0 || keyArea > cantidadMercadosCubiertos);
                eliminarAreaMercadoCubierto(keyAreaCubierta);
                break;
            case 7:
                System.out.println("Seleccione Empresa:\n");
                BaseDeDatosSingleton.listarEmpresas();
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                //if(BaseDeDatosSingleton.usuarioAsesoraAEmpresa(this,empresas.get(keyEmpresa))){
                if(empresas.get(keyEmpresa).esAsesoradoPor(this)){
                    System.out.println("El usuario ya asesora dicha empresa!");
                }
                else{
                    System.out.println("Ingrese la fecha de inicio dd/mm/aaaa:");
                    LocalDate fechaInicioModif = Consola.leerFecha();
                    Asesor asesorModif = this;
                    BaseDeDatosSingleton.agregarAsesorAEmpresa(keyEmpresa, asesorModif, fechaInicioModif);
                    //empresas.get(keyEmpresa).agregarAsesor(this,Consola.leerFecha());
                }
                break;
            case 8:
                System.out.println("SELECCIONE EMPRESA A DESVINCULAR - 0 para salir -:");
                BaseDeDatosSingleton.listarEmpresasDeAsesor(this);
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || !empresas.containsKey(keyEmpresa));

                if(BaseDeDatosSingleton.usuarioAsesoraAEmpresa(this,empresas.get(keyEmpresa))){
                   // empresas.get(keyEmpresa).eliminarAsesor(this);
                    BaseDeDatosSingleton.eliminarAsesorDeEmpresa(keyEmpresa, this);
                    System.out.println("El asesor " + getNombre() + " se desvinculo exitosamente de la empresa " +
                            empresas.get(keyEmpresa).getNombre());
                }
            default:
                break;
        }
    }

    /**
     * Muestra por pantalla las areas de mercado que cubre el usuario
     */
    public void listarMercadosCubiertos(){
        for(var merc : mercadosCubiertos.entrySet()){
            System.out.println("-"+merc.getValue());
        }
    }

    /**
     * Permite agregar un area de mercado determinada al listado de areas de mercados que puede asesorar el usuario
     * @param area Area de mercado a agregar
     */
    public void agregarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.put(cantidadMercadosCubiertos+1,area);
        cantidadMercadosCubiertos++;
    }

    /**
     * Elimina el area de mercado que asesora el usuario
     * @param keyArea
     */

    private void eliminarAreaMercadoCubierto(int keyArea){
        mercadosCubiertos.remove(keyArea);
    }

    /**
     * Evalua si el usuario asesora un area de mercado en particular
     * @param area Area de mercado a evaluar
     * @return boolean de la evaluacion
     */
    public boolean asesoraElArea(AreasMercado area){
        return mercadosCubiertos.entrySet().contains(area);
    }

    @Override
    public String toString() {
        return getCodigoUsuario() + "-" + getNombre()+", "+ titulacion;
    }
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
}
