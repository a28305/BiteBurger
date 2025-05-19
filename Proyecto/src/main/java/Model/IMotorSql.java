package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IMotorSql {
    public void connect();
    public int execute(String sql);
    public ResultSet executeQuery(String sql);
    public void disconnect();
    public Connection getConnection();
    public void setPreparedStatement(PreparedStatement stnt);
    public boolean execute(PreparedStatement stnt);
}
