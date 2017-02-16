package parc.vues.formation;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import machine.Formation;
import parc.Principale;

public class FormationVueControleur {
	
	@FXML
	private TableView<Formation> formationTable;
	@FXML
	private TableColumn<Formation, Integer> idColonne;
	@FXML
	private TableColumn<Formation, String> nomColonne;
	@FXML
	private Label idLabel;
	@FXML
	private Label nomLabel;
	@FXML
	private Label dateDebutLabel;
	@FXML
	private Label dateFinLabel;
	@FXML
	private Button retourMenu;
	
	private Principale principale;
	
	@SuppressWarnings("unchecked")
	private DAO<Formation> dao = DaoFactory.creerDao(Classe.FORMATION);
	
	public FormationVueControleur(){
	}
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().foIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().foNomProperty());
		
		afficherFormationDetails(null);
		
		formationTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> afficherFormationDetails(newValue));
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		formationTable.setItems(principale.getFormationData());
	}

	private void afficherFormationDetails(Formation formation) {
		if (formation != null){
			idLabel.setText(Integer.toString(formation.getFoId()));
			nomLabel.setText(formation.getFoNom());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateDebutLabel.setText(sdf.format(formation.getFoDatedebut()));
			dateFinLabel.setText(sdf.format(formation.getFoDatefin()));			
		} else {
			idLabel.setText("");
			nomLabel.setText("");
			dateDebutLabel.setText("");
			dateFinLabel.setText("");
		}
		
	}
	
	@FXML
	private void handleSuppressionFormation() throws SQLException{
		int selectedIndex = formationTable.getSelectionModel().getSelectedIndex();
		Formation formation = formationTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			formationTable.getItems().remove(selectedIndex);
			dao.supprimer(formation);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune formation sélectionnée");
			alert.setContentText("Veuillez sélectionner une formation");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouvelleFormation() throws SQLException, ParseException{
		Formation formation = new Formation();
		boolean clickOK = principale.affichageFormationEdit(formation, 1);
		if (clickOK){
			principale.getFormationData().add(formation);
			dao.creer(formation);
		}
	}
	
	@FXML
	private void handleEditFormation() throws SQLException, ParseException{
		Formation formation = formationTable.getSelectionModel().getSelectedItem();
		if (formation != null){
			boolean clickOK = principale.affichageFormationEdit(formation, 0);
			if (clickOK){
				afficherFormationDetails(formation);
				dao.modifier(formation);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune formation sélectionnée");
			alert.setContentText("Veuillez sélectionner une formation");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void retourMenuAction(){
		principale.affichageMenuVue();
	}
}
