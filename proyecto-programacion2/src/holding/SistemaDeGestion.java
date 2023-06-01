package holding;

public class SistemaDeGestion implements CapazDeVisualizarMenu {

    private BaseDatosHolding bd;
    public SistemaDeGestion(){
        bd = new BaseDatosHolding();
    }
    public void run(){
        int num=1;//mati do while
        while(num!=2) {
            num=mostrarMenu();
            if (num == 1) {
                Usuario loggedInUsuario = bd.iniciarSesion();
                loggedInUsuario.proceder();
                num = mostrarMenu();
            }
        }
    }

    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema \n-");
        System.out.println();
        return Consola.leerEntero();

    }

}
