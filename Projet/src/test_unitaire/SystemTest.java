package test_unitaire;

import static org.junit.Assert.*;

import java.util.Map.Entry;

import org.junit.Test;

import model.EnumRessource;
import model.carte.stellaire.Anomalie;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import model.parametre.EnumAbondanceRessource;
import model.parametre.Parametre;

public class SystemTest {


	@Test
	public void testSysteme() {
		
		
		for(int j=0;j<10;j++) {
			
			Parametre p = new Parametre(null, EnumAbondanceRessource.NORMAL , null, null, 2, 10, 10, null);
			Systeme s = new Systeme(p.getAbondanceRessource(), p.getNbMaxPlanete(), p.getNbMaxAnomalie(), 0, 0);
			int i = 0;
			
			
			//-----------------------------------------------
			
			if(s.getJoueur() != null)
				fail("numJoueur not null");
			
			//-----------------------------------------------
			
			
			if(s.getTAnomalie() == null) {
				fail("TAnomalie is null");
			}else {
				System.out.println("il y as " + s.getTAnomalie().size() + " Anomalie");
				for (Anomalie t : s.getTAnomalie()) {
					i++;
					if(t.getAnomalie() == null) {
						fail("Anomalie " + i + " is type null");
					}else {
						System.out.println("	- Anomalie " + i + " est de type " + t.getAnomalie());
					}
				}
			}
			i = 0;
			
			//-----------------------------------------------
			
			
			if(s.getTPlanete() == null) {
				fail("TPlanete is null");
			}else {
				System.out.println("il y as " + s.getTPlanete().size() + " Planete");
				for (Planete t : s.getTPlanete()) {
					i++;
					if(t.getTypePlanete() == null) {
						fail("Planete " + i + " is type null");
					}else {
						System.out.println(t.getTypePlanete());
						if(t.getTRessource() == null) {
							fail("TRessource is null");
						}else {
							System.out.println("	- il y as  " + t.getTRessource().size() + " Ressource");
							for(Entry<EnumRessource, Integer> entry : t.getTRessource().entrySet()) {
								EnumRessource key = entry.getKey();
								int value = entry.getValue();
								System.out.println("		- Ressource " + key + " = " + value);
							}
						}
						if(t.getTBatiment() == null)
							fail("TBatiment is null");
						if(t.getVille() != null)
							fail("Ville not null");
					}
				}
			}
			i = 0;
			System.out.println(" ");
			System.out.println(" ");
		}
		
		
	}
}
