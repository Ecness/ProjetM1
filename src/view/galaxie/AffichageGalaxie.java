package view.galaxie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import view.launcher.Project;

public class AffichageGalaxie {
	private Stage stage;
	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	private Circle circle;
	
	public AffichageGalaxie () {
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
//		Carte carte = new Carte(Projet.parametre);
		
		circle = new Circle(500, 500, 100);
		shapeRenderer = new ShapeRenderer();
		Project.batch.setProjectionMatrix(Project.camera.combined);
		
		
	}
	
	public void render() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(circle.x, circle.y, circle.radius);
		shapeRenderer.end();
	}
}
