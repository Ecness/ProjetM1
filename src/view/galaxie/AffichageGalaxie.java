package view.galaxie;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageGalaxie {
	private ShapeRenderer shapeRenderer;
	private HorizontalGroup afficheurHaut;
//	private AffichageRessources ressources;
	private VerticalGroup afficheurDroite;
	
	private Button boutonMenu;
	
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
		boutonMenu.setSize(200,50);
		boutonMenu.setColor(Color.TEAL);
		boutonMenu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
				Project.affichageGalaxie = false;
				Project.clicked = false;
			}
		});
		
		//Affichage des ressources
		AffichageRessources ressources = new AffichageRessources(Project.partie.getTJoueur()[0], skin);
		ressources.setPosition(boutonMenu.getX() + boutonMenu.getWidth(),  Project.staticStage.getCamera().viewportHeight-50);
	
		afficheurHaut.addActor(boutonMenu);
		afficheurHaut.addActor(ressources);
		afficheurHaut.setHeight(boutonMenu.getHeight());
		afficheurHaut.setPosition(0,  Project.staticStage.getCamera().viewportHeight - afficheurHaut.getHeight());
		afficheurHaut.grow();
		
		afficheurDroite.setPosition(Project.staticStage.getCamera().viewportWidth - Project.staticStage.getCamera().viewportWidth / 10, Project.staticStage.getCamera().viewportHeight - afficheurHaut.getHeight());
		
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
				shapeRenderer.line(new Vector2(sys.getCoordonnees().getX(), sys.getCoordonnees().getY()), new Vector2(sys.getCoordonnees().getX() + vect.x, sys.getCoordonnees().getY() + vect.y));
			}
			if (sys.getJoueur() == null) {
				shapeRenderer.setColor(Color.WHITE);
			} else {
				shapeRenderer.setColor(sys.getJoueur().getCouleur());
			}
			shapeRenderer.circle(sys.getX(), sys.getY(), 10);
			sys.getBouton().setPosition(sys.getX() - 10, sys.getY() - 10);
		}
		shapeRenderer.end();
		
		//Mise à jour de l'affichage des ressources
		((AffichageRessources) afficheurHaut.findActor("afficheur_ressources")).update(Project.partie.getTJoueur()[0]);
	
		//Affichage du système sélectionné
		if (Project.systemeSelectionne != null && Project.changeSysteme) {
			Project.changeSysteme = false;
			afficheurDroite.clear();
			AffichageSysteme systeme = new AffichageSysteme(Project.systemeSelectionne, Project.skin);
			afficheurDroite.addActor(systeme);
		}
		
		//Mise à jourAffichage
		if (Project.planeteSelectionne != null && Project.changePlanete) {
			Project.changePlanete = false;
			afficheurDroite.removeActor(afficheurDroite.findActor("afficheur_planete"));
			AffichagePlanete planete = new AffichagePlanete(Project.planeteSelectionne, Project.skin);
			afficheurDroite.addActor(planete);
		}
		
		//Mise à jour du système et de la planète sélectionnés
		if (Project.planeteSelectionne != null && Project.planeteSelectionne.isReDraw()) {
			((AffichagePlanete) afficheurDroite.findActor("afficheur_planete")).update(Project.planeteSelectionne);
			Project.planeteSelectionne.setReDraw(false);
			((AffichageSysteme) afficheurDroite.findActor("afficheur_systeme")).update(Project.systemeSelectionne);
		}
	}
}
