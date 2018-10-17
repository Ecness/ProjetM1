package test.carte.stellaire;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import model.parametre.EnumTailleCarte;
import model.parametre.Parametre;

public class CarteStellaireTest {
	
	private EnumTailleCarte tailleCarte;
	private Carte carte;

	@Before
	public void setUp() {
		tailleCarte = EnumTailleCarte.PETITE;
		carte = new Carte(new Parametre(null, null, null, tailleCarte, 0));
	}
	
	@Test
	public void testCarteExistante() {
		assertNotNull(carte);
	}
	
	@Test
	public void testGenerationSysteme() {
		assertNotNull(carte.getListeSysteme());
		assertEquals(tailleCarte.getQuantite(), carte.getListeSysteme().size());
		carte.affichage();
		for (Systeme sys : carte.getListeSysteme()) {
			assertTrue(sys.getNbLiens() <= sys.getNbLiensMax());
		}
	}
}
