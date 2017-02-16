package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.dbutils.QueryRunner;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class ConnectionOracle {
	
	private static ResourceBundle bundleConnection = ResourceBundle.getBundle("ConnectionOracle", Locale.FRENCH);
	private static String url = bundleConnection.getString("url");
	private static String user = bundleConnection.getString("user");
	private static String password = bundleConnection.getString("password");
	private static Connection connection;
	
	
	private ConnectionOracle(){
		OracleDataSource dataSource;

		try {
			dataSource = new OracleDataSource();
			dataSource.setURL(url);
			connection = dataSource.getConnection(user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connecter() throws SQLException {
		if (connection == null || connection.isClosed()){
			new ConnectionOracle();
		}
		return connection;
	}
	
	public static void deconnecter() throws SQLException {
		connection.close();
	}
	
	public static <T> void procedureIn(T objet, String requete, Object... params) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		connection.setAutoCommit(false);

		try {
			QueryRunner queryRunner = new QueryRunner();
			CallableStatement callableStatement = connection.prepareCall(requete);
			queryRunner.fillStatement(callableStatement, params);
			callableStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		}	
		connection.close();
	}
	
	public static ResultSet procedureOut(String requete) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		ResultSet rs = null;

		try {
			CallableStatement callableStatement = connection.prepareCall(requete);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			rs = (ResultSet) callableStatement.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static List<ResultSet> procedureOutMultiple(String requete, Integer nombreObjets) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		List<ResultSet> resultSets = new ArrayList<>();

		try {
			CallableStatement callableStatement = connection.prepareCall(requete);
			for (int i = 1; i <= nombreObjets; i++) {
				callableStatement.registerOutParameter(i, OracleTypes.CURSOR);				
			}
			callableStatement.execute();
			for (int i = 1; i <= nombreObjets; i++) {
				ResultSet rs = (ResultSet) callableStatement.getObject(i);
				resultSets.add(rs);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSets;
	}
	
	public static ResultSet procedureInOut(String requete, Object... params) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		ResultSet rs = null;

		try {
			CallableStatement callableStatement = connection.prepareCall(requete);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			for (int i = 0; i < params.length; i++) {
				callableStatement.setObject(i + 2, params[i]);
			}
			callableStatement.execute();
			rs = (ResultSet) callableStatement.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static List<ResultSet> procedureInOutMultiple(String requete, Integer nombreObjets, Object... params) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		List<ResultSet> resultSets = new ArrayList<>();

		try {
			CallableStatement callableStatement = connection.prepareCall(requete);
			for (int i = 1; i <= nombreObjets; i++) {
				callableStatement.registerOutParameter(i, OracleTypes.CURSOR);				
			}
			for (int i = 0; i < params.length; i++) {
				callableStatement.setObject(i + nombreObjets + 1, params[i]);
			}
			callableStatement.execute();
			for (int i = 1; i <= nombreObjets; i++) {
				ResultSet rs = (ResultSet) callableStatement.getObject(i);
				resultSets.add(rs);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSets;
	}
	
	public static ResultSet compteur(String requete) throws SQLException {
		Connection connection = ConnectionOracle.connecter();
		ResultSet rs = null;

		try {
			CallableStatement callableStatement = connection.prepareCall(requete);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			rs = (ResultSet) callableStatement.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
