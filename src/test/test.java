package test;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.IntMap.Entry;

import model.EnumRessource;
import model.carte.combat.DetailCombat;
import model.carte.combat.PhaseCombat;
import model.entity.vaisseau.Corvette;
import model.entity.vaisseau.Croiseur;
import model.entity.vaisseau.EnumDommageCritique;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.ListVaisseaux;
import model.entity.vaisseau.Vaisseau;
import model.module.Arme;
import model.module.Blindage;
import model.module.ListeModule;



public class test {

	public static Map<EnumRessource, Integer> setRessource(int gaz, int science, int cristal, int production, int credit, int acier){
		Map<EnumRessource, Integer> ressources = new HashMap<EnumRessource, Integer>();
		ressources.put(EnumRessource.GAZ, gaz);
		ressources.put(EnumRessource.SCIENCE, science);
		ressources.put(EnumRessource.CRISTAL, cristal);
		ressources.put(EnumRessource.PRODUCTION, production);
		ressources.put(EnumRessource.CREDIT, credit);
		ressources.put(EnumRessource.ACIER, acier);	
		return ressources;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException {
		
		ListeModule listModule = new ListeModule();
		
		Json parser = new Json();
		try(FileReader file = new FileReader("Ressources/FileMaker/Modules_Vaiseaux.json")) {
			
			ListeModule module = parser.fromJson(ListeModule.class, file);
			
			for (Entry<Arme> arme : module.getArmeCinetique().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, arme.value.getCout().get(ressource.name()));
				}
				arme.value.setCout(cout);
			}
			for (Entry<Arme> arme : module.getArmeLaser().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, arme.value.getCout().get(ressource.name()));
				}
				arme.value.setCout(cout);
			}
			for (Entry<Arme> arme : module.getArmePlasma().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, arme.value.getCout().get(ressource.name()));
				}
				arme.value.setCout(cout);
			}
			for (Entry<Blindage> arme : module.getBlindage().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, arme.value.getCout().get(ressource.name()));
				}
				arme.value.setCout(cout);
			}
		    listModule=module;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ListVaisseaux listVaisseau = new ListVaisseaux();
		
		try(FileReader file = new FileReader("Ressources/FileMaker/Vaisseaux.json")) {
			
			ListVaisseaux vaisseaux = parser.fromJson(ListVaisseaux.class, file);
			
			for (Entry<Corvette> vaisseau : vaisseaux.getCorvettePirate().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, vaisseau.value.getCout().get(ressource.name()));
				}
				vaisseau.value.setCout(cout);
			}
			listVaisseau=vaisseaux;
		    
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
		Map<Integer, Arme> armes2 = new HashMap<Integer, Arme>();
		Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
		Map<Integer, Blindage> blindages2 = new HashMap<Integer, Blindage>();
		
		armes.put(0, listModule.getArmeLaser().get(0));
		armes.put(1, listModule.getArmeLaser().get(0));
		armes.put(2, listModule.getArmeCinetique().get(0));
		armes.put(3, listModule.getArmeCinetique().get(0));
		armes.put(4, listModule.getArmePlasma().get(0));
		armes.put(5, listModule.getArmeCinetique().get(2));

		armes2.put(0, listModule.getArmeLaser().get(1));
		armes2.put(2, listModule.getArmeCinetique().get(1));
		armes2.put(2, listModule.getArmeCinetique().get(1));
		armes2.put(3, listModule.getArmeCinetique().get(1));
		armes2.put(4, listModule.getArmePlasma().get(1));
		armes2.put(5, listModule.getArmeCinetique().get(2));

		blindages.put(0, listModule.getBlindage().get(2));
		blindages.put(1, listModule.getBlindage().get(3));
		blindages.put(2, listModule.getBlindage().get(3));
		blindages.put(3, listModule.getBlindage().get(1));
		
		blindages2.put(0, listModule.getBlindage().get(3));
		blindages2.put(1, listModule.getBlindage().get(3));
		blindages2.put(2, listModule.getBlindage().get(3));
		blindages2.put(3, listModule.getBlindage().get(3));
		
		Croiseur croiseurTest = new Croiseur("Universe", listModule.getChassie().get(4), armes, blindages, 0, setRessource(0, 0, 0, 0, 0, 0));
		Croiseur croiseurTest2 = new Croiseur("Ulysse", listModule.getChassie().get(4), armes2, blindages2, 0, setRessource(0, 0, 0, 0, 0, 0));
		Flotte flotte = new Flotte();
		flotte.addVaisseau(0, croiseurTest);
		Flotte flotte2 = new Flotte();
		flotte2.addVaisseau(0, croiseurTest2);
		
		System.out.println(flotte.toString());
		
		Flotte flottePirate = new Flotte();
		flottePirate.addVaisseau(0, listVaisseau.getCorvettePirate().get(0));
		flottePirate.addVaisseau(1, listVaisseau.getCorvettePirate().get(0));
		flottePirate.addVaisseau(2, listVaisseau.getCorvettePirate().get(0));
		flottePirate.addVaisseau(3, listVaisseau.getCorvettePirate().get(0));

		flottePirate.addVaisseau(4, listVaisseau.getCorvettePirate().get(1));
		flottePirate.addVaisseau(5, listVaisseau.getCorvettePirate().get(1));
		
		System.out.println(flottePirate.toString());
		
		PhaseCombat phaseCombat = new PhaseCombat(null);
		
		do {
			for (java.util.Map.Entry<Integer, Vaisseau> vaisseau : flotte2.getTVaisseau().entrySet()) {
				DetailCombat detail = phaseCombat.infligerDommage(vaisseau.getValue(), flotte.getTVaisseau().get(0), 0);
				System.out.println("\n Tire\n"+vaisseau.getValue().toString());
				TimeUnit.SECONDS.sleep(1);
				System.out.println("\n\nRapport\n"+detail.toString());
				TimeUnit.SECONDS.sleep(1);
				if (detail.detruit) {
					flotte.getTVaisseau().remove(0);
				}
			}
			flotte.getTVaisseau().get(0).dommageFire();
			System.out.println("\n FEU\n" + flotte.toString());
			TimeUnit.SECONDS.sleep(1);
			if(!flotte.getTVaisseau().get(0).getDommageCritique().contains(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT)) {
				flotte.getTVaisseau().get(0).regeneBouclier();
			}
			flotte.getTVaisseau().get(0).reparationVaisseau();
			System.out.println(" reparation\n" + flotte.toString());
			TimeUnit.SECONDS.sleep(3);
		} while (!flotte.getTVaisseau().isEmpty() || !flotte2.getTVaisseau().isEmpty() );
	}
}
