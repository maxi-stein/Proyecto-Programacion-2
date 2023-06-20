package menues;

import holding.BaseDeDatosSingleton;

public class MenuEliminarAreaDeMercado implements CapazDeEjecutarAccionMenu {

    private BaseDeDatosSingleton bd;
    public MenuEliminarAreaDeMercado(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("MENU SIN IMPLEMENTAR");

    }
}
