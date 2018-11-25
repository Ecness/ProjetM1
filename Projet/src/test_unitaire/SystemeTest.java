package test_unitaire;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.carte.stellaire.Systeme;
import model.parametre.EnumAbondanceRessource;

public class SystemeTest {
	
	private Systeme systeme, systeme2, systeme3, systeme4, systeme5;

	@Before
	public void setUp() {
		systeme = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 0, 0, 0);
		systeme2 = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 1, 0, 0);
		systeme3 = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 1, 1, 0);
		systeme4 = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 2, 0, 0);
		systeme5 = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 3, 0, 0);
	}
	
	@Test
	public void testExistanceSysteme() {
		assertNotNull(systeme);
	}
	
	@Test
	public void testNbLiens() {
		assertTrue(systeme.getNbLiensMax() >= 0 && systeme.getNbLiensMax() <= 5);
	}
	
	@Test
	public void faireLienTest() {
		systeme.faireLien(systeme2, 0);
		systeme.faireLien(systeme5, 0);
		systeme.faireLien(systeme3, 0);
		systeme.faireLien(systeme4, 0);
		
		assertTrue(systeme.getLiens().containsKey(systeme2));
		assertTrue(systeme2.getLiens().containsKey(systeme));
		
		//Test ordre liens croissant 
		for (Systeme sys : systeme.getLiens().keySet()) {
			if (sys.getLiens().higherKey(sys) != null) {
				assertTrue(sys.getCouche() <= sys.getLiens().higherKey(sys).getCouche());
				if (sys.getCouche() <= sys.getLiens().higherKey(sys).getCouche()) {
					assertTrue(sys.getRang() <= sys.getLiens().higherKey(sys).getRang());
				}
			}
		}
	}
}
