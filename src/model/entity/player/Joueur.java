package model.entity.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import model.carte.stellaire.Ville;
import model.entity.general.General;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;
import model.parametre.EnumRessourceDepart;

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
	private int scienceDepart;
	
	
	public Joueur(String name, EnumNation nation, Systeme systeme, EnumRessourceDepart ressourceDepart) {
		
		this.name = name;
		this.systeme = new ArrayList<Systeme>();
		this.systeme.add(systeme);
		this.nation = nation;
		TRessource = new HashMap<EnumRessource, Integer>();
		for (EnumRessource t : EnumRessource.values()) {
			TRessource.put(t, 0);
		}
		ressourceDepart(ressourceDepart);
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		TGeneral = new ArrayList<General>();
		fileTechnology = new ArrayList<Science>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
		
		this.technology = new TechnologieEtBatiment();
		technology.getScience().getScience().put(-1, new Science("Base", "Pour les techno de base", true, 0, -1, -1));
	}

	public Joueur(String name, EnumNation nation, Systeme systeme,TechnologieEtBatiment technology, EnumRessourceDepart ressourceDepart) {
		
		this.name = name;
		this.systeme = new ArrayList<Systeme>();
		this.systeme.add(systeme);
		this.nation = nation;
		TRessource = new HashMap<EnumRessource, Integer>();
		for (EnumRessource t : EnumRessource.values()) {
			TRessource.put(t, 0);
		}
		ressourceDepart(ressourceDepart);
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		TGeneral = new ArrayList<General>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
		fileTechnology = new ArrayList<Science>();
		this.technology=technology;
		technology.getScience().getScience().put(-1, new Science("Base", "Pour les techno de base", true, 0, -1, -1));
	}
	
	public void ressourceDepart(EnumRessourceDepart e) {
		
		switch (e) {
		case DEPART_DIFFICILE:
			for (EnumRessource t : EnumRessource.values()) {
				switch (t) {
				case SCIENCE:
					this.TRessource.put(t, TRessource.get(t)+5);
					this.scienceDepart=5;
					testScience();
					break;
				case PRODUCTION:
					this.TRessource.put(t, 0);
					break;
				case CREDIT:
					this.TRessource.put(t, 20);
					break;
				default:
					this.TRessource.put(t, 10);
					break;
				}
			}
			break;
		case NORMAL:
			for (EnumRessource t : EnumRessource.values()) {
				switch (t) {
				case SCIENCE:
					this.TRessource.put(t, TRessource.get(t)+5);
					this.scienceDepart=5;
					testScience();
					break;
				case PRODUCTION:
					this.TRessource.put(t, 0);
					break;
				case CREDIT:
					this.TRessource.put(t, 50);
					break;
				default:
					this.TRessource.put(t, 15);
					break;
				}
			}
			break;
		case DEPART_FACILE:
			for (EnumRessource t : EnumRessource.values()) {
				switch (t) {
				case SCIENCE:
					this.TRessource.put(t, TRessource.get(t)+5);
					this.scienceDepart=5;
					testScience();
					break;
				case PRODUCTION:
					this.TRessource.put(t, 0);
					break;
				case CREDIT:
					this.TRessource.put(t, 100);
					break;
				default:
					this.TRessource.put(t, 20);
					break;
				}
			}
			break;
		case DEPART_RAPIDE:
			for (EnumRessource t : EnumRessource.values()) {
				switch (t) {
				case SCIENCE:
					this.TRessource.put(t, TRessource.get(t)+5);
					this.scienceDepart=5;
					testScience();
					break;
				case PRODUCTION:
					this.TRessource.put(t, 0);
					break;
				case CREDIT:
					this.TRessource.put(t, 500);
					break;
				default:
					this.TRessource.put(t, 30);
					break;
				}
			}
			break;
		default:
			for (EnumRessource t : EnumRessource.values()) {
				this.scienceDepart=0;
				this.TRessource.put(t, 0);
			}
			testScience();
			break;
		}
	}
	
	public boolean addRecherche(int numero) {
		
		if(technology.getScience().getScience().get(numero).isRechercher()==false) {
			if(technology.getScience().getScience().get(technology.getScience().getScience().get(numero).getDependanceDeux()).isRechercher()==true
					&& technology.getScience().getScience().get(technology.getScience().getScience().get(numero).getDependanceUn()).isRechercher()==true) {
				fileTechnology.add(technology.getScience().getScience().get(numero));
				return true;
			}
		}
		return false;
	}
	
	public boolean testFinRechercheBonusScience(int bonus) {
		
		if(!fileTechnology.isEmpty()) {	
			
			fileTechnology.get(0).setCout(fileTechnology.get(0).getCout()-bonus);
			
			if(fileTechnology.get(0).getCout()<=0) {
				fileTechnology.get(0).setRechercher(true);
				fileTechnology.remove(0);
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
	
	public void debutDeTour() {
		testFinRecherche();
		for (Ville v : TVille) {
			v.testFinConstruction();
		}
		ajoutRessourceVille();
		ajoutRessourcePlanete();
		testScience();
		regenerationVille();
	}
	
	public void regenerationVille() {
		for (Ville ville : TVille) {
			ville.regenerationPuissance();
		}
	}
	
	public void testScience() {
		
		int scienceTotal=scienceDepart;
		
		for(Systeme systeme : this.systeme) {
			for(Planete planete : systeme.getTPlanete()) {
				if(planete.getJoueur()!=null && planete.getVille()==null) {					
					scienceTotal+=planete.getTRessource().get(EnumRessource.SCIENCE);
				}
			}
		}
		
		if(TVille!=null) {
			for (Ville v : TVille) {
				scienceTotal+=v.getTRessource().get(EnumRessource.SCIENCE);
			}			
		}
		
		if(TRessource.get(EnumRessource.SCIENCE)<scienceTotal) {
			TRessource.put(EnumRessource.SCIENCE, scienceTotal);
		}
	}
	
	
	public void ajoutRessourcePlanete() {
		for(Systeme systeme : this.systeme) {
			for(Planete planete : systeme.getTPlanete()) {
				if(planete.getJoueur()!=null && planete.getVille()==null) {		
					for (EnumRessource t : EnumRessource.values()) {
						switch (t) {
						case ACIER:
							TRessource.put(t, planete.getTRessource().get(t)+TRessource.get(t));
							if(TRessource.get(t)>999) {
								TRessource.put(t, 999);
							}
							break;
						case CREDIT:
							TRessource.put(t, planete.getTRessource().get(t)+TRessource.get(t));
							if(TRessource.get(t)>10000) {
								TRessource.put(t, 10000);
							}
							break;
						case CRISTAL:
							TRessource.put(t, planete.getTRessource().get(t)+TRessource.get(t));
							if(TRessource.get(t)>999) {
								TRessource.put(t, 999);
							}
							break;
						case GAZ:
							TRessource.put(t, planete.getTRessource().get(t)+TRessource.get(t));
							if(TRessource.get(t)>999) {
								TRessource.put(t, 999);
							}
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	
	
	public void ajoutRessourceVille() {
		for (Ville v : TVille) {
			for (EnumRessource t : EnumRessource.values()) {
				switch (t) {
				case ACIER:
					TRessource.put(t, v.getTRessource().get(t)+TRessource.get(t));
					if(TRessource.get(t)>999) {
						TRessource.put(t, 999);
					}
					break;
				case CREDIT:
					TRessource.put(t, v.getTRessource().get(t)+TRessource.get(t));
					if(TRessource.get(t)>10000) {
						TRessource.put(t, 10000);
					}
					break;
				case CRISTAL:
					TRessource.put(t, v.getTRessource().get(t)+TRessource.get(t));
					if(TRessource.get(t)>999) {
						TRessource.put(t, 999);
					}
					break;
				case GAZ:
					TRessource.put(t, v.getTRessource().get(t)+TRessource.get(t));
					if(TRessource.get(t)>999) {
						TRessource.put(t, 999);
					}
					break;
				default:
					break;
				}
			}
		}	
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

	public int getScienceDepart() {
		return scienceDepart;
	}

	public void setScienceDepart(int scienceDepart) {
		this.scienceDepart = scienceDepart;
	}

	public void setFileTechnology(List<Science> fileTechnology) {
		this.fileTechnology = fileTechnology;
	}
}
