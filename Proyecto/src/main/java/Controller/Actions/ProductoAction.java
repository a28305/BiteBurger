package Controller.Actions;

import Model.Producto;
import Model.ProductoDAO;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ProductoAction {
    private ProductoDAO productoDAO;

    public ProductoAction() {
        this.productoDAO = new ProductoDAO();
    }

    // Cambiado para usar findById directo
    public Producto obtenerProducto(int id) {
        return productoDAO.findById(id);
    }

    public String buscarPorNombre(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        Producto filtro = new Producto();
        filtro.setNombre(nombre);
        ArrayList<Producto> productos = productoDAO.findAll(filtro);
        return new Gson().toJson(productos);
    }

    public ArrayList<Producto> listarProductos() {
        return productoDAO.findAll(null);
    }

    public int crearProducto(Producto producto) {
        return productoDAO.add(producto);
    }

    public int actualizarProducto(Producto producto) {
        return productoDAO.update(producto);
    }

    public int eliminarProducto(int id) {
        return productoDAO.delete(id);
    }
}
