package menues;

import java.util.HashMap;

public abstract class MenuSuper implements CapazDeEjecutarAccionMenu {
    private HashMap<Integer,CapazDeEjecutarAccionMenu> acciones;
    public MenuSuper() {
        acciones = new HashMap<>();
    }
    public void agregarAccion(int key,CapazDeEjecutarAccionMenu accion){
        acciones.put(key,accion);
    }
    public CapazDeEjecutarAccionMenu getAccion(int key) {
        return acciones.get(key);
    }
}
