package model.util;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.BatimentVille;
import model.batiment.ListBatiment;
import model.entity.player.Science;
import model.entity.player.donnee.Technologie;
import model.entity.vaisseau.Corvette;
import model.entity.vaisseau.Croiseur;
import model.entity.vaisseau.ListVaisseaux;
import model.entity.vaisseau.VaisseauCivil;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;
import model.module.EnumTypeArme;
import model.module.ListeModule;

public class FileMaker {

	/**
	 * 
	 * @param gaz
	 * @param science
	 * @param cristal
	 * @param production
	 * @param credit
	 * @param acier
	 * @param puissance
	 * @return
	 */
	public static MapRessource setRessource(int gaz, int science, int cristal, int production, int credit, int acier, int puissance){
		MapRessource ressources = new MapRessource();
		ressources.put(EnumRessource.GAZ, gaz);
		ressources.put(EnumRessource.SCIENCE, science);
		ressources.put(EnumRessource.CRISTAL, cristal);
		ressources.put(EnumRessource.PRODUCTION, production);
		ressources.put(EnumRessource.CREDIT, credit);
		ressources.put(EnumRessource.ACIER, acier);	
		ressources.put(EnumRessource.PUISSANCE, puissance);
		return ressources;
	}
	
	public static void main(String[] args) {
		
		// Vaisseau ------------------------------------
		
		ListeModule listModule = new ListeModule();
		

		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point, cout)
		listModule.addArmeLaser(0, new Arme("Laser MKI", "Petit module d'arme laser", 5, 90, 3, 45, 5, 10, setRessource(0,0,5,0,50,5,0), EnumTypeArme.LASER));
		listModule.addArmeLaser(1, new Arme("Laser MKII", "Version amélioré du laser MKI", 5, 75, 6, 45, 5, 15, setRessource(0,0,10,0,100,10,0),EnumTypeArme.LASER));
		

		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point, cout)
		listModule.addArmeCinetique(0, new Arme("Type 490/3", "Des canon de la marine modifier pour l'environement spacial", 20, 60, 3, 5, 10, 20, setRessource(0,0,0,0,100,30,0),EnumTypeArme.CINETIQUE));
		listModule.addArmeCinetique(1, new Arme("Type 490/2", "Version modifier de notre précédent canon", 20, 75, 2, 5, 10, 25, setRessource(0,0,10,0,100,30,0),EnumTypeArme.CINETIQUE));
		listModule.addArmeCinetique(2, new Arme("Canon électrique", "Une arme é projectile accéléré par une force électromagnétique", 70, 85, 1, 0, 25, 70, setRessource(10,0,20,0,250,50,0),EnumTypeArme.CINETIQUE));
		

		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point, cout)
		listModule.addArmePlasma(0, new Arme("Fusil Plasma", "Cette arme a un fort potentielle destructeur", 60, 10, 1, 60, 50, 25, setRessource(20,0,0,0,100,10,0),EnumTypeArme.PLASMA));
		listModule.addArmePlasma(1, new Arme("Fusil Plasma jumelé", "Arme plasma jumelé", 30, 20, 2, 60, 25, 30, setRessource(30,0,0,0,120,10,0),EnumTypeArme.PLASMA));
		

		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		//new Blindage(nom, description, point, valeurBlindage, valeurBlouclier, cout)
		listModule.addBlindage(0, new Blindage("Alliage métalique", "Blindage sommaire", 5, 80, 30, setRessource(0,0,0,0,100,20,0)));
		listModule.addBlindage(1, new Blindage("Alliage Composite", "Melange de matériaux diver", 10, 150, 80, setRessource(0,0,0,0,150,30,0)));
		listModule.addBlindage(2, new Blindage("Arc electrique", "Des arc electrifié pour réduire les chance de frape directe", 10, 0, 120, setRessource(0,0,10,0,100,20,0)));
		listModule.addBlindage(3, new Blindage("Aliage composite MKII", "Blindage renforcé", 20, 200, 110,setRessource(0,0,10,0,200,60,0)));
		
		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		//new Chassie(nom, description, point, sante, santeMax, bouclier, bouclierMax, nbArme, nbBlindage, nbModule,cout)
		listModule.addChassie(0, new Chassie("Sonde", "Parfaite pour exploré l'espace en sécurité", 1, 1, 1, 0, 0, 0, 0, 0, setRessource(0,0,0,0,20,0,0)));
		listModule.addChassie(1, new Chassie("Classe WarHound", "Conéu pour les petit vaisseaux", 50, 150, 150, 20, 20, 2, 1, 1, setRessource(5,0,0,0,50,10,0)));
		listModule.addChassie(2, new Chassie("Classe DauntLess", "Chassie pour les vaisseaux de reconaissance", 70, 200, 200, 30, 30, 3, 1, 2, setRessource(5,0,5,0,100,20,0)));
		listModule.addChassie(3, new Chassie("Classe StarLight", "Chassie pour les vaisseaux de ligne ou d'escorte", 120, 300, 300, 50, 50, 4, 2, 2, setRessource(10,0,10,0,200,50,0)));
		listModule.addChassie(4, new Chassie("Classe NightBringer", "Chassie pour les vaisseaux de combat",350, 500, 500, 110, 110, 6, 4, 3, setRessource(50,0,20,0,500,100,0)));
		
		// Batiment ------------------------------------
		
		ListBatiment listBatiment = new ListBatiment();
		
		//new BatimentPlanete(nom, description, techNecessaire, bonus, cout)
		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		listBatiment.addBatimentsPlanete(0, new BatimentPlanete("Mine de cristal", "Améliore la production de cristal", 0, setRessource(0,0,2,0,0,0,0), setRessource(0,0,0,0,50,10,0)));
		listBatiment.addBatimentsPlanete(1, new BatimentPlanete("Mine de metal", "Améliore la production d'acier", 0, setRessource(0,0,0,0,0,2,0), setRessource(0,0,0,0,50,10,0)));
		listBatiment.addBatimentsPlanete(2, new BatimentPlanete("Extracteur de gaz", "Améliore la production de gaz", 0, setRessource(2,0,0,0,0,0,0), setRessource(0,0,0,0,50,10,0)));
		listBatiment.addBatimentsPlanete(3, new BatimentPlanete("Centre de recherche xenos", "Recherche sur les forme de vie extra-planetaire", 4, setRessource(0,5,0,0,-2,0,0), setRessource(10,0,50,0,80,50,0)));
		listBatiment.addBatimentsPlanete(4, new BatimentPlanete("Spatioport", "Améliore les echanges avec les autre colonie", 3, setRessource(1,0,1,0,5,1,0), setRessource(5,0,5,0,50,20,0)));
		listBatiment.addBatimentsPlanete(5, new BatimentPlanete("Exploitation de cristal", "Améliore la production de cristal", 1, setRessource(0,0,4,0,0,0,0), setRessource(0,0,20,0,75,50,0)));
		listBatiment.addBatimentsPlanete(6, new BatimentPlanete("Exploitation de metal", "Améliore la production d'acier", 1, setRessource(0,0,0,0,0,4,0), setRessource(0,0,50,0,75,50,0)));
		listBatiment.addBatimentsPlanete(7, new BatimentPlanete("Extracteur amélioré de gaz", "Améliore la production de gaz", 1, setRessource(4,0,0,0,0,0,0), setRessource(0,0,50,0,75,50,0)));
		
		
		//new BatimentVille(nom, description, techNecessaire, bonus, cout)
		//setRessource(gaz, science, cristal, production, credit, acier, puissance)
		listBatiment.addBatimentsVille(0, new BatimentVille("Quartier ouvrier", "Quartier résidentelle peu luxueux", 0, setRessource(0,0,0,2,2,0,0), 20));
		listBatiment.addBatimentsVille(1, new BatimentVille("Mega-usine", "Usine de la taille d'une ville servant a traiter les ressources", 1, setRessource(2,0,2,1,-2,2,0), 200));
		listBatiment.addBatimentsVille(2, new BatimentVille("Usine Robotique", "Usine de création robotique", 1, setRessource(0,0,1,1,-1,2,0), 150));
		listBatiment.addBatimentsVille(3, new BatimentVille("Batrie Planétaire", "Batrie de defense planétaire", 2, setRessource(0,0,0,-1,-2,-1,50), 100));
		listBatiment.addBatimentsVille(4, new BatimentVille("Centre de recherche", "Petit centre de recherche", 0, setRessource(0,2,0,-1,-1,0,0), 50));
		listBatiment.addBatimentsVille(5, new BatimentVille("Banque central planétaire", "Centralise les fonts de la colonie", 0, setRessource(0,0,0,-1,10,0,0), 80));		
		
		
		// Science ------------------------------------
		Technologie listTech = new Technologie();
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceBatiment(0, new Science("Voyage Spacial", "Le Commencement", true, 0, -1, -1));
		listTech.addScienceBatiment(1, new Science("Mega-usine", "Delock la construction de Mega-usine", false, 50, 0, 0));
		listTech.addScienceBatiment(2, new Science("Defence planétaire", "Delock la construction de defence planétaire", false, 20, 0, 0));
		listTech.addScienceBatiment(3, new Science("Spatioport", "Delock la construction de spatioport", false, 80, 1, 2));
		listTech.addScienceBatiment(4, new Science("Centre de recherche", "Delock la construction de centre de recherche medium", false, 80, 1, 0));
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceBonus(0, new Science("Recherche des modules basic", "Le commencement des améliorations", true, 0, -1, -1));
		listTech.addScienceBonus(1, new Science("Module d'arme Laser Booster", "Ameliore de 10% la prescision des armes laser", false, 50, 0, 0));
		listTech.addScienceBonus(2, new Science("Module d'arme perforante", "Auguemente de 10% les dommages des armes cinétique", false, 50, 0, 0));
		listTech.addScienceBonus(3, new Science("Holochamp", "Réduit les chances de se faire toucher des 10%", false, 100, 2, 3));
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceMillitaire(0, new Science("Sonde", "Début de la conquéte spacial", true, 0, -1, -1));
		listTech.addScienceMillitaire(1, new Science("Vaiseau de defence", "Premier vaiseau conue pour la defence de la colonie", false, 20, 0, 0));
		listTech.addScienceMillitaire(2, new Science("Fusion solaire controler", "Permet la construction d'arme Plasma", false, 50, 1, 0));
		listTech.addScienceMillitaire(3, new Science("Module MKII", "Delock les armes tier 2", false, 50, 1, 2));
		listTech.addScienceMillitaire(4, new Science("RailGun", "Permet la construction d'un canon éléctrique", false, 100, 3, 5));
		listTech.addScienceMillitaire(5, new Science("Vaisseau d'escorte", "Délock les vaisseaux tier 2", false, 60, 3, 0));
		listTech.addScienceMillitaire(6, new Science("Croiseur stailaire", "Délock les vaisseaux tier 3", false, 150, 4, 5));
	
		
		// Vaisseaux ------------------------------------
		
		ListVaisseaux listVaisseau = new ListVaisseaux();
		ListVaisseaux listVaisseauPirate = new ListVaisseaux();
//		new Corvette(nom, chassie, armes, blindages, vitesse, cout)
//		new Croiseur(nom, chassie, armes, blindages, vitesse, cout)
//		new VaisseauCivil(nom, vitesse, chassie, cout)
//		new Chassie(nom, description, point, sante, santeMax, bouclier, bouclierMax, nbArme, nbBlindage, nbModule)
//		new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point, cout)
//		new Blindage(nom, description, point, valeurBlindage, valeurBlouclier, cout)
//		setRessource(gaz, science, cristal, production, credit, acier)		
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			armes.put(0, listModule.getArmeLaser().get(0));
			armes.put(1, listModule.getArmeCinetique().get(0));
			blindages.put(0, listModule.getBlindage().get(0));
			listVaisseauPirate.addVaisseau(0,new Corvette("Reaper", new Chassie("Reaper", "Chassie petit et rapide", 20, 60, 60, 20, 20, 2, 1, 0, setRessource(5, 0, 5, 0, 500, 5,0)),armes, blindages, 0,0,null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Reaper");
			e.printStackTrace();
		}
		
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			armes.put(0, listModule.getArmePlasma().get(0));
			blindages.put(0, listModule.getBlindage().get(0));
			listVaisseauPirate.addVaisseau(1, new Corvette("Specter", new Chassie("Specter", "Cargo civil modifier", 5, 30, 30, 0, 0, 1, 1, 0,setRessource(2, 0, 2, 0, 200, 2, 0)),armes, blindages, 0,0,null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Specter");
			e.printStackTrace();
		}
		
		try {
			listVaisseau.addVaisseau(0, new VaisseauCivil("Sonde", 200, new Chassie("Sonde", "Créé pour l'exploration spacial", 1, 1, 1, 0, 0, 0, 0, 0, setRessource(1, 0, 1, 0, 50, 1, 0)), 0,null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Sonde");
			e.printStackTrace();
		}
		
		try {
			listVaisseau.addVaisseau(1, new VaisseauCivil("Cargo colonial", 0, new Chassie("Cargo", "Cargo", 1, 80, 80, 0, 0, 0, 0, 0, setRessource(0, 0, 0, 0, 1000, 5, 0)), 0,null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Cargo colonial");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeLaser().get(1));
			armes.put(1, listModule.getArmeLaser().get(1));
			armes.put(2, listModule.getArmeCinetique().get(1));
			armes.put(3, listModule.getArmeCinetique().get(1));
			armes.put(4, listModule.getArmePlasma().get(1));
			armes.put(5, listModule.getArmeCinetique().get(2));

			blindages.put(0, listModule.getBlindage().get(2));
			blindages.put(1, listModule.getBlindage().get(3));
			blindages.put(2, listModule.getBlindage().get(3));
			blindages.put(3, listModule.getBlindage().get(3));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(2, new Croiseur("Universe", listModule.getChassie().get(4), armes, blindages, 0, 6, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Universe");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(0));
			armes.put(1, listModule.getArmeCinetique().get(0));

			blindages.put(0, listModule.getBlindage().get(0));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(3, new Corvette("Unexpected A", listModule.getChassie().get(1), armes, blindages, 0, 1, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Unexpected A");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(0));
			armes.put(1, listModule.getArmeLaser().get(0));

			blindages.put(0, listModule.getBlindage().get(0));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(4, new Corvette("Unexpected B", listModule.getChassie().get(1), armes, blindages, 300, 0, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Unexpected B");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmePlasma().get(0));
			armes.put(1, listModule.getArmePlasma().get(0));

			blindages.put(0, listModule.getBlindage().get(0));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(5, new Corvette("Unexpected C", listModule.getChassie().get(1), armes, blindages, 0, 1, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Unexpected C");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeCinetique().get(1));

			blindages.put(0, listModule.getBlindage().get(1));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(6, new Corvette("FireBird A", listModule.getChassie().get(2), armes, blindages, 0, 3, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau FireBird A");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeLaser().get(1));
			armes.put(1, listModule.getArmeLaser().get(1));

			blindages.put(0, listModule.getBlindage().get(1));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(7, new Corvette("FireBird B", listModule.getChassie().get(2), armes, blindages, 0, 3, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau FireBird B");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeCinetique().get(1));
			armes.put(0, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeCinetique().get(1));

			blindages.put(0, listModule.getBlindage().get(1));
			blindages.put(0, listModule.getBlindage().get(2));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(8, new Croiseur("Lightning A", listModule.getChassie().get(3), armes, blindages, 0, 5, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Lightning A");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmePlasma().get(1));
			armes.put(1, listModule.getArmePlasma().get(1));
			armes.put(0, listModule.getArmePlasma().get(1));
			armes.put(1, listModule.getArmePlasma().get(1));

			blindages.put(0, listModule.getBlindage().get(1));
			blindages.put(0, listModule.getBlindage().get(1));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(9, new Croiseur("Lightning B", listModule.getChassie().get(3), armes, blindages, 0, 5, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Lightning B");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			
			armes.put(0, listModule.getArmeCinetique().get(1));
			armes.put(1, listModule.getArmeCinetique().get(1));
			armes.put(2, listModule.getArmeCinetique().get(1));
			armes.put(3, listModule.getArmeCinetique().get(1));
			armes.put(4, listModule.getArmeCinetique().get(1));
			armes.put(5, listModule.getArmePlasma().get(1));

			blindages.put(0, listModule.getBlindage().get(1));
			blindages.put(1, listModule.getBlindage().get(3));
			blindages.put(2, listModule.getBlindage().get(2));
			blindages.put(3, listModule.getBlindage().get(2));
			
			//new Croiseur(nom, chassie, armes, blindages, vitesse, techNecessaire, joueur)
			
			listVaisseau.addVaisseau(10, new Croiseur("Ulysse", listModule.getChassie().get(4), armes, blindages, 0, 4, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Ulysse");
			e.printStackTrace();
		}
		try {
			Map<Integer, Arme> armes = new HashMap<Integer, Arme>();
			Map<Integer, Blindage> blindages = new HashMap<Integer, Blindage>();
			armes.put(0, listModule.getArmeCinetique().get(2));
			armes.put(1, listModule.getArmeCinetique().get(2));
			armes.put(2, listModule.getArmeCinetique().get(2));
			blindages.put(0, listModule.getBlindage().get(0));
			blindages.put(1, listModule.getBlindage().get(1));
			listVaisseauPirate.addVaisseau(2, new Croiseur("Pillager", new Chassie("Pillager", "Conçue pour piller", 5, 30, 30, 0, 0, 3, 2, 0, setRessource(5, 0, 5, 0, 500, 5, 0)),armes, blindages, 0, 0, null));
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage() + "  # Vaisseau Pillager");
			e.printStackTrace();
		}

		
		// Ecriture ------------------------------------
		System.out.println("### Start File Generation ###");
		Json json = new Json();
		json.setOutputType(OutputType.json);
		System.out.println("### Start Module File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/FileMaker/Modules_Vaiseaux.json")){
			file.write(json.prettyPrint(listModule));
			file.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("### Start Building File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/FileMaker/Batiments.json")){
			file.write(json.prettyPrint(listBatiment));
			file.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("### Start Science File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/FileMaker/Sciences.json")){
			file.write(json.prettyPrint(listTech));
			file.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("### Start Ship File Generation ###");
		System.out.println("  * Start General Ship File Generation");
		try(FileWriter file = new FileWriter("Ressources/FileMaker/Vaisseaux.json")){
			file.write(json.prettyPrint(listVaisseau));
			file.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("  * Start Pirate Ship File Generation");
		try(FileWriter file = new FileWriter("Ressources/FileMaker/VaisseauxPirate.json")){
			file.write(json.prettyPrint(listVaisseauPirate));
			file.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("### End File Generation ###");
	}
}
