package holding;

import menues.MenuCrearEmpresa;

import java.io.*;
import java.util.HashMap;

/**
 * @author Maximiliano Stein, Matias Di Lella
 * Esta clase es la clase principal del programa. Se encarga de ejecutar el metodo proceder del usuario asi como
 * de serializar y deserializar todos los datos de la clase BaseDeDatosSingleton
 */
public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    public SistemaDeGestion() throws IOException {
        BaseDeDatosSingleton.getInstance();
        deserializarBD();
        BaseDeDatosSingleton.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);
    }

    /**
     *
     * Este metodo es el que permite ejecutar toda la funcionalidad de la clase
     */

    public void run() throws IOException {
        int num;
        do{
            num=mostrarMenu();
            BaseDeDatosSingleton.desloguearUsuario();
            switch (num) {
                case 1:
                    Usuario loggedInUsuario = BaseDeDatosSingleton.iniciarSesion();
                    if(loggedInUsuario != null){
                        loggedInUsuario.proceder();
                    }
                    break;
                case 2:
                    BaseDeDatosSingleton.desloguearUsuario();
                    break;
            }
            serializarBD(BaseDeDatosSingleton.obtenerUsuarios(), BaseDeDatosSingleton.obtenerEmpresas(),
                        BaseDeDatosSingleton.obtenerAreasDeMercado(), BaseDeDatosSingleton.obtenerCiudades(),
                        BaseDeDatosSingleton.obtenerPaises());
            System.out.println("* Base de Datos Serializada *");
        }while(num!=2);
    }

    /**
     * Muestra el menu que ve el usuario por consola
     * @return opcion elegida por el usuario
     */
    public int mostrarMenu(){
        int opcion=0;
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema\n");
        while (opcion<1 || opcion>2){
            opcion = Consola.leerEntero();
        }
        return opcion;
    }

    /**
     * Toma todos los datos de BaseDeDatosSingleton y los serializa mediante la funcion personalizada {@link #writeObject}
     * @param usuarios2 Map de usuarios de BaseDeDatosSingleton
     * @param empresas2 Map de empresas de BaseDeDatosSingleton
     * @param areasDeMercado2 Map de areas de mercado de BaseDeDatosSingleton
     * @param ciudades2 Map de ciudades de BaseDeDatosSingleton
     * @param paises2 Map de paises de BaseDeDatosSingleton
     * @throws IOException en caso de fallar al serializar
     */
    private static void serializarBD(HashMap<Integer,Usuario> usuarios2, HashMap<Integer,Empresa> empresas2, HashMap<Integer,AreasMercado> areasDeMercado2,
                             HashMap<Integer,Ciudad> ciudades2, HashMap<Integer,Pais> paises2) throws IOException {

        writeObject(usuarios2,"usuarios.dat");
        writeObject(areasDeMercado2,"areasDeMercado.dat");
        writeObject(ciudades2,"ciudades.dat");
        writeObject(paises2,"paises.dat");
        writeObject(empresas2,"empresas.dat");
    }

    /**
     * Obtiene los datos de los archivos guardados en el registro mediante la funcion personalizada {@link #readObject}
     * y carga dichos datos a la instancia de BaseDeDatosSingleton mediante el metodo {@link #cargarDatosDefault}
     * @throws IOException si ocurre un error inesperado
     */
    private static void deserializarBD() throws IOException {
        usuarios = readObject(usuarios,"usuarios.dat");
        areasDeMercado = readObject(areasDeMercado,"areasDeMercado.dat");
        ciudades = readObject(ciudades,"ciudades.dat");
        paises = readObject(paises,"paises.dat");
        empresas = readObject(empresas,"empresas.dat");

        cargarDatosDefault();
    }

    /**
     * Metodo que permite crear de manera rapida un {@link ObjectOutputStream} decorado con {@link BufferedOutputStream} y
     * {@link FileOutputStream}
     * @param nomArchivo nombre del archivo a crear
     * @return la variable que contiene el archivo
     * @throws IOException si ocurre algun error inesperado
     */
    private static ObjectOutputStream crearObjectOutputStream(String nomArchivo) throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomArchivo)));
    }
    /**
     * Metodo que permite crear de manera rapida un {@link ObjectInputStream} decorado con {@link BufferedInputStream} y
     * {@link FileInputStream}
     * @param nomArchivo nombre del archivo a leer
     * @return la variable que contiene el archivo
     * @throws IOException si ocurre algun error inesperado
     */
    private static ObjectInputStream crearObjectInputStream(String nomArchivo) throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomArchivo)));
    }

    /**
     * Metodo que se encarga de efectivamente escribir el objeto pasado por parametro en el archivo
     * @param objetoAEscribir objecto a escribir en el archivo
     * @param nombreArchivo nombre del archivo
     * @param <T> generic
     * @throws IOException si ocurre algun error inesperado
     */
    private static <T> void writeObject(T objetoAEscribir,String nombreArchivo) throws IOException {
        try{
            ObjectOutputStream oos = crearObjectOutputStream(nombreArchivo);
            oos.writeObject(objetoAEscribir);
            oos.close();
        }catch (IOException e){
            System.out.println("Error al serializar "+nombreArchivo);
            System.out.println("Error: "+e.getMessage());
        }
    }

    /**
     * Metodo que lee el archivo y le asigna a la variable pasada por paraemtro los datos leidos
     * @param objetoALeer objecto que recibira la informacion leida en el archivo
     * @param nombreArchivo nombre del archivo que contiene los registros
     * @return el objeto leido
     * @param <T> generic
     * @throws IOException si ocurre algun error inesperado
     */
    private static <T> T readObject(T objetoALeer,String nombreArchivo) throws IOException {
        try{
            ObjectInputStream ois = crearObjectInputStream(nombreArchivo);
            objetoALeer =  (T) ois.readObject();
            System.out.println("Lectura de "+nombreArchivo+" exitosa");
            ois.close();
            return objetoALeer;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("No se encontró "+nombreArchivo);
            return null;
        }
    }

    /**
     * llama a los metodos {@link #cargarUsuarioDefault()},{@link #cargarAreaDeMercadoDefault()},
     * {@link #cargarPaisYCiudadDefault()},{@link #cargarEmpresaDefault()}
     */
    private static void cargarDatosDefault(){
        cargarUsuarioDefault();
        cargarAreaDeMercadoDefault();
        cargarPaisYCiudadDefault();
        cargarEmpresaDefault();
    }

    /**
     * Si {@link usuarios} no esta instancaido, lo instancia y le crea el usuario {@link Admin} con datos default
     */
    private static void cargarUsuarioDefault(){
        if(usuarios == null){
            usuarios = new HashMap<>();
            usuarios.put(1, new Admin("adminDefault", "Direccion default ", "1"));
            BaseDeDatosSingleton.agregarUsuario(usuarios.get(1));
            System.out.println("Usuario Admin Default creado con Numero de Usuario = 1 y contraseña = 1");
        }
    }

    /**
     * Si {@link areasDeMercado} no esta instancaido, lo instancia y le crea instancias de {@link AreasMercado}
     */
    private  static void cargarAreaDeMercadoDefault(){
        if(areasDeMercado == null){
            areasDeMercado = new HashMap<>();
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("CONSTRUCCION", "Obras Publicas"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("METALURGICA", "Acero Industrial"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("ASESORAMIENTO", "Servicio de Consultoria"));
            areasDeMercado = BaseDeDatosSingleton.obtenerAreasDeMercado();
            System.out.println("Areas de Mercado default creadas");
        }
    }

    /**
     * Si {@link paises} o {@link ciudades} no estan instanciados, los instancia e ingresa instancias de {@link Ciudad}
     * y {@link Pais} con datos default
     */
    private static void cargarPaisYCiudadDefault(){
        if(paises == null || ciudades == null){
            ciudades = new HashMap<>();
            paises = new HashMap<>();
            Pais paisDefault = new Pais("Argentina",487.2,50000000);
            BaseDeDatosSingleton.agregarPais(paisDefault);
            BaseDeDatosSingleton.agregarCiudad(new Ciudad("Buenos Aires",paisDefault));
            paises = BaseDeDatosSingleton.obtenerPaises();
            ciudades = BaseDeDatosSingleton.obtenerCiudades();
            System.out.println("Paises y Ciudades default creadas");
        }
    }

    /**
     * Si {@link empresas} no esta instanciado, le pide al usuario crear una instancia de {@link Empresa}
     */
    private static void cargarEmpresaDefault(){
        if(empresas == null) {
            empresas = new HashMap<>();
            System.out.println("Se procede a crear una primer empresa:");
            MenuCrearEmpresa m = new MenuCrearEmpresa();
            m.ejecutar();
            empresas = BaseDeDatosSingleton.obtenerEmpresas();
        }
    }
}
