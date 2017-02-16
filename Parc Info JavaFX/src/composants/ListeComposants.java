package composants;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListeComposants {
	
	private IntegerProperty compId;
	private IntegerProperty cmId;
	private IntegerProperty procId;
	private IntegerProperty osId;
	private IntegerProperty maId;
	private ListProperty<MemoireVive> mvs;
	private ListProperty<DisqueDur> dds;
	
	public ListeComposants() {
		this(0,0);
	}

	public ListeComposants(Integer compId, Integer cmId, Integer procId, Integer osId, Integer maId, List<MemoireVive> mvs, List<DisqueDur> dds) {
		this.compId = new SimpleIntegerProperty(compId);
		this.cmId = new SimpleIntegerProperty(cmId);
		this.procId = new SimpleIntegerProperty(procId);
		this.osId = new SimpleIntegerProperty(osId);
		this.maId = new SimpleIntegerProperty(maId);
		ObservableList<MemoireVive> memoireVives = FXCollections.observableArrayList(mvs);
		this.mvs = new SimpleListProperty<MemoireVive>(memoireVives);
		ObservableList<DisqueDur> disqueDurs = FXCollections.observableArrayList(dds);
		this.dds = new SimpleListProperty<DisqueDur>(disqueDurs);
	}
	
	public ListeComposants(Integer compId, Integer maId) {
		this.compId = new SimpleIntegerProperty(compId);
		this.maId = new SimpleIntegerProperty(maId);
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

	public Integer getCmId() {
		return cmId.get();
	}

	public void setCmId(Integer cmId) {
		this.cmId.set(cmId);
	}
	
	public IntegerProperty cmIdProperty(){
		return cmId;
	}

	public Integer getProcId() {
		return procId.get();
	}

	public void setProcId(Integer procId) {
		this.procId.set(procId);
	}
	
	public IntegerProperty procIdProperty(){
		return procId;
	}

	public Integer getOsId() {
		return osId.get();
	}

	public void setOsId(Integer osId) {
		this.osId.set(osId);
	}
	
	public IntegerProperty osIdProperty(){
		return osId;
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

	public List<MemoireVive> getMvs() {
		return mvs.get();
	}

	public void setMvs(List<MemoireVive> mvs) {
		ObservableList<MemoireVive> memoireVives = FXCollections.observableArrayList(mvs);
		this.mvs.set(memoireVives);
	}
	
	public ListProperty<MemoireVive> mvsProperty(){
		return mvs;
	}

	public List<DisqueDur> getDds() {
		return dds.get();
	}

	public void setDds(DisqueDur dds) {
		ObservableList<DisqueDur> disqueDurs = FXCollections.observableArrayList(dds);
		this.dds.set(disqueDurs);
	}
	
	public ListProperty<DisqueDur> ddsProperty(){
		return dds;
	}

}
