package test_unitaire;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import model.carte.combat.MapCombat;
import model.carte.combat.obstacle.EnumListObstacle;
import model.carte.stellaire.Systeme;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumTailleMapCombat;

public class MapCombatGeneration {

	@Test
	public void testGenerationMapCombat() {
		
		EnumTailleMapCombat taille = EnumTailleMapCombat.PETITE;
		
		Systeme S = new Systeme(EnumAbondanceRessource.NORMAL, 10, 10, 0, 0, 0);
		MapCombat map = new MapCombat(null, null, null, null, S,taille);
		
		if(map.getClass() == null) {
			fail("not yet created");
		}
		
		if(map.getObstacle() == null) {
			fail("not yet created");
		}
		
		System.out.println("Type systeme : " + S.getTypeSysteme());
		System.out.println("il y as " + map.getObstacle().size() + " obstacle.");
		
		for (EnumListObstacle t : map.getList()) {
			System.out.println(t);
		}
	}
}
