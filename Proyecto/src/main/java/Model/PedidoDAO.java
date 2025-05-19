package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class PedidoDAO implements IDao<Pedido, Integer> {

    private final String SQL_FIND = "SELECT * FROM Pedido WHERE 1=1";
    private IMotorSql motorSql;

    public PedidoDAO() {
        motorSql = new MotorSQL(); // Asegúrate que MotorSQL implemente IMotorSql
    }

    @Override
    public int add(Pedido bean) {
        String sql = "INSERT INTO Pedido (id_pedido, id_factura, id_usuario, fecha, estado_pedido) VALUES (" +
                bean.getIdPedido() + ", " +
                bean.getIdFactura() + ", " +
                bean.getIdUsuario() + ", '" +
                new Date(bean.getFecha().getTime()) + "', '" +
                bean.getEstadoPedido() + "')";
        motorSql.connect();
        int result = motorSql.execute(sql);
        motorSql.disconnect();
        return result;
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Pedido WHERE id_pedido = " + id;
        motorSql.connect();
        int result = motorSql.execute(sql);
        motorSql.disconnect();
        return result;
    }

    @Override
    public int update(Pedido bean) {
        String sql = "UPDATE Pedido SET " +
                "id_factura = " + bean.getIdFactura() + ", " +
                "id_usuario = " + bean.getIdUsuario() + ", " +
                "fecha = '" + new Date(bean.getFecha().getTime()) + "', " +
                "estado_pedido = '" + bean.getEstadoPedido() + "' " +
                "WHERE id_pedido = " + bean.getIdPedido();
        motorSql.connect();
        int result = motorSql.execute(sql);
        motorSql.disconnect();
        return result;
    }

    @Override
    public ArrayList<Pedido> findAll(Pedido filtro) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdPedido() > 0) {
                sql += " AND id_pedido = " + filtro.getIdPedido();
            }
            if (filtro.getIdFactura() > 0) {
                sql += " AND id_factura = " + filtro.getIdFactura();
            }
            if (filtro.getIdUsuario() > 0) {
                sql += " AND id_usuario = " + filtro.getIdUsuario();
            }
            if (filtro.getFecha() != null) {
                sql += " AND fecha = '" + new Date(filtro.getFecha().getTime()) + "'";
            }
            if (filtro.getEstadoPedido() != null && !filtro.getEstadoPedido().isEmpty()) {
                sql += " AND LOWER(estado_pedido) LIKE '%" + filtro.getEstadoPedido().toLowerCase() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdFactura(rs.getInt("id_factura"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setFecha(rs.getDate("fecha"));
                p.setEstadoPedido(rs.getString("estado_pedido"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll PedidoDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return pedidos;
    }
}
