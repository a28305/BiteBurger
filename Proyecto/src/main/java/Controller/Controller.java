package Controller;

import Controller.Actions.UsuarioAction;
import Controller.Actions.ProductoAction;
import Controller.Actions.IngredienteAction;
import Model.Ingrediente;
import Model.Producto;
import Model.MotorSQL;
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
        // Configuración de cabeceras y contenido JSON
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");
        String[] arrayAction = new String[2];

        if (strAction != null && !strAction.isEmpty()) {
            arrayAction = strAction.split("\\."); // Ejemplo: ["PRODUCTO","FIND_BY_ID"]
        }

        try {

            switch (arrayAction[0].toUpperCase()) {
                // ================= USUARIO =================
                case "USUARIO": {
                    UsuarioAction usuarioAction = new UsuarioAction();
                    if ("FIND_ALL".equalsIgnoreCase(arrayAction[1])) {
                        out.print(new Gson().toJson(usuarioAction.listarUsuarios()));
                    } else {
                        throw new ServletException("Acción de USUARIO no válida: " + arrayAction[1]);
                    }
                    break;
                }

                // ================= PRODUCTO =================
                case "PRODUCTO": {
                    ProductoAction productoAction = new ProductoAction();
                    switch (arrayAction[1].toUpperCase()) {
                        case "FIND_ALL":
                            List<Producto> productos = productoAction.listarProductos();
                            out.print(new Gson().toJson(productos));
                            break;

                        case "FIND_BY_NAME":
                            out.print(productoAction.buscarPorNombre(request));
                            break;

                        case "FIND_BY_ID":
                            String idProdStr = request.getParameter("id_producto");
                            if (idProdStr != null && !idProdStr.isEmpty()) {
                                try {
                                    int idProd = Integer.parseInt(idProdStr);
                                    Producto prod = productoAction.obtenerProducto(idProd);
                                    if (prod != null) {
                                        out.print(new Gson().toJson(prod));
                                    } else {
                                        out.print("{\"error\":\"No se encontró producto con id " + idProd + "\"}");
                                    }
                                } catch (NumberFormatException nfe) {
                                    out.print("{\"error\":\"El parámetro 'id_producto' no es un entero válido.\"}");
                                }
                            } else {
                                out.print("{\"error\":\"Falta parámetro 'id_producto'\"}");
                            }
                            break;

                        default:
                            throw new ServletException("Acción de PRODUCTO no válida: " + arrayAction[1]);
                    }
                    break;
                }


                // ================= INGREDIENTE =================
                case "INGREDIENTE": {
                    IngredienteAction ingredienteAction = new IngredienteAction();

                    // Listar todos los ingredientes
                    if ("FIND_ALL".equalsIgnoreCase(arrayAction[1])) {
                        ArrayList<Ingrediente> lista = ingredienteAction.buscarIngredientes(null);
                        out.print(new Gson().toJson(lista));

                        // Buscar ingredientes por nombre (busqueda parcial)
                    } else if ("FIND_BY_NAME".equalsIgnoreCase(arrayAction[1])) {
                        String nombre = request.getParameter("nombre");
                        if (nombre != null && !nombre.isEmpty()) {
                            Model.Ingrediente filtro = new Model.Ingrediente();
                            filtro.setNombre(nombre);
                            ArrayList<Ingrediente> lista = ingredienteAction.buscarIngredientes(filtro);
                            out.print(new Gson().toJson(lista));
                        } else {
                            out.print("{\"error\":\"Falta parámetro 'nombre'\"}");
                        }

                        // Listar ingredientes asociados a un producto específico
                    } else if ("FIND_BY_PRODUCT".equalsIgnoreCase(arrayAction[1])) {
                        String idProdStr = request.getParameter("id_producto");
                        if (idProdStr != null && !idProdStr.isEmpty()) {
                            try {
                                int idProd = Integer.parseInt(idProdStr);
                                ArrayList<Ingrediente> listaIng = ingredienteAction.listarPorProducto(idProd);
                                out.print(new Gson().toJson(listaIng));
                            } catch (NumberFormatException nfe) {
                                out.print("{\"error\":\"El parámetro 'id_producto' no es un entero válido.\"}");
                            }
                        } else {
                            out.print("{\"error\":\"Falta parámetro 'id_producto'\"}");
                        }

                    } else {
                        throw new ServletException("Acción de INGREDIENTE no válida: " + arrayAction[1]);
                    }
                    break;
                }

                // ================= OTROS CASOS =================
                default:
                    throw new ServletException("Acción " + arrayAction[0] + " no válida.");
            }
        } catch (Exception e) {
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
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



