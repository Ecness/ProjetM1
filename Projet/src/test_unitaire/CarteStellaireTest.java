package test_unitaire;

import static org.junit.Assert.*;

import java.util.Map;

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
		parametre = new Parametre(null, EnumAbondanceRessource.ABONDANT, null, EnumTailleCarte.MINUSCULE, 2, 10, 10, EnumTailleMapCombat.MOYENNE, null);
		carte = new Carte(parametre);
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
	
	@Test
	public void testEvaluationDistance() {
		for (Systeme sys : carte.getListeSysteme()) {
			if (carte.getPositionSysteme().get(sys.getPositionPrecedente()) != null) {
				Systeme cible1 = carte.getPositionSysteme().get(sys.getPositionPrecedente());
				Systeme premierLien = sys.getLiens().firstKey();
				while (premierLien.getPosition().getCouche() <= sys.getPosition().getCouche()) {
					premierLien = sys.getLiens().higherKey(premierLien);
				}
				Systeme cible2 = sys.getLiens().ceilingKey(premierLien);
				assertTrue(sys.getDistance(cible2) < sys.getDistance(cible1) + cible1.getDistance(cible2));
				assertTrue(sys.getDistance(cible1) < cible1.getDistance(cible2) + sys.getDistance(cible2));
				assertTrue(cible1.getDistance(cible2) < sys.getDistance(cible1) + sys.getDistance(cible2));
			}

			if (carte.getPositionSysteme().get(sys.getPositionSuivante()) != null) {
				Systeme cible1 = carte.getPositionSysteme().get(sys.getPositionSuivante());
				Systeme cible2 = sys.getLiens().lastKey();
				assertTrue(sys.getDistance(cible2) < sys.getDistance(cible1) + cible1.getDistance(cible2));
				assertTrue(sys.getDistance(cible1) < cible1.getDistance(cible2) + sys.getDistance(cible2));
				assertTrue(cible1.getDistance(cible2) < sys.getDistance(cible1) + sys.getDistance(cible2));
			}

			for (Map.Entry<Systeme, Integer> entry : sys.getLiens().entrySet()) {
				Systeme cible1 = sys.getLiens().firstKey();
				Systeme cible2 = entry.getKey();
				if (cible1 != cible2
						&& cible1.getCouche() > sys.getCouche()
						&& cible2.getCouche() > sys.getCouche()) {
					assertTrue(sys.getDistance(cible2) < sys.getDistance(cible1) + cible1.getDistance(cible2));
					assertTrue(sys.getDistance(cible1) < cible1.getDistance(cible2) + sys.getDistance(cible2));
					assertTrue(cible1.getDistance(cible2) < sys.getDistance(cible1) + sys.getDistance(cible2));
					cible1 = cible2;
				}
			}
		}
	}
}