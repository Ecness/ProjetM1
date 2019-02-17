package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;

public abstract class Vaisseau {

	private int puissance;
	private int sante;
	private int santeMax;
	private int bouclier;
	private int bouclierMax;
	private String nom;
	private Chassie chassie;
	private Map<Integer, Arme> armes;
	private Map<Integer, Blindage> blindages;
	private List<EnumDommageCritique> dommageCritique;
	private int vitesse;
	private int fire;
	
	
	public Vaisseau( String nom, Chassie chassie, int puissance, int santeMax,
			int bouclierMax, int vitesse) {
		
		this.puissance = puissance;
		this.nom = nom;
		this.sante = santeMax;
		this.santeMax = santeMax;
		this.bouclier = bouclierMax;
		this.bouclierMax = bouclierMax;
		this.chassie = chassie;
		this.armes = new HashMap<Integer, Arme>();
		this.blindages = new HashMap<Integer, Blindage>();
		this.dommageCritique = new ArrayList<EnumDommageCritique>();
		this.vitesse = vitesse;
		this.fire = 0;
	}

	public Vaisseau(int puissance, String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer,
			Blindage> blindages, List<EnumDommageCritique> dommageCritique, int vitesse, int santeMax, int bouclierMax) {
		
		this.puissance = puissance;
		this.nom = nom;
		this.sante = santeMax;
		this.santeMax = santeMax;
		this.bouclier = bouclierMax;
		this.bouclierMax = bouclierMax;
		this.chassie = chassie;
		this.armes = armes;
		this.blindages = blindages;
		this.vitesse = vitesse;
		this.dommageCritique = dommageCritique;
		this.fire = 0;
		addBlindage();
	}

	//-------------------------------------------------------------------------------------------------------------------------
	
	public void addBlindage() {
		for (Entry<Integer, Blindage> blindage : blindages.entrySet()) {
			santeMax += blindage.getValue().getValeurBlindage();
			bouclierMax += blindage.getValue().getValeurBlouclier();
		}
		sante = santeMax;
		bouclier = bouclierMax;
	}
	
	public void addArme(int id, Arme arme) {
		armes.put(id, arme);
	}
	public void addBlindage(int id, Blindage blindage) {
		blindages.put(id, blindage);
	}
	
	public void prendreDommage(int dommage){
		if(bouclier>0) {
			if(bouclier>=dommage) {
				bouclier-=dommage;
			}else {
				dommage-=bouclier;
				bouclier=0;
				sante-=dommage;
			}
		}else {
			sante-=dommage;
		}
		if(sante<0) {
			sante=0;
		}
	}
	
	public void regeneBouclier() {
		
		bouclier += (int)(bouclierMax*0.1);
		
		if(bouclier>bouclierMax) {
			bouclier=bouclierMax;
		}
	}
	
	public void reparation() {
		
		if(fire>0) {
			fire--;
		}else {
			sante += (int)(santeMax*0.05);
			
			if(sante>santeMax) {
				sante=santeMax;
			}
		}
	}
	
	public void dommageFire() {
	
		sante-=(santeMax*0.05)*fire;
		if(sante<0) {
			sante=0;
		}
	}
	
	public void addFire() {
		
		if(fire<3) {
			fire ++;
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------------

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Chassie getChassie() {
		return chassie;
	}

	public void setChassie(Chassie chassie) {
		this.chassie = chassie;
	}

	public Map<Integer, Arme> getArmes() {
		return armes;
	}

	public void setArmes(Map<Integer, Arme> armes) {
		this.armes = armes;
	}

	public Map<Integer, Blindage> getBlindages() {
		return blindages;
	}

	public void setBlindages(Map<Integer, Blindage> blindages) {
		this.blindages = blindages;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getSante() {
		return sante;
	}

	public void setSante(int sante) {
		this.sante = sante;
	}

	public int getBouclier() {
		return bouclier;
	}

	public void setBouclier(int bouclier) {
		this.bouclier = bouclier;
	}

	public int getSanteMax() {
		return santeMax;
	}

	public void setSanteMax(int santeMax) {
		this.santeMax = santeMax;
	}

	public int getBouclierMax() {
		return bouclierMax;
	}

	public void setBouclierMax(int bouclierMax) {
		this.bouclierMax = bouclierMax;
	}

	public List<EnumDommageCritique> getDommageCritique() {
		return dommageCritique;
	}

	public void setDommageCritique(List<EnumDommageCritique> dommageCritique) {
		this.dommageCritique = dommageCritique;
	}	
}
