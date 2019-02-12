package view.galaxie;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageGalaxie {
	private Carte carte;
	
//	private Stage stage;
//	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	
	public AffichageGalaxie () {
//		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		
		carte = new Carte(Project.parametre);
		
		shapeRenderer = new ShapeRenderer();
		carte.affichage();
		
		for (Systeme sys : carte.getListeSysteme()) {
			System.out.println(sys.getIdSysteme() + "-----" + sys.getNbLiens() + "/" + sys.getNbLiensMax() + "-----");
		}
	}
	
	public void render() {
		Project.camera.update();
		shapeRenderer.setProjectionMatrix(Project.camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		for (Systeme sys : carte.getListeSysteme()) {
			shapeRenderer.circle(sys.getX(), sys.getY(), 10);
			for (Vector2 vect : sys.getLiens().values()) {
				shapeRenderer.line(new Vector2(sys.getCoordonnees().getX(), sys.getCoordonnees().getY()), new Vector2(sys.getCoordonnees().getX() + vect.x, sys.getCoordonnees().getY() + vect.y));
			}
		}
		shapeRenderer.end();
		Project.batch.setProjectionMatrix(Project.camera.combined);
	}
}
