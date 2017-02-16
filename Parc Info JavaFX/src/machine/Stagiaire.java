package machine;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stagiaire {

	private StringProperty stgNom;
	private StringProperty stgPrenom;
	private IntegerProperty stgId;
	private IntegerProperty maId;
	private IntegerProperty foId;
	private StringProperty stgAdresse;
	private IntegerProperty stgNumtel;

	public Stagiaire() {
		this(null, null, 0, 0, 0, null, 0);
	}

	public Stagiaire(String stgNom, String stgPrenom, Integer stgId,
			Integer maId, Integer foId, String stgAdresse, Integer stgNumtel) {
		this.stgNom = new SimpleStringProperty(stgNom);
		this.stgPrenom = new SimpleStringProperty(stgPrenom);
		this.stgId = new SimpleIntegerProperty(stgId);
		this.maId = new SimpleIntegerProperty(maId);
		this.foId = new SimpleIntegerProperty(foId);
		this.stgAdresse = new SimpleStringProperty(stgAdresse);
		this.stgNumtel = new SimpleIntegerProperty(stgNumtel);
	}

	public String getStgNom() {
		return stgNom.get();
	}

	public void setStgNom(String stgNom) {
		this.stgNom.set(stgNom);
	}
	
	public StringProperty stgNomProperty(){
		return stgNom;
	}

	public String getStgPrenom() {
		return stgPrenom.get();
	}

	public void setStgPrenom(String stgPrenom) {
		this.stgPrenom.set(stgPrenom);
	}
	
	public StringProperty stgPrenomProperty(){
		return stgPrenom;
	}

	public Integer getStgId() {
		return stgId.get();
	}

	public void setStgId(Integer stgId) {
		this.stgId.set(stgId);
	}
	
	public IntegerProperty stgIdProperty(){
		return stgId;
	}

	public Integer getMaId() {
		return maId.get();
	}

	public void setMaId(Integer maId) {
		this.maId.set(maId);
	}
	
	public IntegerProperty maIdProperty(){
		return maId;
	}

	public Integer getFoId() {
		return foId.get();
	}

	public void setFoId(Integer foId) {
		this.foId.set(foId);
	}
	
	public IntegerProperty foIdProperty(){
		return foId;
	} 

	public String getStgAdresse() {
		return stgAdresse.get();
	}

	public void setStgAdresse(String stgAdresse) {
		this.stgAdresse.set(stgAdresse);
	}
	
	public StringProperty stgAdresseProperty(){
		return stgAdresse;
	}

	public Integer getStgNumtel() {
		return stgNumtel.get();
	}

	public void setStgNumtel(Integer stgNumtel) {
		this.stgNumtel.set(stgNumtel);
	}
	
	public IntegerProperty stgNumtelProperty(){
		return stgNumtel;
	}

	@Override
	public String toString() {
		return "Stagiaire [stgNom=" + stgNom.get() + ", stgPrenom=" + stgPrenom.get() + ", stgId=" + stgId.get()
				+ ", maId=" + maId.get() + ", foId=" + foId.get() + ", stgAdresse="
				+ stgAdresse.get() + ", stgNumtel=" + stgNumtel.get() + "]";
	}

}