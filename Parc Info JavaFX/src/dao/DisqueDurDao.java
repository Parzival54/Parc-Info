package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.DisqueDur;

public class DisqueDurDao extends ComposantDAO<DisqueDur> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes",Locale.FRENCH);
	String lister = rb.getString("listerDD");
	String select = rb.getString("selectionDD");	
	String selectCompId = rb.getString("selectionDDAvecCompId");	
	String ajouter = rb.getString("ajouterDD");
	String modifier = rb.getString("modifierDD");
	String retirer = rb.getString("retirerDD");
	
	public DisqueDurDao(Class<DisqueDur> parametre) {
		super(parametre);
	}
	
	@Override
	public void creer(DisqueDur dd) throws SQLException {
		ConnectionOracle.procedureIn(dd, ajouter, dd.getCompId(), dd.getFabricant(), dd.getType(), dd.getReference(),
				dd.getNom(), dd.getNumSerie(), dd.getDdCapacite(), dd.getDdConnectique());
	}

	@Override
	public void supprimer(DisqueDur dd) throws SQLException {
		ConnectionOracle.procedureIn(dd, retirer, dd.getId());
	}

	@Override
	public void modifier(DisqueDur dd) throws SQLException {
		ConnectionOracle.procedureIn(dd, modifier, dd.getId(), dd.getCompId(), dd.getFabricant(), dd.getType(), dd.getReference(),
				dd.getNom(), dd.getNumSerie(), dd.getDdCapacite(), dd.getDdConnectique());
	}

	@Override
	public List<DisqueDur> lister() throws SQLException {
		List<DisqueDur> listeDisqueDurs = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()) {
			DisqueDur dd = new DisqueDur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
			listeDisqueDurs.add(dd);
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return listeDisqueDurs;
	}

	@Override
	public DisqueDur select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		DisqueDur dd = new DisqueDur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
		rs.close();
		ConnectionOracle.deconnecter();
		return dd;
	}
	
	
	public List<DisqueDur> selectCompId(Integer id) throws SQLException {
		List<DisqueDur> listeDisqueDurs = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureInOut(selectCompId, id);
		
		while (rs.next()) {
			DisqueDur dd = new DisqueDur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
			listeDisqueDurs.add(dd);
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return listeDisqueDurs;
	}

	@Override
	public String selectIdToNom(Integer id) {
		return null;
	}

}