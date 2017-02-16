package parc.vues;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.fxml.FXML;
import parc.Principale;

public class MenuVueControleur {
	
	private Principale principale;
	
	public MenuVueControleur(){
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	@FXML
	private void initialize(){
	}
	
	@FXML
	public void handleStagiaire() throws SQLException, ParseException {
		principale.affichageStagiaireVue();
	}
	
	@FXML
	public void handleMachine() throws SQLException, ParseException {
		principale.affichageMachineVue();
	}
	
	@FXML
	public void handleFormation() throws SQLException, ParseException {
		principale.affichageFormationVue();
	}
	
	@FXML
	public void handleLocalisation() throws SQLException, ParseException {
		principale.affichageLocalisationVue();
	}
	
	@FXML
	public void handleComposant() throws SQLException, ParseException {
		principale.affichageComposantVue();
	}

}
