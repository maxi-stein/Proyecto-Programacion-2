package holding;

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
    }
    @Override
    public void proceder(BaseDatosHolding bd) {
        MenuPrincipalAsesor mpa = new MenuPrincipalAsesor();
        mpa.ejecutar(bd);
    }

    public void listarEmpresas(){
        System.out.println("Implementar codigo de listado de empresas. Se debe acceder al hashmap de empresas de basededatos, luego al arraylist de VinculacionEmpresa asesor" +
                "y verificar si dicho asesor esta dentro o no. Si nolo esta, se avanza a la siguiente empresa y asi sucesivamente...");
    }
    public void listarAreasMercado(){
        if(mercadosCubiertos.isEmpty()){
            System.out.println("El usuario no asesora ningun area de mercado aún.");
        }
        else {
            System.out.println("AREAS:");
            for (int i=0;i<mercadosCubiertos.size();i++){
                System.out.println(i+" - "+mercadosCubiertos.get(i));
            }
            System.out.println();
        }
    }

    public void agregarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.put(cantidadMercadosCubiertos,area);
        cantidadMercadosCubiertos++;
    }

    public void eliminarAreaMercadoCubierto(int keyArea){
        mercadosCubiertos.remove(keyArea);
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public boolean asesoraElArea(AreasMercado area){
        boolean contiene =false;
        for(int i=0;i<mercadosCubiertos.size();i++){
            if(mercadosCubiertos.get(i) == area){
                contiene = true;
            }
        }
        return contiene;
    }

    public HashMap<Integer,AreasMercado> listarAreasMercadoCubiertas(){
        for(int i=0;i<mercadosCubiertos.size();i++){
            System.out.println(i+" - "+mercadosCubiertos.get(i));
        }
        return mercadosCubiertos;
    }
}
