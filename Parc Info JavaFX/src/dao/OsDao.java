package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.Os;

public class OsDao extends ComposantDAO<Os> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerOs");
	String select = rb.getString("selectionOs");	
	String selectCompId = rb.getString("selectionOsAvecCompId");	
	String ajouter = rb.getString("ajouterOs");
	String modifier = rb.getString("modifierOs");
	String retirer = rb.getString("retirerOs");

	public OsDao(Class<Os> parametre) {
		super(parametre);
	}
	
	@Override
	public void creer(Os os) throws SQLException {
		ConnectionOracle.procedureIn(os, ajouter, os.getCompId(), os.getFabricant(), os.getType(), 
			os.getReference(), os.getNom(), os.getNumSerie(), os.getOsVersion());
	}

	@Override
	public void supprimer(Os os) throws SQLException {
		ConnectionOracle.procedureIn(os, modifier, os.getId());
	}

	@Override
	public void modifier(Os os) throws SQLException {
		ConnectionOracle.procedureIn(os, modifier, os.getId(), os.getCompId(), os.getFabricant(), 
				os.getType(), os.getReference(), os.getNom(), os.getNumSerie(), os.getOsVersion());
	}

	@Override
	public List<Os> lister() throws SQLException {
		List<Os> listeOs = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()) {
			Os os = new Os(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			listeOs.add(os);
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return listeOs;
		
	}

	@Override
	public Os select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		Os os = new Os(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
			rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		rs.close();
		ConnectionOracle.deconnecter();
		return os;
	}
	
	public Os selectCompId(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(selectCompId, id);
		Os os = null;
		if (rs.next()){
			os = new Os(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));			
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return os;
	}

	@Override
	public String selectIdToNom(Integer id) {
		return null;
	}

}
