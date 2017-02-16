package structure;

import javax.validation.constraints.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Campus extends Structure {

	private StringProperty cpsAdresse;
	@Pattern(regexp = "^[0-9]{4,5}$")
	private IntegerProperty cpsCp;
	private StringProperty cpsVille;
	
	public Campus() {
	}

	public Campus(Integer id, String reference, String nom, String cpsAdresse, Integer cpsCp, String cpsVille) {
		super(id, reference, nom);
		this.cpsAdresse = new SimpleStringProperty(cpsAdresse);
		this.cpsCp = new SimpleIntegerProperty(cpsCp);
		this.cpsVille = new SimpleStringProperty(cpsVille);
	}
	
	public Campus(Integer id, String nom) {
		super(id, nom);
	}

	public String getCpsAdresse() {
		return cpsAdresse.get();
	}

	public void setCpsAdresse(String cpsAdresse) {
		this.cpsAdresse.set(cpsAdresse);
	}

	public Integer getCpsCp() {
		return cpsCp.get();
	}

	public void setCpsCp(Integer cpsCp) {
		this.cpsCp.set(cpsCp);
	}

	public String getCpsVille() {
		return cpsVille.get();
	}

	public void setCpsVille(String cpsVille) {
		this.cpsVille.set(cpsVille);
	}

}