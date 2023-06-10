package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empresa{

    private String nombre;
    private LocalDate fechaEntrada;
    private double facturacionAnual;
    private ArrayList<Ciudad> ciudades;
    private Ciudad sede;
    private ArrayList<VinculacionEmpresaAsesor> asesores;
    private ArrayList<AreasMercado> areasMercado;
    private ArrayList<Vendedor> vendedores;
    private int contador;
    private int codigoEmpresa;

    public Empresa(String nombre, LocalDate fechaEntrada, double facturacionAnual) {
        contador++;
        this.codigoEmpresa = codigoEmpresa;
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.facturacionAnual = facturacionAnual;
        ciudades = new ArrayList<>();
        sede = null;
        areasMercado = new ArrayList<>();
        asesores = new ArrayList<>();
        vendedores = new ArrayList<>();
    }

    public String displayInfo(){
        return String.format("%s \n",nombre);
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
        int flag=0;
        for(int i=0;i<asesores.size();i++){
            if(asesores.get(i).contiene(a)) {
                asesores.remove(asesores.get(i));
                flag=1;
            }
        }
        if(flag ==0 ) {
            throw new RuntimeException("El asesor ya se encuentra vinculado a la empresa");
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

}