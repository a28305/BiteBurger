package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetodoPagoDAO implements IDao<MetodoPago, Integer> {

    private final String SQL_FIND = "SELECT * FROM Metodo_Pago WHERE 1=1";
    private IMotorSql motorSql;

    public MetodoPagoDAO() {
        this.motorSql = new MotorSQL(); // MotorSQL debe implementar IMotorSql
    }

    @Override
    public int add(MetodoPago metodo) {
        String sql = "INSERT INTO Metodo_Pago (id_metodo, tipo) VALUES (" +
                metodo.getIdMetodo() + ", '" + metodo.getTipo() + "')";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Metodo_Pago WHERE id_metodo = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(MetodoPago metodo) {
        String sql = "UPDATE Metodo_Pago SET tipo = '" + metodo.getTipo() +
                "' WHERE id_metodo = " + metodo.getIdMetodo();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<MetodoPago> findAll(MetodoPago filtro) {
        ArrayList<MetodoPago> metodos = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdMetodo() > 0) {
                sql += " AND id_metodo = " + filtro.getIdMetodo();
            }
            if (filtro.getTipo() != null && !filtro.getTipo().isEmpty()) {
                sql += " AND LOWER(tipo) LIKE '%" + filtro.getTipo().toLowerCase() + "%'";
            }
        }

        motorSql.connect();
        try {
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                MetodoPago metodo = new MetodoPago();
                metodo.setIdMetodo(rs.getInt("id_metodo"));
                metodo.setTipo(rs.getString("tipo"));
                metodos.add(metodo);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll MetodoPagoDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return metodos;
    }
}
