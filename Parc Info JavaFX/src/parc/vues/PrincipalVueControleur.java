package parc.vues;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import parc.Principale;

public class PrincipalVueControleur {
	
	private Principale principale;
	
	public PrincipalVueControleur(){
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	@FXML
	private void initialize(){
	}
	
	@FXML
	public void handleFermer(){
		System.exit(0);
	}
	
	@FXML
	public void handleAPropos(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("A propos");
		alert.setHeaderText("Gestion Parc Info");
		alert.setContentText("Version 1.0 (29/11/2016)");
		alert.showAndWait();
	}
	
	@FXML
	public void handleStatistiques(){
		principale.affichageStatistiques();
	}

}
