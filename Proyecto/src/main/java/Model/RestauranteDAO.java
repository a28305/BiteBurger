package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestauranteDAO implements IDao<Restaurante, Integer> {

    private final String SQL_FIND = "SELECT * FROM Restaurante WHERE 1=1";
    private IMotorSql motorSql;

    public RestauranteDAO() {
        motorSql = new MotorSQL(); // Asegúrate que MotorSQL implemente IMotorSql
    }

    @Override
    public int add(Restaurante bean) {
        String sql = "INSERT INTO Restaurante (id_restaurante, nombre, direccion) VALUES (" +
                bean.getIdRestaurante() + ", '" +
                bean.getNombre() + "', '" +
                bean.getDireccion() + "')";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Restaurante WHERE id_restaurante = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Restaurante bean) {
        String sql = "UPDATE Restaurante SET " +
                "nombre = '" + bean.getNombre() + "', " +
                "direccion = '" + bean.getDireccion() + "' " +
                "WHERE id_restaurante = " + bean.getIdRestaurante();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Restaurante> findAll(Restaurante filtro) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdRestaurante() > 0) {
                sql += " AND id_restaurante = " + filtro.getIdRestaurante();
            }
            if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
                sql += " AND LOWER(nombre) LIKE '%" + filtro.getNombre().toLowerCase() + "%'";
            }
            if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
                sql += " AND LOWER(direccion) LIKE '%" + filtro.getDireccion().toLowerCase() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("id_restaurante"));
                restaurante.setNombre(rs.getString("nombre"));
                restaurante.setDireccion(rs.getString("direccion"));
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll RestauranteDAO: " + e.getMessage());
        }

        return restaurantes;
    }
}
