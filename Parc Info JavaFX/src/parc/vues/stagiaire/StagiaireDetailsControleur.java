package parc.vues.stagiaire;

import java.sql.SQLException;
import java.text.ParseException;

import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import machine.Formation;
import machine.Stagiaire;
import parc.Principale;

public class StagiaireDetailsControleur {
	
	@FXML
	private Label nomLabel;
	@FXML
	private Label prenomLabel;
	@FXML
	private Label adresseLabel;
	@FXML
	private Label numtelLabel;
	@FXML
	private Label formationLabel;
	
	private Stage detailsStage;
	private Stagiaire stagiaire;
	private Principale principale;
	
	@SuppressWarnings("unchecked")
	private DAO<Formation> daoFormation = DaoFactory.creerDao(Classe.FORMATION);
	
	@FXML
	private void initialize(){
	}
	
	public void setDetailsStage(Stage detailsStage){
		this.detailsStage = detailsStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
		
		nomLabel.setText(stagiaire.getStgNom());
		prenomLabel.setText(stagiaire.getStgPrenom());
		adresseLabel.setText(stagiaire.getStgAdresse());
		if (stagiaire.getStgNumtel() > 0){
			numtelLabel.setText(Integer.toString(stagiaire.getStgNumtel()));			
		}
		if (stagiaire.getFoId() > 0){
			Formation formation = null;
			try {
				formation = daoFormation.select(stagiaire.getFoId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			formationLabel.setText(formation.getFoNom());
		}
	}
	
	@FXML
	public void handleOK(){
			detailsStage.close();
	}

}
