package holding;

import java.util.ArrayList;

public class Asesor extends Usuario{
    private String titulacion;
    private ArrayList<AreasMercado> mercadosCubiertos;

    public Asesor(String nombre, String direccion, String pass, String titulacion) {
        super(nombre, direccion, pass);
        this.titulacion = titulacion;
        mercadosCubiertos = new ArrayList<>();
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
            for (AreasMercado area : mercadosCubiertos) {
                //System.out.println(area.name() + " - " + area.getDescripcion());
            }
            System.out.println();
        }
    }

    public void agregarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.add((area));
    }

    public void eliminarAreaMercadoCubierto(AreasMercado area){
        mercadosCubiertos.remove((area));
    }
}
