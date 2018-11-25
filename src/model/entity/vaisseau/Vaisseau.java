package model.entity.vaisseau;

import model.module.EnumArme;
import model.module.EnumBlindage;
import model.module.EnumModulVaisseau;

public class Vaisseau {

	private int puissance;
	private int hp;
	private String nom;
	private EnumModulVaisseau[] TModuleVaisseau;
	private EnumArme[] TArme;
	private EnumBlindage[] TBlindage;
	private int vitesse;
	
	
	
	
	public Vaisseau( String nom, int nbModule, int nbArme, int nbBlindage, int puissance, int hp,
			int vitesse) {
		
		this.puissance = puissance;
		this.nom = nom;
		this.setHp(hp);
		TModuleVaisseau = new EnumModulVaisseau[nbModule];
		TArme = new EnumArme[nbArme];
		TBlindage = new EnumBlindage[nbBlindage];
		this.vitesse = vitesse;
	}


	public Vaisseau(int puissance, String nom, EnumModulVaisseau[] tModuleVaisseau, EnumArme[] tArme,
			EnumBlindage[] tBlindage, int vitesse, int hp) {
		
		this.puissance = puissance;
		this.nom = nom;
		this.setHp(hp);
		TModuleVaisseau = tModuleVaisseau;
		TArme = tArme;
		TBlindage = tBlindage;
		this.vitesse = vitesse;
		
	}


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


	public EnumModulVaisseau[] getTModuleVaisseau() {
		return TModuleVaisseau;
	}


	public void setTModuleVaisseau(EnumModulVaisseau[] tModuleVaisseau) {
		TModuleVaisseau = tModuleVaisseau;
	}


	public EnumArme[] getTArme() {
		return TArme;
	}


	public void setTArme(EnumArme[] tArme) {
		TArme = tArme;
	}


	public EnumBlindage[] getTBlindage() {
		return TBlindage;
	}


	public void setTBlindage(EnumBlindage[] tBlindage) {
		TBlindage = tBlindage;
	}


	public int getVitesse() {
		return vitesse;
	}


	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}
	
}
