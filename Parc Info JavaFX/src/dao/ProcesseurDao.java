package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import composants.Processeur;

public class ProcesseurDao extends ComposantDAO<Processeur> {
	
	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerProc");
	String select = rb.getString("selectionProc");	
	String selectCompId = rb.getString("selectionProcAvecCompId");	
	String ajouter = rb.getString("ajouterProc");
	String modifier = rb.getString("modifierProc");
	String retirer = rb.getString("retirerProc");

	public ProcesseurDao(Class<Processeur> parametre) {
		super(parametre);
	}

	@Override
	public void creer(Processeur proc) throws SQLException {
		ConnectionOracle.procedureIn(proc, ajouter, proc.getCompId(), proc.getFabricant(), proc.getType(), proc.getReference(),
				proc.getNom(), proc.getNumSerie(), proc.getProcFrequence(), proc.getProcNbCoeurs());
	}

	@Override
	public void supprimer(Processeur proc) throws SQLException {
		ConnectionOracle.procedureIn(proc, modifier, proc.getId());
	}

	@Override
	public void modifier(Processeur proc) throws SQLException {
		ConnectionOracle.procedureIn(proc, modifier, proc.getId(), proc.getCompId(), proc.getFabricant(), proc.getType(),
				proc.getReference(), proc.getNom(), proc.getNumSerie(), proc.getProcFrequence(), proc.getProcNbCoeurs());
	}

	@Override
	public List<Processeur> lister() throws SQLException {
		List<Processeur> listeProcesseurs = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		
		while (rs.next()) {
		Processeur proc = new Processeur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
		listeProcesseurs.add(proc);
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return listeProcesseurs;
		
	}

	@Override
	public Processeur select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		Processeur proc = new Processeur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
		rs.close();
		ConnectionOracle.deconnecter();
		return proc;
	}
	
	public Processeur selectCompId(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(selectCompId, id);
		Processeur proc = null;
		if (rs.next()){
			proc = new Processeur(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));			
		}
		rs.close();
		ConnectionOracle.deconnecter();
		return proc;
	}

	@Override
	public String selectIdToNom(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
