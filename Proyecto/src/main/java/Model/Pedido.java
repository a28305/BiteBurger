package Model;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private int idFactura;
    private int idUsuario;
    private Date fecha;
    private String estadoPedido;

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getEstadoPedido() { return estadoPedido; }
    public void setEstadoPedido(String estadoPedido) { this.estadoPedido = estadoPedido; }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idFactura=" + idFactura +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", estadoPedido='" + estadoPedido + '\'' +
                '}';
    }
}
