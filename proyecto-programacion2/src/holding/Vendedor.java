package holding;

import menues.MenuPrincipalNoAdmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Vendedor es una clase heredada de {@link Usuario} el cual puede poseer de manera jeraquica vendedores captados. Trabajan todos para una unica {@link Empresa}
 */

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
    public int getCantidadVendedoresCapt(){
        return vendedoresCaptados.size();

    }
    @Override
    public void proceder() {
        MenuPrincipalNoAdmin mp = new MenuPrincipalNoAdmin();
        mp.ejecutar();
    }
    @Override
    public void mostrarInfo() {
        mostrarCredenciales();
        Consola.mostrarUnRenglon("Empresa de trabajo actual: ");
        Consola.mostrarUnRenglon(empresaTrabajo.getNombre()+ "(Captado: "+fechaCaptado.getDayOfMonth()+"/"+fechaCaptado.getMonth()+"/"+fechaCaptado.getYear()+")");
        Consola.mostrarUnRenglon("El vendedor posee un total de "+getCantidadVendedoresCapt()+" vendedores captados");
        listarVendedoresCaptados();
    }
    private String listarVendedoresCaptados(){
        StringBuilder sb = new StringBuilder();
        if(vendedoresCaptados.size()>0) {
            sb.append("Los empleados captados son: "+'\n');
            for (Vendedor v : vendedoresCaptados) {
                sb.append(v.getCodigoUsuario()+" - "+v.getNombre()+'\n');
            }
        }
        return sb.toString();
    }
    public void modificar(){
        super.modificar();
        Consola.mostrarVariosRenglones("4- Cambiar Empresa de Trabajo","5- Salir");
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
                Consola.mostrarUnRenglon("Seleccione la empresa a la cual cambiar: ");
                BaseDeDatosSingleton.listarEmpresas();
                HashMap<Integer,Empresa> empresas = BaseDeDatosSingleton.obtenerEmpresas();
                int keyEmpresa = 0;
                do {
                    keyEmpresa = Consola.leerEntero();
                } while (keyEmpresa < 0 || keyEmpresa > empresas.size());

                empresaTrabajo.eliminarVendedor(this); //retiro el vendedor de la empresa vieja
                empresas.get(keyEmpresa).agregarVendedor(this); //ingreso el vendedor a la empresa nueva
                empresaTrabajo = empresas.get(keyEmpresa); //actualizo el atributo de la empresa de trabajo del vendedor
                fechaCaptado = LocalDate.now();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCodigoUsuario()+" - "+getNombre()+'\n'+ "Empleado en: "+empresaTrabajo.getNombre()+
                "(Captado: "+fechaCaptado.getDayOfMonth()+"/"+fechaCaptado.getMonth()+"/"+fechaCaptado.getYear()+")"+'\n'
                + "El vendedor posee un total de "+getCantidadVendedoresCapt()+" vendedores captados");
        sb.append(listarVendedoresCaptados());
        return sb.toString();
    }
}
