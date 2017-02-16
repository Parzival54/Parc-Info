package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import structure.Salle;

public class SalleDao extends StructureDAO<Salle> {

	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerSalles");
	String select = rb.getString("selectionSalle");
	String selectAvecId = rb.getString("selectionSalleAvecSalId");
	String ajouter = rb.getString("ajouterSalle");
	String modifier = rb.getString("modifierSalle");
	String retirer = rb.getString("retirerSalle");

	public SalleDao(Class<Salle> parametre) {
		super(parametre);
	}

	@Override
	public void creer(Salle salle) throws SQLException {
		ConnectionOracle.procedureIn(salle, ajouter, salle.getReference(),
				salle.getNom(), salle.getSalNumero());
	}

	@Override
	public void supprimer(Salle salle) throws SQLException {
		ConnectionOracle.procedureIn(salle, retirer, salle.getId());
	}

	@Override
	public void modifier(Salle salle) throws SQLException {
		ConnectionOracle.procedureIn(salle, modifier, salle.getId(),salle.getReference(),
				salle.getNom(), salle.getSalNumero());
	}

	@Override
	public List<Salle> lister() throws SQLException {
		List<Salle> salles = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		while (rs.next()){
			Salle salle = new Salle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			salles.add(salle);
		}
		rs.close();
		return salles;
	}

	@Override
	public Salle select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		Salle salle = new Salle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		rs.close();
		
		return salle;
	}
	
	@Override
	public String selectIdToNom(Integer id) {
		ResultSet rs = null;
		try {
			rs = ConnectionOracle.procedureInOut(selectAvecId, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String salNom = null;
		try {
			rs.next();
			salNom = rs.getString(1);			
			rs.close();
			ConnectionOracle.deconnecter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salNom;			
	}
	
}