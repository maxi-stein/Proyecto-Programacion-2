package holding;

public enum AREAS_MERCADO {
    ENERGIA("Exploración y producción de energía"),
    CONSTRUCCION("Ingeniería y construcción de infraestructuras"),
    INDUSTRIA("Fabricación y comercialización de productos industriales"),
    PETROQUIMICA("Producción y comercialización de productos químicos derivados del petróleo"),
    MINERIA("Extracción y comercialización de minerales");

    private String descripcion;

    AREAS_MERCADO(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
