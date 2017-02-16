package machine;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Formation {

	private IntegerProperty foId;
	private StringProperty foNom;
	private ObjectProperty<Date> foDatedebut;
	private ObjectProperty<Date> foDatefin;
	
	public Formation() {
		this(0, null, null, null);
	}

	public Formation(Integer fo_id, String fo_nom, Date foDatedebut, Date foDatefin) {
		this.foId = new SimpleIntegerProperty(fo_id);
		this.foNom = new SimpleStringProperty(fo_nom);
		this.foDatedebut = new SimpleObjectProperty<Date>(foDatedebut);
		this.foDatefin = new SimpleObjectProperty<Date>(foDatefin);
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

	public String getFoNom() {
		return foNom.get();
	}

	public void setFoNom(String foNom) {
		this.foNom.set(foNom);
	}
	
	public StringProperty foNomProperty(){
		return foNom;
	}

	public Date getFoDatedebut() {
		return foDatedebut.get();
	}

	public void setFoDatedebut(Date foDatedebut) {
		this.foDatedebut.set(foDatedebut);
	}
	
	public ObjectProperty<Date> foDatedebutProperty(){
		return foDatedebut;
	}

	public Date getFoDatefin() {
		return foDatefin.get();
	}

	public void setFoDatefin(Date foDatefin) {
		this.foDatefin.set(foDatefin);
	}
	
	public ObjectProperty<Date> foDatefinProperty(){
		return foDatefin;
	}

}