package view.galaxie;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;
import view.galaxie.systeme.AffichageSysteme;
import view.launcher.Project;
import view.menus.MenuPartie;

public class AffichageGalaxie {
	private ShapeRenderer shapeRenderer;
	private HorizontalGroup afficheurHaut;
	private VerticalGroup afficheurDroite;

	private Button boutonMenu;
	private Button finTour;
	private Label cptTour;

	private Carte carte;

	public AffichageGalaxie(Carte carte, Skin skin) {
		this.carte = carte;

		shapeRenderer = new ShapeRenderer();
		afficheurHaut = new HorizontalGroup();
		afficheurHaut.setName("afficheur_haut");
		afficheurDroite = new VerticalGroup();
		afficheurDroite.setName("afficheur_droite");

		//Bouton des menus
		boutonMenu = new TextButton("Menu", skin);
		boutonMenu.setColor(Color.TEAL);
		boutonMenu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.clicked = false;
				Project.staticStage.addActor(new MenuPartie(skin));
				Project.pause = true;
			}
		});

		//Affichage des ressources
		AffichageRessources ressources = new AffichageRessources(Project.partie.getTJoueur()[0], skin);
		ressources.setPosition(boutonMenu.getX() + boutonMenu.getWidth(),  Project.staticStage.getCamera().viewportHeight-50);

		//Bouton de fin de tour
		finTour = new TextButton("Fin du tour", skin);
		finTour.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.finTour = true;
			}

		});

		cptTour = new Label("Tour " + Project.cptTours, skin);
		
		afficheurHaut.addActor(boutonMenu);
		afficheurHaut.addActor(ressources);
		afficheurHaut.addActor(finTour);
		afficheurHaut.addActor(cptTour);
		afficheurHaut.addActor(new AffichageRecherche(Project.partie.getTJoueur()[0], skin));
		
		afficheurHaut.setPosition(0,  Project.staticStage.getCamera().viewportHeight - afficheurHaut.getPrefHeight());

		afficheurDroite.setPosition(Project.staticStage.getCamera().viewportWidth - Project.staticStage.getCamera().viewportWidth / 10, Project.staticStage.getCamera().viewportHeight - afficheurHaut.getPrefHeight());

		Project.staticStage.addActor(afficheurHaut);
		Project.staticStage.addActor(afficheurDroite);

		for (Systeme sys : carte.getListeSysteme()) {
			Project.dynamicStage.addActor(sys.getBouton());
		}
	}

	public void render() {
		shapeRenderer.setProjectionMatrix(Project.dynamicStage.getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		for (Systeme sys : carte.getListeSysteme()) {
			for (Vector2 vect : sys.getLiens().values()) {
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.line(new Vector2(sys.getX(), sys.getY()), new Vector2(sys.getX() + vect.x, sys.getY() + vect.y));
			}
			if (sys.getJoueur() == null) {
				shapeRenderer.setColor(Color.WHITE);
			} else {
				shapeRenderer.setColor(sys.getJoueur().getCouleur());
			}
			shapeRenderer.circle(sys.getX(), sys.getY(), 10);
			sys.getBouton().setPosition(sys.getX() - 10, sys.getY() - 10);
		}
		for (Joueur joueur : Project.partie.getTJoueur()) {
			shapeRenderer.setColor(joueur.getCouleur());
			for (Flotte flotte : joueur.getTFlotte()) {
				shapeRenderer.rect(flotte.getCoordonnees().x, flotte.getCoordonnees().y, 15, 15);
			}
		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
		if (Project.flotteSelectionnee != null) {
			shapeRenderer.setColor(Project.partie.getTJoueur()[0].getCouleur());
			shapeRenderer.circle(Project.flotteSelectionnee.getCoordonnees().x, 
					Project.flotteSelectionnee.getCoordonnees().y, 
					15);
			if (!Project.flotteSelectionnee.getTrajet().isEmpty()) {
				shapeRenderer.circle(Project.flotteSelectionnee.getTrajet().get(Project.flotteSelectionnee.getTrajet().size()-1).getX(), 
						Project.flotteSelectionnee.getTrajet().get(Project.flotteSelectionnee.getTrajet().size()-1).getY(), 
						15);
			}
		}
		shapeRenderer.end();

		//Mise à jour de l'affichage des ressources
		((AffichageRessources) afficheurHaut.findActor("afficheur_ressources")).update(Project.partie.getTJoueur()[0]);
		cptTour.setText("Tour " + Project.cptTours);

		//Affichage du système sélectionné
		if (Project.displayHasChanged) {
			if (Project.systemeSelectionne != null) {
				Project.displayHasChanged = false;
				if (afficheurDroite.findActor("afficheur_systeme") != null) {
					((AffichageSysteme) afficheurDroite.findActor("afficheur_systeme")).update(Project.systemeSelectionne, Project.skin);
				} else {
					afficheurDroite.clear();
					AffichageSysteme systeme = new AffichageSysteme(Project.systemeSelectionne, Project.skin);
					afficheurDroite.addActor(systeme);
				}

			}
			if (Project.flotteSelectionnee != null) {
				if (afficheurDroite.findActor("flotte_selectionnee") != null) {
					((AffichageFlotteSelectionnee) afficheurDroite.findActor("flotte_selectionnee")).update(Project.flotteSelectionnee, Project.skin);
				} else {
					afficheurDroite.addActor(new AffichageFlotteSelectionnee(Project.flotteSelectionnee, Project.skin));
				}
			} else {
				if (afficheurDroite.findActor("flotte_selectionnee") != null) {
					afficheurDroite.findActor("flotte_selectionnee").remove();
				}
			}
			((AffichageRecherche) afficheurHaut.findActor("group_tech")).update(Project.partie.getTJoueur()[0], Project.skin);
		}
	}
}
