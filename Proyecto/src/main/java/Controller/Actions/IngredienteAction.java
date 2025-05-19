package Controller.Actions;

import Model.Ingrediente;
import Model.IngredienteDAO;

import java.util.ArrayList;

public class IngredienteAction {

    private IngredienteDAO ingredienteDAO;

    public IngredienteAction() {
        this.ingredienteDAO = new IngredienteDAO();
    }

    public int crearIngrediente(Ingrediente ingrediente) {
        return ingredienteDAO.add(ingrediente);
    }

    public int actualizarIngrediente(Ingrediente ingrediente) {
        return ingredienteDAO.update(ingrediente);
    }

    public int eliminarIngrediente(int id) {
        return ingredienteDAO.delete(id);
    }

    public ArrayList<Ingrediente> buscarIngredientes(Ingrediente filtro) {
        return ingredienteDAO.findAll(filtro);
    }

    public Ingrediente obtenerIngredientePorId(int id) {
        Ingrediente filtro = new Ingrediente();
        filtro.setId_ingrediente(id);
        ArrayList<Ingrediente> lista = ingredienteDAO.findAll(filtro);
        return lista.isEmpty() ? null : lista.get(0);
    }
}
