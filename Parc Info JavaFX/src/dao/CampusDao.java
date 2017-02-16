package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import structure.Campus;

public class CampusDao extends StructureDAO<Campus> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerCampus");
	String select = rb.getString("selectionCampus");
	String selectAvecId = rb.getString("selectionCampusAvecCpsId");
	String ajouter = rb.getString("ajouterCampus");
	String modifier = rb.getString("modifierCampus");
	String retirer = rb.getString("retirerCampus");

	public CampusDao(Class<Campus> parametre) {
		super(parametre);
	}

	@Override
	public void creer(Campus campus) throws SQLException {
		ConnectionOracle.procedureIn(campus, ajouter, campus.getReference(), campus.getNom(),
				campus.getCpsAdresse(), campus.getCpsCp(), campus.getCpsVille());
	}

	@Override
	public void supprimer(Campus campus) throws SQLException {
		ConnectionOracle.procedureIn(campus, retirer, campus.getId());
	}

	@Override
	public void modifier(Campus campus) throws SQLException {
		ConnectionOracle.procedureIn(campus, modifier, campus.getId(), campus.getReference(), campus.getNom(),
				campus.getCpsAdresse(), campus.getCpsCp(), campus.getCpsVille());

	}

	@Override
	public List<Campus> lister() throws SQLException {
		ResultSet rs = super.lister(lister);
		List<Campus> lcampus = new ArrayList<>();
		while (rs.next()){
			Campus campus = new Campus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
			lcampus.add(campus);
		}
		
		rs.close();
		return lcampus;
	}

	@Override
	public Campus select(Integer id) throws SQLException {
		ResultSet rs = super.select(select, id);
		rs.next();
		Campus campus = new Campus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
		rs.close();
		
		return campus;
	}
	
	@Override
	public String selectIdToNom(Integer id) {
		ResultSet rs = null;
		try {
			rs = ConnectionOracle.procedureInOut(selectAvecId, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String cpsNom = null;
		try {
			rs.next();
			cpsNom = rs.getString(1);			
			rs.close();
			ConnectionOracle.deconnecter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cpsNom;			
	}
	
}