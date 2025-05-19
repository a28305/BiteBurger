package Controller.Actions;

import Model.Pedido;
import Model.PedidoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoAction {

    private PedidoDAO pedidoDAO;

    public PedidoAction() {
        this.pedidoDAO = new PedidoDAO(); // Usa MotorSQL internamente
    }

    public Pedido obtenerPedido(int id) throws SQLException {
        Pedido filtro = new Pedido();
        filtro.setIdPedido(id);
        ArrayList<Pedido> pedidos = pedidoDAO.findAll(filtro);
        if (!pedidos.isEmpty()) {
            return pedidos.get(0);
        }
        return null;
    }

    public ArrayList<Pedido> listarPedidos() throws SQLException {
        return pedidoDAO.findAll(null); // Sin filtro
    }

    public void crearPedido(Pedido pedido) throws SQLException {
        pedidoDAO.add(pedido);
    }

    public void actualizarPedido(Pedido pedido) throws SQLException {
        pedidoDAO.update(pedido);
    }

    public void eliminarPedido(int id) throws SQLException {
        pedidoDAO.delete(id);
    }
}
