package view.launcher;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;

import model.parametre.Parametre;
import view.galaxie.AffichageGalaxie;

public class Project extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static int width;
	//private Texture img;
	public static int height;
	
	public static Parametre parametre;
	
	public AffichageGalaxie galaxie;
	
	public Skin skin;
	public Stage stage;
	
	public static Camera camera;
	FillViewport viewPort;
	
	public static int menu;
	public static boolean change;
	
	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		FillViewport viewPort = new FillViewport(width, height, camera);
		camera = new OrthographicCamera(viewPort.getScreenWidth(), viewPort.getScreenHeight());
		
		menu = 0;
		change = true;
		
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		//img = new Texture("./GoldenFuck.jpg");
		
		stage = new Stage();
		
		galaxie = new AffichageGalaxie();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		galaxie.render();
//		batch.begin();
//		//batch.draw(img, 0, 0);

//		batch.end();
//		if (change) {
//			stage.clear();
//			switch(menu) {
//			case 0:
//				stage = new MenuPrincipal(this).getStage();
//				break;
//			case 1:
//				stage = new MenuParametre(this).getStage();
//				break;
//			}
//			change = false;
//			Gdx.input.setInputProcessor(stage);
//		}
//		
//		
//		stage.act();
//		stage.draw();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
		stage.dispose();
	}
}
