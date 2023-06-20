package menues;

import holding.FactoryUsuarios;

public class MenuVendedorCaptado implements CapazDeEjecutarAccionMenu{
    @Override
    public void ejecutar() {
        System.out.println("Ingrese los datos del vendedor captado:");
        FactoryUsuarios.crearUsuario(4); //opcion 4 = Vendedor captado
    }
}
