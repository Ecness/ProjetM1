package view.galaxie;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import model.EnumRessource;
import model.carte.stellaire.Carte;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageGalaxie {
	private Carte carte;
	
	private List<Button> liste;
	
	private Stage stage;
//	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	private Button boutonMenu;
	private HorizontalGroup barreDuHaut;
	
	public AffichageGalaxie (Carte carte) {
//		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
//		Project.stage.clear();
		
		stage = Project.stage;
		
		this.carte = carte;
		
		shapeRenderer = new ShapeRenderer();
		carte.affichage();
		
		liste = new ArrayList<Button>();
		
//		barreDuHaut.pad(0, 20, 0, 20);
		
//		for (Systeme sys : carte.getListeSysteme()) {
			boutonMenu = new TextButton("Menu", Project.skin);
//			liste.add(boutonMenu);
			boutonMenu.setPosition(0,  Project.camera.viewportHeight-50);
			boutonMenu.setSize(200,50);
//			actor.setBounds(sys.getX(), sys.getY(), 20, 20);
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
			
			
			barreDuHaut = new HorizontalGroup();
			barreDuHaut.setPosition(boutonMenu.getX() + boutonMenu.getWidth(),  Project.camera.viewportHeight-50);
			barreDuHaut.setSize(Project.camera.viewportWidth, 50);
			barreDuHaut.setColor(Color.RED);
//			barreDuHaut.addActor(actor);
//			barreDuHaut.fill();
//			barreDuHaut.align(Align.center);
//			barreDuHaut.wrap(true);
			barreDuHaut.space(Project.camera.viewportWidth / 16);
//			barreDuHaut.top();
			barreDuHaut.expand();
//			barreDuHaut.expand(true);
//			barreDuHaut.wrap(true);
//			barreDuHaut.rowCenter();
			
			for (EnumRessource ressource : EnumRessource.values()) {
				Group groupe = new Group();
//				Image icone = new Image(new Texture("ressources/" + ressource + ".png"));
				
//				System.out.println(Project.partie.getTJoueur()[0]);
				
				Label qteRessource = new Label("", Project.skin);
				qteRessource.setName(ressource.toString());
//				
//				groupe.addActor(icone);
//				groupe.addActor(qteRessource);
//				
//				barreDuHaut.addActor(groupe);
				
				barreDuHaut.addActor(qteRessource);
			}

			//			System.out.println(sys.getIdSysteme() + "-----" + sys.getNbLiens() + "/" + sys.getNbLiensMax() + "-----");
//		}
		
//		for (Button actor : liste) {
			stage.addActor(boutonMenu);
			stage.addActor(barreDuHaut);
//		}
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
		
		for (Actor qteRessource : barreDuHaut.getChildren().items) {
			if (qteRessource instanceof Label) {
				((Label) qteRessource).setText(qteRessource.getName() + " : " + Project.partie.getTJoueur()[0].getTRessource().get(EnumRessource.valueOf(qteRessource.getName())));
			}
		}
		
		Gdx.input.setInputProcessor(Project.stage);
//		if (Gdx.input.isTouched()/* && Project.stage.hit(Gdx.input.getX(), Gdx.input.getY(), true) != null*/) {
////			Gdx.app.exit();
//			System.out.println("IN");
//		}
	}
	
	public Stage getStage() {
		return stage;
	}
}
