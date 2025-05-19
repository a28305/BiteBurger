package Controller;

import Model.Usuario;
import Controller.Actions.UsuarioAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    private UsuarioAction usuarioAction = new UsuarioAction();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer parámetros del formulario
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        // Crear objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setDireccion(direccion);

        try {
            // Guardar en base de datos usando UsuarioAction
            usuarioAction.crearUsuario(nuevoUsuario);

            // Respuesta simple (puedes redireccionar o mostrar mensaje en JSP)
            response.getWriter().println("Usuario creado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al crear usuario: " + e.getMessage());
        }
    }
}
