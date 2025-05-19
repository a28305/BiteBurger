package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlmacenDAO implements IDao<Almacen, Integer> {

    private final String SQL_FIND = "SELECT * FROM Almacen WHERE 1=1";
    private IMotorSql motorSql;

    public AlmacenDAO() {
        motorSql = new MotorSQL(); // Asegúrate de que MotorSQL implemente IMotorSql
    }

    @Override
    public int add(Almacen bean) {
        String sql = "INSERT INTO Almacen (id_almacen, id_restaurante, ubicacion, capacidad_maxima, observaciones, id_ingrediente, cantidad) VALUES (" +
                bean.getIdAlmacen() + ", " +
                bean.getIdRestaurante() + ", '" +
                bean.getUbicacion() + "', " +
                bean.getCapacidadMaxima() + ", '" +
                bean.getObservaciones() + "', " +
                bean.getIdIngrediente() + ", " +
                bean.getCantidad() + ")";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Almacen WHERE id_almacen = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Almacen bean) {
        String sql = "UPDATE Almacen SET " +
                "id_restaurante = " + bean.getIdRestaurante() + ", " +
                "ubicacion = '" + bean.getUbicacion() + "', " +
                "capacidad_maxima = " + bean.getCapacidadMaxima() + ", " +
                "observaciones = '" + bean.getObservaciones() + "', " +
                "id_ingrediente = " + bean.getIdIngrediente() + ", " +
                "cantidad = " + bean.getCantidad() + " " +
                "WHERE id_almacen = " + bean.getIdAlmacen();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Almacen> findAll(Almacen filtro) {
        ArrayList<Almacen> almacenes = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdAlmacen() > 0) {
                sql += " AND id_almacen = " + filtro.getIdAlmacen();
            }
            if (filtro.getIdRestaurante() > 0) {
                sql += " AND id_restaurante = " + filtro.getIdRestaurante();
            }
            if (filtro.getUbicacion() != null && !filtro.getUbicacion().isEmpty()) {
                sql += " AND LOWER(ubicacion) LIKE '%" + filtro.getUbicacion().toLowerCase() + "%'";
            }
            if (filtro.getCapacidadMaxima() > 0) {
                sql += " AND capacidad_maxima = " + filtro.getCapacidadMaxima();
            }
            if (filtro.getObservaciones() != null && !filtro.getObservaciones().isEmpty()) {
                sql += " AND LOWER(observaciones) LIKE '%" + filtro.getObservaciones().toLowerCase() + "%'";
            }
            if (filtro.getIdIngrediente() > 0) {
                sql += " AND id_ingrediente = " + filtro.getIdIngrediente();
            }
            if (filtro.getCantidad() > 0) {
                sql += " AND cantidad = " + filtro.getCantidad();
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Almacen almacen = new Almacen();
                almacen.setIdAlmacen(rs.getInt("id_almacen"));
                almacen.setIdRestaurante(rs.getInt("id_restaurante"));
                almacen.setUbicacion(rs.getString("ubicacion"));
                almacen.setCapacidadMaxima(rs.getInt("capacidad_maxima"));
                almacen.setObservaciones(rs.getString("observaciones"));
                almacen.setIdIngrediente(rs.getInt("id_ingrediente"));
                almacen.setCantidad(rs.getInt("cantidad"));
                almacenes.add(almacen);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll AlmacenDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return almacenes;
    }
}
