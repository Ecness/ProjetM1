package model.entity.vaisseau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.IntMap;

import model.EnumRessource;
import model.carte.combat.DetailCombat;
import model.entity.player.Joueur;
import model.entity.player.Science;
import model.module.Arme;
import model.module.Blindage;
import model.module.Chassie;
import model.util.MapRessource;

public class Vaisseau implements Cloneable {

	protected int techNecessaire;
	protected int puissance;
	protected int sante;
	protected int santeMax;
	protected int bouclier;
	protected int bouclierMax;
	protected String nom;
	protected Chassie chassie;
	protected Map<Integer, Arme> armes;
	protected Map<Integer, Blindage> blindages;
	protected List<EnumDommageCritique> dommageCritique;
	protected int vitesse;
	protected int fire;
	protected MapRessource cout;
	protected Boolean moteurEndomager;
	protected Boolean moteurDetruit;
	protected Boolean bouclierDetruit;
	protected Joueur joueur;

	public Vaisseau() {
		this("Default", new Chassie(), new HashMap<Integer, Arme>(), new HashMap<Integer, Blindage>(), new ArrayList<EnumDommageCritique>(), 0, 0, null);
	}

	public Vaisseau( String nom, Chassie chassie, int vitesse, int techNecessaire, Joueur joueur) {

		this.puissance = 0;
		this.techNecessaire = techNecessaire;
		this.nom = nom;
		this.sante = chassie.getSanteMax();
		this.santeMax = chassie.getSanteMax();
		this.bouclier = chassie.getBouclierMax();
		this.bouclierMax = chassie.getBouclierMax();
		this.chassie = chassie;
		this.armes = new HashMap<Integer, Arme>();
		this.blindages = new HashMap<Integer, Blindage>();
		this.dommageCritique = new ArrayList<EnumDommageCritique>();
		this.vitesse = vitesse;
		this.fire = 0;
		this.cout = new MapRessource();
		this.moteurDetruit=false;
		this.moteurEndomager=false;
		this.bouclierDetruit=false;
		this.joueur = joueur;
		calculPuissanceTotal();
		addCout();
	}

	public Vaisseau(String nom, Chassie chassie, Map<Integer, Arme> armes, Map<Integer,
			Blindage> blindages, List<EnumDommageCritique> dommageCritique, int vitesse,
			int techNecessaire, Joueur joueur) {

		this.techNecessaire = techNecessaire;
		this.puissance = 0;
		this.nom = nom;
		this.sante = chassie.getSanteMax();
		this.santeMax = chassie.getSanteMax();
		this.bouclier = chassie.getBouclierMax();
		this.bouclierMax = chassie.getBouclierMax();
		this.chassie = chassie;
		this.armes = armes;
		this.blindages = blindages;
		this.vitesse = vitesse;
		this.dommageCritique = dommageCritique;
		this.fire = 0;
		this.cout = new MapRessource();;
		this.moteurDetruit=false;
		this.moteurEndomager=false;
		this.bouclierDetruit=false;
		this.joueur = joueur;
		addBlindage();
		addCout();
		calculPuissanceTotal();
	}

	//-------------------------------------------------------------------------------------------------------------------------

	public void calculPuissanceTotal() {
		for (Entry<Integer, Blindage> blindage : blindages.entrySet()) {
			puissance += blindage.getValue().getPoint();
		}
		for (Entry<Integer, Arme> arme : armes.entrySet()) {
			puissance += arme.getValue().getPoint();
		}
		puissance += chassie.getPoint();
	}

	public void addBlindage() {
		for (Entry<Integer, Blindage> blindage : blindages.entrySet()) {
			santeMax += blindage.getValue().getValeurBlindage();
			bouclierMax += blindage.getValue().getValeurBlouclier();
		}
		sante = santeMax;
		bouclier = bouclierMax;
	}

	public void addCout() {
		for (Entry<Integer, Blindage> blindage : blindages.entrySet()) {
			for (Entry<EnumRessource, Integer> coutBlindage : blindage.getValue().getCout().entrySet()) {
				cout.put(coutBlindage.getKey(), coutBlindage.getValue());
			}
		}
		for (Entry<Integer, Arme> arme : armes.entrySet()) {
			for (Entry<EnumRessource, Integer> coutArme : arme.getValue().getCout().entrySet()) {
				cout.put(coutArme.getKey(), coutArme.getValue()+cout.get(coutArme.getKey()));
			}
		}
		if(!chassie.getCout().isEmpty()) {
			for (EnumRessource e : EnumRessource.values()) {
				if(cout.get(e)!=null) {
					cout.put(e, chassie.getCout().get(e)+cout.get(e));
				}else {
					cout.put(e, chassie.getCout().get(e));
				}
			}			
		}
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

	public void regeneBouclier(DetailCombat detailCombat) {

		bouclier += (int)(bouclierMax*0.1);
		detailCombat.addBouclier(getNom(), (int)(bouclierMax*0.1));

		if(bouclier>bouclierMax) {
			bouclier=bouclierMax;
		}
	}

	public void reparationVaisseau(DetailCombat detailCombat) {

		if(fire>0) {
			fire--;
			detailCombat.addReparationFeu(getNom());
		}else {
			sante += (int)(santeMax*0.05);
			detailCombat.addReparation(getNom(), (int)(santeMax*0.05));
			if(sante>santeMax) {
				sante=santeMax;
			}
		}
	}

	public void reparationCritique() {

		if(!dommageCritique.isEmpty()){
			int id = (int)(dommageCritique.size()*Math.random());
			dommageCritique.remove(id);
		}
	}

	public void dommageFire() {

		sante-=(int)(santeMax*0.05)*fire;
		if(sante<0) {
			sante=0;
		}
	}

	public void addFire() {

		if(fire<3) {
			fire ++;
		}
	}

	@Override
	public Vaisseau clone() throws CloneNotSupportedException {
		Vaisseau clone = new Vaisseau(this.nom, this.chassie, this.armes, this.blindages, this.dommageCritique, this.vitesse, this.techNecessaire, this.joueur);
		clone.setCout(this.cout);
		return clone;

	}

	public IntMap<Science> getScienceMilitaire(){
		return this.joueur.getTechnology().getScienceMillitaire();
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
	public MapRessource getCout() {
		return cout;
	}
	public void setCout(MapRessource cout) {
		this.cout = cout;
	}
	public Boolean getMoteurDetruit() {
		return moteurDetruit;
	}
	public Boolean getMoteurEndomager() {
		return moteurEndomager;
	}
	public void setMoteurDetruit(Boolean moteurDetruit) {
		this.moteurDetruit = moteurDetruit;
	}
	public void setMoteurEndomager(Boolean moteurEndomager) {
		this.moteurEndomager = moteurEndomager;
	}
	public Boolean getBouclierDetruit() {
		return bouclierDetruit;
	}
	public void setBouclierDetruit(Boolean bouclierDetruit) {
		this.bouclierDetruit = bouclierDetruit;
	}
	public int getTechNecessaire() {
		return techNecessaire;
	}
	public void setTechNecessaire(int techNecessaire) {
		this.techNecessaire = techNecessaire;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
