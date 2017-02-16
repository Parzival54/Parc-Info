package test;

import java.sql.SQLException;

import org.junit.Test;

import dao.StagiaireDao;
import machine.Stagiaire;

public class TestStagiaireDao {

//	Stagiaire stg1 = new Stagiaire(1, 60, 1, "doe", "john", "rue principale", 1234567890);
	Stagiaire stg2 = new Stagiaire("pascal", "blaise", 106, 66, 1, "rue sans issue", 1246879531);
	Stagiaire stg3 = new Stagiaire("poincare", "henri", 10, 49, 1, "rue", 1234567890);
	StagiaireDao dao = new StagiaireDao(Stagiaire.class);

	@Test
	public void testStagiaireDao() {
		StagiaireDao dao2 = new StagiaireDao(Stagiaire.class);
	}

	@Test
	public void testCreer() throws SQLException {
		dao.creer(stg2);
		dao.creer(stg3);
	}

	@Test
	public void testSupprimer() throws SQLException {
		//dao.supprimer(stg2);
	}

	@Test
	public void testModifier() throws SQLException {
		//dao.modifier(stg3);
	}

	@Test
	public void testLister() throws SQLException {
		System.out.println(dao.lister());
		System.out.println("------------");
	}

	@Test
	public void testSelect() throws SQLException {
		System.out.println(dao.select(181));
	}

}
