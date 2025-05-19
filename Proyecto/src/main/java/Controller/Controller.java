package Controller;

import Controller.Actions.UsuarioAction;
import Controller.Actions.ProductoAction;
import Controller.Actions.IngredienteAction;
import Model.Ingrediente;
import Model.MotorSQL;
import Model.Producto;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setContentType("text/plain;charset=UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();

        String strAction = request.getParameter("ACTION");
        String[] arrayAction = new String[2];

        if (strAction != null && !strAction.isEmpty()) {
            arrayAction = strAction.split("\\."); // Ej: ["PRODUCTO", "FIND_ALL"]
        }

        MotorSQL motorSQL = new MotorSQL();
        Connection conn = null;

        try {
            motorSQL.connect();
            conn = motorSQL.getConnection(); // Si es público o lo tienes implementado

            switch (arrayAction[0].toUpperCase()) {
                case "USUARIO": {
                    UsuarioAction action = new UsuarioAction(); // Sin pasar conn
                    if ("FIND_ALL".equalsIgnoreCase(arrayAction[1])) {
                        out.print(action.listarUsuarios());
                    }
                    break;
                }
                case "PRODUCTO": {
                    ProductoAction action = new ProductoAction(); // sin pasar Connection
                    switch (arrayAction[1].toUpperCase()) {
                        case "FIND_ALL":
                            List<Producto> productos = action.listarProductos();
                            String json = new Gson().toJson(productos);
                            out.print(json);
                            break;
                        case "FIND_BY_NAME":
                            out.print(action.buscarPorNombre(request));
                            break;
                        default:
                            throw new ServletException("Acción de PRODUCTO no válida: " + arrayAction[1]);
                    }
                    break;
                }
                case "INGREDIENTE": {
                    IngredienteAction action = new IngredienteAction(); // sin conn, estilo profesor

                    if ("FIND_ALL".equalsIgnoreCase(arrayAction[1])) {
                        ArrayList<Ingrediente> lista = action.buscarIngredientes(null); // sin filtro
                        out.print(new Gson().toJson(lista));
                    } else if ("FIND_BY_NAME".equalsIgnoreCase(arrayAction[1])) {
                        String nombre = request.getParameter("nombre");

                        if (nombre != null && !nombre.isEmpty()) {
                            Ingrediente filtro = new Ingrediente();
                            filtro.setNombre(nombre);
                            ArrayList<Ingrediente> lista = action.buscarIngredientes(filtro);
                            out.print(new Gson().toJson(lista));
                        } else {
                            out.print("{\"error\": \"Falta parámetro 'nombre'\"}");
                        }
                    }
                    break;
                }

                default:
                    throw new ServletException("Acción " + arrayAction[0] + " no válida.");
            }
        } catch (Exception e) {
            out.print("{\"error\": \"" + e.getMessage() + "\"}");
        } finally {
            motorSQL.disconnect(); // Muy importante
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

