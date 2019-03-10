package model.carte.combat;

import java.util.ArrayList;
import java.util.List;

import model.entity.vaisseau.EnumDommageCritique;

public class DetailCombat {

	public Boolean detruit;
	public int dommage;
	public int feu;
	public List<EnumDommageCritique> dommageCritique;
	
	public DetailCombat() {
		detruit = false;
		dommage = 0;
		feu = 0;
		dommageCritique = new ArrayList<EnumDommageCritique>();
	}	
	
	public void addDommage(int dommage) {
		this.dommage+=dommage;
	}
	public void addFeu() {
		feu++;
	}
	public void addDommageCritique(EnumDommageCritique dommage) {
		dommageCritique.add(dommage);
	}
	
	@Override
	public String toString() {
		String string ="";
		for (EnumDommageCritique enumDommageCritique : dommageCritique) {
			string += enumDommageCritique.name() + "\n";
		}
		return "Rapport :\n Vaisseau detruit : " + detruit + "\nDommage subie : " + dommage + "\nNombre de feu reçue : " + feu + "\nDommagecritique : " + string;
	}
	
	public Boolean getDetruit() {
		return detruit;
	}
	public int getDommage() {
		return dommage;
	}
	public List<EnumDommageCritique> getDommageCritique() {
		return dommageCritique;
	}
	public int getFeu() {
		return feu;
	}
	public void setDetruit(Boolean detruit) {
		this.detruit = detruit;
	}
	public void setDommage(int dommage) {
		this.dommage = dommage;
	}
	public void setDommageCritique(List<EnumDommageCritique> dommageCritique) {
		this.dommageCritique = dommageCritique;
	}
	public void setFeu(int feu) {
		this.feu = feu;
	}
}
