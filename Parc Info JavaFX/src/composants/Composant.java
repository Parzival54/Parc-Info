package composants;

import java.sql.SQLException;

import dao.MachineDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import machine.Machine;

public abstract class Composant {

	private IntegerProperty id;
	private IntegerProperty compId;
	private StringProperty fabricant;
	private StringProperty type;
	private StringProperty reference;
	private StringProperty nom;
	private StringProperty numSerie;
	
	public Composant() {
	}

	public Composant(Integer id, Integer compId, String fabricant, String type,
			String reference, String nom, String numSerie) {
		this.id = new SimpleIntegerProperty(id);
		this.compId = new SimpleIntegerProperty(compId);
		this.fabricant = new SimpleStringProperty(fabricant);
		this.type = new SimpleStringProperty(type);
		this.reference = new SimpleStringProperty(reference);
		this.nom = new SimpleStringProperty(nom);
		this.numSerie = new SimpleStringProperty(numSerie);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public IntegerProperty idProperty(){
		return id;
	}

	public Integer getCompId() {
		return compId.get();
	}

	public void setCompId(Integer compId) {
		this.compId.set(compId);
	}
	
	public IntegerProperty compIdProperty(){
		return compId;
	}

	public String getFabricant() {
		return fabricant.get();
	}

	public void setFabricant(String fabricant) {
		this.fabricant.set(fabricant);
	}
	
	public StringProperty fabricantProperty(){
		return fabricant;
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}
	
	public StringProperty typeProperty(){
		return type;
	}

	public String getReference() {
		return reference.get();
	}

	public void setReference(String reference) {
		this.reference.set(reference);
	}
	
	public StringProperty referenceProperty(){
		return reference;
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
	public StringProperty nomProperty(){
		return nom;
	}

	public String getNumSerie() {
		return numSerie.get();
	}

	public void setNumSerie(String numSerie) {
		this.numSerie.set(numSerie);
	}
	
	public StringProperty numSerieProperty(){
		return numSerie;
	}
	
	public String getMaNom(){
		MachineDao daoMachine = new MachineDao(Machine.class);
		String nomMachine = null;
		nomMachine = daoMachine.selectIdToNom(getCompId());
		return nomMachine;
	}
	
	public StringProperty maNomProperty() {
		MachineDao daoMachine = new MachineDao(Machine.class);
		String nomMachine = null;
		nomMachine = daoMachine.selectIdToNom(getCompId());
		return new SimpleStringProperty(nomMachine);
		
	}

}