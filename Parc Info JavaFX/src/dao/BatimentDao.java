package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import structure.Batiment;

public class BatimentDao extends StructureDAO<Batiment> {

	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerBatiments");
	String select = rb.getString("selectionBatiment");
	String selectAvecId = rb.getString("selectionBatimentAvecBatId");
	String ajouter = rb.getString("ajouterBatiment");
	String modifier = rb.getString("modifierBatiment");
	String retirer = rb.getString("retirerBatiment");

	public BatimentDao(Class<Batiment> parametre) {
		super(parametre);
	}

	@Override
	public void creer(Batiment batiment) throws SQLException {
		ConnectionOracle.procedureIn(batiment, ajouter, batiment.getReference(), batiment.getNom(),
				batiment.getBatNumero());
	}

	@Override
	public void supprimer(Batiment batiment) throws SQLException {
		ConnectionOracle.procedureIn(batiment, retirer, batiment.getId());
	}

	@Override
	public void modifier(Batiment batiment) throws SQLException {
		ConnectionOracle.procedureIn(batiment, modifier, batiment.getId(), 
				batiment.getReference(), batiment.getNom(), batiment.getBatNumero());
	}

	@Override
	public List<Batiment> lister() throws SQLException {
		ResultSet rs = super.lister(lister);
		List<Batiment> batiments = new ArrayList<>();

		while (rs.next()) {
			Batiment batiment = new Batiment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			batiments.add(batiment);
		}

		rs.close();
		return batiments;
	}

	@Override
	public Batiment select(Integer id) throws SQLException {
		ResultSet rs = super.select(select, id);
		rs.next();
		Batiment batiment = new Batiment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		rs.close();

		return batiment;
	}
	
	@Override
	public String selectIdToNom(Integer id) {
		ResultSet rs = null;
		try {
			rs = ConnectionOracle.procedureInOut(selectAvecId, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String batNom = null;
		try {
			rs.next();
			batNom = rs.getString(1);			
			rs.close();
			ConnectionOracle.deconnecter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return batNom;			
	}

}