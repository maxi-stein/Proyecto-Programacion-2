package holding;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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
    public void agregarCiudad(int keyCiudad){
        Ciudad ciudadSeleccionada = BaseDeDatosSingleton.obtenerCiudades().get(keyCiudad);
        if(!ciudades.containsValue(ciudadSeleccionada)){
            ciudades.put(ciudades.size()+1,ciudadSeleccionada);
        }
        else {
            System.out.println("No se admiten repetidos!");
        }
    }
    public void eliminarCiudad(Ciudad c){
            ciudades.entrySet().remove(c);
    }
    public void listarCiudades(){
        for(var parClaveValor : ciudades.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue());
        }
    }
    public HashMap<Integer, Ciudad> obtenerCiudades(){
        return ciudades;
    }
    public void seleccionarSede(Ciudad c){ //recordar que para seleccionar una sede, antes hay que listar todas las disponibles
        if(ciudades.entrySet().contains(c)){
            throw new RuntimeException("La ciudad no se encuentra ingresada a la empresa! Verifique haberla ingresado a la empresa antes de convertirla en Sede");
        }
        if(sede == c){
            throw new RuntimeException("La ciudad ya es sede!");
        }
        sede=c;
        System.out.println("Sede modificada exitosamente");
    }
    public boolean estaUbicadaEnCiudad(Ciudad c){
        return ciudades.containsValue(c);
    }
    public void agregarAsesor(Asesor a,LocalDate fechaInicio){
        for(int i=0;i<asesores.size();i++){
            if(asesores.get(i).contiene(a)) {
                throw new RuntimeException("El asesor ya se encuentra vinculado a la empresa");
            }
        }
        asesores.add(new VinculacionEmpresaAsesor(a,fechaInicio));
    }
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
    public boolean contieneAreaDeMercado(AreasMercado a){
        return areasMercado.containsValue(a);
    }
    public void listarAreasDeMercado(){
        for(var parClaveValor : areasMercado.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue());
        }
    }
    public void agregarAreaMercado(AreasMercado a){
        if(!areasMercado.containsValue(a)){
            areasMercado.put(areasMercado.size() + 1, a);
            System.out.println("Area de mercado agregada exitosamente!");
        }
        else{
            System.out.println("No se pueden agregar repetidos!");
        }
    }
    public void eliminarAreaMercado(AreasMercado a) {
        try{
            areasMercado.remove(a);
        }
        catch(NullPointerException e){
            System.out.println("No se encontró registro del Area de Mercado en "+nombre);
        }
    }
    public HashMap<Integer,AreasMercado> obtenerAreasDeMercado(){
        return areasMercado;
    }
    public void agregarVendedor(Vendedor v){
        if(vendedores.contains(v)){
            throw new RuntimeException("El vendedor ya se encuentra asociado.");
        }
        vendedores.add(v);
    }

    public void eliminarVendedor(Vendedor v){
        if(!vendedores.contains(v)){
            throw new RuntimeException("Vendedor no encontrado.");
        }
        vendedores.remove(v);
        System.out.println("Vendedor eliminado.");
    }
    public boolean areaCompatible(AreasMercado a){
        return areasMercado.entrySet().contains(a);
    }
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
        if(asesores.size()>0 || vendedores.size()>0)
        {
            System.out.println("La empresa registra empleados activos!");
            System.out.println("Empleados:");
            for(int i = 0; i<asesores.size(); i++){
                System.out.println(asesores.get(i).toString());
            }
            for(int i = 0; i<vendedores.size(); i++){
                System.out.println(vendedores.get(i).toString());
            }
            valor = true;
        }
        return valor;
    }
    public void eliminarRegistrosAreaDeMercado(){
        areasMercado.clear();
    }


}