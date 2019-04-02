package model.carte.combat;

import model.entity.vaisseau.EnumDommageCritique;

public class DetailCombat {

	public Boolean detruit;
	public String dommage;
	public int dommageTotal;
	public int feu;
	public String dommageCritique;
	public String vaisseaux;
	
	public DetailCombat() {
		detruit = false;
		dommage = "";
		feu = 0;
		dommageTotal=0;
		dommageCritique = "";
		vaisseaux = "";
	}	
	
	public void addDetail(DetailCombat detail) {
		
		detruit=detail.getDetruit();
		dommage+=detail.getDommage();
		feu+=detail.getFeu();
		dommageTotal+=detail.getDommageTotal();
		dommageCritique+=detail.getDommageCritique();
		vaisseaux+=detail.getVaisseaux();
		
	}
	
	public void ajoutNomVaisseau(String vaisseauAttaquant, String vaisseauDefensseur) {
		vaisseaux+="Le vaisseau " + vaisseauAttaquant + " attaque le vaisseau " + vaisseauDefensseur + ".\n";
	}
	public void addDommage(int dommage, String nom) {
		this.dommage+= "L'arme " + nom + " à infliger "+ dommage + " dommage.\n";
		dommageTotal+=dommage;
	}
	public void pasToucher(String nom) {
		this.dommage+= "L'arme " + nom + " a rater sa cible.\n";
	}
	public void addFeu() {
		feu++;
	}
	public void addDommageCritique(EnumDommageCritique dommageCritique, int dommageSuplementaire) {
		this.dommageCritique+="Le navire a subie " + dommageCritique + " et il as reçue " + dommageSuplementaire + " de dommage suplaimentaire.\n";
		dommageTotal+=dommageSuplementaire;
	}
	
	@Override
	public String toString() {
		String detruit=(this.detruit? "oui" : "non");
		
		
		return "Rapport :\n\n" + vaisseaux + "\nVaisseau detruit : " + detruit + "\n\nDommage subie :\n" + dommage + "\nLe total de dommage subie est de "+ dommageTotal + ".\n\nNombre de feu reçue : " + feu + "\n\nDommagecritique :\n" + dommageCritique;
	}
	
	public Boolean getDetruit() {
		return detruit;
	}
	public String getDommage() {
		return dommage;
	}
	public String getDommageCritique() {
		return dommageCritique;
	}
	public int getFeu() {
		return feu;
	}
	public void setDetruit(Boolean detruit) {
		this.detruit = detruit;
	}
	public void setDommage(String dommage) {
		this.dommage = dommage;
	}
	public void setDommageCritique(String dommageCritique) {
		this.dommageCritique = dommageCritique;
	}
	public void setFeu(int feu) {
		this.feu = feu;
	}
	public int getDommageTotal() {
		return dommageTotal;
	}
	public String getVaisseaux() {
		return vaisseaux;
	}
	public void setDommageTotal(int dommageTotal) {
		this.dommageTotal = dommageTotal;
	}
	public void setVaisseaux(String vaisseaux) {
		this.vaisseaux = vaisseaux;
	}
}
