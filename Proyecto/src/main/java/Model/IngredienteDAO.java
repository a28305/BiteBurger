package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredienteDAO implements IDao<Ingrediente, Integer> {

    private final String SQL_FIND = "SELECT * FROM Ingrediente WHERE 1=1";
    private IMotorSql motorSql;

    public IngredienteDAO() {
        this.motorSql = new MotorSQL(); // Asegúrate de que MotorSQL implementa IMotorSql
    }

    @Override
    public int add(Ingrediente ing) {
        // No se debe insertar id_ingrediente si es AUTO_INCREMENT
        String sql = "INSERT INTO Ingrediente (nombre, imagen) VALUES ('" +
                ing.getNombre() + "', '" +
                ing.getImagen() + "')";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Ingrediente WHERE id_ingrediente = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Ingrediente ing) {
        String sql = "UPDATE Ingrediente SET " +
                "nombre = '" + ing.getNombre() + "', " +
                "imagen = '" + ing.getImagen() + "' " +
                "WHERE id_ingrediente = " + ing.getId_ingrediente();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Ingrediente> findAll(Ingrediente filtro) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getId_ingrediente() > 0) {
                sql += " AND id_ingrediente = " + filtro.getId_ingrediente();
            }
            if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
                sql += " AND LOWER(nombre) LIKE '%" + filtro.getNombre().toLowerCase() + "%'";
            }
            if (filtro.getImagen() != null && !filtro.getImagen().isEmpty()) {
                sql += " AND LOWER(imagen) LIKE '%" + filtro.getImagen().toLowerCase() + "%'";
            }
        }

        motorSql.connect();
        try {
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setId_ingrediente(rs.getInt("id_ingrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setImagen(rs.getString("imagen"));
                ingredientes.add(ing);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll IngredienteDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return ingredientes;
    }
}


