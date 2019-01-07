package test_unitaire;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import model.EnumRessource;
import model.carte.stellaire.EnumTypePlanete;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import model.carte.stellaire.Ville;
import model.entity.player.EnumNation;
import model.entity.player.Joueur;
import model.module.EnumModule;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumRessourceDepart;
import model.parametre.Parametre;

public class GereUnSysteme {

	@Test
	public void test() {

		Parametre parametre = new Parametre(null, EnumAbondanceRessource.NORMAL , null, null, 2, 10, 10, null,EnumRessourceDepart.NORMAL);
		Systeme systeme = new Systeme(parametre.getAbondanceRessource(), parametre.getNbMaxPlanete(), parametre.getNbMaxAnomalie(), 0, 0, 0);
		Joueur joueur = new Joueur("test", EnumNation.HUMANOIDE, systeme, parametre.getRessourceDepart());
		
		
		System.out.println("Type de systeme : " + systeme.getTypeSysteme());
		
		int i = 0;
		if(systeme.getTPlanete() == null) {
			fail("TPlanete is null");
		}else {
			System.out.println("il y as " + systeme.getTPlanete().size() + " Planete");
			for (Planete t : systeme.getTPlanete()) {
				i++;
				if(t.getTypePlanete() == null) {
					fail("Planete " + i + " is type null");
				}else {
					System.out.println("Planete " + i + " " + t.getTypePlanete());
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
		
		for(Planete planete : systeme.getTPlanete()) {
			if(planete.getVille() != null) {
				fail("planete.getVille() not null");
			}
		}
		
		System.out.println("habitable : " + systeme.presencePlaneteHabitable());	
		
		if(systeme.presenceVille() == true) {
			fail("systeme.presenceVille() return true");
		}
		
		
		if(systeme.presencePlaneteHabitable()) {
			if(systeme.ajouterVille(givePlanete(systeme),joueur) == false) {
				fail("systeme.ajouterVille return true (joueur=null)");
			}
		}
		
		System.out.println("");
		
		i = 0;
		for(Planete planete : systeme.getTPlanete()) {
			i++;

			if(planete.getVille() != null) {
				System.out.println("Ville sur la planete n° : " + i);
				System.out.println("Nom du joueur sur le système : " + systeme.getJoueur().getName());
				System.out.println("Nombre de planete dans le systéme du joueur : " + joueur.getSysteme().get(0).getTPlanete().size());
				System.out.println("Id de la ville dans le systéme joueur : " + joueur.getSysteme().get(0).getTPlanete().get(i-1).getVille().getId());
				System.out.println("Nom du joueur sur la planéte " + i + " du systéme : " + systeme.getTPlanete().get(i-1).getJoueur().getName());
				System.out.println("planete appartient au joueur : " + planete.getJoueur().getName());
				System.out.println("ville appartient au joueur : " + planete.getVille().getJoueur().getName());
				System.out.println("TBatiment lenght : " + planete.getTBatiment().length);
				System.out.println("TBatiment[0] : " + planete.getTBatimentNum(0));
				for (Ville v : joueur.getTVille()) {
					System.out.println("Nom du joueur de la ville : " + v.getJoueur().getName());
				}
				System.out.println("Module : ");
				for(EnumModule e : planete.getVille().getModule()) {					
					System.out.println("	" + e);
				}
				for (Map.Entry<EnumRessource, Integer> e : joueur.getTRessource().entrySet()) {
					
					System.out.println("Ressource du joueur " + e.getKey() + " : " + e.getValue());
				}
				System.out.println("au début de son tour (bonus de ville et planete de ses systéme)");
				joueur.debutDeTour();
                for (Map.Entry<EnumRessource, Integer> e : joueur.getTRessource().entrySet()) {
					
					System.out.println("Ressource du joueur " + e.getKey() + " : " + e.getValue());
				}	
			}
		}
		System.out.println(joueur.getTechnology().getScience().getScience().size());
		System.out.println(joueur.getTechnology().getScience().getScience().get(-1).isRechercher());
		System.out.println(joueur.getTechnology().getScience().getScience().get(0).isRechercher());
		joueur.addRecherche(0);
		joueur.getTRessource().put(EnumRessource.SCIENCE, 80);
		joueur.testFinRecherche();
		System.out.println(joueur.getTechnology().getScience().getScience().get(0).isRechercher());
		
		
	}
	
	private Planete givePlanete(Systeme systeme) {
		
		for(Planete planete : systeme.getTPlanete()) {
			if(planete.getTypePlanete() != EnumTypePlanete.GAZEUSE && planete.getVille()==null) {
				return planete;
			}
		}
		return null;
	}
}
