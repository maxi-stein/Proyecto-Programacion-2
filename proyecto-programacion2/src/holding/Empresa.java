package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empresa implements CapazDeSerBloqueado{
    private static int CONTADOR = 0;
    private int codigoEmpresa;
    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private ArrayList<Ciudad> ciudades;
    private Ciudad sede;
    private ArrayList<VinculacionEmpresaAsesor> asesores;
    private ArrayList<AreasMercado> areasMercado;
    private ArrayList<Vendedor> vendedores;
    private boolean bloqueado;
    public Empresa(String nombre, LocalDate fechaEntrada, double facturacionAnual) {
        CONTADOR++;
        this.codigoEmpresa = CONTADOR;
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.facturacionAnual = facturacionAnual;
        ciudades = new ArrayList<>();
        sede = null;
        areasMercado = new ArrayList<>();
        asesores = new ArrayList<>();
        vendedores = new ArrayList<>();
        bloqueado = false;
    }
    public void setBloqueo(boolean valor){
        this.bloqueado = valor;
    }
    public boolean estaBloqueado(){
        return bloqueado;
    }
    public void agregarCiudad(Ciudad c){
        if(ciudades.contains(c)){
            throw new RuntimeException("La ciudad ya se encuentra ingresada");
        }
       ciudades.add(c);
    }
    public void eliminarCiudad(Ciudad c){
        if(!ciudades.contains(c)){
            throw new RuntimeException("La ciudad no esta ingresada en la empresa!");
        }
        if(sede == c){
            sede = null;
            System.out.println("ADVERTENCIA: Se elimino la ciudad sede");
        }
        ciudades.remove(c);
    }
    public void seleccionarSede(Ciudad c){ //recordar que para seleccionar una sede, antes hay que listar todas las disponibles
        if(!ciudades.contains(c)){
            throw new RuntimeException("La ciudad no se encuentra ingresada a la empresa");
        }
        if(sede == c){
            throw new RuntimeException("La ciudad ya es sede");
        }
        sede=c;
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
    public void agregarAreaMercado(AreasMercado a){
        if(areasMercado.contains(a))
        {
            throw new RuntimeException("El área ya se encuentra ingresada.");
        }
        areasMercado.add(a);
    }
    public void eliminarAreaMercado(AreasMercado a)
    {
        if(!areasMercado.contains(a))
        {
          throw new RuntimeException("Area no encontrada.");
        }
        areasMercado.remove(a);
    }
    public void agregarVendedor(Vendedor v)
    {
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
    }
    public Boolean areaCompatible(AreasMercado a){
        return areasMercado.contains(a);
    }
    public void traspasoDeVendedor(Vendedor v){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        eliminarVendedor(v);
        agregarVendedorAEmpresa(v);
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

    private void agregarVendedorAEmpresa(Vendedor v){
        vendedores.add(v);
        v.setEmpresaTrabajo(this); //actualizo la nueva empresa de trabajo
        v.getEmpresaTrabajo().eliminarVendedor(v); //elimino el vendedor de la empresa de trabajo vieja
    }
    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", facturacionAnual=" + facturacionAnual +
                ", ciudades=" + ciudades +
                ", sede=" + sede +
                ", asesores=" + asesores +
                ", areasMercado=" + areasMercado +
                ", vendedores=" + vendedores +
                '}';
    }
    public String getNombre(){
        return nombre;
    }
}