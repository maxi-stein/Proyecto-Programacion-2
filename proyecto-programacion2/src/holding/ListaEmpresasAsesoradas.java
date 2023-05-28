package holding;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaEmpresasAsesoradas {

    private Nodo nodo;
    private class Nodo{
        Empresa empresaAsesorada;
        LocalDate fechaInicio;
        ArrayList<AREAS_MERCADO> areasAsesoradas;

        Nodo proximo;

        private Nodo(Empresa empresaAsesorada,LocalDate fechaInicio,ArrayList<AREAS_MERCADO> areasAsesoradas){
            this.empresaAsesorada = empresaAsesorada;
            this.fechaInicio = fechaInicio;
            this.areasAsesoradas.addAll(areasAsesoradas);
            proximo = null;
        }
    }

    public ListaEmpresasAsesoradas(Empresa empresaAsesorada,LocalDate fechaInicio,ArrayList<AREAS_MERCADO> areasAsesoradas){
        nodo = new Nodo(empresaAsesorada,fechaInicio,areasAsesoradas);

    }

    public void agrearEmpresa(Empresa empresaAsesorada,LocalDate fechaInicio,ArrayList<AREAS_MERCADO> areasAsesoradas){
        nodo.proximo = new Nodo(empresaAsesorada,fechaInicio,areasAsesoradas);
    }

    public void eliminarEmpresa(Empresa empresa){
        Nodo busqueda = nodo;
        while(busqueda!= null){

            busqueda = busqueda.proximo;
        }
    }



}
