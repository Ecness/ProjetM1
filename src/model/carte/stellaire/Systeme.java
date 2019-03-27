package model.carte.stellaire;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.IntMap.Entry;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.BatimentVille;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.ListVaisseaux;
import model.entity.vaisseau.Vaisseau;
import model.parametre.EnumAbondanceRessource;
import model.util.Coordonnees;
import view.launcher.Project;

public class Systeme {
	private Coordonnees coordonnees;
	private int idSysteme;
	private List<Planete> TPlanete;
	private Joueur joueur;
	/**Nombre de liaison vers d'autres systèmes*/
	private int nbLiens;
	/**Nombre de liens maximum vers d'autres systèmes*/
	private int nbLiensMax;
	/**Liens du système vers d'autres systèmes*/
	private HashMap<Systeme, Vector2> liens;
	private List<Anomalie> TAnomalie;
	private List<Flotte> flottes;
	private GenerationRessourceEtAnomalie ressourceEtAnomalie;
	private EnumTypeSysteme typeSysteme;
	private Button bouton;

	public Systeme(EnumAbondanceRessource nbRessource, int maxPlanete, int maxAnomalie, int idSysteme) {
		coordonnees = new Coordonnees();
		this.idSysteme=idSysteme;
		this.ressourceEtAnomalie=new GenerationRessourceEtAnomalie();
		this.typeSysteme=EnumTypeSysteme.type();
		TPlanete = new ArrayList<Planete>();
		this.joueur = null;
		TAnomalie = new ArrayList<Anomalie>();
		this.flottes = new ArrayList<Flotte>();
		if(typeSysteme!=EnumTypeSysteme.NEBULEUSE && typeSysteme!=EnumTypeSysteme.TROU_NOIR) {
			generationSystem(nbRessource, maxPlanete);
		}
		generationAnomalie(maxAnomalie);
		
		liens = new HashMap<Systeme, Vector2>();
		nbLiensMax = generationNbLiens();
		bouton = new Button(Project.skin);
		bouton.setPosition(getX() - 10, getY() - 10);
		bouton.setSize(20, 20);
		bouton.setColor(0, 0, 0, 0);
		Systeme sys = this;
		bouton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.systemeSelectionne = sys;
				Project.changeSysteme = true;
			}
			
		});
	}
	
	public Systeme(int x, int y, EnumAbondanceRessource nbRessource, int maxPlanete, int maxAnomalie, int idSysteme) {
		coordonnees = new Coordonnees(x, y);
		this.idSysteme=idSysteme;
		this.ressourceEtAnomalie=new GenerationRessourceEtAnomalie();
		this.typeSysteme=EnumTypeSysteme.type();
		TPlanete = new ArrayList<Planete>();
		this.joueur = null;
		TAnomalie = new ArrayList<Anomalie>();
		this.flottes = new ArrayList<Flotte>();
		if(typeSysteme!=EnumTypeSysteme.NEBULEUSE && typeSysteme!=EnumTypeSysteme.TROU_NOIR) {
			generationSystem(nbRessource, maxPlanete);
		}
		generationAnomalie(maxAnomalie);
		liens = new HashMap<Systeme, Vector2>();
		nbLiensMax = generationNbLiens();
		bouton = new Button(Project.skin);
		bouton.setPosition(getX() - 5, getY() - 5);
		bouton.setSize(10, 10);
		Systeme sys = this;
		bouton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.systemeSelectionne = sys;
			}
			
		});
	}
	
	public boolean presenceVille() {
		for (Planete planete : TPlanete) {
			if(planete.getVille() != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean presencePlaneteHabitable() {
		for (Planete planete : TPlanete) {
			if(planete.getTypePlanete() != EnumTypePlanete.GAZEUSE) {
				return true;
			}
		}
		return false;
	}
	
	public boolean rechercheAnomalie(int numero, Joueur joueur) {
		
		if(!TAnomalie.get(numero).isDecouverte()) {
			TAnomalie.get(numero).giveBonus(joueur);
			if(TAnomalie.get(numero).getAnomalie()==EnumAnomalie.EPAVE) {				
				TAnomalie.remove(numero);
			}
		}
		return false;
	}
	
	//--------------------------------------
	
	public boolean constructionBatimentVille(Joueur joueur, BatimentVille batiment, Ville ville) {
		
		if(joueur.getName()==this.joueur.getName() && ville.getJoueur().getName()==this.joueur.getName()) {
			return ville.constructionBatiment(batiment);
		}
		return false;
	}
	
	public boolean testFinConstructionVille(Joueur joueur, Ville ville) {
		
		if(joueur.getName()==this.joueur.getName()&& ville.getJoueur().getName()==this.joueur.getName()) {
			return ville.testFinConstruction();
		}
		return false;
	}
	
	//--------------------------------------
	
	public boolean testPriseDeVille(Joueur joueur, Ville ville, Flotte flotte) {
		
		int i=0;

		if(this.joueur.getName()==joueur.getName()) {
			return false;
		}
		if(ville.getPuissance()>0) {
			attaqueVille(ville, flotte);
		}
		if(ville.getPuissance()==0) {
			while(i<this.joueur.getSysteme().size() && this.joueur.getSysteme().get(i).getIdSysteme()!=this.idSysteme) {
				i++;
			}
			if(i<this.joueur.getSysteme().size()) {
				return false;
			}
			this.joueur.getSysteme().remove(i);
			this.joueur.getTVille().remove(ville.getId());
			joueur.getSysteme().add(this);
			this.setJoueur(joueur);
			ville.setJoueur(joueur);
			ville.setId(joueur.getTVille().size());
			joueur.ajoutVille(ville);
			for(Planete planete : this.TPlanete) {
				if(planete.getJoueur()!=null) {
					planete.setJoueur(joueur);
				}
			}
			ville.setPuissance((int)(ville.getPuissanceTotal()/2));
			ville.setFileDeConstructionBatiment(null);
			ville.setFileDeConstructionUnite(null);
			return true;
		} 
		return false;
	}
	
	private void attaqueVille(Ville ville, Flotte flotte) {

		if(flotte.getPuissance()>10*ville.getPuissance()) {
			ville.setPuissance(0);
		}else {
			ville.setPuissance(ville.getPuissance()-(int)(flotte.getPuissance()/10));
		}
		if(ville.getPuissance()<0) {
			ville.setPuissance(0);
		}
	}
	
	public boolean pillage(Planete planete, Joueur joueur) {
		
		if(planete.getJoueur()==null) {
			return false;
		}
		if(planete.getJoueur().getName()==joueur.getName()) {
			return false;
		}
		
		for (BatimentPlanete batiment : planete.getTBatiment()) {
			if(batiment!=null) {
				batiment=null;
				joueur.getTRessource().put(EnumRessource.CREDIT, joueur.getTRessource().get(EnumRessource.CREDIT)+150);
			}
		}
		planete.setJoueur(null);
		return true;
	}
	
	//--------------------------------------
	
	public boolean ajouterVille(Planete planete,Joueur joueur) {
		
		if(this.getJoueur() == null) {
			if(!presenceVille() && presencePlaneteHabitable()) {
				this.joueur=joueur;
				planete.setVille(new Ville(this.joueur,planete));
				planete.setJoueur(this.joueur);
				this.joueur.ajoutVille(planete.getVille());
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public void ajoutPlanete(Planete p) {
		this.TPlanete.add(p);
	}

	private void generationSystem(EnumAbondanceRessource nbRessource,int maxPlanete) {
		double x=0,y=0;
		
		do {
			y = Math.random();
			x = (int)((maxPlanete)*Math.random()+1);			
		} while(y > (1-Math.pow((((2*x)/maxPlanete )-1),2)));
		
		for(int i=0; i<(int)x;i++) {				
			TPlanete.add(new Planete(EnumTypePlanete.type(),nbRessource,this.ressourceEtAnomalie, i));
		}		
	}
	
	/**
	 * Génération du nombre maximum de systèmes liés
	 * 
	 * @return	Nombre de liens maximum du système vers d'autres systèmes
	 */
	private int generationNbLiens() {
		double x = Math.random();

		if (x < 0.15) {
			return 1;
		} else if (x < 0.45) {
			return 2;
		} else if (x < 0.75) {
			return 3;
		} else if (x < 0.9) {
			return 4;
		} else {
			return 5;
		}
	}
	
	private void generationAnomalie(int maxAnomalie) {
		
		int nbAnomalie = (int) (maxAnomalie*Math.random());
		
		if(typeSysteme==EnumTypeSysteme.TROU_NOIR) {
			nbAnomalie=1;
		}
		
		for( int i=0; i<nbAnomalie; i++) {
			TAnomalie.add(new Anomalie(ressourceEtAnomalie.generationAnomalieSysteme(typeSysteme)));
		}	
	}
	
	public void generationAnomalieDepat(int maxAnomalie) {
		
		int nbAnomalie = (int) (maxAnomalie*Math.random());
		
		TAnomalie = new ArrayList<Anomalie>();
		
		for( int i=0; i<nbAnomalie; i++) {
			TAnomalie.add(new Anomalie(ressourceEtAnomalie.generationAnomalieSystemeDepart()));
		}	
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	private void generationFlotePirate() {
		
		ListVaisseaux listVaisseau = new ListVaisseaux();
		Json parser = new Json();
		
		try(FileReader file = new FileReader("Ressources/Nation/PIRATE/Vaisseaux.json")) {
			
			ListVaisseaux vaisseaux = parser.fromJson(ListVaisseaux.class, file);
		
			for (Entry<Vaisseau> vaisseau : vaisseaux.getVaisseaux().entries()) {
				//Correction type lecture json (de String vers EnumRessource)
				Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();
				
				for (EnumRessource ressource : EnumRessource.values()) {
					cout.put(ressource, vaisseau.value.getCout().get(ressource.name()));
				}
				vaisseau.value.setCout(cout);
			}
			listVaisseau=vaisseaux;
		    
		} catch (Exception e) {
			System.out.println(e.toString() + "### ERREUR : /n FILE:\"Ressources/Nation/PIRATE/Vaisseaux.json\" ###");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	/**Fait le lien avec un autre système (distance aléatoire)*/
	/*public void ajouterLien(Systeme systeme) {
		this.liens.put(systeme, (int) Math.random()*12+1);
	}*/
	
	/**
	 * Liaison de deux systèmes.
	 * Les coordonnées du second système sont liées au premier système
	 * 
	 * @param systeme	Système à lier
	 */
	public boolean ajouterLienNouveauSysteme(Systeme systeme, boolean exterieur) {
		//TODO passer la distance maximum ou un intervalle de distance en paramètre
		if (this.nbLiens < this.nbLiensMax && systeme.getNbLiens() < systeme.getNbLiensMax()) {
			int x = new Random().nextInt(500 - 250) + 250;
			int y = new Random().nextInt(500 - 250) + 250;
			Vector2 vecteur = new Vector2();
			Vector2 oppose = new Vector2();
			vecteur.setToRandomDirection();
			vecteur.nor();
			vecteur.scl(x, y);
			if (exterieur) {				
				Vector2 direction = new Vector2(this.getCoordonnees().getX(), this.getCoordonnees().getY());
				float angle = direction.angle();
				vecteur.setAngle(angle);
			}
			liens.put(systeme, vecteur);
			systeme.setCoordonnees(coordonnees.getX() + (int) vecteur.x, coordonnees.getY() + (int) vecteur.y);
			oppose = new Vector2(vecteur);
			oppose.rotate(180);
			systeme.getLiens().put(this, oppose);
			nbLiens++;
			systeme.setNbLiens(systeme.getNbLiens() + 1);
			
			return true;
		}
		
		return false;
	}
	
	/**Fait le lien (réel) avec un autre système avec une distance prédéfinie (sens unique)*/
	public void ajouterLien(Systeme systeme) {
		//Création d'un vecteur vide (0,0)
		Vector2 vecteur = new Vector2();
		//Orientation du vecteur dans une direction aléatoire
		vecteur.setToRandomDirection();
		//Normalisation du vecteur
		vecteur.nor();
		//Multiplication du vecteur afin d'avoir le vecteur système->système lié
		vecteur.scl(systeme.coordonnees.getX(), systeme.getCoordonnees().getY());
		liens.put(systeme, vecteur);
		nbLiens++;
	}
	
	/**Fait le lien (réel) avec un autre système avec une distance prédéfinie (dans les deux sens)*/
//	public void faireLien(Systeme systeme) {
//		this.ajouterLien(systeme);
//		systeme.ajouterLien(this);
//	}
	
	/**
	 * Récupère la distance entre le système et un autre système.
	 * Un lien virtuel avec une distance aléatoire (entre 1 et 10) est créé si aucun lien n'existe.
	 * 
	 * @param systeme	Système à lier
	 * 
	 * @return Distance avec le système
	 */
//	public Map<List<Systeme>, Integer> getChemin(Systeme systeme) {
//		//TODO Implémenter un PCC 1
//		//Faire une recherche en largeur et une fois un chemin trouvé, continuer la recherche uniquement pour les chemins ayant un coût inférieur
//		int distance = 0;
//		Map<List<Systeme>, Integer> listeChemins = new HashMap<List<Systeme>, Integer>();
//		List<Systeme> marques = new ArrayList<Systeme>();
//		List<Systeme> chemin = new ArrayList<Systeme>();
//		Systeme actuel = this;
//		
//		chemin.add(actuel);
//
//		if (this.equals(systeme)) {
//			listeChemins.put(chemin , distance);
//		} else if (liens.containsKey(systeme)) {
//			chemin.add(systeme);
//			listeChemins.put(chemin , distance);
//		} else {
//			while (!actuel.equals(systeme)) {				
//				marques.add(actuel);
//				for (Entry<Systeme, Vector2> sys : actuel.getLiens().entrySet()) {
//					chemin = 
//				}
//			}
//		}
//		
//		
//		return listeChemins;
//	}
	
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public void setCoordonnees(int x, int y) {
		this.coordonnees.setX(x);
		this.coordonnees.setY(y);
	}
	
	public void setCoordonneesOnCircle(Systeme sys) {
		
	}
	
	public int getX() {
		return coordonnees.getX();
	}
	
	public int getY() {
		return coordonnees.getY();
	}

	public List<Planete> getTPlanete() {
		return TPlanete;
	}

	public void setTPlanete(List<Planete> tPlanete) {
		TPlanete = tPlanete;
	}

	public int getNbLiensMax() {
		return nbLiensMax;
	}

	public void setNbLiensMax(int nbLiensMax) {
		this.nbLiensMax = nbLiensMax;
	}

	public int getNbLiens() {
		return nbLiens;
	}
	
	public void setNbLiens(int nbLiens) {
		this.nbLiens = (nbLiens > this.nbLiensMax ? nbLiensMax : nbLiens);
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
		for (Planete planete : TPlanete) {
			planete.setJoueur(joueur);
		}
	}

	public List<Anomalie> getTAnomalie() {
		return TAnomalie;
	}

	public void setTAnomalie(List<Anomalie> tAnomalie) {
		TAnomalie = tAnomalie;
	}

	public List<Flotte> getFlottes() {
		return flottes;
	}

	public void setFlottes(List<Flotte> flottes) {
		this.flottes = flottes;
	}
	
	public HashMap<Systeme, Vector2> getLiens() {
		return liens;
	}

	public GenerationRessourceEtAnomalie getRessourceEtAnomalie() {
		return ressourceEtAnomalie;
	}

	public void setRessourceEtAnomalie(GenerationRessourceEtAnomalie ressourceEtAnomalie) {
		this.ressourceEtAnomalie = ressourceEtAnomalie;
	}

	public EnumTypeSysteme getTypeSysteme() {
		return typeSysteme;
	}

	public int getIdSysteme() {
		return idSysteme;
	}

	public void setIdSysteme(int idSysteme) {
		this.idSysteme = idSysteme;
	}

	public void setTypeSysteme(EnumTypeSysteme typeSysteme) {
		this.typeSysteme = typeSysteme;
	}

	public Button getBouton() {
		return bouton;
	}

	@Override
	public boolean equals(Object obj) {
		return idSysteme == ((Systeme)obj).getIdSysteme();
	}
}
