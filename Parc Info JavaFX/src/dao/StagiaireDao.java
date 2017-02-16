package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import machine.Stagiaire;

public class StagiaireDao implements DAO<Stagiaire> {

	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerStagiaires");
	String select = rb.getString("selectionStagiaire");
	String ajouter = rb.getString("ajouterStagiaire");
	String modifier = rb.getString("modifierStagiaire");
	String retirer = rb.getString("retirerStagiaire");

	final Class<Stagiaire> parametre;

	public StagiaireDao(Class<Stagiaire> parametre) {
		this.parametre = parametre;
	}

	@Override
	public void creer(Stagiaire stagiaire) throws SQLException {
		ConnectionOracle.procedureIn(stagiaire, ajouter, stagiaire.getMaId(), stagiaire.getFoId(), stagiaire.getStgNom(),
				stagiaire.getStgPrenom(), stagiaire.getStgAdresse(), stagiaire.getStgNumtel());
	}

	@Override
	public void supprimer(Stagiaire stagiaire) throws SQLException {
		ConnectionOracle.procedureIn(stagiaire, retirer, stagiaire.getStgId());
	}

	@Override
	public void modifier(Stagiaire stagiaire) throws SQLException {
		ConnectionOracle.procedureIn(stagiaire, modifier, stagiaire.getStgId(), stagiaire.getMaId(), stagiaire.getFoId(),
				stagiaire.getStgNom(), stagiaire.getStgPrenom(), stagiaire.getStgAdresse(), stagiaire.getStgNumtel());
	}

	@Override
	public List<Stagiaire> lister() throws SQLException {
		
		List<Stagiaire> stagiaires = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		try {
			while (rs.next()) {
				Stagiaire stagiaire = new Stagiaire(rs.getString(4), rs.getString(5), 
						rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(6), rs.getInt(7));
				stagiaires.add(stagiaire);
			}			
		} catch (SQLException e) {
			System.out.println("aucun résultat");
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return stagiaires;

	}

	@Override
	public Stagiaire select(Integer id) throws SQLException {
		
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		Stagiaire stagiaire = null;
		try {		
			rs.next();
			stagiaire = new Stagiaire(rs.getString(3), rs.getString(4), id, rs.getInt(1), rs.getInt(2), rs.getString(5), rs.getInt(6));
		} catch (SQLException e) {
			System.out.println("aucun résultat");
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return stagiaire;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
