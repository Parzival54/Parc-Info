package structure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Structure {

	private IntegerProperty id;
	private StringProperty reference;
	private StringProperty nom;

	public Structure() {
	}
	
	public Structure(Integer id, String reference, String nom){
		this.id = new SimpleIntegerProperty(id);
		this.reference = new SimpleStringProperty(reference);
		this.nom = new SimpleStringProperty(nom);
	}
	
	public Structure(Integer id, String nom){
		this.id = new SimpleIntegerProperty(id);
		this.nom = new SimpleStringProperty(nom);
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

}