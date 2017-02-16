package composants;

import javax.validation.constraints.Min;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CarteMere extends Composant {

	@Min(value = 0, message = "le nombre de slots mémoire ne doit pas être négatif")
	private IntegerProperty cmNbslots;

	public CarteMere() {
	}

	public CarteMere(Integer id, Integer compId, String fabricant, String type, String reference,
			String nom, String numSerie, Integer cmNbslots) {
		super(id, compId, fabricant, type, reference, nom, numSerie);
		this.cmNbslots = new SimpleIntegerProperty(cmNbslots);
	}

	public Integer getCmNbslots() {
		return cmNbslots.get();
	}

	public void setCmNbslots(Integer cmNbslots) {
		this.cmNbslots.set(cmNbslots);
	}
	
	public IntegerProperty cmNbslotsProperty(){
		return cmNbslots;
	}

}