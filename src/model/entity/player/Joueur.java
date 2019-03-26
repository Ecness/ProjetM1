package model.entity.player;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;

import model.EnumRessource;
import model.carte.stellaire.EnumTypePlanete;
import model.carte.stellaire.EnumTypeSysteme;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import model.carte.stellaire.Ville;
import model.entity.general.General;
import model.entity.player.donnee.Technologie;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;
import model.parametre.EnumRessourceDepart;
import view.launcher.Project;

public class Joueur {
	
	private String name;
	private EnumNation nation;
	private Color couleur;
	private Map<EnumRessource, Integer> TRessource;
	private Map<EnumRessource, Integer> TRessourceMax;
	private List<Flotte> TFlotte;
	private List<Ville> TVille;
	private Technologie technology;
	private List<General> TGeneral;
	private Vaisseau[] patternVaisseau;
	private List<Systeme> systeme;
	private int scienceDepart;
	private List<Science> fileTechnology;
	
	
	public Joueur(String name, EnumNation nation, Color couleur, EnumRessourceDepart ressourceDepart) {
		
		this.name = name;
		this.systeme = new ArrayList<Systeme>();
		TFlotte = new ArrayList<Flotte>();
		TVille = new ArrayList<Ville>();
		TGeneral = new ArrayList<General>();
		this.patternVaisseau = new Vaisseau[10];//A deffinir
		this.fileTechnology = new ArrayList<Science>();
		this.technology = new Technologie();
		this.nation = nation;
		this.couleur = couleur;
		TRessource = new HashMap<EnumRessource, Integer>();
		for (EnumRessource t : EnumRessource.values()) {
			TRessource.put(t, 0);
		}
		TRessourceMax = new HashMap<EnumRessource, Integer>();
		//TODO Définir le montant maximum de ressource de départ
		for (EnumRessource t : EnumRessource.values()) {
			if (t != EnumRessource.SCIENCE && t != EnumRessource.PRODUCTION) {
				TRessourceMax.put(t, 500);
			}
		}
		placementInitial();
		ressourceDepart(ressourceDepart);
		try{
			loadTechFile(nation.getPath()+"/Sciences/Sciences.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadTechFile(String path) {
		Json parser = new Json();
		try(FileReader file = new FileReader(path)) {
			
			Technologie tech = parser.fromJson(Technologie.class, file);
			
		    this.technology=tech;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public boolean addRechercheMilitaire(int numero) {
		
		if(technology.getScienceMillitaire().get(numero).isRechercher()==false) {
			if(technology.getScienceMillitaire().get(technology.getScienceMillitaire().get(numero).getDependanceDeux()).isRechercher()==true
					&& technology.getScienceMillitaire().get(technology.getScienceMillitaire().get(numero).getDependanceUn()).isRechercher()==true) {
				fileTechnology.add(technology.getScienceMillitaire().get(numero));
				return true;
			}
		}
		return false;
	}
	public boolean addRechercheBonus(int numero) {
		
		if(technology.getScienceBonus().get(numero).isRechercher()==false) {
			if(technology.getScienceBonus().get(technology.getScienceBonus().get(numero).getDependanceDeux()).isRechercher()==true
					&& technology.getScienceBonus().get(technology.getScienceBonus().get(numero).getDependanceUn()).isRechercher()==true) {
				fileTechnology.add(technology.getScienceBonus().get(numero));
				return true;
			}
		}
		return false;
	}
	public boolean addRechercheBatiment(int numero) {
	
		if(technology.getScienceBatiment().get(numero).isRechercher()==false) {
			if(technology.getScienceBatiment().get(technology.getScienceBatiment().get(numero).getDependanceDeux()).isRechercher()==true
					&& technology.getScienceBatiment().get(technology.getScienceBatiment().get(numero).getDependanceUn()).isRechercher()==true) {
				fileTechnology.add(technology.getScienceBatiment().get(numero));
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
	
	/**
	 * Limitation des ressources
	 */
	public void limitRessources() {
		for (EnumRessource ressource : EnumRessource.values()) {
			if (TRessourceMax.containsKey(ressource) &&
					TRessource.get(ressource) >= TRessourceMax.get(ressource)) {
				TRessource.put(ressource, TRessourceMax.get(ressource));
			}
			
			if (TRessource.get(ressource) <= 0) {
				TRessource.put(ressource, 0);
			}
		}
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
						if (t != EnumRessource.SCIENCE && t != EnumRessource.PRODUCTION) {
							TRessource.put(t, planete.getTRessource().get(t)+TRessource.get(t));
							
							if (TRessource.get(t) > TRessourceMax.get(t)) {
								TRessource.put(t, TRessourceMax.get(t));
							}
						}
						
						if (TRessource.get(t) < 0) {
							TRessource.put(t, 0);
						}
					}
				}
			}
		}
	}
	
	
	public void ajoutRessourceVille() {
		for (Ville v : TVille) {
			for (EnumRessource t : EnumRessource.values()) {
				if (t != EnumRessource.SCIENCE && t != EnumRessource.PRODUCTION) {
					TRessource.put(t, v.getTRessource().get(t)+TRessource.get(t));
					
					if (TRessource.get(t) > TRessourceMax.get(t)) {
						TRessource.put(t, TRessourceMax.get(t));
					}
				}
				
				if (TRessource.get(t) < 0) {
					TRessource.put(t, 0);
				}
			}
		}	
	}
	
	/**
	 * Placement initial du joueur sur la carte stellaire
	 */
	private void placementInitial() {
		Systeme depart;
		
		do {
			depart = Project.galaxie.getListeSysteme().get(new Random().nextInt(Project.galaxie.getListeSysteme().size()));
		} while (depart.getJoueur() != null || 
				depart.getTypeSysteme().equals(EnumTypeSysteme.NEBULEUSE) || depart.getTypeSysteme().equals(EnumTypeSysteme.TROU_NOIR));
		
		Planete planeteDepart = new Planete(EnumTypePlanete.typeHabitable(), Project.parametre.getAbondanceRessource(), depart.getTPlanete().size(), this);
		Ville ville = new Ville(this, planeteDepart);
		planeteDepart.setVille(ville);
		this.TVille.add(ville);

		if(depart.getTPlanete().size()>= Project.parametre.getNbMaxPlanete()) {
			depart.getTPlanete().set(0, planeteDepart);
		}else {
			depart.ajoutPlanete(planeteDepart);
		}
		
		depart.setJoueur(this);
		this.systeme.add(depart);
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

	public Color getCouleur() {
		return couleur;
	}

	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}

	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}

	public Map<EnumRessource, Integer> getTRessourceMax() {
		return TRessourceMax;
	}

	public void setTRessourceMax(Map<EnumRessource, Integer> tRessourceMax) {
		TRessourceMax = tRessourceMax;
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
	
	public void addSysteme(Systeme systeme) {
		this.systeme.add(systeme);
	}

	public Technologie getTechnology() {
		return technology;
	}

	public void setTechnology(Technologie technology) {
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
