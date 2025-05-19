package Model;

import java.math.BigDecimal;
import java.util.Date;

public class    Factura {
    private int idFactura;
    private int idMetodo;
    private Date fechaEmision;
    private BigDecimal total;
    private BigDecimal iva;

    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public int getIdMetodo() { return idMetodo; }
    public void setIdMetodo(int idMetodo) { this.idMetodo = idMetodo; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public BigDecimal getIva() { return iva; }
    public void setIva(BigDecimal iva) { this.iva = iva; }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", idMetodo=" + idMetodo +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                ", iva=" + iva +
                '}';
    }
}
