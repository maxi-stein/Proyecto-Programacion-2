package menues;

import holding.BaseDeDatosSingleton;

public class MenuModificarAreaDeMercado implements CapazDeEjecutarAccionMenu {

    private BaseDeDatosSingleton bd;
    public MenuModificarAreaDeMercado(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("MENU SIN IMPLEMENTAR");

    }

}
