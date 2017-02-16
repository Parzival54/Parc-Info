package dao;

import composants.Composant;

/***********************************************************************
 * Module:  ComposantDAO.java
 * Author:  merguez
 * Purpose: Defines the Class ComposantDAO
 ***********************************************************************/

public abstract class ComposantDAO<T extends Composant> implements DAO<T> {
	
	final Class<T> parametre;
	
	public ComposantDAO(Class<T> parametre) {
		this.parametre = parametre;
	}


}