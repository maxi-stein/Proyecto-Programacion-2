package holding;

import menues.MenuCrearEmpresa;

import java.io.*;
import java.util.HashMap;

public class SistemaDeGestion implements Serializable {

    private static HashMap<Integer,Usuario> usuarios;
    private static HashMap<Integer,Empresa> empresas;
    private static HashMap<Integer,AreasMercado> areasDeMercado;
    private static HashMap<Integer,Ciudad> ciudades;
    private static HashMap<Integer,Pais> paises;
    public SistemaDeGestion() throws IOException {
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        ciudades = new HashMap<>();
        paises = new HashMap<>();
        areasDeMercado = new HashMap<>();
        deserializarBD();
        BaseDeDatosSingleton.cargarDatosSerializados(usuarios,empresas,areasDeMercado,ciudades, paises);

    }
    public void run() throws IOException {
        int num;
        do{
            num=mostrarMenu();
            if (num == 1) {

                Usuario loggedInUsuario = BaseDeDatosSingleton.iniciarSesion();
                if(loggedInUsuario != null){
                    loggedInUsuario.proceder();
                }
                BaseDeDatosSingleton.desloguearUsuario();
                serializarBD(BaseDeDatosSingleton.obtenerUsuarios(), BaseDeDatosSingleton.obtenerEmpresas(),
                        BaseDeDatosSingleton.obtenerAreasDeMercado(), BaseDeDatosSingleton.obtenerCiudades(),
                        BaseDeDatosSingleton.obtenerPaises());
            }
        }while(num!=2);
    }
    public int mostrarMenu(){
        System.out.print("1-Iniciar Sesion \n2-Salir del Sistema\n");
        return Consola.leerEntero();
    }
    public static void serializarBD(HashMap<Integer,Usuario> usuarios2,
                             HashMap<Integer,Empresa> empresas2,
                             HashMap<Integer,AreasMercado> areasDeMercado2,
                             HashMap<Integer,Ciudad> ciudades2, HashMap<Integer,Pais> paises2) throws IOException {

        try{
            ObjectOutputStream objUser = crearObjectOutputStream("Usuarios.bin");
            objUser.writeObject(usuarios2);

            ObjectOutputStream objEmp = crearObjectOutputStream("Empresas.bin");
            objEmp.writeObject(empresas2);

            ObjectOutputStream objArea = crearObjectOutputStream("AreasDeMercado.bin");
            objArea.writeObject(areasDeMercado2);

            ObjectOutputStream objCiudad = crearObjectOutputStream("Ciudades.bin");
            objCiudad.writeObject(ciudades2);

            ObjectOutputStream objPais = crearObjectOutputStream("Paises.bin");
            objPais.writeObject(paises2);

            objUser.close();
            objEmp.close();
            objArea.close();
            objCiudad.close();
            objPais.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deserializarBD() throws IOException, FileNotFoundException {
        try{
            try{
                ObjectInputStream objUser = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Usuarios.bin"))));
                usuarios = (HashMap<Integer, Usuario>) objUser.readObject();
                objUser.close();
            }catch (FileNotFoundException e) {
                System.out.println("Error de E/S: " + e.getMessage());
                System.out.println("~ BASE DE USUARIOS NO HALLADA ~");
                cargarDatosDefault();
            }
            ObjectInputStream objEmp = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Empresas.bin"))));
            ObjectInputStream objArea = new ObjectInputStream((new BufferedInputStream(new FileInputStream("AreasDeMercado.bin"))));
            ObjectInputStream objCiudad = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Ciudades.bin"))));
            ObjectInputStream objPais = new ObjectInputStream((new BufferedInputStream(new FileInputStream("Paises.bin"))));

            empresas = (HashMap<Integer, Empresa>) objEmp.readObject();
            areasDeMercado = (HashMap<Integer, AreasMercado>) objArea.readObject();
            ciudades = (HashMap<Integer, Ciudad>)  objCiudad.readObject();
            paises = (HashMap<Integer, Pais>)  objPais.readObject();

            objEmp.close();
            objArea.close();
            objCiudad.close();
            objPais.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Error de E/S: " + e.getMessage());
            System.out.println("BASE NO RECUPERADA");
        }
    }
    private static ObjectOutputStream crearObjectOutputStream(String nomArchivo) throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomArchivo)));
    }
    private static ObjectInputStream crearObjectInputStream(String nomArchivo) throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomArchivo)));
    }
    private static void cargarDatosDefault(){
        usuarios.put(usuarios.size()+1, new Admin("admin1", "Calle 123", "1"));
        if(areasDeMercado.size() == 0){
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("CONSTRUCCION", "Obras Publicas"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("METALURGICA", "Acero Industrial"));
            BaseDeDatosSingleton.agregarAreaDeMercado(new AreasMercado("ASESORAMIENTO", "Servicio de Consultoria"));
        }
        if(paises.size() == 0){
            Pais paisDefault = new Pais("Argentina",487.2,50000000);
            BaseDeDatosSingleton.agregarPais(paisDefault);
            BaseDeDatosSingleton.agregarCiudad(new Ciudad("Buenos Aires",paisDefault));
        }

        if(empresas.size()==0) {
            System.out.println("NO HAY EMPRESAS REGISTRADAS - SE PROCEDERA A REGISTRAR UNA EMPRESA");
            MenuCrearEmpresa m = new MenuCrearEmpresa();
            m.ejecutar();
        }
    }
}
