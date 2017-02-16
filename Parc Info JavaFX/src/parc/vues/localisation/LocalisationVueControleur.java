package parc.vues.localisation;

import java.sql.SQLException;
import java.text.ParseException;

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
import parc.Principale;
import structure.Batiment;
import structure.Campus;
import structure.Localisation;
import structure.Salle;

@SuppressWarnings("unchecked")
public class LocalisationVueControleur {
	
	@FXML
	private TableView<Localisation> localisationTable;
	@FXML
	private TableColumn<Localisation, Integer> idColonne;
	@FXML
	private TableColumn<Localisation, String> nomColonne;
	@FXML
	private Label idLabel;
	@FXML
	private Label nomLabel;
	@FXML
	private Label campusLabel;
	@FXML
	private Label batimentLabel;
	@FXML
	private Label salleLabel;
	@FXML
	private Button retourMenu;
	
	private Principale principale;
	
	private DAO<Localisation> dao = DaoFactory.creerDao(Classe.LOCALISATION);
	private DAO<Campus> daoCampus = DaoFactory.creerDao(Classe.CAMPUS);
	private DAO<Batiment> daoBatiment = DaoFactory.creerDao(Classe.BATIMENT);
	private DAO<Salle> daoSalle = DaoFactory.creerDao(Classe.SALLE);
	
	public LocalisationVueControleur(){
	}
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().locIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().locNomProperty());
		
		afficherLocalisationDetails(null);
		
		localisationTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> afficherLocalisationDetails(newValue));
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		localisationTable.setItems(principale.getLocalisationData());
	}

	private void afficherLocalisationDetails(Localisation localisation) {
		if (localisation != null){
			idLabel.setText(Integer.toString(localisation.getLocId()));
			nomLabel.setText(localisation.getLocNom());
			if (localisation.getCpsId() > 0){
				Campus campus = null;
				try {
					campus = daoCampus.select(localisation.getCpsId());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				campusLabel.setText(campus.getNom());
			}
			if (localisation.getBatId() > 0){
				Batiment batiment = null;
				try {
					batiment = daoBatiment.select(localisation.getBatId());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				batimentLabel.setText(batiment.getNom());
			}
			if (localisation.getSalId() > 0){
				Salle salle = null;
				try {
					salle = daoSalle.select(localisation.getSalId());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				salleLabel.setText(salle.getNom());
			}
		} else {
			idLabel.setText("");
			nomLabel.setText("");
			campusLabel.setText("");
			batimentLabel.setText("");
			salleLabel.setText("");
		}
		
	}
	
	@FXML
	private void handleSuppressionLocalisation() throws SQLException{
		int selectedIndex = localisationTable.getSelectionModel().getSelectedIndex();
		Localisation localisation = localisationTable.getSelectionModel().getSelectedItem();
		if (selectedIndex >= 0){
			localisationTable.getItems().remove(selectedIndex);
			dao.supprimer(localisation);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(principale.getPrimaryStage());
			alert.setTitle("Selection vide");
			alert.setHeaderText("Aucune localisation sélectionnée");
			alert.setContentText("Veuillez sélectionner une localisation");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNouvelleLocalisation() throws SQLException, ParseException{
		Localisation localisation = new Localisation();
		boolean clickOK = principale.affichageLocalisationEdit(localisation, 1);
		if (clickOK){
			principale.getLocalisationData().add(localisation);
			dao.creer(localisation);
		}
	}
	
	@FXML
	private void handleEditLocalisation() throws SQLException, ParseException{
		Localisation localisation = localisationTable.getSelectionModel().getSelectedItem();
		if (localisation != null){
			boolean clickOK = principale.affichageLocalisationEdit(localisation, 0);
			if (clickOK){
				afficherLocalisationDetails(localisation);
				dao.modifier(localisation);
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
