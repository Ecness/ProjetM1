package view.launcher;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import controller.controles.CameraController;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumRessourceDepart;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.Parametre;
import model.Partie;
import model.carte.stellaire.Carte;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;
import view.galaxie.AffichageGalaxie;
import view.menus.MenuParametre;
import view.menus.MenuPrincipal;

public class Project extends ApplicationAdapter {
	public static int width;
	public static int height;

	public static Parametre parametre;

	public static Partie partie;
	public static Carte galaxie;

	public static Skin skin;
	/**Stage pour affichage statique (menus)**/
	public static Stage staticStage;
	/**Stage pour affichage dynamique (jeu hors menus)**/
	public static Stage dynamicStage;
	/**Gestionnaire d'input**/
	public static InputMultiplexer inputManager;

	public static int menu, cptTours;
	public static boolean change, affichageGalaxie;
	public static boolean clicked, changeSysteme;
	public static boolean finTour;
	public static boolean pause;
	public static boolean displayHasChanged;

	private AffichageGalaxie afficheurGalaxie;

	public static Rectangle cameraBound;

	public static Systeme systemeSelectionne;
	public static Planete planeteSelectionne;
	public static Flotte flotteSelectionnee;

	public static ShapeRenderer shape;
	Vector2 vector;

	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		parametre = new Parametre(null, EnumAbondanceRessource.FAIBLE, null, EnumTailleCarte.TEST, 2, 7, 5, EnumTailleMapCombat.IMMENSE, EnumRessourceDepart.NORMAL);

		System.err.println(width);
		System.err.println(height);

		staticStage = new Stage();
		dynamicStage = new Stage();
		inputManager = new InputMultiplexer(staticStage, dynamicStage);


		menu = 0;
		change = true;
		affichageGalaxie = false;
		clicked = false;

		skin = new Skin(new FileHandle("Ressources/Skin/default/uiskin.json"));

		Gdx.input.setInputProcessor(inputManager);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!pause) {
			CameraController.controle();
			staticStage.getCamera().update();
			dynamicStage.getCamera().update();

			if (change) {
				staticStage.clear();
				dynamicStage.clear();
				switch(menu) {
				case 0:
					new MenuPrincipal();
					break;
				case 1:
					new MenuParametre(skin);
					break;
				case 2:
					//				partie = new Partie(parametre);
					break;
				}
				change = false;
			}

			if (finTour) {
				for (Joueur joueur : partie.getTJoueur()) {
					joueur.debutDeTour();
				}
				finTour = false;
				cptTours++;
			}
		}

		if (affichageGalaxie) {
			if (afficheurGalaxie == null) {
				afficheurGalaxie = new AffichageGalaxie(partie.getGalaxie(), skin);
			}
			afficheurGalaxie.render();
		}

		dynamicStage.act();
		dynamicStage.draw();
		staticStage.draw();
		staticStage.act();
	}

	@Override
	public void dispose () {
		staticStage.dispose();
		dynamicStage.dispose();
	}
}
