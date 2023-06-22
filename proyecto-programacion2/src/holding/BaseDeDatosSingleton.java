package holding;

import java.time.LocalDate;
import java.util.HashMap;

public class BaseDeDatosSingleton {
    private static BaseDeDatosSingleton INSTANCE;
    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    private static Usuario usuarioLogueado;

    private BaseDeDatosSingleton(){
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        areasDeMercado = new HashMap<>();
        ciudades = new HashMap<>();
        paises = new HashMap<>();
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
                                               HashMap<Integer,Ciudad> ciudades2,
                                               HashMap<Integer,Pais> paises2){
        usuarios = usuarios2;
        empresas = empresas2;
        areasDeMercado = areasDeMercado2;
        ciudades = ciudades2;
        paises = paises2;
    }
    public static Usuario iniciarSesion(){
        int intentos = 0;
        while(usuarioLogueado == null && intentos < 3 ) {
            intentos ++;
            System.out.println("Codigo de Usuario:");
            int user = Consola.leerEntero();
            System.out.println("Contraseña:");
            String pass = Consola.leerString();
            try{
                usuarioLogueado =  autenticarUsuario(user,pass);
            }catch (NullPointerException e) {
                System.out.println("Usuario o contraseña incorrecta");
            }
        }
        return usuarioLogueado;
    }
    private static Usuario autenticarUsuario(int userCod, String pass){
        Usuario usuarioEncontrado = null;
        if(usuarios.get(userCod).validarPass(pass)){
            usuarioEncontrado = usuarios.get(userCod);
        }
        return usuarioEncontrado;
    }
    public static void listarUsuarios() {
        for(var parClaveValor : usuarios.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue().toString());
        }
    }
    public static void agregarUsuario(Usuario u){
        usuarios.put(u.getCodigoUsuario(),u);
    }
    public static void eliminarUsuario(int key){
        usuarios.get(key).setBloqueo(true);
    }
    public static void listarEmpresas(){
        for(int i=1;i<=empresas.size();i++){
            if(!empresas.get(i).estaBloqueado()){
                System.out.println(i+" - "+empresas.get(i).getNombre());
            }
        }
    }
    public static HashMap<Integer,Empresa> obtenerEmpresas(){
        return empresas;
    }
    public static void listarAreasDeMercado(){
        for(int i=1;i<=areasDeMercado.size();i++){
            if(!areasDeMercado.get(i).estaBloqueado()){
                System.out.println(i+" - "+areasDeMercado.get(i).toString());
            }
        }
    }
    public static HashMap<Integer,AreasMercado> obtenerAreasDeMercado(){
        return areasDeMercado;
    }
    public static HashMap<Integer, Usuario> obtenerUsuarios(){
        return usuarios;
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
    public static void listarVendedores(){
        for (int i = 1; i <= usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if(!usuario.estaBloqueado() && usuario instanceof Vendedor){
                System.out.println(i + " - " + usuario.toString());
            }
        }
    }
    public static HashMap<Integer,Vendedor> obtenerVendedores(){
        HashMap<Integer,Vendedor> vendedores = new HashMap<>();
        for (int i = 1; i <= usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if(!usuario.estaBloqueado() && usuario instanceof Vendedor){
                vendedores.put(i,(Vendedor) usuario);
            }
        }
        return vendedores;
    }
    public static void agregarPais(Pais p){
        paises.put(paises.size()+1,p);
    }
    public static void listarPaises(){
        for(int i=1;i<=paises.size();i++){
            if(!paises.get(i).estaBloqueado()){
                System.out.println(i+" - "+paises.get(i).getNombre());
            }
        }
    }
    public static HashMap<Integer,Pais> obtenerPaises(){
        return paises;
    }
    public static boolean paisYaExiste(Pais p){
        boolean existe = false;
        int i=1;
        while(!existe && i<=paises.size()){
            if(paises.get(i).esIgual(p)){
                existe=true;
            }
            i++;
        }
        return existe;
    }
    public static void agregarCiudad(Ciudad c){
        ciudades.put(ciudades.size()+1,c);
    }
    public static void agregarAreaDeMercado(AreasMercado areaMercado){
        areasDeMercado.put(areasDeMercado.size()+1,areaMercado);
    }
    public static boolean ciudadYaExiste(Ciudad c){
        boolean existe = false;
        int i=1;
        if(!ciudades.isEmpty()){
            while(!existe && i<=ciudades.size()){
                if(ciudades.get(i).esIgual(c)){
                    existe=true;
                }
                i++;
            }
        }
        return existe;
    }
    public static void listarCiudades(){
        for(int i=1;i<=ciudades.size();i++){
            if(!ciudades.get(i).estaBloqueado()){
                System.out.println(i+" - "+ciudades.get(i));
            }
        }
    }
    public static void listarCiudadesDeEmpresa(int key){
        Empresa empresa = empresas.get(key);
        empresa.listarCiudades();
    }
    public static HashMap<Integer,Ciudad> obtenerCiudadesDeEmpresa(int keyEmpresa){
        return empresas.get(keyEmpresa).obtenerCiudades();
    }
    public static HashMap<Integer,Ciudad> obtenerCiudades(){
        return ciudades;
    }
    public static boolean ciudadEnUso(Ciudad c){
        boolean enUso = false;
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).estaUbicadaEnCiudad(c) && !empresas.get(i).estaBloqueado()){
                enUso = true;
            }
        }
        return enUso;
    }
    public static void eliminarCiudad(int key){
        ciudades.get(key).setBloqueo(true);
    }
    public static void eliminarAreaDeMercado(int key){
        areasDeMercado.get(key).setBloqueo(true);
    }
    public static void listarEmpresasQueUtilizanCiudad(Ciudad c){
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).estaUbicadaEnCiudad(c) && !empresas.get(i).estaBloqueado()){
                System.out.println(empresas.get(i).getNombre());
            }
        }
    }
    public static void agregarEmpresa(Empresa empresaNueva){
        empresas.put(empresas.size()+1, empresaNueva);
    }
    public static int obtenerCodigoMayor(){
        return usuarios.size();
    }
    public static void desloguearUsuario(){
        usuarioLogueado = null;
    }
    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
    public static void listarEmpresasDeAsesor(Asesor a){
        for(var empresa : empresas.entrySet()){
            if(empresa.getValue().esAsesoradoPor(a)){
                System.out.println(empresa.getValue().getNombre());
                System.out.print("Fecha de inicio: ");
                empresa.getValue().mostrarFechaInicioAsesor(a);
                System.out.println();
            }
        }
    }

    public static boolean areaEnUso(AreasMercado a){
        boolean enUso = false;
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).contieneAreaDeMercado(a) && !empresas.get(i).estaBloqueado()){
                enUso = true;
            }
        }
        return enUso;
    }

    public static void listarEmpresasQueUtilizanAreaMercado(AreasMercado a){
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).contieneAreaDeMercado(a) && !empresas.get(i).estaBloqueado()){
                System.out.println(empresas.get(i).getNombre());
            }
        }
    }
    public static void eliminarEmpresa(int key){
        empresas.get(key).setBloqueo(true);
    }
}
