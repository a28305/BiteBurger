package Controller.Actions;

import Model.Usuario;
import Model.UsuarioDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioAction {

    private UsuarioDAO usuarioDAO;

    public UsuarioAction() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public ArrayList<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.findAll(null);
    }

    public void crearUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.add(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.update(usuario);
    }

    public void eliminarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.delete(usuario.getIdUsuario());
    }

    public Usuario obtenerUsuario(int id) throws SQLException {
        Usuario filtro = new Usuario();
        filtro.setIdUsuario(id);
        ArrayList<Usuario> usuarios = usuarioDAO.findAll(filtro);
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }
}
