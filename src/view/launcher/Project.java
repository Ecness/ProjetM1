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

import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumRessourceDepart;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.Parametre;
import view.galaxie.AffichageGalaxie;
import view.menus.MenuParametre;
import view.menus.MenuPrincipal;

public class Project extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static int width;
	//private Texture img;
	public static int height;
	
	public static Parametre parametre;
	
	public static AffichageGalaxie galaxie;
	
	Sprite sprite;
	
	public Skin skin;
	public Stage stage;
	
	public static Camera camera;
	FitViewport viewPort;
	
	public static int menu;
	public static boolean change, affichageGalaxie;
	
	public static ShapeRenderer shape;
	Vector2 vector;
	
	int  a = 100, b = 100;
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		parametre = new Parametre(null, EnumAbondanceRessource.FAIBLE, null, EnumTailleCarte.TEST, 2, 7, 5, EnumTailleMapCombat.IMMENSE, EnumRessourceDepart.NORMAL);
		
		System.err.println(width);
		System.err.println(height);
		
		camera = new OrthographicCamera(width, height);
//		viewPort = new FitViewport(width, height, camera);
		
		menu = 0;
		change = true;
		
		batch = new SpriteBatch();
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		vector = new Vector2(500, 500);
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.begin();
		
		if (change) {
			stage.clear();
			switch(menu) {
			case 0:
				stage = new MenuPrincipal(this).getStage();
				break;
			case 1:
				stage = new MenuParametre(this).getStage();
				break;
			case 2:
				galaxie = new AffichageGalaxie();
				break;
			}
			change = false;
			Gdx.input.setInputProcessor(stage);
		}
		
		if (affichageGalaxie) {
			galaxie.render();
		}
		
		batch.end();
		
		stage.act();
		stage.draw();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
		stage.dispose();
	}
}
