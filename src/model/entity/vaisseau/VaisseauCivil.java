package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;

import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public class VaisseauCivil extends Vaisseau {

	public VaisseauCivil(String nom, int puissance, int sante, int bouclier,int vitesse, Chassie chassie) {
		super(puissance, nom, chassie, new HashMap<Integer, Arme>(), new HashMap<Integer, Blindage>(),
				new ArrayList<EnumDommageCritique>(), vitesse, sante, bouclier);
	}
	
	
}
