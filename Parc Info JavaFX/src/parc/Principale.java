package parc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import dao.CarteMereDao;
import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import dao.DisqueDurDao;
import dao.MemoireViveDao;
import dao.OsDao;
import dao.ProcesseurDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import machine.Formation;
import machine.Machine;
import machine.Stagiaire;
import parc.vues.MenuVueControleur;
import parc.vues.PrincipalVueControleur;
import parc.vues.StatistiquesControleur;
import parc.vues.composant.ComposantEditControleur;
import parc.vues.composant.ComposantVueControleur;
import parc.vues.composant.ListeComposantsDetailsControleur;
import parc.vues.composant.liste.ListeComposantVueControleur;
import parc.vues.formation.FormationEditControleur;
import parc.vues.formation.FormationVueControleur;
import parc.vues.formation.ListeFormationsVueControleur;
import parc.vues.localisation.ListeLocalisationsVueControleur;
import parc.vues.localisation.LocalisationDetailsControleur;
import parc.vues.localisation.LocalisationEditControleur;
import parc.vues.localisation.LocalisationVueControleur;
import parc.vues.machine.ListeMachinesVueControleur;
import parc.vues.machine.MachineEditControleur;
import parc.vues.machine.MachineVueControleur;
import parc.vues.stagiaire.ListeStagiairesVueControleur;
import parc.vues.stagiaire.StagiaireDetailsControleur;
import parc.vues.stagiaire.StagiaireEditControleur;
import parc.vues.stagiaire.StagiaireVueControleur;
import parc.vues.structure.ListeStructuresVueControleur;
import structure.Batiment;
import structure.Campus;
import structure.Localisation;
import structure.Salle;
import structure.Structure;

import java.util.ArrayList;
import java.util.List;

import composants.CarteMere;
import composants.Composant;
import composants.DisqueDur;
import composants.ListeComposants;
import composants.MemoireVive;
import composants.Os;
import composants.Processeur;

@SuppressWarnings("unchecked")
public class Principale extends Application {

	private Stage primaryStage;
	private BorderPane vuePrincipale;
	private ObservableList<Stagiaire> stagiaireData = FXCollections.observableArrayList();
	private ObservableList<Formation> formationData = FXCollections.observableArrayList();
	private ObservableList<Machine> machineData = FXCollections.observableArrayList();
	private ObservableList<Localisation> localisationData = FXCollections.observableArrayList();
	private ObservableList<Structure> structureData = FXCollections.observableArrayList();
	private ObservableList<Composant> composantData = FXCollections.observableArrayList();
	private ObservableList<ListeComposants> listeComposantsData = FXCollections.observableArrayList();
	private final String DISQUEDUR = "DisqueDur";
	private final String MEMOIREVIVE = "MemoireVive";
	private final String CARTEMERE = "CarteMere";
	private final String PROCESSEUR = "Processeur";
	private final String OS = "Os";
	private int foId;
	private int maId;
	private int stgId;
	private int locId;
	private int cpsId;
	private int batId;
	private int salId;
	private int compId;
	private String foNom;
	private String cpsNom;
	private String batNom;
	private String salNom;
	private String locNom;

	public ObservableList<Stagiaire> getStagiaireData() {
		return stagiaireData;
	}
	
	public ObservableList<Formation> getFormationData() {
		return formationData;
	}

	public ObservableList<Machine> getMachineData() {
		return machineData;
	}
	
	public ObservableList<Localisation> getLocalisationData() {
		return localisationData;
	}
	
	public ObservableList<Structure> getStructureData() {
		return structureData;
	}
	
	public ObservableList<Composant> getComposantData() {
		return composantData;
	}
	
	public ObservableList<ListeComposants> getListeComposantsData() {
		return listeComposantsData;
	}
	
	public int getFoId(){
		return foId;
	}
	
	public int getMaId(){
		return maId;
	}
	
	public int getStgId(){
		return stgId;
	}
	
	public int getLocId(){
		return locId;
	}
	
	public int getCompId(){
		return compId;
	}
	
	public int getBatId(){
		return batId;
	}
	
	public int getCpsId(){
		return cpsId;
	}
	
	public int getSalId(){
		return salId;
	}
	
	public String getFoNom(){
		return foNom;
	}
	
	public String getCpsNom(){
		return cpsNom;
	}
	
	public String getBatNom(){
		return batNom;
	}
	
	public String getSalNom(){
		return salNom;
	}
	
	public String getLocNom(){
		return locNom;
	}
	
	public BorderPane getVuePrincipale(){
		return vuePrincipale;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("StagiaireApp");
		this.primaryStage.getIcons().add(new Image("file:resources/images/icone.png"));

		initialisationVue();
		affichageMenuVue();
	}

	public void initialisationVue() {
		try {

			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/PrincipalVue.fxml"));
			vuePrincipale = (BorderPane) loader.load();

			// Affichage de la scene contenant le layout
			Scene scene = new Scene(vuePrincipale);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			PrincipalVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void affichageMenuVue() {
		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/MenuVue.fxml"));
			AnchorPane menuVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Menu dans le Principal
			vuePrincipale.setCenter(menuVue);

			MenuVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void affichageStatistiques() {
		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/Statistiques.fxml"));
			AnchorPane statistiquesVue = (AnchorPane) loader.load();
			Stage statistiques = new Stage();
			statistiques.setTitle("Statistiques");
			statistiques.initModality(Modality.WINDOW_MODAL);
			statistiques.initOwner(primaryStage);
			Scene scene = new Scene(statistiquesVue);
			statistiques.setScene(scene);

			StatistiquesControleur controleur = loader.getController();
			controleur.setData();
			statistiques.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void affichageStagiaireVue() {

		stagiaireData.clear();
		DAO<Stagiaire> dao = DaoFactory.creerDao(Classe.STAGIAIRE);
		List<Stagiaire> stagiaires = null;

		try {
			stagiaires = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Stagiaire stagiaire : stagiaires) {
			stagiaireData.add(new Stagiaire(stagiaire.getStgNom(), stagiaire.getStgPrenom(), stagiaire.getStgId(),
					stagiaire.getMaId(), stagiaire.getFoId(), stagiaire.getStgAdresse(), stagiaire.getStgNumtel()));
		}

		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/stagiaire/StagiaireVue.fxml"));
			AnchorPane stagiaireVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Stagiaire dans le
			// Principal
			vuePrincipale.setCenter(stagiaireVue);

			StagiaireVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean affichageStagiaireEdit(Stagiaire stagiaire, int mode) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/stagiaire/StagiaireEdit.fxml"));
			AnchorPane stagiaireEdit = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			switch (mode) {
			case 0:
				editStage.setTitle("Modification Stagiaire");
				break;
			case 1:
				editStage.setTitle("Création Stagiaire");
				break;
			default:
				editStage.setTitle("???");
				break;
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(stagiaireEdit);
			editStage.setScene(scene);

			StagiaireEditControleur stagiaireEditControleur = loader.getController();
			stagiaireEditControleur.setPrincipale(this);
			stagiaireEditControleur.setEditStage(editStage);
			stagiaireEditControleur.setStagiaire(stagiaire);
			editStage.showAndWait();
			//
			return stagiaireEditControleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageStagiaireDetails(Stagiaire stagiaire) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/stagiaire/StagiaireDetails.fxml"));
			AnchorPane stagiaireDetails = (AnchorPane) loader.load();

			Stage detailsStage = new Stage();
			detailsStage.setTitle("Détails Stagiaire");
			detailsStage.initModality(Modality.WINDOW_MODAL);
			detailsStage.initOwner(primaryStage);
			Scene scene = new Scene(stagiaireDetails);
			detailsStage.setScene(scene);
			StagiaireDetailsControleur stagiaireDetailsControleur = loader.getController();
			stagiaireDetailsControleur.setPrincipale(this);
			stagiaireDetailsControleur.setDetailsStage(detailsStage);
			stagiaireDetailsControleur.setStagiaire(stagiaire);
			detailsStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
	}
	
	public boolean affichageListeStagiaires() {
		stagiaireData.clear();
		DAO<Stagiaire> dao = DaoFactory.creerDao(Classe.STAGIAIRE);
		
		List<Stagiaire> stagiaires = null;

		try {
			stagiaires = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Stagiaire stagiaire : stagiaires) {
			stagiaireData.add(new Stagiaire(stagiaire.getStgNom(), stagiaire.getStgPrenom(), stagiaire.getStgId(),
					stagiaire.getMaId(), stagiaire.getFoId(), stagiaire.getStgAdresse(), stagiaire.getStgNumtel()));
		}	
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/stagiaire/ListeStagiairesVue.fxml"));
			AnchorPane listeStagiairesVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des localisations");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeStagiairesVue);
			editStage.setScene(scene);

			ListeStagiairesVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			stgId = controleur.getStgId();
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageFormationVue() {

		formationData.clear();
		DAO<Formation> dao = DaoFactory.creerDao(Classe.FORMATION);
		List<Formation> formations = null;

		try {
			formations = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Formation formation : formations) {
			formationData.add(new Formation(formation.getFoId(), formation.getFoNom(),
					formation.getFoDatedebut(), formation.getFoDatefin()));
		}

		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/formation/FormationVue.fxml"));
			AnchorPane formationVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Formation dans le
			// Principal
			vuePrincipale.setCenter(formationVue);

			FormationVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean affichageFormationEdit(Formation formation, int mode) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/formation/FormationEdit.fxml"));
			AnchorPane formationEdit = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			switch (mode) {
			case 0:
				editStage.setTitle("Modification Formation");
				break;
			case 1:
				editStage.setTitle("Création Formation");
				break;
			default:
				editStage.setTitle("???");
				break;
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(formationEdit);
			editStage.setScene(scene);

			FormationEditControleur controleur = loader.getController();
			controleur.setEditStage(editStage);
			controleur.setFormation(formation);
			editStage.showAndWait();
			//
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean affichageListeFormations() {
		
		formationData.clear();
		DAO<Formation> dao = DaoFactory.creerDao(Classe.FORMATION);
		List<Formation> formations = null;

		try {
			formations = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Formation formation : formations) {
			formationData.add(new Formation(formation.getFoId(), formation.getFoNom(),
					formation.getFoDatedebut(), formation.getFoDatefin()));
		}

		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/formation/ListeFormationsVue.fxml"));
			AnchorPane listeFormationsVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des formations");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeFormationsVue);
			editStage.setScene(scene);

			ListeFormationsVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			foId = controleur.getFoId();
			foNom = controleur.getFoNom();
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageMachineVue() {

		machineData.clear();
		DAO<Machine> dao = DaoFactory.creerDao(Classe.MACHINE);
		List<Machine> machines = null;

		try {
			machines = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Machine machine : machines) {
			machineData.add(new Machine(machine.getMaId(), machine.getStgId(), machine.getLocId(),
					machine.getCompId(), machine.getMaNumAfpa(), machine.getMaDateAchat(),
					machine.getMaDureeGarantie(), machine.getMaIP(), machine.getMaNom()));
		}

		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/machine/MachineVue.fxml"));
			AnchorPane machineVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Stagiaire dans le
			// Principal
			vuePrincipale.setCenter(machineVue);

			MachineVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean affichageMachineEdit(Machine machine, int mode) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/machine/MachineEdit.fxml"));
			AnchorPane machineEdit = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			switch (mode) {
			case 0:
				editStage.setTitle("Modification Machine");
				break;
			case 1:
				editStage.setTitle("Création Machine");
				break;
			default:
				editStage.setTitle("???");
				break;
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(machineEdit);
			editStage.setScene(scene);

			MachineEditControleur machineEditControleur = loader.getController();
			machineEditControleur.setPrincipale(this);
			machineEditControleur.setEditStage(editStage);
			machineEditControleur.setMachine(machine);
			editStage.showAndWait();
			//
			return machineEditControleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean affichageListeMachines() {
		
		machineData.clear();
		DAO<Machine> dao = DaoFactory.creerDao(Classe.MACHINE);
		List<Machine> machines = null;

		try {
			machines = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Machine machine : machines) {
			machineData.add(new Machine(machine.getMaId(), machine.getStgId(), machine.getLocId(),
					machine.getCompId(), machine.getMaNumAfpa(), machine.getMaDateAchat(),
					machine.getMaDureeGarantie(), machine.getMaIP(), machine.getMaNom()));
		}

		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/machine/ListeMachinesVue.fxml"));
			AnchorPane listeMachinesVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des machines");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeMachinesVue);
			editStage.setScene(scene);

			ListeMachinesVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			maId = controleur.getMaId();
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageLocalisationVue() {

		localisationData.clear();
		DAO<Localisation> dao = DaoFactory.creerDao(Classe.LOCALISATION);
		List<Localisation> localisations = null;

		try {
			localisations = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Localisation localisation : localisations) {
			localisationData.add(new Localisation(localisation.getLocId(), localisation.getSalId(),
					localisation.getCpsId(), localisation.getBatId(), localisation.getLocNom()));
		}

		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/localisation/LocalisationVue.fxml"));
			AnchorPane localisationVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Localisation dans le
			// Principal
			vuePrincipale.setCenter(localisationVue);

			LocalisationVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean affichageLocalisationEdit(Localisation localisation, int mode) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/localisation/LocalisationEdit.fxml"));
			AnchorPane localisationEdit = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			switch (mode) {
			case 0:
				editStage.setTitle("Modification Localisation");
				break;
			case 1:
				editStage.setTitle("Création Localisation");
				break;
			default:
				editStage.setTitle("???");
				break;
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(localisationEdit);
			editStage.setScene(scene);

			LocalisationEditControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			controleur.setLocalisation(localisation);
			editStage.showAndWait();
			//
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageLocalisationDetails(Localisation localisation) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/localisation/LocalisationDetails.fxml"));
			AnchorPane localisationDetails = (AnchorPane) loader.load();

			Stage detailsStage = new Stage();
			detailsStage.setTitle("Détails Localisation");
			detailsStage.initModality(Modality.WINDOW_MODAL);
			detailsStage.initOwner(primaryStage);
			Scene scene = new Scene(localisationDetails);
			detailsStage.setScene(scene);
			LocalisationDetailsControleur localisationDetailsControleur = loader.getController();
			localisationDetailsControleur.setPrincipale(this);
			localisationDetailsControleur.setDetailsStage(detailsStage);
			localisationDetailsControleur.setLocalisation(localisation);
			detailsStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
	}
	
	public boolean affichageListeLocalisations() {
		localisationData.clear();
		DAO<Localisation> dao = DaoFactory.creerDao(Classe.LOCALISATION);
		
		List<Localisation> localisations = null;

		try {
			localisations = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Localisation localisation : localisations) {
			localisationData.add(new Localisation(localisation.getLocId(), localisation.getSalId(),
					localisation.getCpsId(), localisation.getBatId(), localisation.getLocNom()));
		}	
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/localisation/ListeLocalisationsVue.fxml"));
			AnchorPane listelocalisationsVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des localisations");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listelocalisationsVue);
			editStage.setScene(scene);

			ListeLocalisationsVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			locId = controleur.getLocId();
			locNom = controleur.getLocNom();
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean affichageListeStructures(Classe classe) {
		structureData.clear();
		DAO<Structure> dao = DaoFactory.creerDao(classe);
		
		List<Structure> structures = null;

		try {
			structures = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		switch (classe) {
		case CAMPUS:
			for (Structure structure : structures) {
				structureData.add(new Campus(structure.getId(), structure.getNom()));
			}
			break;
		case BATIMENT:
			for (Structure structure : structures) {
				structureData.add(new Batiment(structure.getId(), structure.getNom()));
			}
			break;
		case SALLE:
			for (Structure structure : structures) {
				structureData.add(new Salle(structure.getId(), structure.getNom()));	
			}
			break;
		default:
			break;
		}
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/structure/ListeStructuresVue.fxml"));
			AnchorPane listeStructuresVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des structures");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeStructuresVue);
			editStage.setScene(scene);

			ListeStructuresVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			switch (classe) {
			case CAMPUS:
				cpsId = controleur.getId();
				cpsNom = controleur.getNom();
				break;
			case BATIMENT:
				batId = controleur.getId();
				batNom = controleur.getNom();
				break;
			case SALLE:
				salId = controleur.getId();
				salNom = controleur.getNom();
				break;
			default:
				break;
			}
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean affichageListeComposants() {
		listeComposantsData.clear();
		DAO<ListeComposants> dao = DaoFactory.creerDao(Classe.LISTECOMPOSANTS);
		
		List<ListeComposants> listeComposants = null;

		try {
			listeComposants = dao.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (ListeComposants liste : listeComposants) {
			listeComposantsData.add(new ListeComposants(liste.getCompId(), liste.getMaId()));
		}
			
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/composant/liste/ListeComposantsVue.fxml"));
			AnchorPane listeComposantsVue = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des composants");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeComposantsVue);
			editStage.setScene(scene);

			ListeComposantVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			editStage.showAndWait();
			compId = controleur.getCompId();
			
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void affichageListeComposantsDetails(int compId) {
		
		composantData.clear();
		DisqueDurDao daoDisqueDur = new DisqueDurDao(DisqueDur.class);
		List<DisqueDur> disqueDurs = null;
		MemoireViveDao daoMemoireVive = new MemoireViveDao(MemoireVive.class);
		List<MemoireVive> memoireVives = null;
		CarteMereDao daoCarteMere = new CarteMereDao(CarteMere.class);
		CarteMere carteMere = null;
		ProcesseurDao daoProcesseur = new ProcesseurDao(Processeur.class);
		Processeur processeur = null;
		OsDao daoOs = new OsDao(Os.class);
		Os os = null;

		try {
			disqueDurs = daoDisqueDur.selectCompId(compId);
			memoireVives = daoMemoireVive.selectCompId(compId);
			carteMere = daoCarteMere.selectCompId(compId);
			processeur = daoProcesseur.selectCompId(compId);
			os = daoOs.selectCompId(compId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (DisqueDur disqueDur : disqueDurs) {
			if (disqueDur.getCompId() == compId)
			composantData.add(disqueDur);
		}
		for (MemoireVive memoireVive : memoireVives) {
			if (memoireVive.getCompId() == compId)
			composantData.add(memoireVive);
		}
		if (carteMere != null)
			composantData.add(carteMere);
		if (processeur != null)
			composantData.add(processeur);
		if (os != null)
			composantData.add(os);
			
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/composant/ListeComposantsDetails.fxml"));
			AnchorPane listeComposantsDetails = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			
			editStage.setTitle("Liste des composants");
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(listeComposantsDetails);
			editStage.setScene(scene);

			ListeComposantsDetailsControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setDetailsStage(editStage);
			editStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void affichageComposantVue() {

		composantData.clear();
		DAO<DisqueDur> daoDisqueDur = DaoFactory.creerDao(Classe.DISQUEDUR);
		List<DisqueDur> disqueDurs = null;
		DAO<MemoireVive> daoMemoireVive = DaoFactory.creerDao(Classe.MEMOIREVIVE);
		List<MemoireVive> memoireVives = null;
		DAO<CarteMere> daoCarteMere = DaoFactory.creerDao(Classe.CARTEMERE);
		List<CarteMere> carteMeres = null;
		DAO<Processeur> daoProcesseur = DaoFactory.creerDao(Classe.PROCESSEUR);
		List<Processeur> processeurs = null;
		DAO<Os> daoOs = DaoFactory.creerDao(Classe.OS);
		List<Os> os = null;

		try {
			disqueDurs = daoDisqueDur.lister();
			memoireVives = daoMemoireVive.lister();
			carteMeres = daoCarteMere.lister();
			processeurs = daoProcesseur.lister();
			os = daoOs.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (DisqueDur disqueDur : disqueDurs) {
			composantData.add(disqueDur);
		}
		for (MemoireVive memoireVive : memoireVives) {
			composantData.add(memoireVive);
		}
		for (CarteMere carteMere : carteMeres) {
			composantData.add(carteMere);
		}
		for (Processeur processeur : processeurs) {
			composantData.add(processeur);
		}
		for (Os o : os) {
			composantData.add(o);
		}

		try {
			// Chargement du layout principal à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/composant/ComposantVue.fxml"));
			AnchorPane composantVue = (AnchorPane) loader.load();

			// Affichage de la scene contenant le layout Composant dans le
			// Principal
			vuePrincipale.setCenter(composantVue);

			ComposantVueControleur controleur = loader.getController();
			controleur.setPrincipale(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean affichageComposantEdit(Composant composant, int mode) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principale.class.getResource("vues/composant/ComposantEdit.fxml"));
			AnchorPane composantEdit = (AnchorPane) loader.load();

			Stage editStage = new Stage();
			switch (mode) {
			case 0:
				editStage.setTitle("Modification composant");
				break;
			case 1:
				editStage.setTitle("Création composant");
				break;
			default:
				editStage.setTitle("???");
				break;
			}
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(primaryStage);
			Scene scene = new Scene(composantEdit);
			editStage.setScene(scene);

			ComposantEditControleur controleur = loader.getController();
			controleur.setPrincipale(this);
			controleur.setEditStage(editStage);
			controleur.setComposant(composant);
			editStage.showAndWait();
			//
			return controleur.isClickOK();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
