package test_unitaire;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.Parametre;

public class CarteStellaireTest {
	
	private Parametre parametre;
	private Carte carte;

	@Before
	public void setUp() {
		parametre = new Parametre(null, EnumAbondanceRessource.ABONDANT, null, EnumTailleCarte.MOYENNE, 2, 10, 10, EnumTailleMapCombat.MOYENNE);
		carte = new Carte(parametre);
	}
	
	@Test
	public void testCarteExistante() {
		assertNotNull(carte);
	}
	
	@Test
	public void testGenerationSysteme() {
		assertNotNull(carte.getListeSysteme());
		assertEquals(parametre.getTailleCarte().getQuantite(), carte.getListeSysteme().size());
		carte.affichage();
		for (Systeme sys : carte.getListeSysteme()) {
			assertTrue(sys.getNbLiens() <= sys.getNbLiensMax());
		}
	}
}
