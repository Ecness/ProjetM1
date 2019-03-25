package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class Corvette extends Vaisseau {

	public Corvette(String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer,
			Blindage> blindages, int vitesse,  Map<EnumRessource, Integer> cout, int techNecessaire) {
		super( nom, chassie, armes, blindages, new ArrayList<EnumDommageCritique>() , vitesse, cout, techNecessaire);
		// TODO Auto-generated constructor stub
	}

	public Corvette() {
		this("Default", new Chassie(), new HashMap<Integer, Arme>(), new HashMap<Integer, Blindage>(), 0, new HashMap<EnumRessource, Integer>(), 0);
	}
	
	@Override
	public String toString() {
		
		String string ="";
		for (EnumDommageCritique enumDommageCritique : dommageCritique) {
			string += enumDommageCritique.name() + "\n";
		}
		
		return "Corvette \"" + nom +"\" de " + chassie.getNom() + "\n Point : " + puissance +  
				"\n Vie : " + sante + "/" + santeMax + "\n Bouclier : " + bouclier + "/" + bouclierMax +
				"\n Vitesse : " + vitesse + "\n DommageCritique : " + string + "\n Nombre de feu : " + fire;
	}
}
