package structure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Batiment extends Structure {

	private IntegerProperty batNumero;

	public Batiment() {
	}

	public Batiment(Integer batId, String batReference, String batNom, Integer batNumero) {
		super(batId, batReference, batNom);
		this.batNumero = new SimpleIntegerProperty(batNumero);
	}
	
	public Batiment(Integer batId, String batNom) {
		super(batId, batNom);
	}

	public Integer getBatNumero() {
		return batNumero.get();
	}

	public void setBatNumero(Integer batNumero) {
		this.batNumero.set(batNumero);
	}
	
	public IntegerProperty batNumeroProperty(){
		return batNumero;
	}

}