package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;

public class FacturaDAO implements IDao<Factura, Integer> {

    private final String SQL_FIND = "SELECT * FROM Facturas WHERE 1=1";
    private IMotorSql motorSql;

    public FacturaDAO() {
        this.motorSql = new MotorSQL();  // Asumiendo que MotorSQL implementa IMotorSql
    }

    @Override
    public int add(Factura factura) {
        String sql = "INSERT INTO Facturas (id_factura, id_metodo, fecha_emision, total, iva) VALUES (" +
                factura.getIdFactura() + ", " +
                factura.getIdMetodo() + ", '" +
                new java.sql.Date(factura.getFechaEmision().getTime()) + "', " +
                factura.getTotal() + ", " +
                factura.getIva() + ")";
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Facturas WHERE id_factura = " + id;
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public int update(Factura factura) {
        String sql = "UPDATE Facturas SET " +
                "id_metodo = " + factura.getIdMetodo() + ", " +
                "fecha_emision = '" + new java.sql.Date(factura.getFechaEmision().getTime()) + "', " +
                "total = " + factura.getTotal() + ", " +
                "iva = " + factura.getIva() + " " +
                "WHERE id_factura = " + factura.getIdFactura();
        motorSql.connect();
        return motorSql.execute(sql);
    }

    @Override
    public ArrayList<Factura> findAll(Factura filtro) {
        ArrayList<Factura> facturas = new ArrayList<>();
        String sql = SQL_FIND;

        if (filtro != null) {
            if (filtro.getIdFactura() > 0) {
                sql += " AND id_factura = " + filtro.getIdFactura();
            }
            if (filtro.getIdMetodo() > 0) {
                sql += " AND id_metodo = " + filtro.getIdMetodo();
            }
            if (filtro.getFechaEmision() != null) {
                sql += " AND fecha_emision = '" + new java.sql.Date(filtro.getFechaEmision().getTime()) + "'";
            }
            if (filtro.getTotal() != null) {
                sql += " AND total = " + filtro.getTotal();
            }
            if (filtro.getIva() != null) {
                sql += " AND iva = " + filtro.getIva();
            }
        }

        motorSql.connect();
        try {
            ResultSet rs = motorSql.executeQuery(sql);
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("id_factura"));
                factura.setIdMetodo(rs.getInt("id_metodo"));
                factura.setFechaEmision(rs.getDate("fecha_emision"));
                factura.setTotal(rs.getBigDecimal("total"));
                factura.setIva(rs.getBigDecimal("iva"));
                facturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll FacturaDAO: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return facturas;
    }
}

