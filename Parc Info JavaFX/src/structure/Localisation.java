package structure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Localisation {

	private IntegerProperty locId;
	private IntegerProperty salId;
	private IntegerProperty cpsId;
	private IntegerProperty batId;
	private StringProperty locNom;

	public Localisation() {
		this(0, 0, 0, 0, null);
	}

	public Localisation(Integer locId, Integer salId, Integer cpsId, Integer batId, String locNom) {
		this.locId = new SimpleIntegerProperty(locId);
		this.salId = new SimpleIntegerProperty(salId);
		this.cpsId = new SimpleIntegerProperty(cpsId);
		this.batId = new SimpleIntegerProperty(batId);
		this.locNom = new SimpleStringProperty(locNom);
	}

	public Integer getLocId() {
		return locId.get();
	}

	public void setLocId(Integer locId) {
		this.locId.set(locId);
	}
	
	public IntegerProperty locIdProperty(){
		return locId;
	}

	public Integer getSalId() {
		return salId.get();
	}

	public void setSalId(Integer salId) {
		this.salId.set(salId);
	}
	
	public IntegerProperty salIdProperty(){
		return salId;
	}

	public Integer getCpsId() {
		return cpsId.get();
	}

	public void setCpsId(Integer cpsId) {
		this.cpsId.set(cpsId);
	}
	
	public IntegerProperty cpsIdProperty(){
		return cpsId;
	}

	public Integer getBatId() {
		return batId.get();
	}

	public void setBatId(Integer batId) {
		this.batId.set(batId);
	}
	
	public IntegerProperty batIdProperty(){
		return batId;
	}

	public String getLocNom() {
		return locNom.get();
	}

	public void setLocNom(String locNom) {
		this.locNom.set(locNom);
	}
	
	public StringProperty locNomProperty(){
		return locNom;
	}


}
