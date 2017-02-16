package parc.vues.machine;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import machine.Machine;
import parc.Principale;

public class ListeMachinesVueControleur {
	
	@FXML
	private TableView<Machine> machineTable;
	@FXML
	private TableColumn<Machine, Integer> idColonne;
	@FXML
	private TableColumn<Machine, String> nomColonne;
	
	private Principale principale;
	private Stage editStage;
	private boolean clickOK = false;
	private int maId;
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().maIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().maNomProperty());
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		machineTable.setItems(principale.getMachineData());
	}
	
	public Principale getPrincipale(){
		return principale;
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	public int getMaId(){
		return maId;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			Machine machine = machineTable.getSelectionModel().getSelectedItem();
			maId = machine.getMaId();
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}

	private boolean isValid() {
		//TODO: faire la validation
		
		String message = "";
		
		if (message.length() == 0){
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(editStage);
			alert.setTitle("Données non valides");
			alert.setHeaderText("Veuillez corriger les données");
			alert.setContentText(message);
			alert.showAndWait();
			return false;
		}
	}
	
}
