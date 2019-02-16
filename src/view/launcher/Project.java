package view.launcher;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

import controller.controles.CameraController;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumRessourceDepart;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.Parametre;
import model.Partie;
import model.carte.stellaire.Carte;
import model.entity.player.Joueur;
import view.menus.MenuParametre2;
import view.menus.MenuPrincipal;

public class Project extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static int width;
	private Sprite img;
	public static int height;
	
	public static Parametre parametre;
	
	public static Partie partie;
	public static Carte galaxie;
	
	Sprite sprite;
	
	public static Skin skin;
	public static Stage stage;
	
	public static Camera camera;
	FitViewport viewPort;
	
	public static int menu;
	public static boolean change, affichageGalaxie;
	public static boolean clicked;
	
	public static Joueur[] joueurs;
	
	public static ShapeRenderer shape;
	Vector2 vector;
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		parametre = new Parametre(null, EnumAbondanceRessource.FAIBLE, null, EnumTailleCarte.TEST, 2, 7, 5, EnumTailleMapCombat.IMMENSE, EnumRessourceDepart.NORMAL);
		
		System.err.println(width);
		System.err.println(height);
		
		camera = new OrthographicCamera(width, height);
//		viewPort = new FitViewport(50, 50, camera);
//		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		
		menu = 0;
		change = true;
		affichageGalaxie = false;
		clicked = false;
		
		joueurs = new Joueur[8];
		
		batch = new SpriteBatch();
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
//		img = new Sprite(new Texture("ressources/badlogic.jpg"));
//		img.setCenter(0, 0);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		CameraController.controle();
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		
		batch.begin();

		if (change) {
			stage.clear();
			switch(menu) {
			case 0:
				stage = new MenuPrincipal(this).getStage();
				break;
			case 1:
				new MenuParametre2();
				break;
			case 2:
//				partie = new Partie(parametre);
				break;
			}
			change = false;
			Gdx.input.setInputProcessor(stage);
		}
		
		if (affichageGalaxie) {
			clicked = Gdx.input.isTouched();
			partie.getGalaxie().render();
		}
		
		if (stage == null) {
			System.out.print("ERREUR");
		}

		stage.act();
		stage.draw();
		batch.end();
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
		stage.dispose();
	}
}
