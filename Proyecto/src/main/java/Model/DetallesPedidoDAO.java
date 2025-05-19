package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallesPedidoDAO implements IDao<DetallesPedido, Integer> {

    private final String SQL_FIND = "SELECT * FROM Detalles_pedido WHERE 1=1";
    private IMotorSql motorSql;

    public DetallesPedidoDAO() {
        this.motorSql = new MotorSQL();  // Asumiendo que MotorSQL implementa IMotorSql
    }

    @Override
    public int add(DetallesPedido detalle) {
        String sql = "INSERT INTO Detalles_pedido (id_detalle, id_pedido, id_producto, cantidad, precio, total) VALUES (" +
                detalle.getIdDetalle() + ", " +
                detalle.getIdPedido() + ", " +
                detalle.getIdProducto() + ", " +
                detalle.getCantidad() + ", " +
                detalle.getPrecio() + ", " +
                detalle.getTotal() + ")";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Detalles_pedido WHERE id_detalle = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(DetallesPedido detalle) {
        String sql = "UPDATE Detalles_pedido SET " +
                "id_pedido = " + detalle.getIdPedido() + ", " +
                "id_producto = " + detalle.getIdProducto() + ", " +
                "cantidad = " + detalle.getCantidad() + ", " +
                "precio = " + detalle.getPrecio() + ", " +
                "total = " + detalle.getTotal() + " " +
                "WHERE id_detalle = " + detalle.getIdDetalle();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<DetallesPedido> findAll(DetallesPedido filtro) {
        ArrayList<DetallesPedido> detalles = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdDetalle() > 0) {
                sql += " AND id_detalle = " + filtro.getIdDetalle();
            }
            if (filtro.getIdPedido() > 0) {
                sql += " AND id_pedido = " + filtro.getIdPedido();
            }
            if (filtro.getIdProducto() > 0) {
                sql += " AND id_producto = " + filtro.getIdProducto();
            }
            if (filtro.getCantidad() > 0) {
                sql += " AND cantidad = " + filtro.getCantidad();
            }
            if (filtro.getPrecio() != null) {
                sql += " AND precio = " + filtro.getPrecio();
            }
            if (filtro.getTotal() != null) {
                sql += " AND total = " + filtro.getTotal();
            }
        }

        motorSql.connect();
        try {
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                DetallesPedido detalle = new DetallesPedido();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdPedido(rs.getInt("id_pedido"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getBigDecimal("precio"));
                detalle.setTotal(rs.getBigDecimal("total"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll DetallesPedidoDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return detalles;
    }
}
