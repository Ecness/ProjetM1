package model.entity.player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
import model.carte.stellaire.Systeme;
import model.carte.stellaire.Ville;
import model.entity.general.General;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;

public class Joueur {
	
	private String name;
	private EnumNation nation;
	private Map<EnumRessource, Integer> TRessource;
	private List<Flotte> TFlotte;
	private List<Ville> TVille;
	private TechnologieEtBatiment technology;
	private List<Science> fileTechnology;
	private List<General> TGeneral;
	private Vaisseau[] patternVaisseau;
	private List<Systeme> systeme;
	
	
	public Joueur(String name, EnumNation nation, Systeme systeme) {
		
		this.name = name;
		this.systeme = new ArrayList<Systeme>();
		this.systeme.add(systeme);
		this.nation = nation;
		TRessource = new HashMap<EnumRessource, Integer>();
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		TGeneral = new ArrayList<General>();
		fileTechnology = new ArrayList<Science>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
		
		try {
			this.technology = (TechnologieEtBatiment) model.util.XMLTools.decodeFromFile("./Ressource/tech.xml");
		} catch (FileNotFoundException e) {
			System.out.println("File not found : erreur sur le chemin d'accer ou le fichier et manquant");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exeption : erreur sur les flux entré/sortie");
			e.printStackTrace();
		}
		technology.getScience().put(-1, new Science("Base", "Pour les techno de base", true, 0, -1, -1));
	}

	public Joueur(String name, EnumNation nation, Systeme systeme,TechnologieEtBatiment technology) {
		
		this.name = name;
		this.systeme = new ArrayList<Systeme>();
		this.systeme.add(systeme);
		this.nation = nation;
		TRessource = new HashMap<EnumRessource, Integer>();
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		TGeneral = new ArrayList<General>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
		fileTechnology = new ArrayList<Science>();
		this.technology=technology;
		technology.getScience().put(-1, new Science("Base", "Pour les techno de base", true, 0, -1, -1));
	}
	
	public boolean addRecherche(int numero) {
		
		if(technology.getScience().get(numero).isRechercher()==false) {
			if(technology.getScience().get(technology.getScience().get(numero).getDependanceDeux()).isRechercher()==true
					&& technology.getScience().get(technology.getScience().get(numero).getDependanceUn()).isRechercher()==true) {
				fileTechnology.add(technology.getScience().get(numero));
				return true;
			}
		}
		return false;
	}
	
	public boolean testFinRecherche() {
		if(TRessource.get(EnumRessource.SCIENCE)>0 && !fileTechnology.isEmpty()) {	
			
			fileTechnology.get(0).setCout(fileTechnology.get(0).getCout()-TRessource.get(EnumRessource.SCIENCE));
			
			if(fileTechnology.get(0).getCout()<=0) {
				fileTechnology.get(0).setRechercher(true);
				fileTechnology.remove(0);
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EnumNation getNation() {
		return nation;
	}

	public void setNation(EnumNation nation) {
		this.nation = nation;
	}

	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}

	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}

	public List<Flotte> getTFlotte() {
		return TFlotte;
	}

	public void setTFlotte(List<Flotte> tFlotte) {
		TFlotte = tFlotte;
	}

	public List<Ville> getTVille() {
		return TVille;
	}

	public void setTVille(List<Ville> tVille) {
		TVille = tVille;
	}
	
	public void ajoutVille(Ville ville) {
		TVille.add(ville);
	}

	public List<General> getTGeneral() {
		return TGeneral;
	}

	public void setTGeneral(List<General> tGeneral) {
		TGeneral = tGeneral;
	}

	public Vaisseau[] getPatternVaisseau() {
		return patternVaisseau;
	}

	public void setPatternVaisseau(Vaisseau[] patternVaisseau) {
		this.patternVaisseau = patternVaisseau;
	}

	public List<Systeme> getSysteme() {
		return systeme;
	}

	public void setSysteme(List<Systeme> systeme) {
		this.systeme = systeme;
	}

	public TechnologieEtBatiment getTechnology() {
		return technology;
	}

	public void setTechnology(TechnologieEtBatiment technology) {
		this.technology = technology;
	}

	public List<Science> getFileTechnology() {
		return fileTechnology;
	}

	public void setFileTechnology(ArrayList<Science> fileTechnology) {
		this.fileTechnology = fileTechnology;
	}

}
