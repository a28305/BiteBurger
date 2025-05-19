package Model;

public class MetodoPago {
    private int idMetodo;
    private String tipo;

    public int getIdMetodo() { return idMetodo; }
    public void setIdMetodo(int idMetodo) { this.idMetodo = idMetodo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idMetodo=" + idMetodo +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
