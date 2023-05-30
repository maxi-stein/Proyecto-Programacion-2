package holding;

import java.time.LocalDate;
import java.util.ArrayList;


public class BaseDatosHolding{
    private ArrayList<Usuario> usuarios;
    private ArrayList<Empresa> empresas;

    public BaseDatosHolding(){
        usuarios = new ArrayList<>();
        empresas = new ArrayList<>();

        usuarios.add(new Admin("Mati","Calle Falsa","123"));
        //empresas.add(new Empresa("Terrabusi", LocalDate.now(),100));
        //usuarios.add(new Vendedor("Maxi","Calle Verdadera","321",LocalDate.now(),empresas.get(0)));
        usuarios.add(new Asesor("Pepe","Fal 123","1","Especialista en Mineria"));
        Asesor a = (Asesor) usuarios.get(2);
        a.agregarAreaMercadoCubierto(AREAS_MERCADO.MINERIA);
        a.agregarEmpresaAsesorada(empresas.get(0),LocalDate.now());
        Vendedor v = (Vendedor) usuarios.get(1);
        v.captarVendedor(new Vendedor("Corsi","Calle semifalsa","145",LocalDate.now(),empresas.get(0)));
    }
    public  Usuario iniciarSesion(){
        System.out.println("Usuario:");
        String user = Consola.leerString();
        System.out.println("Contraseña:");
        String pass = Consola.leerString();

        return autenticarUsuario(user,pass);
    }

    private Usuario autenticarUsuario(String user, String pass){
        Usuario usuarioEncontrado = null;
        for(Usuario usuario : usuarios){
            if(usuario.validarNombre(user)){
                if(usuario.validarPass(pass)){
                    usuarioEncontrado = usuario;
                    break;
                }
            }
        }
        return usuarioEncontrado;
    }

}
