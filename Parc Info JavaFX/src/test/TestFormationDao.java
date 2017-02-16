package test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dao.FormationDao;
import machine.Formation;

public class TestFormationDao {
	
	FormationDao dao = new FormationDao(Formation.class);

	@Test
	public void testFormationDao() {
		FormationDao dao2 = new FormationDao(Formation.class);
	}

	@Test
	public void testCreer() throws SQLException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date deb = sdf.parse("12/12/2016");
		Date fin = sdf.parse("12/12/2017");
		Date deb2 = sdf.parse("09/10/2016");
		Date fin2 = sdf.parse("09/10/2017");
		Formation form1 = new Formation(0, "dl", deb, fin);
		Formation form2 = new Formation(0, "cdi2", deb2, fin2);
		dao.creer(form1);
		dao.creer(form2);
	}

	@Test
	public void testSupprimer() throws ParseException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date deb = sdf.parse("12/12/2016");
		Date fin = sdf.parse("12/12/2017");
		Date deb2 = sdf.parse("09/10/2016");
		Date fin2 = sdf.parse("09/10/2017");
		Formation form1 = new Formation(65, "dl", deb, fin);
		Formation form2 = new Formation(44, "cdi2", deb2, fin2);
		dao.supprimer(form1);
		dao.supprimer(form2);
	}

	@Test
	public void testModifier() throws ParseException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date deb = sdf.parse("05/09/2015");
		Date fin = sdf.parse("05/09/2016");
		Formation form1 = new Formation(1, "dl", deb, fin);
		dao.modifier(form1);
	}

	@Test
	public void testLister() throws SQLException, ParseException {
		System.out.println(dao.lister());
		System.out.println("------------");
	}

	@Test
	public void testSelect() throws SQLException, ParseException {
		System.out.println(dao.select(1));
		System.out.println(dao.select(2));
	}

}
