package parc.vues.composant;

import composants.Composant;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import parc.Principale;

public class ListeComposantsDetailsControleur {
	
	@FXML
	private TableView<Composant> composantsTable;
	@FXML
	private TableColumn<Composant, String> typeColonne;
	@FXML
	private TableColumn<Composant, String> fabricantColonne;
	@FXML
	private TableColumn<Composant, String> referenceColonne;
	@FXML
	private TableColumn<Composant, String> nomColonne;
	@FXML
	private TableColumn<Composant, String> numSerieColonne;
	
	private Stage detailsStage;
	private Principale principale;
	
	@FXML
	private void initialize(){
		typeColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
		fabricantColonne.setCellValueFactory(cellData -> cellData.getValue().fabricantProperty());
		referenceColonne.setCellValueFactory(cellData -> cellData.getValue().referenceProperty());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		numSerieColonne.setCellValueFactory(cellData -> cellData.getValue().numSerieProperty());
	}
	
	public void setDetailsStage(Stage detailsStage){
		this.detailsStage = detailsStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		composantsTable.setItems(principale.getComposantData());
	}
	
	@FXML
	public void handleOK(){
			detailsStage.close();
	}

}
