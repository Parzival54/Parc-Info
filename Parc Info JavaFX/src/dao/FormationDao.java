package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import machine.Formation;

public class FormationDao implements DAO<Formation> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerFormations");
	String select = rb.getString("selectionFormation");
	String ajouter = rb.getString("ajouterFormation");
	String modifier = rb.getString("modifierFormation");
	String retirer = rb.getString("retirerFormation");

	final Class<Formation> parametre;

	public FormationDao(Class<Formation> parametre) {
		this.parametre = parametre;
	}
	
	@Override
	public void creer(Formation formation) throws SQLException, ParseException {
		ConnectionOracle.procedureIn(formation, ajouter, formation.getFoNom(),
					sqlDate(formation.getFoDatedebut()), sqlDate(formation.getFoDatefin()));
	}

	@Override
	public void supprimer(Formation formation) throws SQLException {
		ConnectionOracle.procedureIn(formation, retirer, formation.getFoId());
	}

	@Override
	public void modifier(Formation formation) throws SQLException, ParseException {
		ConnectionOracle.procedureIn(formation, modifier, formation.getFoId(), formation.getFoNom(),
				sqlDate(formation.getFoDatedebut()), sqlDate(formation.getFoDatefin()));
	}

	@Override
	public List<Formation> lister() throws SQLException, ParseException {
		List<Formation> formations = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		try {
			while (rs.next()) {
				Formation formation = new Formation(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4));
				formations.add(formation);
			}			
		} catch (SQLException e) {
			System.out.println("aucun résultat");
		}
		

		rs.close();
		ConnectionOracle.deconnecter();
		return formations;
	}

	@Override
	public Formation select(Integer id) throws SQLException, ParseException {
		
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		Formation formation = null;
		try{
			rs.next();
			formation = new Formation(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4));
		} catch (SQLException e){
			System.out.println("aucun résultat");
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return formation;
	}
	
	private static java.sql.Date sqlDate(java.util.Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		String dateDeb = sdf.format(date);
		java.sql.Date deb = new Date(sdf.parse(dateDeb).getTime());
		return deb;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
