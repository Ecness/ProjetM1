package model.entity.player;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.entity.player.donnee.ListeDesBatimentPlanete;
import model.entity.player.donnee.ListeDesBatimentVille;
import model.entity.player.donnee.Technologie;

public class TechnologieEtBatiment {

	private Technologie science;
	private ListeDesBatimentPlanete batimentPlanete;
	private ListeDesBatimentVille batimentVille;

	public TechnologieEtBatiment() {
		
		try {
			this.science = (Technologie) model.util.XMLTools.decodeFromFile("./Ressource/tech.xml");
		} catch (FileNotFoundException e) {
			System.out.println("File not found : erreur sur le chemin d'accer ou le fichier et manquant");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exeption : erreur sur les flux entré/sortie");
			e.printStackTrace();
		}
		
		this.batimentVille=new ListeDesBatimentVille();
		this.batimentPlanete=new ListeDesBatimentPlanete();
	}
	
	public TechnologieEtBatiment(Technologie science, ListeDesBatimentPlanete batimentPlanete, ListeDesBatimentVille batimentVille) {
		this.science = science;
		this.batimentVille = batimentVille;
		this.batimentPlanete = batimentPlanete;
	}

	public Technologie getScience() {
		return science;
	}

	public void setScience(Technologie science) {
		this.science = science;
	}

	public ListeDesBatimentPlanete getBatimentPlanete() {
		return batimentPlanete;
	}

	public void setBatimentPlanete(ListeDesBatimentPlanete batimentPlanete) {
		this.batimentPlanete = batimentPlanete;
	}

	public ListeDesBatimentVille getBatimentVille() {
		return batimentVille;
	}

	public void setBatimentVille(ListeDesBatimentVille batimentVille) {
		this.batimentVille = batimentVille;
	}

	
}
