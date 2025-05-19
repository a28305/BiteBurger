package Model;

public class Restaurante {
    private int idRestaurante;
    private String nombre;
    private String direccion;

    // Getters y Setters
    public int getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "Restaurante{" +
                "idRestaurante=" + idRestaurante +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

