package parc.vues.machine;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import composants.ListeComposants;
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
import machine.Machine;
import machine.Stagiaire;
import parc.Principale;
import structure.Localisation;

@SuppressWarnings("unchecked")
public class MachineVueControleur {
	
	@FXML
	private TableView<Machine> machineTable;
	@FXML
	private TableColumn<Machine, String> nomColonne;
	@FXML
	private TableColumn<Machine, String> numAfpaColonne;
	
	@FXML
	private Label idLabel;
	@FXML
	private Label nomLabel;
	@FXML
	private Label stgIdLabel;
	@FXML
	private Label locIdLabel;
	@FXML
	private Label compIdLabel;
	@FXML
	private Label numAfpaLabel;
	@FXML
	private Label dateAchatLabel;
	@FXML
	private Label dureeGarantieLabel;
	@FXML
	private Label ipLabel;
	@FXML
	private Button retourMenu;
	
	private Principale principale;
	
	private DAO<Machine> daoMachine = DaoFactory.creerDao(Classe.MACHINE);
	private DAO<Stagiaire> daoStagiaire = DaoFactory.creerDao(Classe.STAGIAIRE);
	private DAO<Localisation> daoLocalisation = DaoFactory.creerDao(Classe.LOCALISATION);
	
	public MachineVueControleur(){
	}
	
	@FXML
	private void initialize(){
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().maNomProperty());
		numAfpaColonne.setCellValueFactory(cellData -> cellData.getValue().maNumAfpaProperty());
		
		afficherMachineDetails(null);
		
		machineTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> afficherMachineDetails(newValue));

	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		machineTable.setItems(principale.getMachineData());
	}
	
	private void afficherMachineDetails(Machine machine){
		if (machine != null){
			idLabel.setText(Integer.toString(machine.getMaId()));
			nomLabel.setText(machine.getMaNom());
			stgIdLabel.setText(Integer.toString(machine.getStgId()));
			locIdLabel.setText(Integer.toString(machine.getLocId()));
			compIdLabel.setText(Integer.toString(machine.getCompId()));
			numAfpaLabel.setText(machine.getMaNumAfpa());
			dateAchatLabel.setText(new SimpleDateFormat("dd/MM/yyyy").format(machine.getMaDateAchat()));
			dureeGarantieLabel.setText(Integer.toString(machine.getMaDureeGarantie()));
			ipLabel.setText(machine.getMaIP());
		} else {
			idLabel.setText("");
			nomLabel.setText("");
			stgIdLabel.setText("");
			locIdLabel.setText("");
			compIdLabel.setText("");
			numAfpaLabel.setText("");
			dateAchatLabel.setText("");
			dureeGarantieLabel.setText("");
			ipLabel.setText("");
		}
			
	}
	
	@FXML
	private void handleSuppressionMachine() throws SQLException{
		int selectedIndex = machineTable.getSelectionModel().getSelectedIndex();
		Machine machine = machineTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			machineTable.getItems().remove(selectedIndex);
			daoMachine.supprimer(machine);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune machine sélectionnée");
			alert.setContentText("Veuillez sélectionner une machine");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouvelleMachine() throws SQLException, ParseException {
		Machine machine = new Machine();
		boolean clickOK = principale.affichageMachineEdit(machine, 1);
		if (clickOK){
			principale.getMachineData().add(machine);
			daoMachine.creer(machine);
		}
	}
	
	@FXML
	private void handleEditMachine() throws SQLException, ParseException {
		Machine machine = machineTable.getSelectionModel().getSelectedItem();
		if (machine != null){
			boolean clickOK = principale.affichageMachineEdit(machine, 0);
			if (clickOK){
				afficherMachineDetails(machine);
				daoMachine.modifier(machine);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucun machine sélectionnée");
			alert.setContentText("Veuillez sélectionner une machine");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleDetailsStagiaire() throws SQLException, ParseException{
		int selectedIndex = machineTable.getSelectionModel().getSelectedIndex();
		Machine machine = machineTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			if (machine.getStgId() > 0){
				Stagiaire stagiaire = daoStagiaire.select(machine.getStgId());
				principale.affichageStagiaireDetails(stagiaire);				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(principale.getPrimaryStage());
				alert.setTitle("Machine sans stagiaire");
				alert.setHeaderText("Aucun détail à afficher");
				alert.setContentText("Cette machine n'est pas affectée à un stagiaire");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune machine sélectionnée");
			alert.setContentText("Veuillez sélectionner une machine");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleDetailsLocalisation() throws SQLException, ParseException {
		int selectedIndex = machineTable.getSelectionModel().getSelectedIndex();
		Machine machine = machineTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			if (machine.getLocId() > 0){
				Localisation localisation = daoLocalisation.select(machine.getLocId());
				principale.affichageLocalisationDetails(localisation);				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(principale.getPrimaryStage());
				alert.setTitle("Machine sans localisation");
				alert.setHeaderText("Aucun détail à afficher");
				alert.setContentText("Cette machine n'est pas affectée à une localisation");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune machine sélectionnée");
			alert.setContentText("Veuillez sélectionner une machine");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleDetailsComposants() throws SQLException, ParseException {
		int selectedIndex = machineTable.getSelectionModel().getSelectedIndex();
		Machine machine = machineTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			if (machine.getCompId() > 0){
				principale.affichageListeComposantsDetails(machine.getCompId());				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(principale.getPrimaryStage());
				alert.setTitle("Machine sans composants");
				alert.setHeaderText("Aucun détail à afficher");
				alert.setContentText("Cette machine n'est pas affectée à une liste de composants");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune machine sélectionnée");
			alert.setContentText("Veuillez sélectionner une machine");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void retourMenuAction(){
		principale.affichageMenuVue();
	}

}
