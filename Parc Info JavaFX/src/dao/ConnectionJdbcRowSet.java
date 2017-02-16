package dao;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class ConnectionJdbcRowSet {
	
	private static ResourceBundle bundleConnection = ResourceBundle.getBundle("ConnectionOracle", Locale.FRENCH);
	private static String url = bundleConnection.getString("url");
	private static String user = bundleConnection.getString("user");
	private static String password = bundleConnection.getString("password");
	private static JdbcRowSet rowSet;
	
	private ConnectionJdbcRowSet(){
		RowSetFactory rsf;
		try {
			rsf = RowSetProvider.newFactory();
			rowSet = rsf.createJdbcRowSet();
			rowSet.setUrl(url);
			rowSet.setUsername(user);
			rowSet.setPassword(password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static JdbcRowSet connecter(){
		if (rowSet == null){
			new ConnectionJdbcRowSet();
		}
		return rowSet;
	}

}
