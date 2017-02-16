package parc.vues;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ConnectionOracle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

// permet l'affichage d'un histogramme représentant le nombre de lignes présentes dans chaque table
public class StatistiquesControleur {
	
	@FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> tables = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
    }
    
    public void setData(){	
    	// On initialise un tableau avec le nombre de tables
    	//TODO:récupérer le nombre de tables via une procédure
    	int[] classeCompteur = new int[13];
		ResultSet rs;
		try {
			int i = 0;
			rs = ConnectionOracle.compteur("{CALL COMPTEUR(?)");
			//Pour chaque table on ajoute le nom et le nombre de lignes dans tables/classeCompteur
			while (rs.next()) {
				tables.add(rs.getString(1));
				classeCompteur[i] = rs.getInt(2);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		// On ajoute les noms à l'axe des abscisses
		xAxis.setCategories(tables);
		
		// On ajoute les données à l'histogramme
    	XYChart.Series<String, Integer> series = new XYChart.Series<>();
    	for (int i = 0; i < classeCompteur.length; i++) {
			series.getData().add(new XYChart.Data<>(tables.get(i), classeCompteur[i]));
		}
    	
 
    	barChart.getData().add(series);
    }

}
