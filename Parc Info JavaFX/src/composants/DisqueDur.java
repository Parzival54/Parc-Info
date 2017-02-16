package composants;

import javax.validation.constraints.Min;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DisqueDur extends Composant {

	@Min(value = 0, message = "La capacité ne doit pas être négative")
	private IntegerProperty ddCapacite;
	private StringProperty ddConnectique;

	public DisqueDur() {
	}

	public DisqueDur(Integer id, Integer compId, String fabricant, String type, String reference,
			String nom, String numSerie, int ddCapacite, String ddConnectique) {
		super(id, compId, fabricant, type, reference, nom, numSerie);
		this.ddCapacite = new SimpleIntegerProperty(ddCapacite);
		this.ddConnectique = new SimpleStringProperty(ddConnectique);
	}

	public Integer getDdCapacite() {
		return ddCapacite.get();
	}

	public void setDdCapacite(Integer ddCapacite) {
		this.ddCapacite.set(ddCapacite);
	}
	
	public IntegerProperty ddCapaciteProperty(){
		return ddCapacite;
	}

	public String getDdConnectique() {
		return ddConnectique.get();
	}

	public void setDdConnectique(String ddConnectique) {
		this.ddConnectique.set(ddConnectique);
	}
	
	public StringProperty ddConnectiqueProperty(){
		return ddConnectique;
	}

}