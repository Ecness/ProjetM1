package model.entity.vaisseau;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.entity.player.Joueur;
import model.module.Chassie;

public class VaisseauCivil extends Vaisseau {

	public VaisseauCivil(String nom,int vitesse, Chassie chassie, int techNecessaire, Joueur joueur) {
		super(nom, chassie, vitesse, techNecessaire, joueur);
	}
	
	public VaisseauCivil() {
		this("Default", 0, new Chassie(), 0, null);
	}
}
