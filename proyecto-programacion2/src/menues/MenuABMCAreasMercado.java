package menues;

import holding.BaseDeDatosSingleton;

public class MenuABMCAreasMercado extends Menu {
    private BaseDeDatosSingleton bd;
    public MenuABMCAreasMercado(){
        bd = BaseDeDatosSingleton.getInstance();
    }
    @Override
    public void ejecutar() {
        System.out.println("menu mercado sin implementar");
    }
}
