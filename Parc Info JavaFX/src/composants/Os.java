package composants;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Os extends Composant {

	private StringProperty osVersion;

	public Os() {
	}

	public Os(Integer id, Integer compId, String fabricant, String type, String reference,
			String nom, String numSerie, String osVersion) {
		super(id, compId, fabricant, type, reference, nom, numSerie);
		this.osVersion = new SimpleStringProperty(osVersion);
	}

	public String getOsVersion() {
		return osVersion.get();
	}

	public void setOsVersion(String osVersion) {
		this.osVersion.set(osVersion);
	}
	
	public StringProperty osVersionProperty(){
		return osVersion;
	}

}