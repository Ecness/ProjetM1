package model.entity.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
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
	private Science science;
	private List<General> TGeneral;
	private Vaisseau[] patternVaisseau ;
	
	public Joueur(String name, EnumNation nation, List<Flotte> tFlotte,
			List<Ville> tVille, Science science, List<General> tGeneral, Vaisseau[] patternVaisseau) {
		
		this.name = name;
		this.nation = nation;
		TRessource = new HashMap<EnumRessource, Integer>();
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		this.science = new Science();
		TGeneral = new ArrayList<General>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
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

	public Science getScience() {
		return science;
	}

	public void setScience(Science science) {
		this.science = science;
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
	
	
}
