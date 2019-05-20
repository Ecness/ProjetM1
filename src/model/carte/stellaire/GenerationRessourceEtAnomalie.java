package model.carte.stellaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;

public class GenerationRessourceEtAnomalie {

	private Map<EnumTypePlanete, List<EnumRessource>> ressourcePlanete;
	private Map<EnumTypeSysteme, List<EnumAnomalie>> anomalieSysteme;
	
	public GenerationRessourceEtAnomalie() {
		this.ressourcePlanete = new HashMap<EnumTypePlanete,List<EnumRessource>>();
		this.anomalieSysteme = new HashMap<EnumTypeSysteme,List<EnumAnomalie>>();
		generationRessourcePlanete();
		generationAnomalieSysteme();
	}
	
	private void generationAnomalieSysteme() {
		for (EnumTypeSysteme t : EnumTypeSysteme.values()) {
			switch (t) {
			case TROU_NOIR:
				anomalieSysteme.put(t, new ArrayList<EnumAnomalie>());
				
				anomalieSysteme.get(t).add(EnumAnomalie.TROU_NOIR);
				break;

			case NEBULEUSE:
				anomalieSysteme.put(t, new ArrayList<EnumAnomalie>());
				
				anomalieSysteme.get(t).add(EnumAnomalie.NEBULEUSE);
				anomalieSysteme.get(t).add(EnumAnomalie.NEBULEUSE);
				anomalieSysteme.get(t).add(EnumAnomalie.NEBULEUSE);
				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.PETITE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.MOYENNE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.MOYENNE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.GRANDE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.GRANDE_FLOTTE_PIRATE);
				break;
			
			case SYSTEME_DOUBLE_ETOILE:
				anomalieSysteme.put(t, new ArrayList<EnumAnomalie>());
				
				anomalieSysteme.get(t).add(EnumAnomalie.SOLEIL_SUPPLEMENTAIRE);
				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.PETITE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.MOYENNE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.MOYENNE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.GRANDE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.GRANDE_FLOTTE_PIRATE);
				break;
				
			case SYSTEME_PLANETAIRE:
				anomalieSysteme.put(t, new ArrayList<EnumAnomalie>());

				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				anomalieSysteme.get(t).add(EnumAnomalie.PETITE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.PETITE_FLOTTE_PIRATE);
				anomalieSysteme.get(t).add(EnumAnomalie.MOYENNE_FLOTTE_PIRATE);
				break;
			default:
				anomalieSysteme.put(t, new ArrayList<EnumAnomalie>());
				
				anomalieSysteme.get(t).add(EnumAnomalie.ASTEROIDE);
				anomalieSysteme.get(t).add(EnumAnomalie.COMETE);
				anomalieSysteme.get(t).add(EnumAnomalie.EPAVE);
				break;
			}
		}
	}
	
	private void generationRessourcePlanete() {
		for (EnumTypePlanete t : EnumTypePlanete.values()) {	
			switch (t) {
			case GAZEUSE:
				ressourcePlanete.put(t, new ArrayList<EnumRessource>());
				
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.CREDIT);
				ressourcePlanete.get(t).add(EnumRessource.CREDIT);
				break;
				
			case GLACEE:
				ressourcePlanete.put(t, new ArrayList<EnumRessource>());
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.ACIER);
				ressourcePlanete.get(t).add(EnumRessource.ACIER);
				ressourcePlanete.get(t).add(EnumRessource.ACIER);
				ressourcePlanete.get(t).add(EnumRessource.CRISTAL);
				ressourcePlanete.get(t).add(EnumRessource.CRISTAL);
				ressourcePlanete.get(t).add(EnumRessource.CRISTAL);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				break;
				
			default:
				ressourcePlanete.put(t, new ArrayList<EnumRessource>());
				ressourcePlanete.get(t).add(EnumRessource.ACIER);
				ressourcePlanete.get(t).add(EnumRessource.ACIER);
				ressourcePlanete.get(t).add(EnumRessource.CRISTAL);
				ressourcePlanete.get(t).add(EnumRessource.GAZ);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.SCIENCE);
				ressourcePlanete.get(t).add(EnumRessource.CREDIT);
				ressourcePlanete.get(t).add(EnumRessource.CREDIT);
				ressourcePlanete.get(t).add(EnumRessource.CREDIT);
				break;
			}
		}
	}
	
	
	public EnumRessource generationRessourcePlanete(EnumTypePlanete type) {
		
		int ressource = (int) ((ressourcePlanete.get(type).size())*Math.random());	
		
		return ressourcePlanete.get(type).get(ressource);
	}
	
	public EnumAnomalie generationAnomalieSysteme(EnumTypeSysteme type) {
		
		int anomalie = (int) ((anomalieSysteme.get(type).size())*Math.random());	
		
		return anomalieSysteme.get(type).get(anomalie);
	}
	
	public EnumAnomalie generationAnomalieSystemeDepart() {
		
		int anomalie = (int) ((anomalieSysteme.get(EnumTypeSysteme.DEPART).size())*Math.random());	
		
		return anomalieSysteme.get(EnumTypeSysteme.DEPART).get(anomalie);
	}
	
}
