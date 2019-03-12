package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class Croiseur extends Vaisseau {

	public Croiseur(String nom, Chassie chassie, Map<Integer, Arme> armes, 
			Map<Integer, Blindage> blindages, int vitesse, Map<EnumRessource, Integer> cout) {
		super( nom, chassie, armes, blindages, new ArrayList<EnumDommageCritique>() , vitesse, cout);
		// TODO Auto-generated constructor stub
	}
	
	public Croiseur() {
		this("Default", new Chassie(), new HashMap<Integer, Arme>(), new HashMap<Integer, Blindage>(), 0, new HashMap<EnumRessource, Integer>());
	}
	
	
	
	@Override
	public String toString() {
		String string ="";
		for (EnumDommageCritique enumDommageCritique : dommageCritique) {
			string += enumDommageCritique.name() + "\n";
		}
		
		return "Croiseur \"" + nom +"\" de " + chassie.getNom() + "\n Point : " + puissance +  
				"\n Vie : " + sante + "/" + santeMax + "\n Bouclier : " + bouclier + "/" + bouclierMax +
				"\n Vitesse : " + vitesse + "\n DommageCritique : " + string + "\n Nombre de feu : " + fire;
	}
}
