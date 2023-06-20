package menues;

import holding.BaseDeDatosSingleton;

public class MenuMostrarInformacion extends Menu {
    @Override
    public void ejecutar() {
        BaseDeDatosSingleton.getUsuarioLogueado().mostrarInfo();
    }
}
