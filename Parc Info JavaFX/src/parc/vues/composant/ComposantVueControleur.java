package parc.vues.composant;

import java.sql.SQLException;
import java.text.ParseException;

import composants.CarteMere;
import composants.Composant;
import composants.DisqueDur;
import composants.MemoireVive;
import composants.Os;
import composants.Processeur;
import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import dao.MachineDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import machine.Machine;
import parc.Principale;

@SuppressWarnings("unchecked")
public class ComposantVueControleur {
	
	private final String DISQUEDUR = "DisqueDur";
	private final String MEMOIREVIVE = "MemoireVive";
	private final String CARTEMERE = "CarteMere";
	private final String PROCESSEUR = "Processeur";
	private final String OS = "Os";
	
	@FXML
	private TableView<Composant> composantTable;
	@FXML
	private TableColumn<Composant, String> typeColonne;
	@FXML
	private TableColumn<Composant, String> nomColonne;
	@FXML
	private TableColumn<Composant, String> machineColonne;
	@FXML
	private Label idLabel;
	@FXML
	private Label typeLabel;
	@FXML
	private Label listeComposantsLabel;
	@FXML
	private Label fabricantLabel;
	@FXML
	private Label referenceLabel;
	@FXML
	private Label nomLabel;
	@FXML
	private Label numSerieLabel;
	@FXML
	private Label machineLabel;
	@FXML
	private Label extra1NomLabel;
	@FXML
	private Label extra2NomLabel;
	@FXML
	private Label extra1Label;
	@FXML
	private Label extra2Label;
	@FXML
	private TextField typeField;
	@FXML
	private Button retourMenu;
	
	private Principale principale;
	
	private DAO<CarteMere> daoCarteMere = DaoFactory.creerDao(Classe.CARTEMERE);
	private DAO<DisqueDur> daoDisqueDur = DaoFactory.creerDao(Classe.DISQUEDUR);
	private DAO<MemoireVive> daoMemoireVive = DaoFactory.creerDao(Classe.MEMOIREVIVE);
	private DAO<Os> daoOs = DaoFactory.creerDao(Classe.OS);
	private DAO<Processeur> daoProcesseur = DaoFactory.creerDao(Classe.PROCESSEUR);
	private MachineDao daoMachine = new MachineDao(Machine.class);
	
	
	@FXML
	private void initialize() {
		typeColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		machineColonne.setCellValueFactory(cellData -> {
			if (cellData.getValue().getMaNom() != null){
				return cellData.getValue().maNomProperty();					
			} else {
				return new SimpleStringProperty("En stock");
			}
		});
		extra1NomLabel.setText("");
		extra2NomLabel.setText("");
		extra1Label.setText("");
		extra2Label.setText("");
		
		afficherComposantDetails(null);
		
		composantTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> afficherComposantDetails(newValue));
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		composantTable.setItems(principale.getComposantData());
	}
	
	private void afficherComposantDetails(Composant composant) {
		extra1NomLabel.setText("");
		extra2NomLabel.setText("");
		extra1Label.setText("");
		extra2Label.setText("");
		if (composant != null){
			idLabel.setText(Integer.toString(composant.getId()));
			typeLabel.setText(composant.getType());
			listeComposantsLabel.setText(Integer.toString(composant.getCompId()));
			fabricantLabel.setText(composant.getFabricant());
			referenceLabel.setText(composant.getReference());
			nomLabel.setText(composant.getNom());
			numSerieLabel.setText(composant.getNumSerie());
			machineLabel.setText(daoMachine.selectIdToNom(composant.getCompId()));
			switch (composant.getClass().getSimpleName()) {
			case DISQUEDUR:
				DisqueDur dd = (DisqueDur) composant;
				extra1NomLabel.setText("Capacité");
				extra2NomLabel.setText("Connectique");
				extra1Label.setText(Integer.toString(dd.getDdCapacite()));
				extra2Label.setText(dd.getDdConnectique());
				break;
			case MEMOIREVIVE:
				MemoireVive mv = (MemoireVive) composant;
				extra1NomLabel.setText("Capacité");
				extra1Label.setText(Integer.toString(mv.getMvCapacite()));
				break;
			case CARTEMERE:
				CarteMere cm = (CarteMere) composant;
				extra1NomLabel.setText("Nombre de slots mémoire");
				extra1Label.setText(Integer.toString(cm.getCmNbslots()));
				break;
			case PROCESSEUR:
				Processeur proc = (Processeur) composant;
				extra1NomLabel.setText("Fréquence");
				extra2NomLabel.setText("Nombre de coeurs");
				extra1Label.setText(Integer.toString(proc.getProcFrequence()));
				extra2Label.setText(Integer.toString(proc.getProcNbCoeurs()));
				break;
			case OS:
				Os os = (Os) composant;
				extra1NomLabel.setText("Version");
				extra1Label.setText(os.getOsVersion());
				break;
			default:
				break;
			}
		} else {
			idLabel.setText("");
			nomLabel.setText("");
			typeLabel.setText("");
			listeComposantsLabel.setText("");
			fabricantLabel.setText("");
			referenceLabel.setText("");
			nomLabel.setText("");
			numSerieLabel.setText("");
			machineLabel.setText("");
		}
		
	}
	
	@FXML
	private void handleSuppressionComposant() throws SQLException{
		int selectedIndex = composantTable.getSelectionModel().getSelectedIndex();
		Composant composant = composantTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			composantTable.getItems().remove(selectedIndex);
			switch (composant.getClass().getSimpleName()) {
			case DISQUEDUR:
				DisqueDur disqueDur = (DisqueDur) composant;
				daoDisqueDur.supprimer(disqueDur);
				break;
			case MEMOIREVIVE:
				daoMemoireVive.supprimer((MemoireVive) composant);
				break;
			case CARTEMERE:
				daoCarteMere.supprimer((CarteMere) composant);
				break;
			case PROCESSEUR:
				daoProcesseur.supprimer((Processeur) composant);
				break;
			case OS:
				daoOs.supprimer((Os) composant);
				break;
			default:
				break;
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucun composant sélectionné");
			alert.setContentText("Veuillez sélectionner un composant");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouveauComposant() throws SQLException, ParseException{
		Composant composant = null;
		boolean clickOK = principale.affichageComposantEdit(composant, 1);
		if (clickOK){
			principale.getComposantData().add(composant);
			switch (typeField.getText()) {
			case DISQUEDUR:
				daoDisqueDur.creer((DisqueDur) composant);
				break;
			case MEMOIREVIVE:
				daoMemoireVive.creer((MemoireVive) composant);
				break;
			case CARTEMERE:
				daoCarteMere.creer((CarteMere) composant);
				break;
			case PROCESSEUR:
				daoProcesseur.creer((Processeur) composant);
				break;
			case OS:
				daoOs.creer((Os) composant);
				break;
			default:
				break;
			}
		}
	}
	
	@FXML
	private void handleEditComposant() throws SQLException, ParseException {
		Composant composant = composantTable.getSelectionModel().getSelectedItem();
		if (composant != null){
			boolean clickOK = principale.affichageComposantEdit(composant, 0);
			if (clickOK){
				afficherComposantDetails(composant);
				switch (typeField.getText()) {
				case DISQUEDUR:
					daoDisqueDur.modifier((DisqueDur) composant);
					break;
				case MEMOIREVIVE:
					daoMemoireVive.modifier((MemoireVive) composant);
					break;
				case CARTEMERE:
					daoCarteMere.modifier((CarteMere) composant);
					break;
				case PROCESSEUR:
					daoProcesseur.modifier((Processeur) composant);
					break;
				case OS:
					daoOs.modifier((Os) composant);
					break;
				default:
					break;
				}
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
