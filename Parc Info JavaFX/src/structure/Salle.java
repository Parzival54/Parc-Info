package structure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Salle extends Structure {

	private IntegerProperty salNumero;
	
	public Salle() {
	}

	public Salle(Integer salId, String salReference, String salNom, Integer salNumero) {
		super(salId, salReference, salNom);
		this.salNumero = new SimpleIntegerProperty(salNumero);
	}
	
	public Salle(Integer salId, String salNom) {
		super(salId, salNom);
	}

	public Integer getSalNumero() {
		return salNumero.get();
	}

	public void setSal_numero(Integer salNumero) {
		this.salNumero.set(salNumero);
	}
	
	public IntegerProperty salNumeroProperty(){
		return salNumero;
	}

}