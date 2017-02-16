package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import structure.Structure;

/***********************************************************************
 * Module:  StructureDAO.java
 * Author:  merguez
 * Purpose: Defines the Class StructureDAO
 ***********************************************************************/

public abstract class StructureDAO<T extends Structure> implements DAO<T> {
	
	final Class<T> parametre;
	
	public StructureDAO(Class<T> parametre) {
		this.parametre = parametre;
	}
	
	public ResultSet lister(String requete) throws SQLException {
		return ConnectionOracle.procedureOut(requete);
	}
	
	public ResultSet select(String requete, Integer id) throws SQLException {
		return ConnectionOracle.procedureInOut(requete, id);
	}
	
}