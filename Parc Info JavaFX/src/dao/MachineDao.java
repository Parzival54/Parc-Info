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
import machine.Machine;

public class MachineDao implements DAO<Machine> {

	ResourceBundle rb = ResourceBundle.getBundle("requetes", Locale.FRENCH);
	String lister = rb.getString("listerMachines");
	String ajouter= rb.getString("ajouterMachine");
	String modifier = rb.getString("modifierMachine");
	String retirer = rb.getString("retirerMachine");
	String select = rb.getString("selectionMachine");
	String selectAvecCompId = rb.getString("selectionMachineAvecCompId");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	final Class<Machine> parametre;

	public MachineDao(Class<Machine> parametre) {
		this.parametre = parametre;
	}

	public void trier() {
	}

	public String decrire() {
		return null;
	}

	@Override
	public void creer(Machine ma) throws SQLException, ParseException {
		ConnectionOracle.procedureIn(ma, ajouter, ma.getStgId(), ma.getLocId(), ma.getCompId(), ma.getMaNumAfpa(),
				sqlDate(ma.getMaDateAchat()), ma.getMaDureeGarantie(), ma.getMaIP(), ma.getMaNom());
	}

	@Override
	public void supprimer(Machine ma) throws SQLException {
		ConnectionOracle.procedureIn(ma, retirer, ma.getMaId());
	}

	@Override
	public void modifier(Machine ma) throws SQLException, ParseException {
		ConnectionOracle.procedureIn(ma, modifier, ma.getMaId(), ma.getStgId(), ma.getLocId(), ma.getCompId(),
				ma.getMaNumAfpa(), sqlDate(ma.getMaDateAchat()), ma.getMaDureeGarantie(), ma.getMaIP(), ma.getMaNom());
	}

	@Override
	public List<Machine> lister() throws SQLException {
		List<Machine> machines = new ArrayList<>();
		ResultSet rs = ConnectionOracle.procedureOut(lister);
		try{
			while (rs.next()) {
				Machine ma = new Machine(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9));
				machines.add(ma);
			}
		} catch (SQLException e) {
			System.out.println("aucun résultat");
		}
		
		rs.close();
		ConnectionOracle.deconnecter();
		return machines;
	}

	@Override
	public Machine select(Integer id) throws SQLException {
		ResultSet rs = ConnectionOracle.procedureInOut(select, id);
		rs.next();
		Machine ma = new Machine(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
				rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9));
		rs.close();
		ConnectionOracle.deconnecter();
		return ma;
	}
	
	private static java.sql.Date sqlDate(java.util.Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		String dateDeb = sdf.format(date);
		java.sql.Date deb = new Date(sdf.parse(dateDeb).getTime());
		return deb;
	}
	
	@Override
	public String selectIdToNom(Integer id) {
		ResultSet rs = null;
		try {
			rs = ConnectionOracle.procedureInOut(selectAvecCompId, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String maNom = null;
		try {
			rs.next();
			maNom = rs.getString(1);			
			rs.close();
			ConnectionOracle.deconnecter();
		} catch (SQLException e) {
			
		}
		return maNom;			
	}

}