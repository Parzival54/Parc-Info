package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.MemoireVive;

public class MemoireViveDao extends ComposantDAO<MemoireVive> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerMV");
	String select = rb.getString("selectionMV");	
	String selectCompId = rb.getString("selectionMVAvecCompId");	
	String ajouter = rb.getString("ajouterMV");
	String modifier = rb.getString("modifierMV");
	String retirer = rb.getString("retirerMV");

	public MemoireViveDao(Class<MemoireVive> parametre) {
		super(parametre);
	}

	@Override
	public void creer(MemoireVive mv) throws SQLException {
		ConnectionOracle.procedureIn(mv, ajouter, mv.getCompId(), mv.getFabricant(), mv.getType(), mv.getReference(),
				mv.getNom(), mv.getNumSerie(), mv.getMvCapacite());
	}

	@Override
	public void supprimer(MemoireVive mv) throws SQLException {
		ConnectionOracle.procedureIn(mv, retirer, mv.getId());
	}

	@Override
	public void modifier(MemoireVive mv) throws SQLException {
		ConnectionOracle.procedureIn(mv, modifier, mv.getId(), mv.getCompId(), mv.getFabricant(), mv.getType(), mv.getReference(),
				mv.getNom(), mv.getNumSerie(), mv.getMvCapacite());
	}

	@Override
	public List<MemoireVive> lister() throws SQLException {
		List<MemoireVive> listeMV = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()) {
			MemoireVive mv = new MemoireVive(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			listeMV.add(mv);
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return listeMV;
	}

	@Override
	public MemoireVive select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		MemoireVive mv = new MemoireVive(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
			rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
		rs.close();
		ConnectionOracle.deconnecter();
		return mv;
	}
	
	public List<MemoireVive> selectCompId(Integer id) throws SQLException {
		List<MemoireVive> listeMV = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureInOut(selectCompId, id);
		
		while (rs.next()) {
			MemoireVive mv = new MemoireVive(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			listeMV.add(mv);
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return listeMV;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


}
