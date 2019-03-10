package model.carte.combat;

import java.util.Map.Entry;

import model.carte.combat.obstacle.EnumListObstacle;
import model.entity.vaisseau.EnumDommageCritique;
import model.entity.vaisseau.Vaisseau;
import model.module.Arme;

public class PhaseCombat {

	private MapCombat mapCombat;
	
	public PhaseCombat(MapCombat mapCombat) {
		this.setMapCombat(mapCombat);
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
	 * @param vaisseauAttaquan
	 * @param vaisseauDefensseur
	 */
	public void dommageColision(Vaisseau vaisseauAttaquan, Vaisseau vaisseauDefensseur) {
		
		vaisseauDefensseur.prendreDommage((int)(vaisseauAttaquan.getSanteMax()*0.1));
		vaisseauAttaquan.prendreDommage((int)(vaisseauDefensseur.getSanteMax()*0.1));

	}
	
	
	/**
	 * @param vaisseauAttaquan
	 * @param vaisseauDefensseur
	 * @return true si le vaiseau defensseur est détruit
	 */
	public DetailCombat infligerDommage(Vaisseau vaisseauAttaquan, Vaisseau vaisseauDefensseur, int bonnusPrecision) {
		
		DetailCombat detail = new DetailCombat();
		for (Entry<Integer, Arme> armes : vaisseauAttaquan.getArmes().entrySet()) {
			Arme arme = armes.getValue();
			if (arme.getUtilisable()==true) {
				for (int i=0; i < arme.getNbTire(); i++) {
					if(arme.getPrecision()+bonnusPrecision>(int)(100*Math.random()+1)) {
						detail.addDommage(arme.getDommage());
						vaisseauDefensseur.prendreDommage(arme.getDommage());
						if(vaisseauDefensseur.getSante()==0) {
							detail.detruit=true;
							return detail;
						}
						if(vaisseauDefensseur.getBouclier()==0){							
							if (arme.getTauxFeu()>(int)(100*Math.random()+1)) {
								vaisseauDefensseur.addFire();
								detail.addFeu();
							}
							if (arme.getCritique()>(int)(100*Math.random()+1)) {
								EnumDommageCritique dommageCritique = EnumDommageCritique.getDammage();
								detail = ajoutDommageCritique(dommageCritique, vaisseauDefensseur, detail);
							}
							if(vaisseauDefensseur.getSante()==0) {
								detail.detruit=true;
								return detail;
							}
						}
					}					
				}
			}
		}
		if(vaisseauDefensseur.getSante()==0) {
			detail.detruit=true;
		}
		return detail;
	}
	
	/**
	 * @param dommage
	 * @param vaisseau
	 */
	public DetailCombat ajoutDommageCritique(EnumDommageCritique dommage, Vaisseau vaisseau, DetailCombat detail) {

		int id = (int)(vaisseau.getArmes().size()*Math.random());
		switch (dommage) {
		case ARME_ENDOMMAGER:	
			if (vaisseau.getArmes().size()>0) {
				if (!vaisseau.getArmes().get(id).getEndomager() && vaisseau.getArmes().get(id).getUtilisable()) {
					vaisseau.getArmes().get(id).setEndomager(true);
					vaisseau.getDommageCritique().add(EnumDommageCritique.ARME_ENDOMMAGER);
					detail.addDommageCritique(EnumDommageCritique.ARME_ENDOMMAGER);
					break;
				}
			}
		case ARME_DETRUITE:
			if (vaisseau.getArmes().size()>0) {
				if(vaisseau.getArmes().get(id).getUtilisable()) {
					if(vaisseau.getArmes().get(id).getEndomager()) {
						vaisseau.getArmes().get(id).setEndomager(false);
						vaisseau.getDommageCritique().remove(EnumDommageCritique.ARME_ENDOMMAGER);
					}
					vaisseau.getArmes().get(id).setUtilisable(false);
					vaisseau.getDommageCritique().add(EnumDommageCritique.ARME_DETRUITE);	
					detail.addDommageCritique(EnumDommageCritique.ARME_DETRUITE);
				}else {
					vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
					detail.addDommage((int)(vaisseau.getSanteMax()*0.1));
				}
			}else {
				vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
				detail.addDommage((int)(vaisseau.getSanteMax()*0.1));
			}
			break;
		case MOTEUR_ENDOMMAGER:
			if(!vaisseau.getMoteurEndomager() && !vaisseau.getMoteurDetruit()) {
				vaisseau.setMoteurEndomager(true);
				vaisseau.getDommageCritique().add(EnumDommageCritique.MOTEUR_ENDOMMAGER);
				detail.addDommageCritique(EnumDommageCritique.MOTEUR_ENDOMMAGER);
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
				detail.addDommageCritique(EnumDommageCritique.MOTEUR_DETRUIT);
				break;
			}
		case GENERATEUR_DE_BOUCLIER_DETRUIT:
			if(!vaisseau.getBouclierDetruit()) {
				vaisseau.setBouclierDetruit(true);
				vaisseau.setBouclier(0);
				vaisseau.getDommageCritique().add(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT);
				detail.addDommageCritique(EnumDommageCritique.GENERATEUR_DE_BOUCLIER_DETRUIT);
				break;
			}
		case BREACH_DANS_LA_COQUE:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.1));
			detail.addDommage((int)(vaisseau.getSanteMax()*0.1));
			detail.addDommageCritique(EnumDommageCritique.BREACH_DANS_LA_COQUE);
			break;
		case STRUCTURE_INTERNE_TOUCHER:
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.25));
			detail.addDommage((int)(vaisseau.getSanteMax()*0.25));
			detail.addDommageCritique(EnumDommageCritique.STRUCTURE_INTERNE_TOUCHER);
			break;
		case MAGASIN_A_MUNITION: 
			vaisseau.prendreDommage((int)(vaisseau.getSanteMax()*0.5));
			detail.addDommage((int)(vaisseau.getSanteMax()*0.5));
			detail.addDommageCritique(EnumDommageCritique.MAGASIN_A_MUNITION);
			break;
		default:
			break;
		}
		return detail;
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------
		
	public MapCombat getMapCombat() {
		return mapCombat;
	}

	public void setMapCombat(MapCombat mapCombat) {
		this.mapCombat = mapCombat;
	}
}
