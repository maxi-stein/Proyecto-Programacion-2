package holding;

/**
 * Interfaz que permite bloquear o desbloquear logicamente una clase
 */
public interface CapazDeSerBloqueado {
    void setBloqueo(boolean valor);
    boolean noEstaBloqueado();
}
