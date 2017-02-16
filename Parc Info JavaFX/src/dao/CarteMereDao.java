package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.CarteMere;

public class CarteMereDao extends ComposantDAO<CarteMere> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerCM");
	String select = rb.getString("selectionCM");	
	String selectCompId = rb.getString("selectionCMAvecCompId");	
	String ajouter = rb.getString("ajouterCM");
	String modifier = rb.getString("modifierCM");
	String retirer = rb.getString("retirerCM");
	
	public CarteMereDao(Class<CarteMere> parametre) {
		super(parametre);
	}

	@Override
	public void creer(CarteMere cm) throws SQLException {
		ConnectionOracle.procedureIn(cm, ajouter, cm.getCompId(), cm.getFabricant(), cm.getType(),
				cm.getReference(), cm.getNom(), cm.getNumSerie(), cm.getCmNbslots());
	}

	@Override
	public void supprimer(CarteMere cm) throws SQLException {
		ConnectionOracle.procedureIn(cm, retirer, cm.getId());		
	}

	@Override
	public void modifier(CarteMere cm) throws SQLException {
		ConnectionOracle.procedureIn(cm, modifier, cm.getId(), cm.getCompId(), cm.getFabricant(), cm.getType(),
				cm.getReference(), cm.getNom(), cm.getNumSerie(), cm.getCmNbslots());
	}

	@Override
	public List<CarteMere> lister() throws SQLException {
		List<CarteMere> listeCarteMeres = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()) {
			CarteMere cm = new CarteMere(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			listeCarteMeres.add(cm);
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return listeCarteMeres;
	}

	@Override
	public CarteMere select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		CarteMere cm = null;
		try {
			rs.next();
			cm = new CarteMere(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return cm;
	}
	
	public CarteMere selectCompId(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(selectCompId, id);
		CarteMere cm = null;
		if	(rs.next()){
			cm = new CarteMere(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));			
		} 
		rs.close();
		ConnectionOracle.deconnecter();
		return cm;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
