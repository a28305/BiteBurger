package Model;


import java.math.BigDecimal;

public class DetallesPedido {
    private int idDetalle;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal total;

    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    @Override
    public String toString() {
        return "DetallesPedido{" +
                "idDetalle=" + idDetalle +
                ", idPedido=" + idPedido +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
