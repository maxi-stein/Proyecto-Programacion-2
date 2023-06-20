package holding;

import menues.MenuPrincipalVendedor;

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
        this.fechaCaptado = LocalDate.now();
        vendedoresCaptados = new ArrayList<>();
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
    public void mostrarInfo() {
        mostrarCredenciales();
        System.out.println("Empresa de trabajo actual: ");
        System.out.print(empresaTrabajo.getNombre()+ "(Captado: "+fechaCaptado+")");
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
        System.out.println("5 - Salir");
        int opcion = 0;
        while(opcion<1 || opcion>5){
            opcion = Consola.leerEntero();
        }
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
                System.out.println("Seleccione la empresa a la cual cambiar: ");
                bd.listarEmpresas();
                HashMap<Integer,Empresa> empresas = bd.obtenerEmpresas();
                int keyEmpresa = 0;
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());

                empresaTrabajo.eliminarVendedor(this); //retiro el vendedor de la empresa vieja
                empresas.get(keyEmpresa).agregarVendedor(this); //ingreso el vendedor a la empresa nueva
                empresaTrabajo = empresas.get(keyEmpresa); //actualizo el atributo de la empresa de trabajo del vendedor
                break;
            default:
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
