package machine;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Machine {

	private IntegerProperty maId;
	private IntegerProperty stgId;
	private IntegerProperty locId;
	private IntegerProperty compId;
	@Pattern(regexp = "^[0-9]{3} [0-9]{4}$", message = "3 chiffres suivis d'un espace suivi de 4 chiffres")
	private StringProperty maNumAfpa;
	@Past(message = "La date d'achat doit être antérieure à aujourd'hui")
	private ObjectProperty<Date> maDateAchat;
	@Min(value = 0)
	private IntegerProperty maDureeGarantie;
	@Pattern(regexp = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", message = "doit être de type x.x.x.x avec x compris entre 0 et 255")
	private StringProperty maIP;
	private StringProperty maNom;

	public Machine() {
		this(0, 0, 0, 0, null, null, 0, null, null);
	}
	
	public Machine(Integer maId, Integer stgId, Integer locId, Integer compId, String maNumAfpa,
			Date maDateAchat, Integer maDureeGarantie, String maIP, String maNom) {
		this.maId = new SimpleIntegerProperty(maId);
		this.stgId = new SimpleIntegerProperty(stgId);
		this.locId = new SimpleIntegerProperty(locId);
		this.compId = new SimpleIntegerProperty(compId);
		this.maNumAfpa = new SimpleStringProperty(maNumAfpa);
		this.maDateAchat = new SimpleObjectProperty<Date>(maDateAchat);
		this.maDureeGarantie = new SimpleIntegerProperty(maDureeGarantie);
		this.maIP = new SimpleStringProperty(maIP);
		this.maNom = new SimpleStringProperty(maNom);
	}

	public Integer getMaId() {
		return maId.get();
	}

	public void setMaId(Integer maId) {
		this.maId.set(maId);
	}
	
	public IntegerProperty maIdProperty(){
		return maId;
	}

	public Integer getStgId() {
		return stgId.get();
	}

	public void setStgId(Integer stgId) {
		this.stgId.set(stgId);
	}
	
	public IntegerProperty stgIdProperty(){
		return stgId;
	}

	public Integer getLocId() {
		return locId.get();
	}

	public void setLocId(Integer locId) {
		this.locId.set(locId);
	}
	
	public IntegerProperty locIdProperty(){
		return locId;
	}

	public Integer getCompId() {
		return compId.get();
	}

	public void setCompId(Integer compId) {
		this.compId.set(compId);
	}
	
	public IntegerProperty compIdProperty(){
		return compId;
	}

	public String getMaNumAfpa() {
		return maNumAfpa.get();
	}

	public void setMaNumAfpa(String maNumAfpa) {
		this.maNumAfpa.set(maNumAfpa);
	}
	
	public StringProperty maNumAfpaProperty(){
		return maNumAfpa;
	}

	public Date getMaDateAchat() {
		return maDateAchat.get();
	}

	public void setMaDateAchat(Date maDateAchat) {
		this.maDateAchat.set(maDateAchat);
	}
	
	public ObjectProperty<Date> maDateAchatProperty(){
		return maDateAchat;
	}

	public Integer getMaDureeGarantie() {
		return maDureeGarantie.get();
	}

	public void setMaDureeGarantie(Integer maDureeGarantie) {
		this.maDureeGarantie.set(maDureeGarantie);
	}
	
	public IntegerProperty maDureeGarantieProperty(){
		return maDureeGarantie;
	}

	public String getMaIP() {
		return maIP.get();
	}

	public void setMaIP(String maIP) {
		this.maIP.set(maIP);
	}
	
	public StringProperty maIPProperty(){
		return maIP;
	}

	public String getMaNom() {
		return maNom.get();
	}

	public void setMaNom(String maNom) {
		this.maNom.set(maNom);
	}
	
	public StringProperty maNomProperty(){
		return maNom;
	}

}