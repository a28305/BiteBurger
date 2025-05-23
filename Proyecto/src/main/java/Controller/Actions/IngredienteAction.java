package Controller.Actions;

import Model.Ingrediente;
import Model.IngredienteDAO;
import Model.ProductoIngredienteDAO;

import java.sql.SQLException;
import java.util.ArrayList;


public class IngredienteAction {

    private IngredienteDAO ingredienteDAO;
    private ProductoIngredienteDAO productoIngredienteDAO;

    public IngredienteAction() {
        this.ingredienteDAO = new IngredienteDAO();
        this.productoIngredienteDAO = new ProductoIngredienteDAO();
    }


    public ArrayList<Ingrediente> buscarIngredientes(Ingrediente filtro) throws SQLException {
        return ingredienteDAO.findAll(filtro);
    }


    public ArrayList<Ingrediente> listarPorProducto(int idProducto) throws SQLException {
        return productoIngredienteDAO.findIngredientesPorProducto(idProducto);
    }
}
