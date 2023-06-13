package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vendedor extends Usuario {

    private LocalDate fechaCaptado;
    private ArrayList<Vendedor> vendedoresCaptados;
    private Empresa empresaTrabajo;


    public Vendedor(String nombre, String direccion, String pass, LocalDate fechaCaptado,Empresa empresaTrabajo) {
        super(nombre, direccion, pass);
        this.fechaCaptado = fechaCaptado;
        this.empresaTrabajo = empresaTrabajo;
        vendedoresCaptados = new ArrayList<>();
    }

    public Vendedor(){
        super();
    }

    public void captarVendedor(Vendedor vendedor){
        vendedoresCaptados.add(vendedor);
    }
    public int contadorVendedoresCapt(){
        return vendedoresCaptados.size();
    }
    @Override
    public void proceder(BaseDatosHolding bd) {
        MenuPrincipalVendedor mp = new MenuPrincipalVendedor();
        mp.ejecutar(bd);
    }
    @Override
    public String toString() {
        return "Vendedor{" +
                "fechaCaptado=" + fechaCaptado +
                ", vendedoresCaptados=" + vendedoresCaptados +
                ", empresaTrabajo=" + empresaTrabajo +
                '}';
    }
    public void mostrarEmpresaActual(){
        System.out.println(empresaTrabajo.displayInfo());
    }

    public void mostrarCantVendCaptados(){
        System.out.println("La cantidad de vendedores captados es: "+contadorVendedoresCapt());
    }

    private void listarVendedoresCaptados(){
        if(vendedoresCaptados.size()==0){
            System.out.println("Aun no posee vendedores captados");
        }
        else {
            for (Vendedor v : vendedoresCaptados) {
                System.out.println(v.toString());
            }
        }
    }

    public void setEmpresaTrabajo(Empresa empresaTrabajo) {
        this.empresaTrabajo = empresaTrabajo;
    }


    public Empresa getEmpresaTrabajo() {
        return empresaTrabajo;
    }
}
