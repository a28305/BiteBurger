package Model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO implements IDao<Producto, Integer> {

    private final String SQL_FIND = "SELECT * FROM Producto WHERE 1=1";
    private IMotorSql motorSql;

    public ProductoDAO() {
        motorSql = new MotorSQL();
    }

    @Override
    public int add(Producto producto) {
        String sql = "INSERT INTO Producto (nombre, precio, id_categoria, id_ingrediente, disponibilidad, imagenes) VALUES (" +
                "'" + producto.getNombre() + "', " +
                producto.getPrecio() + ", " +
                producto.getIdCategoria() + ", " +
                producto.getId_ingrediente() + ", " +
                "'" + producto.getDisponibilidad() + "', " +
                "'" + producto.getImagenes() + "')";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Producto WHERE id_producto = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Producto producto) {
        String sql = "UPDATE Producto SET " +
                "nombre = '" + producto.getNombre() + "', " +
                "precio = " + producto.getPrecio() + ", " +
                "id_categoria = " + producto.getIdCategoria() + ", " +
                "id_ingrediente = " + producto.getId_ingrediente() + ", " +
                "disponibilidad = '" + producto.getDisponibilidad() + "', " +
                "imagenes = '" + producto.getImagenes() + "' " +
                "WHERE id_producto = " + producto.getIdProducto();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Producto> findAll(Producto filtro) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdProducto() > 0) {
                sql += " AND id_producto = " + filtro.getIdProducto();
            }
            if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
                sql += " AND LOWER(nombre) LIKE '%" + filtro.getNombre().toLowerCase() + "%'";
            }
            if (filtro.getIdCategoria() > 0) {
                sql += " AND id_categoria = " + filtro.getIdCategoria();
            }
            if (filtro.getId_ingrediente() > 0) {
                sql += " AND id_ingrediente = " + filtro.getId_ingrediente();
            }
            if (filtro.getDisponibilidad() != null && !filtro.getDisponibilidad().isEmpty()) {
                sql += " AND LOWER(disponibilidad) LIKE '%" + filtro.getDisponibilidad().toLowerCase() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setId_ingrediente(rs.getInt("id_ingrediente"));
                p.setDisponibilidad(rs.getString("disponibilidad"));
                p.setImagenes(rs.getString("imagenes"));
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll ProductoDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return productos;
    }
}
