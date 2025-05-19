package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorSQL implements IMotorSql {
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private static final String URL = "jdbc:mysql://biteburgersql.cqb4hjqwyi3w.us-east-1.rds.amazonaws.com:3306/BiteBurgerSQL";
    private static final String USER = "admin";
    private static final String PASSWORD = "BiteBurgerSQL";

    @Override
    public void connect() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            st = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public int execute(String sql) {
        int result = 0;
        try {
            if (st == null || st.isClosed()) {
                st = conn.createStatement();
            }
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException in execute: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public ResultSet executeQuery(String sql) {
        try {
            if (st == null || st.isClosed()) {
                st = conn.createStatement();
            }
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException in executeQuery: " + ex.getMessage());
        }
        return rs;
    }

    @Override
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if (st != null) {
                st.close();
                st = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in disconnect: " + ex.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void setPreparedStatement(PreparedStatement stnt) {
        this.pstmt = stnt;
    }

    @Override
    public boolean execute(PreparedStatement stnt) {
        try {
            return stnt.execute();
        } catch (SQLException ex) {
            System.out.println("SQLException in execute(PreparedStatement): " + ex.getMessage());
            return false;
        }
    }
}
