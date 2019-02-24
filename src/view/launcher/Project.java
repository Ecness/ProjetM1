package view.launcher;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
import model.carte.stellaire.Systeme;
import model.entity.player.Joueur;
import view.menus.MenuParametre2;
import view.menus.MenuPrincipal;

public class Project extends ApplicationAdapter {
	public static int width;
	private Sprite img;
	public static int height;
	
	public static Parametre parametre;
	
	public static Partie partie;
	public static Carte galaxie;
	
	Sprite sprite;
	
	public static Skin skin;
	/**Stage pour affichage statique (menus)**/
	public static Stage staticStage;
	/**Stage pour affichage dynamique (jeu hors menus)**/
	public static Stage dynamicStage;
	/**Gestionnaire d'input**/
	public static InputMultiplexer inputManager;
	
	public static int menu;
	public static boolean change, affichageGalaxie;
	public static boolean clicked;
	
	public static Joueur[] joueurs;
	
	public static Systeme systemeSelectionne;
	
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
//		staticStage.addListener(new EventListener() {
//			
//			@Override
//			public boolean handle(Event event) {
//				InputEvent inputEvent = (InputEvent) event;
//				
//				inputEvent.setStage(staticStage);
//				
//				return false;
//			}
//		});
		dynamicStage = new Stage();
		inputManager = new InputMultiplexer(staticStage, dynamicStage);
		
		
		menu = 0;
		change = true;
		affichageGalaxie = false;
		clicked = false;
		
		joueurs = new Joueur[8];
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		Gdx.input.setInputProcessor(inputManager);
		
//		img = new Sprite(new Texture("ressources/badlogic.jpg"));
//		img.setCenter(0, 0);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
				new MenuParametre2();
				break;
			case 2:
//				partie = new Partie(parametre);
				break;
			}
			change = false;
		}
		
		if (affichageGalaxie) {
			clicked = Gdx.input.isTouched();
			partie.getGalaxie().render();
		}

		staticStage.act();
		dynamicStage.act();
		staticStage.draw();
		dynamicStage.draw();
		
		
	}
	
	@Override
	public void dispose () {
		staticStage.dispose();
		dynamicStage.dispose();
	}
}
