package model.entity.vaisseau;

import java.util.Map;

import model.EnumRessource;
import model.module.Chassie;

public class VaisseauCivil extends Vaisseau {

	public VaisseauCivil(String nom, int puissance,int vitesse, Chassie chassie, Map<EnumRessource, Integer> cout) {
		super(nom, chassie, vitesse, cout);
	}
	
	public VaisseauCivil() {
		this("Default", 0, 0, null, null);
	}
}
