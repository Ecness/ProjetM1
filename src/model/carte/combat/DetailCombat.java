package model.carte.combat;

import java.util.ArrayList;
import java.util.List;

import model.entity.vaisseau.EnumDommageCritique;
import model.entity.vaisseau.Vaisseau;

public class DetailCombat {

	private List<String> combat;
	private List<Vaisseau> VaisseauDetruit;
	private Boolean detruit;
	
	public DetailCombat() {
		combat = new ArrayList<String>();
		VaisseauDetruit = new ArrayList<Vaisseau>();
		detruit=false;
	}	
	
	public void addBouclier(String vaisseau, int montant) {
		combat.add("Le vaisseau " + vaisseau + " a récupéré " + montant + " point de bouclier.\n");
	}
	public void addReparation(String vaisseau,int montant) {
		combat.add("Le vaisseau " + vaisseau + " a récupéré " + montant + " point de vie.\n");
	}
	public void addReparationFeu(String vaisseau) {
		combat.add("Le vaisseau " + vaisseau + " a éteind un feu.\n");
	}
	public void ajoutNomVaisseau(String vaisseauAttaquant, String vaisseauDefensseur) {
		combat.add("Le vaisseau " + vaisseauAttaquant + " attaque le vaisseau " + vaisseauDefensseur + ".\n");
	}
	public void addDommage(int dommage, String nom) {
		combat.add("L'arme " + nom + " à infliger "+ dommage + " dommage.\n");
	}
	public void pasToucher(String nom) {
		combat.add("L'arme " + nom + " a rater sa cible.\n");
	}
	public void addFeu(String vaisseauDefensseur) {
		combat.add("Le navire " + vaisseauDefensseur +" a un feu qui c'est déclaré.\n");
	}
	public void addVaisseauDetruit(Vaisseau vaisseauDefensseur) {
		combat.add("Le navire " + vaisseauDefensseur + " a été détruit.\n");
		detruit=true;
		VaisseauDetruit.add(vaisseauDefensseur);
	}
	public void addDommageCritique(EnumDommageCritique dommageCritique, int dommageSuplementaire) {
		combat.add("Le navire a subie " + dommageCritique + " et il as reçue " + dommageSuplementaire + " de dommage suplaimentaire.\n");
	}
	
	@Override
	public String toString() {
		String totalCombat = "";
		for (String string : combat) {
			totalCombat+=string;
		}
		return "Rapport :\n\n" + totalCombat;
	}
	public Boolean getDetruit() {
		return detruit;
	}
	public void setDetruit(Boolean detruit) {
		this.detruit = detruit;
	}

	public List<Vaisseau> getVaisseauDetruit() {
		return VaisseauDetruit;
	}
}
