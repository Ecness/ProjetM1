package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.entity.player.Joueur;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class Corvette extends Vaisseau {

	public Corvette(String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer,
			Blindage> blindages, int vitesse, int techNecessaire, Joueur joueur) {
		super( nom, chassie, armes, blindages, new ArrayList<EnumDommageCritique>() , vitesse, techNecessaire, joueur);
	}

	public Corvette() {
		this("Default", new Chassie(), new HashMap<Integer, Arme>(), new HashMap<Integer, Blindage>(), 0, 0, null);
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
