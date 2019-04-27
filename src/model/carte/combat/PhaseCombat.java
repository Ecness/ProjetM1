package model.carte.combat;

import java.util.List;
import java.util.Map.Entry;

import model.carte.combat.obstacle.EnumListObstacle;
import model.entity.vaisseau.EnumDommageCritique;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;
import model.module.Arme;
import model.module.EnumTypeArme;

public class PhaseCombat {

	private MapCombat mapCombat;
	private DetailCombat detailCombat;
	private int nbtour;
	
	public PhaseCombat(MapCombat mapCombat) {
		this.setMapCombat(mapCombat);
		detailCombat = new DetailCombat();
		nbtour = 0;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * @param obstacle (ASTEROIDE,COMMET,CEINTURE_ASTEROIDE,MINE_SPACIAL,DEBRIT)
	 * @param vaisseau
	 * @return true si le vaiseau est détruit
	 */
	public Boolean dommageObstacle(EnumListObstacle obstacle, Vaisseau vaisseau) {
		
		switch (obstacle) {
		case ASTEROIDE:
		case COMMET:
		case CEINTURE_ASTEROIDE:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
			if(vaisseau.getSante()==0) {
				return true;
			}
			return false;
		case MINE_SPACIAL:
			vaisseau.prendreDommage(75);
			if(vaisseau.getSante()==0) {
				return true;
			}
			return false;
		case DEBRIT:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
			if(vaisseau.getSante()==0) {
				return true;
			}
			return false;
		default:
			return false;
		}
	}
	
	
	/**
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 */
	public void dommageColision(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur) {
		
		vaisseauDefensseur.prendreDommage((int)(vaisseauAttaquant.getSanteMax()*0.1));
		vaisseauAttaquant.prendreDommage((int)(vaisseauDefensseur.getSanteMax()*0.1));

	}
	
	/**
	 * Verifie l'ajout d'un feu au vaisseau
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 * @param arme
	 * @param detail
	 * @return	DetailCombat
	 * 			: Le detail du combat.
	 */
	private void verifAjoutFeu(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur, Arme arme) {
		// bonus pour les bonnus de science
		int bonus=0;
		
		if (arme.getTauxFeu()+bonus>(int)(100*Math.random()+1)) {
			vaisseauDefensseur.addFire();
			detailCombat.addFeu(vaisseauDefensseur.getNom());
		}
	}
	
	/**
	 * Verifie l'ajout d'un dommage critique au vaisseau
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 * @param arme
	 * @param detail
	 * @return	DetailCombat
	 * 			: Le detail du combat.
	 */
	private void verifAjoutDommageCritique(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur, Arme arme) {
		// bonus pour les bonnus de science
		double bonus=0;
		
		if (arme.getCritique()>(int)(100*Math.random()+1)) {
			EnumDommageCritique dommageCritique = EnumDommageCritique.getDammage();
			ajoutDommageCritique(dommageCritique, vaisseauDefensseur);
		}
	}
	
	/**
	 * Verifie si le vaisseau a des bouclier pour l'ajout des feux et des dommages critiques
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 * @param arme
	 * @param detail
	 * @return DetailCombat
	 * 			: Le detail du combat.
	 */
	private void verifBouclier(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur, Arme arme) {
		// bonus pour les bonnus de science
		double bonus=0;
		if(vaisseauDefensseur.getBouclier()==0){							
			verifAjoutFeu(vaisseauAttaquant, vaisseauDefensseur, arme);
			verifAjoutDommageCritique(vaisseauAttaquant, vaisseauDefensseur, arme);
			if(vaisseauDefensseur.getSante()==0) {
				detailCombat.addVaisseauDétruit(vaisseauDefensseur);
			}
		}
	}
	
	/**
	 * permet d'infliger les dommages a un vaisseau
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 * @param arme
	 * @param detail
	 * @param bonnusPrecision
	 * @return DetailCombat
	 * 						: Le detail du combat.
	 */
	private Boolean infligerDommage(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur, Arme arme, int bonnusPrecision) {
		// bonus pour les bonnus de science
		double bonusDommage=1;
		if(vaisseauAttaquant.getJoueur() != null) {
			if(arme.getTypeArme() == EnumTypeArme.CINETIQUE && vaisseauAttaquant.getScienceMilitaire().get(2).isRechercher()) {
				bonusDommage=0.1;
			}
			if(arme.getTypeArme() == EnumTypeArme.LASER && vaisseauAttaquant.getScienceMilitaire().get(1).isRechercher()) {
				bonnusPrecision+=10;
			}
			if(vaisseauDefensseur.getScienceMilitaire().get(4).isRechercher()) {
				bonnusPrecision-=10;
			}
		}
		
		if(arme.getPrecision()+bonnusPrecision>(int)(100*Math.random()+1)) {
			detailCombat.addDommage((int)(arme.getDommage()*bonusDommage),arme.getNom());
			vaisseauDefensseur.prendreDommage((int)(arme.getDommage()*bonusDommage));
			if(vaisseauDefensseur.getSante()==0) {
				detailCombat.addVaisseauDétruit(vaisseauDefensseur);
				return true;
			}
			verifBouclier(vaisseauAttaquant, vaisseauDefensseur, arme);
		}else {
			detailCombat.pasToucher(arme.getNom());
		}
		return false;
	}
	
	/**
	 * permet a un vaisseau d'endommager un autre vaisseau
	 * @param vaisseauAttaquant
	 * @param vaisseauDefensseur
	 * @return	DetailCombat
	 * 			: Le detail du combat.
	 */
	private void endommageVaisseau(Vaisseau vaisseauAttaquant, Vaisseau vaisseauDefensseur, int bonnusPrecision) {		
		
		
		detailCombat.ajoutNomVaisseau(vaisseauAttaquant.getNom(), vaisseauDefensseur.getNom());
		
		for (Entry<Integer, Arme> armes : vaisseauAttaquant.getArmes().entrySet()) {
			Arme arme = armes.getValue();
			if (arme.getUtilisable()==true) {
				for (int i=0; i < arme.getNbTire(); i++) {
					infligerDommage(vaisseauAttaquant, vaisseauDefensseur, arme, bonnusPrecision);
				}
			}
		}
		if(!detailCombat.getDetruit() && vaisseauDefensseur.getSante()==0) {
			detailCombat.addVaisseauDétruit(vaisseauDefensseur);
		}
	}
	
	private int getVaisseauAleatoire(List<Vaisseau> ListVaisseau) {
		return (int)(ListVaisseau.size()*Math.random());
	}
	
	/**
	 * Pour chaque vaisseau attaquant , choisie un vaisseaux deffensseur a attaqué.
	 * @param flotteAttaquante
	 * @param flotteDefensseur
	 * @param bonnusPrecision
	 * @param detail
	 * @return DetailCombat
	 * 			: Le detail du combat.
	 */
	private void combatFlotte(Flotte flotteAttaquante, Flotte flotteDefensseur,int bonnusPrecision) {

		for (Vaisseau vaisseauAttaquant : flotteAttaquante.getTVaisseau()) {
			if (!flotteDefensseur.getTVaisseau().isEmpty()) {
				int vaisseauDeffensseur = getVaisseauAleatoire(flotteDefensseur.getTVaisseau());
				endommageVaisseau(vaisseauAttaquant, flotteDefensseur.getTVaisseau().get(vaisseauDeffensseur), bonnusPrecision);
				if(detailCombat.getDetruit()) {
					flotteDefensseur.getTVaisseau().remove(vaisseauDeffensseur);
				}
				detailCombat.setDetruit(false);
			}
		}
	}
	
	
	
	/**
	 * Permet a deux flotte de combatre.
	 * @return	Boolean
	 * 					: true si le combat est fini , false sinon.
	 */
	public Boolean combat() {
		combatFlotte(mapCombat.getFlotteJ1(), mapCombat.getFlotteJ2(), (int)(10*Math.random()-5));
		combatFlotte(mapCombat.getFlotteJ2(), mapCombat.getFlotteJ1(), (int)(10*Math.random()-5));
		nbtour++;
		if (mapCombat.getFlotteJ2().getTVaisseau().isEmpty() || mapCombat.getFlotteJ1().getTVaisseau().isEmpty()) {
			return true;
		}
		actionFinTour(mapCombat.getFlotteJ1());
		actionFinTour(mapCombat.getFlotteJ2());
		return false;
	}
	
	/**
	 * permet a chaque vaisseau de la flotte de récupéré des dommage subie
	 * @param flotte
	 */
	private void actionFinTour(Flotte flotte) {
		for (Vaisseau vaisseau : flotte.getTVaisseau()) {
			if(vaisseau.getDommageCritique().contains(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT)) {
				vaisseau.regeneBouclier(this.detailCombat);
			}
			vaisseau.reparationVaisseau(this.detailCombat);
		}
	}
	
	
	/**
	 * @param dommage
	 * @param vaisseau
	 * @return	
	 * 			: Le detail du combat.
	 */
	private void ajoutDommageCritique(EnumDommageCritique dommage, Vaisseau vaisseau) {

		int id = (int)(vaisseau.getArmes().size()*Math.random());
		switch (dommage) {
		case ARME_ENDOMMAGER:	
			if (vaisseau.getArmes().size()>0) {
				if (!vaisseau.getArmes().get(id).getEndomager() && vaisseau.getArmes().get(id).getUtilisable()) {
					vaisseau.getArmes().get(id).setEndomager(true);
					vaisseau.getDommageCritique().add(EnumDommageCritique.ARME_ENDOMMAGER);
					detailCombat.addDommageCritique(EnumDommageCritique.ARME_ENDOMMAGER,0);
					break;
				}
			}
		case ARME_DETRUITE:
			if (vaisseau.getArmes().size()>0) {
				//TODO Voir pourquoi NullPointerException
				if(vaisseau.getArmes().get(id).getUtilisable()) {
					if(vaisseau.getArmes().get(id).getEndomager()) {
						vaisseau.getArmes().get(id).setEndomager(false);
						vaisseau.getDommageCritique().remove(EnumDommageCritique.ARME_ENDOMMAGER);
					}
					vaisseau.getArmes().get(id).setUtilisable(false);
					vaisseau.getDommageCritique().add(EnumDommageCritique.ARME_DETRUITE);	
					detailCombat.addDommageCritique(EnumDommageCritique.ARME_DETRUITE,0);
				}else {
					vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
					detailCombat.addDommageCritique(EnumDommageCritique.BREACH_DANS_LA_COQUE,(int)(vaisseau.getSanteMax()*0.1));
				}
			}else {
				vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
				detailCombat.addDommageCritique(EnumDommageCritique.BREACH_DANS_LA_COQUE,(int)(vaisseau.getSanteMax()*0.1));
			}
			break;
		case MOTEUR_ENDOMMAGER:
			if(!vaisseau.getMoteurEndomager() && !vaisseau.getMoteurDetruit()) {
				vaisseau.setMoteurEndomager(true);
				vaisseau.getDommageCritique().add(EnumDommageCritique.MOTEUR_ENDOMMAGER);
				detailCombat.addDommageCritique(EnumDommageCritique.MOTEUR_ENDOMMAGER,0);
				break;
			}
		case MOTEUR_DETRUIT:
			if (!vaisseau.getMoteurDetruit()) {
				if(vaisseau.getMoteurEndomager()) {
					vaisseau.setMoteurEndomager(false);
					vaisseau.getDommageCritique().remove(EnumDommageCritique.MOTEUR_ENDOMMAGER);
				}
				vaisseau.setMoteurDetruit(true);
				vaisseau.getDommageCritique().add(EnumDommageCritique.MOTEUR_DETRUIT);	
				detailCombat.addDommageCritique(EnumDommageCritique.MOTEUR_DETRUIT,0);
				break;
			}
		case GENERATEUR_DE_BOUCLIER_DETRUIT:
			if(!vaisseau.getBouclierDetruit()) {
				vaisseau.setBouclierDetruit(true);
				vaisseau.setBouclier(0);
				vaisseau.getDommageCritique().add(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT);
				detailCombat.addDommageCritique(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT,0);
				break;
			}
		case BREACH_DANS_LA_COQUE:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
			detailCombat.addDommageCritique(EnumDommageCritique.BREACH_DANS_LA_COQUE,(int)(vaisseau.getSanteMax()*0.1));
			break;
		case STRUCTURE_INTERNE_TOUCHER:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.25));
			detailCombat.addDommageCritique(EnumDommageCritique.STRUCTURE_INTERNE_TOUCHER,(int)(vaisseau.getSanteMax()*0.25));
			break;
		case MAGASIN_A_MUNITION: 
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.5));
			detailCombat.addDommageCritique(EnumDommageCritique.MAGASIN_A_MUNITION,(int)(vaisseau.getSanteMax()*0.5));
			break;
		default:
			break;
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------
		
	public MapCombat getMapCombat() {
		return mapCombat;
	}

	public void setMapCombat(MapCombat mapCombat) {
		this.mapCombat = mapCombat;
	}
	public int getNbtour() {
		return nbtour;
	}
	public DetailCombat getDetailCombat() {
		return detailCombat;
	}
}
