package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class SequencesDao {

	public static Integer currval(Connection connection, Classe classe) throws SQLException {

		String sql = "SELECT " + classe + "_SEQ.CURRVAL FROM DUAL";
		QueryRunner queryRunner = new QueryRunner();
		
		ResultSetHandler<Integer> resultSetHandler = new ResultSetHandler<Integer>() {

			@Override
			public Integer handle(ResultSet arg0) throws SQLException {
				arg0.next();
				return arg0.getInt(1);
			}
		};
		return queryRunner.query(connection, sql, resultSetHandler);

	}
	
	public static Integer nextval(Connection connection, Classe classe) throws SQLException {

		String sql = "SELECT " + classe + "_SEQ.NEXTVAL FROM DUAL";
		QueryRunner queryRunner = new QueryRunner();
		
		ResultSetHandler<Integer> resultSetHandler = new ResultSetHandler<Integer>() {

			@Override
			public Integer handle(ResultSet arg0) throws SQLException {
				arg0.next();
				return arg0.getInt(1);
			}
		};
		return queryRunner.query(connection, sql, resultSetHandler);

	}

}
