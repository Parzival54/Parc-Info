package composants;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Processeur extends Composant {

	private IntegerProperty procFrequence;
	private IntegerProperty procNbCoeurs;

	public Processeur() {
	}

	public Processeur(Integer id, Integer compId, String fabricant, String type, String reference,
			String nom, String numSerie, Integer procFrequence, Integer procNbCoeurs) {
		super(id, compId, fabricant, type, reference, nom, numSerie);
		this.procFrequence = new SimpleIntegerProperty(procFrequence);
		this.procNbCoeurs = new SimpleIntegerProperty(procNbCoeurs);
	}

	public Integer getProcFrequence() {
		return procFrequence.get();
	}

	public void setProcFrequence(Integer procFrequence) {
		this.procFrequence.set(procFrequence);
	}
	
	public IntegerProperty procFrequenceProperty(){
		return procFrequence;
	}

	public Integer getProcNbCoeurs() {
		return procNbCoeurs.get();
	}

	public void setProcNbCoeurs(Integer procNbCoeurs) {
		this.procNbCoeurs.set(procNbCoeurs);
	}
	
	public IntegerProperty procNbCoeursProperty(){
		return procNbCoeurs;
	}

}