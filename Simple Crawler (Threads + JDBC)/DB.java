import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public Connection conn = null;

	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //syndesh sthn bash dedomenwn
			String url = "jdbc:mysql://195.251.209.12:3306/it125";
			conn = DriverManager.getConnection(url, "pdpuser", "resupdp");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}

	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}

	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}