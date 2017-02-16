package parc.vues.stagiaire;

import java.sql.SQLException;
import java.text.ParseException;

import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import machine.Formation;
import machine.Stagiaire;
import parc.Principale;

@SuppressWarnings("unchecked")
public class StagiaireVueControleur {
	
	@FXML
	private TableView<Stagiaire> stagiaireTable;
	@FXML
	private TableColumn<Stagiaire, String> stgNomColonne;
	@FXML
	private TableColumn<Stagiaire, String> stgPrenomColonne;
	
	@FXML
	private Label nomLabel;
	@FXML
	private Label stgPrenomLabel;
	@FXML
	private Label stgIdLabel;
	@FXML
	private Label maIdLabel;
	@FXML
	private Label foIdLabel;
	@FXML
	private Label stgAdresseLabel;
	@FXML
	private Label stgNumtelLabel;
	@FXML
	private Button retourMenu;
	
	private Principale principale;
	
	
	private DAO<Stagiaire> daoStagiaire = DaoFactory.creerDao(Classe.STAGIAIRE);
	private DAO<Formation> daoFormation = DaoFactory.creerDao(Classe.FORMATION);
	
	public StagiaireVueControleur(){
	}
	
	@FXML
	private void initialize(){
		stgNomColonne.setCellValueFactory(cellData -> cellData.getValue().stgNomProperty());
		stgPrenomColonne.setCellValueFactory(cellData -> cellData.getValue().stgPrenomProperty());
		
		afficherStagiaireDetails(null);
		
		stagiaireTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> afficherStagiaireDetails(newValue));

	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		stagiaireTable.setItems(principale.getStagiaireData());
	}
	
	private void afficherStagiaireDetails(Stagiaire stagiaire){
		if (stagiaire != null){
			nomLabel.setText(stagiaire.getStgNom());
			stgPrenomLabel.setText(stagiaire.getStgPrenom());
			stgIdLabel.setText(Integer.toString(stagiaire.getStgId()));
			maIdLabel.setText(Integer.toString(stagiaire.getMaId()));
			if (stagiaire.getFoId() > 0) {
				Formation formation = null;
				try {
					formation = daoFormation.select(stagiaire.getFoId());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				foIdLabel.setText(formation.getFoNom());				
			}
			stgAdresseLabel.setText(stagiaire.getStgAdresse());
			stgNumtelLabel.setText(Integer.toString(stagiaire.getStgNumtel()));
		} else {
			nomLabel.setText("");
			stgPrenomLabel.setText("");
			stgIdLabel.setText("");
			maIdLabel.setText("");
			foIdLabel.setText("");
			stgAdresseLabel.setText("");
			stgNumtelLabel.setText("");
		}
			
	}
	
	@FXML
	private void handleSuppressionStagiaire() throws SQLException{
		int selectedIndex = stagiaireTable.getSelectionModel().getSelectedIndex();
		Stagiaire stagiaire = stagiaireTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			stagiaireTable.getItems().remove(selectedIndex);
			daoStagiaire.supprimer(stagiaire);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucun stagiaire sélectionné");
			alert.setContentText("Veuillez sélectionner un stagiaire");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouveauStagiaire() throws SQLException, ParseException{
		Stagiaire stagiaire = new Stagiaire();
		boolean clickOK = principale.affichageStagiaireEdit(stagiaire, 1);
		if (clickOK){
			principale.getStagiaireData().add(stagiaire);
			daoStagiaire.creer(stagiaire);
		}
	}
	
	@FXML
	private void handleEditStagiaire() throws SQLException, ParseException{
		Stagiaire stagiaire = stagiaireTable.getSelectionModel().getSelectedItem();
		if (stagiaire != null){
			boolean clickOK = principale.affichageStagiaireEdit(stagiaire, 0);
			if (clickOK){
				afficherStagiaireDetails(stagiaire);
				daoStagiaire.modifier(stagiaire);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucun stagiaire sélectionné");
			alert.setContentText("Veuillez sélectionner un stagiaire");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void retourMenuAction(){
		principale.affichageMenuVue();
	}

}
