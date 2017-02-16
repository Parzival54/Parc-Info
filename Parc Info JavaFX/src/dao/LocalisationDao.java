package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import structure.Localisation;

public class LocalisationDao extends DaoFactory implements DAO<Localisation> {

	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerLocalisations");
	String select = rb.getString("selectionLocalisation");
	String selectAvecCompId = rb.getString("selectionLocalisationAvecLocId");
	String ajouter = rb.getString("ajouterLocalisation");
	String retirer = rb.getString("retirerLocalisation");
	String modifier = rb.getString("modifierLocalisation");
	
	final Class<Localisation> parametre;
	
	public LocalisationDao(Class<Localisation> parametre) {
		this.parametre = parametre;
	}

	@Override
	public void creer(Localisation localisation) throws SQLException {
		ConnectionOracle.procedureIn(localisation, ajouter, localisation.getSalId(),
				localisation.getCpsId(),localisation.getBatId(), localisation.getLocNom());
	}

	@Override
	public void supprimer(Localisation localisation) throws SQLException {
		ConnectionOracle.procedureIn(localisation, retirer, localisation.getLocId());
	}

	@Override
	public void modifier(Localisation localisation) throws SQLException {
		ConnectionOracle.procedureIn(localisation, modifier, localisation.getLocId(), localisation.getSalId(),
				localisation.getCpsId(),localisation.getBatId(), localisation.getLocNom());
	}

	@Override
	public List<Localisation> lister() throws SQLException {
		
		List<Localisation> localisations = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()){
			Localisation localisation = new Localisation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			localisations.add(localisation);
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return localisations;
	}

	@Override
	public Localisation select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		
		rs.next();
		Localisation localisation = new Localisation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
		
		rs.close();
		ConnectionOracle.deconnecter();
		return localisation;
	}
	
	@Override
	public String selectIdToNom(Integer id) {
		ResultSet rs = null;
		try {
			rs = ConnectionOracle.procedureInOut(selectAvecCompId, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String locNom = null;
		try {
			rs.next();
			locNom = rs.getString(1);			
			rs.close();
			ConnectionOracle.deconnecter();
		} catch (SQLException e) {
			
		}
		return locNom;			
	}

}
