package holding;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es una empresa que desarrolla actividad en ciertas areas de mercado
 * para el holding. Contiene tanto vendedores como asesores.
 */
public class Empresa implements CapazDeSerBloqueado, Serializable {
    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private HashMap<Integer,Ciudad> ciudades;
    private Ciudad sede;
    private ArrayList<VinculacionEmpresaAsesor> asesores;
    private HashMap<Integer,AreasMercado> areasMercado;
    private ArrayList<Vendedor> vendedores;
    private boolean bloqueado;
    public Empresa(String nombre, LocalDate fechaEntrada, double facturacionAnual) {
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.facturacionAnual = facturacionAnual;
        ciudades = new HashMap<>();
        sede = null;
        areasMercado = new HashMap<>();
        asesores = new ArrayList<>();
        vendedores = new ArrayList<>();
        bloqueado = false;
    }
    public void setBloqueo(boolean valor){
        this.bloqueado = valor;
    }
    public boolean noEstaBloqueado(){
        return !bloqueado;
    }

    /**
     * Agrega una ciudad al listado de ciudades en las que desarrolla actividad la empresa
     * @param keyCiudad llave del map de ciudades de la emrpesa
     */
    public void agregarCiudad(int keyCiudad){
        Ciudad ciudadSeleccionada = BaseDeDatosSingleton.obtenerCiudades().get(keyCiudad);
        if(!ciudades.containsValue(ciudadSeleccionada)){
            ciudades.put(ciudades.size()+1,ciudadSeleccionada);
        }
        else {
            System.out.println("No se admiten repetidos!");
        }
    }

    /**
     * Elimina la ciudad de la empresa
     * @param c
     */
    public void eliminarCiudad(Ciudad c){
            ciudades.entrySet().remove(c);
    }

    /**
     * Muestra por pantalla las ciudades de la empresa
     */
    public void listarCiudades(){
        for(var parClaveValor : ciudades.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue());
        }
    }

    /**
     * Devuelve el map de ciudades de la empresa
     * @return map de ciudades de la empresa
     */
    public HashMap<Integer, Ciudad> obtenerCiudades(){
        return ciudades;
    }

    /**
     * Permite modificar cual es la sede de la ciudad
     * @param c Ciudad que sera sede
     */
    public void seleccionarSede(Ciudad c){
        if(ciudades.entrySet().contains(c)){
            throw new RuntimeException("La ciudad no se encuentra ingresada a la empresa! Verifique haberla ingresado a la empresa antes de convertirla en Sede");
        }
        if(sede == c){
            throw new RuntimeException("La ciudad ya es sede!");
        }
        sede=c;
        System.out.println("Sede modificada exitosamente");
    }

    /**
     * Evalua si la empresa desarrolla o no actividad en dicha ciudad
     * @param c Ciudad condicion
     * @return boolean del resultado de la evaluacion
     */
    public boolean estaUbicadaEnCiudad(Ciudad c){
        return ciudades.containsValue(c);
    }

    /**
     * Agrega un asesor a la empresa. Tambien crea una instancia de {@link VinculacionEmpresaAsesor} para mantener
     * actualizada la informacion respecto a la fecha de inicio
     * @param a
     * @param fechaInicio
     */
    public void agregarAsesor(Asesor a,LocalDate fechaInicio){
        for(int i=0;i<asesores.size();i++){
            if(asesores.get(i).contiene(a)) {
                throw new RuntimeException("El asesor ya se encuentra vinculado a la empresa");
            }
        }
        asesores.add(new VinculacionEmpresaAsesor(a,fechaInicio));
    }

    /**
     * Elimina un asesor de la lista de asesores de la empresa
     * @param a asesor a eliminar
     */
    public void eliminarAsesor(Asesor a) {
        int flag=0,i=0;
        while(i<asesores.size() && flag==0){
            if(asesores.get(i).contiene(a)) {
                asesores.remove(i);
                flag=1;
            }
            i++;
        }
    }

    /**
     * Evalua si contiene un area de mercado
     * @param a area de mercado a evaluar
     * @return boolean del resultado de la evaluacion
     */
    public boolean contieneAreaDeMercado(AreasMercado a){
        return areasMercado.containsValue(a);
    }

    /**
     * muestra por pantalla todas las areas de mercado de la empresa
     */
    public void listarAreasDeMercado(){
        for(var parClaveValor : areasMercado.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue());
        }
    }

    /**
     * agrega un area de mercado a la empresa
     * @param a Area de mercado a agregar
     */
    public void agregarAreaMercado(AreasMercado a){
        boolean contiene = false;
        for(int i=1;i<=areasMercado.size();i++){
            if(areasMercado.get(i).esIgual(a)){
                contiene = true;
            }
        }
        if(!contiene){
            areasMercado.put(areasMercado.size() + 1, a);
            System.out.println("Area de mercado agregada exitosamente!");
        }
        else{
            System.out.println("No se pueden agregar repetidos!");
        }
    }

    /**
     * Elimina un area de mercado de la empresa
     * @param a area de mercado a eliminar
     */
    public void eliminarAreaMercado(AreasMercado a) {
        try{
            areasMercado.remove(a);
        }
        catch(NullPointerException e){
            System.out.println("No se encontró registro del Area de Mercado en "+nombre);
        }
    }

    /**
     * Devuelve el map de las areas de mercado de la empres
     * @return map de areas de mercado
     */
    public HashMap<Integer,AreasMercado> obtenerAreasDeMercado(){
        return areasMercado;
    }

    /**
     * Agrega un vendedor al listado de vendedores de la empres
     * @param v Vendedora a agregar
     */
    public void agregarVendedor(Vendedor v){
        if(vendedores.contains(v)){
            throw new RuntimeException("El vendedor ya se encuentra asociado.");
        }
        vendedores.add(v);
    }

    /**
     * Elimina un vendedor de la empresa
     * @param v vendedor a eliminar
     */
    public void eliminarVendedor(Vendedor v){
        if(!vendedores.contains(v)){
            throw new RuntimeException("Vendedor no encontrado.");
        }
        vendedores.remove(v);
        System.out.println("Vendedor eliminado.");
    }

    /**
     * Evalua si la empresa es aseosarada por un determinado asesor
     * @param a asesor
     * @return boolean del resultado de la evaluacion
     */
    public boolean esAsesoradoPor(Asesor a){
        boolean contiene = false;
        int i=0;
        while(i<asesores.size() && !contiene){
            if(asesores.get(i).contiene(a)){
                contiene = true;
            }
            i++;
        }
        return contiene;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public double getFacturacionAnual() {
        return facturacionAnual;
    }
    public void setFacturacionAnual(double facturacionAnual) {
        this.facturacionAnual = facturacionAnual;
    }
    public Ciudad getSede() {
        return sede;
    }
    public void mostrarFechaInicioAsesor(Asesor a){
        for(VinculacionEmpresaAsesor vinc : asesores){
            if(vinc.contiene(a)){
                System.out.println(vinc.getFechaInicio());
            }
        }
    }
    public boolean tieneTrabajadores(){
        boolean valor = false;
        if(asesores.size()>0 || vendedores.size()>0) {
            System.out.println("La empresa registra empleados activos!");
            valor = true;
        }
        return valor;
    }
    public void listarTrabajadores(){
        System.out.println("Empleados:");
        for(int i = 0; i<asesores.size(); i++){
            System.out.println(asesores.get(i).getAsesor().getNombre()+" - Usuario N: "+asesores.get(i).getAsesor().getCodigoUsuario());
        }
        for(int i = 0; i<vendedores.size(); i++){
            System.out.println(vendedores.get(i).toString());
        }
    }
    public void eliminarRegistrosAreaDeMercado(){
        areasMercado.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre + ", actividad comenzada en: " + fechaEntrada + '\n' +
                "Facturacion Anual: $" + facturacionAnual + '\n' +
                "Ciudades sede: "+ sede + '\n'+"Vendedores: "+'\n');
        for(int i=0;i<vendedores.size();i++){
            sb.append(vendedores.get(i).getCodigoUsuario()+"-"+vendedores.get(i).getNombre()+'\n');
        }
        sb.append("Asesores: "+'\n');
        for(int i=0;i<asesores.size();i++){
            sb.append(asesores.get(i).getAsesor().toString()+" (iniciado "+asesores.get(i).getFechaInicio()+")"+'\n');
        }
        sb.append("Areas de Mercado: ");
        for(int i=1;i<= areasMercado.size();i++){
            sb.append(areasMercado.get(i).getNombre()+'\n');
        }
        return sb.toString();
    }
}