package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.Map;

import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class Corvette extends Vaisseau {

	public Corvette(String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer, Blindage> blindages, 
			int puissance, int sante, int bouclier, int vitesse) {
		super(puissance, nom, chassie, armes, blindages, new ArrayList<EnumDommageCritique>(), vitesse, sante, bouclier);
		// TODO Auto-generated constructor stub
	}

}
