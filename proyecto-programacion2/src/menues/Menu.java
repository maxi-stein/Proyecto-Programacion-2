package menues;

import java.util.HashMap;

public abstract class Menu implements CapazDeEjecutarAccionMenu {
    private HashMap<Integer,CapazDeEjecutarAccionMenu> acciones;
    public Menu() {
        acciones = new HashMap<>();
    }
    public void agregarAccion(int key,CapazDeEjecutarAccionMenu accion){
        acciones.put(key,accion);
    }
    public CapazDeEjecutarAccionMenu getAccion(int key) {
        return acciones.get(key);
    }
}
