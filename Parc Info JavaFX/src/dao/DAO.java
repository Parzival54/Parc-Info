package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/***********************************************************************
 * Module:  DAO.java
 * Author:  merguez
 * Purpose: Defines the Interface DAO
 ***********************************************************************/

public interface DAO<T> {

   void creer(T objet) throws SQLException, ParseException;

   void supprimer(T objet) throws SQLException;
   
   void modifier(T objet) throws SQLException, ParseException;

   List<T> lister() throws SQLException, ParseException;
   
   T select(Integer id) throws SQLException, ParseException;

   String selectIdToNom(Integer id);

}