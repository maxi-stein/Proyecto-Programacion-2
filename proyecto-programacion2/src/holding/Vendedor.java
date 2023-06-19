package holding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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
    public void proceder() {
        MenuPrincipalVendedor mp = new MenuPrincipalVendedor();
        mp.ejecutar();
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
    public void modificar(){
        BaseDeDatosSingleton bd = BaseDeDatosSingleton.getInstance();
        super.modificar();
        System.out.println("4 - Cambiar Empresa de Trabajo");
        int opcion = 0;
        while(opcion<1 || opcion>4){
            opcion = Consola.leerEntero();
        }
        System.out.println("Ingrese el registro: ");
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
                System.out.println("Elija la empresa a la cual cambiar: ");
                HashMap<Integer,Empresa> empresas = bd.listarEmpresas();
                int keyEmpresa = 0;
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());
                //actualizo la empresas vieja
                empresaTrabajo.traspasoDeVendedor(this);
                break;
            case 5:
                break;
        }
    }
    public void setEmpresaTrabajo(Empresa empresaTrabajo) {
        this.empresaTrabajo = empresaTrabajo;
    }
    public Empresa getEmpresaTrabajo() {
        return empresaTrabajo;
    }
}
