package Model;

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
        String sql = "INSERT INTO Producto (nombre, precio, id_categoria, descripcion, imagenes) VALUES (" +
                "'" + producto.getNombre() + "', " +
                producto.getPrecio() + ", " +
                producto.getIdCategoria() + ", " +
                "'" + producto.getDescripcion() + "', " +
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
                "descripcion = '" + producto.getDescripcion() + "', " +
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
            if (filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty()) {
                sql += " AND LOWER(descripcion) LIKE '%" + filtro.getDescripcion().toLowerCase() + "%'";
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
                p.setDescripcion(rs.getString("descripcion"));
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

    public Producto findById(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM Producto WHERE id_producto = " + id;
        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setImagenes(rs.getString("imagenes"));
            }
        } catch (SQLException e) {
            System.out.println("Error en findById ProductoDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }
        return producto;
    }
}
