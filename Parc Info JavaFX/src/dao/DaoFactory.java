package dao;

import composants.CarteMere;
import composants.Composant;
import composants.DisqueDur;
import composants.ListeComposants;
import composants.MemoireVive;
import composants.Os;
import composants.Processeur;
import machine.Formation;
import machine.Machine;
import machine.Stagiaire;
import structure.Batiment;
import structure.Campus;
import structure.Localisation;
import structure.Salle;

/***********************************************************************
 * Module:  DaoFactory.java
 * Author:  merguez
 * Purpose: Defines the Class DaoFactory
 ***********************************************************************/

public class DaoFactory {

	@SuppressWarnings("rawtypes")
	public static DAO creerDao(Classe classe) {
		switch (classe) {
		case CAMPUS:
			return new CampusDao(Campus.class);
		case BATIMENT:
			return new BatimentDao(Batiment.class);
		case SALLE:
			return new SalleDao(Salle.class);
		case MACHINE:
			return new MachineDao(Machine.class);
		case STAGIAIRE:
			return new StagiaireDao(Stagiaire.class);
		case FORMATION:
			return new FormationDao(Formation.class);
		case CARTEMERE:
			return new CarteMereDao(CarteMere.class);
		case OS:
			return new OsDao(Os.class);
		case DISQUEDUR:
			return new DisqueDurDao(DisqueDur.class);
		case PROCESSEUR:
			return new ProcesseurDao(Processeur.class);
		case MEMOIREVIVE:
			return new MemoireViveDao(MemoireVive.class);
		case LISTECOMPOSANTS:
			return new ListeComposantsDao(ListeComposants.class);
		case LOCALISATION:
			return new LocalisationDao(Localisation.class);
		default:
			return null;
		}
	}

}