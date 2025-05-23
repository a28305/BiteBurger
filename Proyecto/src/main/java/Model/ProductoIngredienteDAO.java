package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO para obtener los ingredientes asociados a un producto (hamburguesa).
 */
public class ProductoIngredienteDAO {

    private static final String SQL_FIND_BY_PRODUCTO =
            "SELECT i.id_ingrediente, i.nombre, i.imagen " +
                    "FROM Producto_Ingrediente pi " +
                    "JOIN Ingrediente i ON pi.id_ingrediente = i.id_ingrediente " +
                    "WHERE pi.id_producto = ?";

    private IMotorSql motorSql;

    public ProductoIngredienteDAO() {
        this.motorSql = new MotorSQL();  // MotorSQL implementa IMotorSql
    }

    /**
     */
    public ArrayList<Ingrediente> findIngredientesPorProducto(int idProducto) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        try {
            motorSql.connect();
            PreparedStatement pst = motorSql.getConnection().prepareStatement(SQL_FIND_BY_PRODUCTO);
            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Ingrediente ing = new Ingrediente();
                ing.setId_ingrediente(rs.getInt("id_ingrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setImagen(rs.getString("imagen"));
                ingredientes.add(ing);
            }
        } catch (SQLException e) {
            System.out.println("Error en findIngredientesPorProducto: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }
        return ingredientes;
    }
}

