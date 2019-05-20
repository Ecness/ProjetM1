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
		combat.add("Le vaisseau " + vaisseau + " a récupéré " + montant + " point(s) de bouclier.\n");
	}
	public void addReparation(String vaisseau,int montant) {
		combat.add("Le vaisseau " + vaisseau + " a récupéré " + montant + " point(s) de vie.\n");
	}
	public void addReparationFeu(String vaisseau) {
		combat.add("Le vaisseau " + vaisseau + " a éteint un feu.\n");
	}
	public void ajoutNomVaisseau(String vaisseauAttaquant, String vaisseauDefensseur) {
		combat.add("Le vaisseau " + vaisseauAttaquant + " attaque le vaisseau " + vaisseauDefensseur + ".\n");
	}
	public void addDommage(int dommage, String nom) {
		combat.add("L'arme " + nom + " a infligé "+ dommage + " dommage(s).\n");
	}
	public void pasToucher(String nom) {
		combat.add("L'arme " + nom + " a raté sa cible.\n");
	}
	public void addFeu(String vaisseauDefensseur) {
		combat.add("Le vaisseau " + vaisseauDefensseur + " a un feu qui s'est déclaré.\n");
	}
	public void addVaisseauDetruit(Vaisseau vaisseauDefensseur) {
		combat.add("Le vaisseau " + vaisseauDefensseur + " a été détruit.\n");
		detruit=true;
		VaisseauDetruit.add(vaisseauDefensseur);
	}
	public void addDommageCritique(EnumDommageCritique dommageCritique, int dommageSuplementaire) {
		combat.add("Le vaisseau a subi " + dommageCritique + " dégât(s) et il a reçu " + dommageSuplementaire + " dégât(s) supplémentaire(s).\n");
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
