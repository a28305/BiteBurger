package Model;

public class Almacen {
    private int idAlmacen;
    private int idRestaurante;
    private String ubicacion;
    private int capacidadMaxima;
    private String observaciones;

    private int idIngrediente;  // Nuevo atributo para el ingrediente
    private int cantidad;       // Cantidad disponible de ese ingrediente

    // Getters y Setters
    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "idAlmacen=" + idAlmacen +
                ", idRestaurante=" + idRestaurante +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", observaciones='" + observaciones + '\'' +
                ", idIngrediente=" + idIngrediente +
                ", cantidad=" + cantidad +
                '}';
    }
}
