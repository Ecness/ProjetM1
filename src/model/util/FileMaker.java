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
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;
import model.module.ListeModule;

public class FileMaker {

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
	
	public static void main(String[] args) {
		
		// Vaisseau ------------------------------------
		
		ListeModule listModule = new ListeModule();
		

		//setRessource(gaz, science, cristal, production, credit, acier)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point)
		listModule.addArmeLaser(0, new Arme("Laser MKI", "Petit module d'arme laser", 5, 90, 3, 45, 5, 10, setRessource(0,0,1,0,10,1)));
		listModule.addArmeLaser(1, new Arme("Laser MKII", "Version amélioré du laser MKI", 5, 75, 6, 45, 5, 15, setRessource(0,0,2,0,10,1)));
		

		//setRessource(gaz, science, cristal, production, credit, acier)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point)
		listModule.addArmeCinetique(0, new Arme("Type 490/3", "Des canon de la marine modifier pour l'environement spacial", 20, 60, 3, 5, 10, 20, setRessource(0,0,0,0,10,3)));
		listModule.addArmeCinetique(1, new Arme("Type 490/2", "Version modifier de notre précédent canon", 20, 75, 2, 5, 10, 25, setRessource(0,0,1,0,10,3)));
		listModule.addArmeCinetique(2, new Arme("Canon électrique", "Une arme à projectile accéléré par une force électromagnétique", 70, 85, 1, 0, 25, 70, setRessource(1,0,2,0,50,5)));
		

		//setRessource(gaz, science, cristal, production, credit, acier)
		//new Arme(nom, description, dommage, precision, nbTire, tauxFeu, critique, point)
		listModule.addArmePlasma(0, new Arme("Fusil Plasma", "Cette arme a un fort potentielle destructeur", 60, 10, 1, 60, 50, 25, setRessource(2,0,0,0,50,1)));
		listModule.addArmePlasma(1, new Arme("Fusil Plasma jumelé", "Arme plasma jumelé", 30, 20, 2, 60, 25, 30, setRessource(3,0,0,0,60,1)));
		

		//setRessource(gaz, science, cristal, production, credit, acier)
		//new Blindage(nom, description, point, valeurBlindage, valeurBlouclier)
		listModule.addBlindage(0, new Blindage("Alliage métalique", "Blindage sommaire", 5, 80, 30, setRessource(0,0,0,0,50,2)));
		listModule.addBlindage(1, new Blindage("Alliage Composite", "Melange de matériaux diver", 10, 150, 80, setRessource(0,0,0,0,60,3)));
		listModule.addBlindage(2, new Blindage("Arc electrique", "Des arc electrifié pour réduire les chance de frape directe", 10, 0, 120, setRessource(0,0,1,0,50,2)));
		listModule.addBlindage(3, new Blindage("Aliage composite MKII", "Blindage renforcé", 20, 200, 110,setRessource(0,0,1,0,50,6)));
		
		//new Chassie(nom, description, point, sante, santeMax, bouclier, bouclierMax, nbArme, nbBlindage, nbModule)
		listModule.addChassie(0, new Chassie("Sonde", "Parfaite pour exploré l'espace en sécurité", 1, 1, 1, 0, 0, 0, 0, 0));
		listModule.addChassie(1, new Chassie("Classe WarHound", "Conçu pour les petit vaisseaux", 50, 150, 150, 20, 20, 2, 1, 1));
		listModule.addChassie(2, new Chassie("Classe DauntLess", "Chassie pour les vaisseaux de reconaissance", 70, 200, 200, 30, 30, 3, 1, 2));
		listModule.addChassie(3, new Chassie("Classe StarLight", "Chassie pour les vaisseaux de ligne ou d'escorte", 120, 300, 300, 50, 50, 4, 2, 2));
		listModule.addChassie(4, new Chassie("Classe NightBringer", "Chassie pour les vaisseaux de combat",350, 500, 500, 110, 110, 6, 4, 3));
		
		// Batiment ------------------------------------
		
		ListBatiment listBatiment = new ListBatiment();
		
		//new BatimentPlanete(nom, description, techNecessaire, bonus, cout)
		//setRessource(gaz, science, cristal, production, credit, acier)
		listBatiment.addBatimentsPlanete(0, new BatimentPlanete("Spatioport", "Améliore les echanges avec les autre colonie", 3, setRessource(0,0,0,1,5,1), setRessource(1,0,2,0,20,2)));
		listBatiment.addBatimentsPlanete(1, new BatimentPlanete("Centre de recherche xenos", "Recherche sur les forme de vie extra-planetaire", 4, setRessource(0,5,0,1,-2,0), setRessource(1,0,5,0,50,5)));
		
		//new BatimentVille(nom, description, techNecessaire, bonus, cout)
		//setRessource(gaz, science, cristal, production, credit, acier)
		listBatiment.addBatimentsVille(0, new BatimentVille("Quartier ouvrier", "Quartier résidentelle peu luxueux", 0, setRessource(0,0,0,5,2,0), 20));
		listBatiment.addBatimentsVille(1, new BatimentVille("Mega-usine", "Usine de la taille d'une ville servant a traiter les ressources", 1, setRessource(2,0,2,2,-2,2), 50));
		listBatiment.addBatimentsVille(2, new BatimentVille("Usine Robotique", "Usine de création robotique", 1, setRessource(0,0,1,1,-1,2), 20));
		listBatiment.addBatimentsVille(3, new BatimentVille("Batrie Planétaire", "Batrie de defense planétaire", 0, setRessource(0,0,0,-1,-2,-1), 20));
		listBatiment.addBatimentsVille(4, new BatimentVille("Centre de recherche", "Petit centre de recherche", 0, setRessource(0,2,0,-1,-1,0), 20));
		listBatiment.addBatimentsVille(4, new BatimentVille("Banque central planétaire", "Centralise les fonts de la colonie", 0, setRessource(0,0,0,-1,10,0), 20));		
		
		
		// Science ------------------------------------
		Technologie listTech = new Technologie();
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceBatiment(0, new Science("Voyage Spacial", "Le Commencement", true, 0, 0, 0));
		listTech.addScienceBatiment(1, new Science("Mega-usine", "Delock la construction de Mega-usine", false, 50, 0, 0));
		listTech.addScienceBatiment(2, new Science("Defence planétaire", "Delock la construction de defence planétaire", false, 20, 0, 0));
		listTech.addScienceBatiment(3, new Science("Spatioport", "Delock la construction de spatioport", false, 80, 1, 2));
		listTech.addScienceBatiment(4, new Science("Centre de recherche", "Delock la construction de centre de recherche medium", false, 80, 1, 0));
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceBonus(0, new Science("Recherche des modules basic", "Le commencement des améliorations", true, 0, 0, 0));
		listTech.addScienceBonus(1, new Science("Module d'arme Laser Booster", "Ameliore de 10% la prescision des armes laser", false, 50, 0, 0));
		listTech.addScienceBonus(2, new Science("Module d'arme perforante", "Auguemente de 10% les dommages des armes cinétique", false, 50, 0, 0));
		listTech.addScienceBonus(3, new Science("Module d'arme suplementaire", "Ajoute un slot d'arme", false, 100, 2, 3));
		
		//new Science(nom, description, rechercher, cout, dependanceUn, dependanceDeux)
		listTech.addScienceMillitaire(0, new Science("Sonde", "Début de la découverte spacial", true, 0, 0, 0));
		listTech.addScienceMillitaire(1, new Science("Vaiseau de defence", "Premier vaiseau conçue pour la defence de la colonie", false, 20, 0, 0));
		listTech.addScienceMillitaire(2, new Science("Fusion solaire controler", "Permet la construction d'arme Plasma", false, 50, 1, 0));
		listTech.addScienceMillitaire(3, new Science("Module MKII", "Delock les armes tier 2", false, 50, 1, 2));
		listTech.addScienceMillitaire(4, new Science("RailGun", "Permet la construction d'un canon éléctrique", false, 100, 3, 0));
		listTech.addScienceMillitaire(5, new Science("Vaisseau d'escorte", "Délock les vaisseau de tier 2", false, 60, 1, 2));
		listTech.addScienceMillitaire(6, new Science("Croiseur stailaire", "Délock les vaisseau de tier 3", false, 150, 4, 5));
		
		// Ecriture ------------------------------------
		System.out.println("### Start File Generation ###");
		Json json = new Json();
		json.setOutputType(OutputType.json);
		System.out.println("### Start Ship File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/Vaisseaux/Modules_Vaiseaux.json")){
			file.write(json.prettyPrint(listModule));
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### Start Building File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/Batiments/Batiments.json")){
			file.write(json.prettyPrint(listBatiment));
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### Start Science File Generation ###");
		try(FileWriter file = new FileWriter("Ressources/Sciences/Sciences.json")){
			file.write(json.prettyPrint(listTech));
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("### End File Generation ###");
	}
}
