package model.entity.vaisseau;

import java.util.Map;

import model.EnumRessource;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class Croiseur extends Vaisseau {

	public Croiseur(String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer, Blindage> blindages,
			int puissance, int sante, int bouclier, int vitesse, Map<EnumRessource, Integer> cout) {
		super(nom, chassie, puissance, sante, bouclier, vitesse, cout);
		// TODO Auto-generated constructor stub
	}
	
}
