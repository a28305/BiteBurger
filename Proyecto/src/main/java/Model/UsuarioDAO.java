package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO implements IDao<Usuario, Integer> {

    private final String SQL_FIND = "SELECT * FROM usuarios WHERE 1=1";
    private IMotorSql motorSql;

    public UsuarioDAO() {
        motorSql = new MotorSQL(); // Asegúrate que MotorSQL implemente IMotorSql
    }

    @Override
    public int add(Usuario bean) {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, contrasena, correo, telefono, direccion) VALUES (" +
                bean.getIdUsuario() + ", '" +
                bean.getNombre() + "', '" +
                bean.getContrasena() + "', '" +
                bean.getCorreo() + "', '" +
                bean.getTelefono() + "', '" +
                bean.getDireccion() + "')";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Usuario bean) {
        String sql = "UPDATE usuarios SET " +
                "nombre = '" + bean.getNombre() + "', " +
                "contrasena = '" + bean.getContrasena() + "', " +
                "correo = '" + bean.getCorreo() + "', " +
                "telefono = '" + bean.getTelefono() + "', " +
                "direccion = '" + bean.getDireccion() + "' " +
                "WHERE id_usuario = " + bean.getIdUsuario();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Usuario> findAll(Usuario filtro) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdUsuario() > 0) {
                sql += " AND id_usuario = " + filtro.getIdUsuario();
            }
            if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
                sql += " AND LOWER(nombre) LIKE '%" + filtro.getNombre().toLowerCase() + "%'";
            }
            if (filtro.getContrasena() != null && !filtro.getContrasena().isEmpty()) {
                sql += " AND contrasena = '" + filtro.getContrasena() + "'";
            }
            if (filtro.getCorreo() != null && !filtro.getCorreo().isEmpty()) {
                sql += " AND LOWER(correo) LIKE '%" + filtro.getCorreo().toLowerCase() + "%'";
            }
            if (filtro.getTelefono() != null && !filtro.getTelefono().isEmpty()) {
                sql += " AND telefono = '" + filtro.getTelefono() + "'";
            }
            if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
                sql += " AND LOWER(direccion) LIKE '%" + filtro.getDireccion().toLowerCase() + "%'";
            }
        }

        try {
            motorSql.connect();
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setContrasena(rs.getString("contrasena"));
                u.setCorreo(rs.getString("correo"));
                u.setTelefono(rs.getString("telefono"));
                u.setDireccion(rs.getString("direccion"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll UsuarioDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return usuarios;
    }
}
