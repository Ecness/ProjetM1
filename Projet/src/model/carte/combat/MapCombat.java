package model.carte.combat;

import java.util.ArrayList;
import java.util.List;

import model.carte.combat.obstacle.Debrit;
import model.carte.combat.obstacle.Obstacle;
import model.carte.stellaire.Anomalie;
import model.carte.stellaire.EnumAnomalie;
import model.carte.stellaire.Systeme;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;
import model.parametre.EnumTailleMapCombat;

public class MapCombat {
	private Joueur joueur1;
	private Joueur joueur2;
	private Flotte flotteJ1;
	private Flotte flotteJ2;
	private double MaxcoordoneX;
	private double MaxcoordoneY;
	private Systeme systeme;
	private List<Obstacle> obstacle;
	private boolean nebuleuse;
	
	public MapCombat(Joueur joueur1, Joueur joueur2, Flotte flotteJ1, Flotte flotteJ2,
			Systeme systeme, EnumTailleMapCombat taille) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.flotteJ1 = flotteJ1;
		this.flotteJ2 = flotteJ2;
		this.systeme = systeme;
		this.nebuleuse = false;
		this.MaxcoordoneX = taille.getTaille();
		this.MaxcoordoneY = taille.getTaille();
		this.obstacle = new ArrayList<Obstacle>();
		generation(taille);
	}
	
	
	public void generation(EnumTailleMapCombat taille) {
		
		for (Anomalie t : systeme.getTAnomalie() ){
			if(t.getAnomalie() == EnumAnomalie.NEBULEUSE) {this.nebuleuse = true;}
		}
		
		for(int i = 0; i < taille.getNbObstacle(); i++) {
			// TODO faire plus d'obstacle !=
			this.obstacle.add(new Debrit((int)(taille.getTaille()*Math.random()), (int)(taille.getTaille()*Math.random())));
		}
	}
	
	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}
	
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Flotte getFlotteJ1() {
		return flotteJ1;
	}

	public void setFlotteJ1(Flotte flotteJ1) {
		this.flotteJ1 = flotteJ1;
	}

	public Flotte getFlotteJ2() {
		return flotteJ2;
	}

	public void setFlotteJ2(Flotte flotteJ2) {
		this.flotteJ2 = flotteJ2;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public double getMaxcoordoneX() {
		return MaxcoordoneX;
	}

	public void setMaxcoordoneX(double maxcoordoneX) {
		MaxcoordoneX = maxcoordoneX;
	}

	public double getMaxcoordoneY() {
		return MaxcoordoneY;
	}

	public void setMaxcoordoneY(double maxcoordoneY) {
		MaxcoordoneY = maxcoordoneY;
	}

	public List<Obstacle> getObstacle() {
		return obstacle;
	}

	public void setObstacle(List<Obstacle> obstacle) {
		this.obstacle = obstacle;
	}

	public boolean isNebuleuse() {
		return nebuleuse;
	}

	public void setNebuleuse(boolean nebuleuse) {
		this.nebuleuse = nebuleuse;
	}	
}
