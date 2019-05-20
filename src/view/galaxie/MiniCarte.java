//package view.galaxie;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//
//import model.carte.stellaire.Carte;
//import model.carte.stellaire.Systeme;
//import view.launcher.Project;
//
//public class MiniCarte {
//	
//	private ShapeRenderer shapeRenderer;
//	/**Zone de la mini carte**/
//	private Rectangle zoneMiniMap;
//	/**Zone affichée de la carte stellaire**/
//	private Rectangle zoneAffichee;
//	
//	private Carte carte;
//	
//	private float ratio = 5;
//
//	public MiniCarte(Carte carte) {
//		this.carte = carte;
//		shapeRenderer = new ShapeRenderer();
//		
//	}
//	
//	public void render() {
//		shapeRenderer.setProjectionMatrix(Project.miniMapStage.getCamera().combined);
//		shapeRenderer.begin(ShapeType.Filled);
//		for (Systeme sys : carte.getListeSysteme()) {
//			for (Vector2 vect : sys.getLiens().values()) {
//				shapeRenderer.setColor(Color.WHITE);
//				Vector2 origine = Project.miniMapStage.screenToStageCoordinates(new Vector2(sys.getCoordonnees().getX(), sys.getCoordonnees().getY()));
//				Vector2 destination = Project.miniMapStage.screenToStageCoordinates(new Vector2(sys.getCoordonnees().getX() + vect.x, sys.getCoordonnees().getY() + vect.y));
//				shapeRenderer.line(origine, destination);
//			}
//			if (sys.getJoueur() == null) {
//				shapeRenderer.setColor(Color.WHITE);
//			} else {
//				shapeRenderer.setColor(sys.getJoueur().getCouleur());
//			}
//			Vector2 centre = Project.miniMapStage.screenToStageCoordinates(new Vector2(sys.getX(), sys.getY()));
//			shapeRenderer.circle(centre.x, centre.y, 2);
//			Vector2 position = Project.miniMapStage.screenToStageCoordinates(new Vector2(sys.getX() - 2, sys.getY() - 2));
//			sys.getBouton().setPosition(position.x, position.y);
//		}
//		shapeRenderer.end();
//	}
//}
