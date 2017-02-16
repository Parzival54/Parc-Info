package composants;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MemoireVive extends Composant {

	private IntegerProperty mvCapacite;

	public MemoireVive() {
	}

	public MemoireVive(Integer id, Integer compId, String fabricant, String type, String reference,
			String nom, String numSerie, Integer mvCapacite) {
		super(id, compId, fabricant, type, reference, nom, numSerie);
		this.mvCapacite = new SimpleIntegerProperty(mvCapacite);
	}

	public Integer getMvCapacite() {
		return mvCapacite.get();
	}

	public void setMvCapacite(Integer mvCapacite) {
		this.mvCapacite.set(mvCapacite);
	}
	
	public IntegerProperty mvCapaciteProperty(){
		return mvCapacite;
	}

}