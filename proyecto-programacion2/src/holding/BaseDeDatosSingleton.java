package holding;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Es la base de datos del sistema, la cual sera estatica y se regirá bajo el patro de diseño Singleton. Esto permite
 * que las clases del sistema puedan acceder a la misma desde cualquier metodo.
 */
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

    /**
     * Metodo que permite obtener la instancia unica de la clase
     * @return la instancia de {@link BaseDeDatosSingleton}
     */
    public static BaseDeDatosSingleton getInstance(){
        if(INSTANCE==null){
            new BaseDeDatosSingleton();
        }
        return INSTANCE;
    }

    /**
     * Permite obtener los datos deserializados en {@link SistemaDeGestion} para poder cargarlos en la base de datos
     * @param usuarios2 usuarios deserializados
     * @param empresas2 empresas deserializadas
     * @param areasDeMercado2 areas de mercado deserializadas
     * @param ciudades2 ciudades deserializadas
     * @param paises2 paises deserializados
     */
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

    /**
     * Permite al usuario ingresar sus credenciales para asi poder autentificarlas mediante  el
     * metodo {@link #autenticarUsuario} y asi permitir al usuario acceder a su metodo de proceder
     * @return la instancia de {@link Usuario} que fue autenticado exitosamente. En caso contrario,
     * se devuelve un null
     */
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

    /**
     * Permite verificar que las credenciales ingresadas coincidan con algun usuario de la base de datos
     * @param userCod codigo del usuario buscado
     * @param pass su contrasenya
     * @return instancia de {@link Usuario} en caso de haber autenticado exitosamente, caso contrario
     * devuelve null
     */
    private static Usuario autenticarUsuario(int userCod, String pass){
        Usuario usuarioEncontrado = null;
        if(usuarios.get(userCod).validarPass(pass)){
            usuarioEncontrado = usuarios.get(userCod);
        }
        return usuarioEncontrado;
    }

    /**
     * Muestra por pantalla todos los usuarios en el Map de usuarios
     */
    public static void listarUsuarios() {
        for(var parClaveValor : usuarios.entrySet()){
            System.out.println(parClaveValor.getKey()+" - "+parClaveValor.getValue().toString());
        }
    }

    /**
     * Permite agregar un usuario al Map de  usuarios
     * @param u El usuario a agregar
     */
    public static void agregarUsuario(Usuario u){
        usuarios.put(u.getCodigoUsuario(),u);
    }

    /**
     * Elimina logicamente al usuario del Map de usuarios
     * @param key Llave del Map del usuario a eliminar
     */
    public static void eliminarUsuario(int key){
        usuarios.get(key).setBloqueo(true);
    }

    /**
     * Muestra por pantalla todas las empresas del Map de empresas
     */
    public static void listarEmpresas(){
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).noEstaBloqueado()){
                System.out.println(i+" - "+empresas.get(i).getNombre());
            }
        }
    }

    /**
     * Metodo que devuelve el Map de empresas
     * @return {@link HashMap} El Map de empresas completo
     */
    public static HashMap<Integer,Empresa> obtenerEmpresas(){
        return empresas;
    }

    /**
     * muestra por pantalla todas las areas de mercado del Map de areas de mercado
     */
    public static void listarAreasDeMercado(){
        for(int i=1;i<=areasDeMercado.size();i++){
            if(areasDeMercado.get(i).noEstaBloqueado()){
                System.out.println(i+" - "+areasDeMercado.get(i).toString());
            }
        }
    }

    /**
     * Devuelve el Map de areasDeMercado
     * @return {@link HashMap} de areas de Mercado
     */
    public static HashMap<Integer,AreasMercado> obtenerAreasDeMercado(){
        return areasDeMercado;
    }
    /**
     * Devuelve el Map de usuarios
     * @return {@link HashMap} de usuarios del sistema
     */
    public static HashMap<Integer, Usuario> obtenerUsuarios(){
        return usuarios;
    }

    /**
     * Permite agregar un {@link Asesor} a la {@link Empresa} de la base de datos
     * @param keyEmpresa llave del Map de empresas
     * @param a asesor a agregar
     * @param fechaInicio fecha con la que inicia actividad en la empresa
     */
    public static void agregarAsesorAEmpresa(int keyEmpresa, Asesor a, LocalDate fechaInicio){
        System.out.println("Empresa modificada: "+empresas.get(keyEmpresa).getNombre());
        empresas.get(keyEmpresa).agregarAsesor(a,fechaInicio);
    }

    /**
     * Permite eliminar un {@link Asesor} de la {@link Empresa} de la base de datos
     * @param keyEmpresa llave del Map de empresas
     * @param a asesor a eliminar
     */
    public static void eliminarAsesorDeEmpresa(int keyEmpresa, Asesor a){
        System.out.println("Empresa modificada: "+empresas.get(keyEmpresa).getNombre());
        empresas.get(keyEmpresa).eliminarAsesor(a);
        System.out.println("Vinculo del Asesor-Empresa: "+empresas.get(keyEmpresa).esAsesoradoPor(a));
    }

    /**
     * permite llamar al metodo {@link #modificarUsuario} de un {@link Usuario} de la base de datos
     * @param key llave del Map de usuarios
     */
    public static void modificarUsuario(int key){
        usuarios.get(key).modificar();
    }

    /**
     * Permite evaluar si el map de usuarios contiene una determinada llave
     * @param key llave del usuario a evaluar si existe o no
     * @return booleano de la evaluacion
     */
    public static boolean contieneUsuario(int key){
        return usuarios.containsKey(key);
    }

    /**
     * Permite evaluar si un {@link Asesor} desarrolla actividad en una determinada {@link Empresa}
     * @param a asesor
     * @param e empresa
     * @return booleano de la evaluacion
     */
    public static boolean usuarioAsesoraAEmpresa(Asesor a,Empresa e){
        return e.esAsesoradoPor(a);
    }

    /**
     * Muestra por pantalla todos los {@link Vendedor} del map de usuarios de la base de datos
     */
    public static void listarVendedores(){
        try{
            for (int i = 1; i <= usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                if(usuario.noEstaBloqueado() && usuario instanceof Vendedor){
                    System.out.println(usuario.toString());
                }
            }
        }catch (NullPointerException e){
            System.out.println("Sin registros de Vendedores!");
        }
    }

    /**
     * Metodo que devuelve el Map con todos los vendedores
     * @return un map de {@link Vendedor}
     */
    public static HashMap<Integer,Vendedor> obtenerVendedores(){
        HashMap<Integer,Vendedor> vendedores = new HashMap<>();
        for (int i = 1; i <= usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if(usuario.noEstaBloqueado() && usuario instanceof Vendedor){
                vendedores.put(i,(Vendedor) usuario);
            }
        }
        return vendedores;
    }

    /**
     * Permite agregar un Pais a la base de datos
     * @param p Pais a agregar
     */
    public static void agregarPais(Pais p){
        paises.put(paises.size()+1,p);
    }

    /**
     * Muestra por pantalla todos los paises registrados
     */
    public static void listarPaises(){
        for(int i=1;i<=paises.size();i++){
            if(paises.get(i).noEstaBloqueado()){
                System.out.println(i+" - "+paises.get(i).getNombre());
            }
        }
    }

    /**
     *Devuelve un map con todos los paises registrados
     * @return {@link HashMap<Integer,Pais>}
     */
    public static HashMap<Integer,Pais> obtenerPaises(){
        return paises;
    }

    /**
     * Evalua si el {@link Pais} pasado por parametro ya se encuentra registrado
     * @param p {@link Pais}  a evaluar
     * @return boolean de la evaluacion
     */
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

    /**
     * Permite registrar una ciudad
     * @param c {@link Ciudad} a registrar
     */
    public static void agregarCiudad(Ciudad c){
        ciudades.put(ciudades.size()+1,c);
    }

    /**
     * Registra un area de mercado
     * @param areaMercado Area de mercado a registrar
     */
    public static void agregarAreaDeMercado(AreasMercado areaMercado){
        boolean contiene=false;
        for(int i=1;i<=areasDeMercado.size();i++){
            if(areasDeMercado.get(i).esIgual(areaMercado)){
                contiene=true;
            }
        }
        if(!contiene){
            areasDeMercado.put(areasDeMercado.size()+1,areaMercado);
            System.out.println("Area agregada exitosamente");
        }
        else{
            System.out.println("El area ya existe! No se admiten duplicados");
        }

    }

    /**
     * Evalua si la {@link Ciudad} ya esta registrada
     * @param c {@link Ciudad} a evaluar si ya esta registrada
     * @return boolean de la evaluacion
     */
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

    /**
     * Muestra por pantalla todas las ciudades registradas
     */
    public static void listarCiudades(){
        for(int i=1;i<=ciudades.size();i++){
            if(ciudades.get(i).noEstaBloqueado()){
                System.out.println(i+" - "+ciudades.get(i));
            }
        }
    }

    /**
     * Muestra por pantalla todas las {@link Ciudad} que se encuentran registradas en una determinada {@link Empresa}
     * @param key
     */
    public static void listarCiudadesDeEmpresa(int key){
        Empresa empresa = empresas.get(key);
        empresa.listarCiudades();
    }

    /**
     * Devuelve un map con las {@link Ciudad} que posee una determinada {@link Empresa}
     * @param keyEmpresa llave del map de ciudades
     * @return map con las ciudades de la empresa determinada
     */
    public static HashMap<Integer,Ciudad> obtenerCiudadesDeEmpresa(int keyEmpresa){
        return empresas.get(keyEmpresa).obtenerCiudades();
    }

    /**
     * Devuelve el map de ciudades registradas
     * @return el map de las ciudades
     */
    public static HashMap<Integer,Ciudad> obtenerCiudades(){
        return ciudades;
    }

    /**
     * Evalua si la ciudad se encuentra siendo utilizada por alguna empresa
     * @param c {@link Ciudad} a evaluar si esta en uso
     * @return boolean del resultado de la evaluacion
     */
    public static boolean ciudadEnUso(Ciudad c){
        boolean enUso = false;
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).estaUbicadaEnCiudad(c) && empresas.get(i).noEstaBloqueado()){
                enUso = true;
            }
        }
        return enUso;
    }

    /**
     * Elimina logicamente una ciudad determinada de la base de datos
     * @param key llave del Map de ciudades a eliminar
     */
    public static void eliminarCiudad(int key){
        ciudades.get(key).setBloqueo(true);
    }

    /**
     * Elimina logicamente un {@link AreasMercado} determinado
     * @param key llave del Map de areas de mercado
     */
    public static void eliminarAreaDeMercado(int key){
        areasDeMercado.get(key).setBloqueo(true);
    }

    /**
     * Muestra por pantalla todas las empresas tienen registrada una determinada ciudad
     * @param c {@link Ciudad} condicion
     */
    public static void listarEmpresasQueUtilizanCiudad(Ciudad c){
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).estaUbicadaEnCiudad(c) && empresas.get(i).noEstaBloqueado()){
                System.out.println(empresas.get(i).getNombre());
            }
        }
    }

    /**
     * Permite registrar una {@link Empresa} empresa
     * @param empresaNueva
     */
    public static void agregarEmpresa(Empresa empresaNueva){
        empresas.put(empresas.size()+1, empresaNueva);
    }

    /**
     * Devuelve el valor del codigo de usuario del proximo {@link Usuario}  a crearse
     * @return
     */
    public static int obtenerCodigoMayor(){
        return usuarios.size();
    }

    /**
     * Desloguea al usuario actualmente logueado
     */
    public static void desloguearUsuario(){
        usuarioLogueado = null;
    }

    /**
     * Se devuelve la instancia de {@link Usuario} que esta logueado
     * @return el {@link Usuario} logueado
     */
    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Muestra por pantalla todas las empresas que asesora un determinado {@link Asesor}
     * @param a Asesor condicion
     */
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

    /**
     * Evalua si una determinada {@link AreasMercado} esta siendo utilizada por una {@link Empresa}
     * @param a area de mercado a evaluar
     * @return boolean de la evaluacion
     */
    public static boolean areaEnUso(AreasMercado a){
        boolean enUso = false;
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).contieneAreaDeMercado(a) && empresas.get(i).noEstaBloqueado()){
                enUso = true;
            }
        }
        return enUso;
    }

    /**
     * Muestra por pantallas todas las {@link Empresa} registradas que tengan el {@link AreasMercado} determinado
     * @param a area del mercado condicion
     */
    public static void listarEmpresasQueUtilizanAreaMercado(AreasMercado a){
        for(int i=1;i<=empresas.size();i++){
            if(empresas.get(i).contieneAreaDeMercado(a) && empresas.get(i).noEstaBloqueado()){
                System.out.println(empresas.get(i).getNombre());
            }
        }
    }

    /**
     * Elimina logicamente a una {@link Empresa} registrada
     * @param key llave del map de la empresa registrada
     */
    public static void eliminarEmpresa(int key){
        empresas.get(key).setBloqueo(true);
    }

    /**
     * Agrega un {@link AreasMercado} a una {@link Empresa}
     * @param keyEmpresa llave del map de la empresa a agregar
     * @param a area de mercado a agregarle
     */
    public static void agregarAreaMercadoAEmpresa(int keyEmpresa, AreasMercado a){
        empresas.get(keyEmpresa).agregarAreaMercado(a);
    }
}
