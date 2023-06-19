package holding;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatosSingleton {
    private static BaseDeDatosSingleton INSTANCE;

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;

    private BaseDeDatosSingleton(){
        INSTANCE = this;
    }
    public static BaseDeDatosSingleton getInstance(){
        if(INSTANCE==null){
            new BaseDeDatosSingleton();
        }
        return INSTANCE;
    }
    public static void cargarDatosSerializados(HashMap<Integer,Usuario> usuarios2,
                                               HashMap<Integer,Empresa> empresas2,
                                               HashMap<Integer,AreasMercado> areasDeMercado2,
                                               HashMap<Integer,Ciudad> ciudades2){
        usuarios = usuarios2;
        empresas = empresas2;
        areasDeMercado = areasDeMercado2;
        ciudades = ciudades2;

    }
    public static Usuario iniciarSesion(){
        Usuario usuarioIN = null;
        int intentos = 0;
        while(usuarioIN == null && intentos < 3 ) {
            intentos ++;

            System.out.println("Numero de Usuario:");
            int user = Consola.leerEntero();
            System.out.println("Contraseña:");
            String pass = Consola.leerString();

            try{
                usuarioIN =  autenticarUsuario(user,pass);
            }catch (NullPointerException e) {
                System.out.println("Usuario o contraseña incorrecta");
            }
        }
        return usuarioIN;
    }
    private static Usuario autenticarUsuario(int userCod, String pass){
        Usuario usuarioEncontrado = null;
        if(usuarios.get(userCod).validarPass(pass)){
            usuarioEncontrado = usuarios.get(userCod);
        }
        return usuarioEncontrado;
    }
    public static void listarUsuarios() {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if(!usuario.estaBloqueado()){
                System.out.println(i + " - " + usuario.toString());
            }
        }
    }
    public static void agregarUsuario(Usuario u){
        usuarios.put(u.getCodigoUsuario(),u);
    }
    public static void eliminarUsuario(int key){
        usuarios.get(key).setBloqueo(true);
    }
    public static HashMap<Integer,Empresa> listarEmpresas(){
        for (Map.Entry<Integer, Empresa> empresa : empresas.entrySet()) {
            Integer key = empresa.getKey();
            Empresa value = empresa.getValue();
            System.out.println(key + " " + value + '\n');
        }
        return empresas;
    }
    public static HashMap<Integer,AreasMercado> listarAreasDeMercado(){
        for (Map.Entry<Integer, AreasMercado> area : areasDeMercado.entrySet()) {
            Integer key = area.getKey();
            AreasMercado value = area.getValue();
            System.out.println(key + " " + value + '\n');
        }
        return areasDeMercado;
    }
    public static void agregarAsesorAEmpresa(Integer keyEmpresa, Asesor a, LocalDate fechaInicio){
        empresas.get(keyEmpresa).agregarAsesor(a,fechaInicio);
    }
    public static void modificarUsuario(int key){
        usuarios.get(key).modificar();
    }
    public static boolean contieneUsuario(int key){
        return usuarios.containsKey(key);
    }
    public static boolean usuarioAsesoraAEmpresa(Asesor a,Empresa e){
        return e.esAsesoradoPor(a);
    }
}
