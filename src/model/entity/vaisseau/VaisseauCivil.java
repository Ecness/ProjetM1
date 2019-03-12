package model.entity.vaisseau;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.module.Chassie;

public class VaisseauCivil extends Vaisseau {

	public VaisseauCivil(String nom,int vitesse, Chassie chassie, Map<EnumRessource, Integer> cout) {
		super(nom, chassie, vitesse, cout);
	}
	
	public VaisseauCivil() {
		this("Default", 0, new Chassie(), new HashMap<EnumRessource, Integer>());
	}
}
