package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.DisqueDur;
import composants.ListeComposants;
import composants.MemoireVive;

public class ListeComposantsDao implements DAO<ListeComposants> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerComposants");
	String select = rb.getString("selectionListeComposants");
	String ajouter = rb.getString("ajouterComposants");
	String modifier = rb.getString("modifierComposants");
	String retirer = rb.getString("retirerComposants");
	
	final Class<ListeComposants> parametre;
	
	public ListeComposantsDao(Class<ListeComposants> parametre) {
		this.parametre = parametre;
	}

	@Override
	public void creer(ListeComposants listeComposants) throws SQLException {
		ConnectionOracle.procedureIn(listeComposants, ajouter, listeComposants.getCmId(), listeComposants.getProcId(),
					listeComposants.getOsId(), listeComposants.getMaId());
	}

	@Override
	public void supprimer(ListeComposants listeComposants) throws SQLException {
		ConnectionOracle.procedureIn(listeComposants, retirer, listeComposants.getCompId());
	}

	@Override
	public void modifier(ListeComposants listeComposants) throws SQLException {
		ConnectionOracle.procedureIn(listeComposants, modifier, listeComposants.getCompId(), listeComposants.getCmId(),
				listeComposants.getProcId(), listeComposants.getOsId(), listeComposants.getMaId());
	}

	@Override
	public List<ListeComposants> lister() throws SQLException {
		List<ListeComposants> listeComposants = new ArrayList<>();
		List<ResultSet> resultSets = ConnectionOracle.procedureOutMultiple(lister, 3);
		List<MemoireVive> memoireVives = new ArrayList<>();
		List<DisqueDur> disqueDurs = new ArrayList<>();
		// Liste Composants
		ResultSet rsLcomp = resultSets.get(0);
		// Liste MemoireVives
		ResultSet rsLmv = resultSets.get(1);
		// Liste DisqueDurs
		ResultSet rsLdd = resultSets.get(2);
		
		while (rsLcomp.next()){
			while (rsLmv.next()) {
				if (rsLmv.getInt(2) == rsLcomp.getInt(1)){
					MemoireVive memoireVive = new MemoireVive(rsLmv.getInt(1), rsLmv.getInt(2), rsLmv.getString(3), rsLmv.getString(4),
					rsLmv.getString(5), rsLmv.getString(6), rsLmv.getString(7), rsLmv.getInt(8));
					memoireVives.add(memoireVive);
				}
			}
			
			while (rsLdd.next()) {
				if (rsLdd.getInt(2) == rsLcomp.getInt(1)){
					DisqueDur disqueDur = new DisqueDur(rsLdd.getInt(1), rsLdd.getInt(2), rsLdd.getString(3), rsLdd.getString(4),
					rsLdd.getString(5), rsLdd.getString(6), rsLdd.getString(7), rsLdd.getInt(8), rsLdd.getString(9));
					disqueDurs.add(disqueDur);
				}
			}
			
			ListeComposants composants = new ListeComposants(rsLcomp.getInt(1), rsLcomp.getInt(2), rsLcomp.getInt(3),
					rsLcomp.getInt(4), rsLcomp.getInt(5), memoireVives, disqueDurs);
			listeComposants.add(composants);
		}
		
		rsLcomp.close();
		rsLdd.close();
		rsLmv.close();
		ConnectionOracle.deconnecter();
		return listeComposants;
	}

	@Override
	public ListeComposants select(Integer id) throws SQLException {
		List<ResultSet> resultSets = ConnectionOracle.procedureInOutMultiple(select, 3, id);
		List<MemoireVive> memoireVives = new ArrayList<>();
		List<DisqueDur> disqueDurs = new ArrayList<>();
		// Liste Composants
		ResultSet rsLcomp = resultSets.get(0);
		// Liste MemoireVives
		ResultSet rsLmv = resultSets.get(1);
		// Liste DisqueDurs
		ResultSet rsLdd = resultSets.get(2);
		
		rsLcomp.next();
		while (rsLmv.next()) {
			if (rsLmv.getInt(2) == rsLcomp.getInt(1)){
				MemoireVive memoireVive = new MemoireVive(rsLmv.getInt(1), rsLmv.getInt(2), rsLmv.getString(3), rsLmv.getString(4),
				rsLmv.getString(5), rsLmv.getString(6), rsLmv.getString(7), rsLmv.getInt(8));
				memoireVives.add(memoireVive);
				}
		}
			
		while (rsLdd.next()) {
			if (rsLdd.getInt(2) == rsLcomp.getInt(1)){
				DisqueDur disqueDur = new DisqueDur(rsLdd.getInt(1), rsLdd.getInt(2), rsLdd.getString(3), rsLdd.getString(4),
				rsLdd.getString(5), rsLdd.getString(6), rsLdd.getString(7), rsLdd.getInt(8), rsLdd.getString(9));
				disqueDurs.add(disqueDur);
			}
		}
			
		ListeComposants composants = new ListeComposants(rsLcomp.getInt(1), rsLcomp.getInt(2), rsLcomp.getInt(3),
				rsLcomp.getInt(4), rsLcomp.getInt(5), memoireVives, disqueDurs);
		
		rsLcomp.close();
		rsLdd.close();
		rsLmv.close();
		ConnectionOracle.deconnecter();
		return composants;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
