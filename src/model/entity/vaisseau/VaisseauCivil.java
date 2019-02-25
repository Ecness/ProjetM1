package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class VaisseauCivil extends Vaisseau {

	public VaisseauCivil(String nom, int puissance, int sante, int bouclier,int vitesse, Chassie chassie, Map<EnumRessource, Integer> cout) {
		super(puissance, nom, chassie, null, null, new ArrayList<EnumDommageCritique>(), vitesse, sante, bouclier, cout);
	}
	
	
}
