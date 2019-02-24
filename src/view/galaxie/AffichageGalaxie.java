package view.galaxie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

	private ShapeRenderer shapeRenderer;

	private Button boutonMenu;
	private HorizontalGroup barreDuHaut;

	public AffichageGalaxie (Carte carte) {
		this.carte = carte;

		shapeRenderer = new ShapeRenderer();
		carte.affichage();

		//		barreDuHaut.pad(0, 20, 0, 20);

		boutonMenu = new TextButton("Menu", Project.skin);
		boutonMenu.setPosition(0,  Project.staticStage.getCamera().viewportHeight-50);
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
		boutonMenu.addListener(new InputListener() {

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//				Gdx.input.setInputProcessor(Project.staticStage);
				System.out.println("STATIC");
			}
//
//			@Override
//			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//				Gdx.input.setInputProcessor(Project.dynamicStage);
//				System.out.println("NOT_STATIC");
//			}

		});


		barreDuHaut = new HorizontalGroup();
		barreDuHaut.setPosition(boutonMenu.getX() + boutonMenu.getWidth(),  Project.staticStage.getCamera().viewportHeight-50);
		barreDuHaut.setSize(Project.staticStage.getCamera().viewportWidth, 50);
		barreDuHaut.setColor(Color.RED);
		//			barreDuHaut.addActor(actor);
		//			barreDuHaut.fill();
		//			barreDuHaut.align(Align.center);
		//			barreDuHaut.wrap(true);
		barreDuHaut.space(Project.staticStage.getCamera().viewportWidth / 16);
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

		Project.staticStage.addActor(boutonMenu);
		Project.staticStage.addActor(barreDuHaut);

		for (Systeme sys : carte.getListeSysteme()) {
			Project.dynamicStage.addActor(sys.getBouton());
		}
	}

	public void render() {
		shapeRenderer.setProjectionMatrix(Project.dynamicStage.getCamera().combined);
		shapeRenderer.begin(ShapeType.Filled);
		for (Systeme sys : carte.getListeSysteme()) {
			if (sys.getJoueur() == null) {
				shapeRenderer.setColor(Color.WHITE);
			} else {
				shapeRenderer.setColor(sys.getJoueur().getCouleur());
			}
			shapeRenderer.circle(sys.getX(), sys.getY(), 10);
			for (Vector2 vect : sys.getLiens().values()) {
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.line(new Vector2(sys.getCoordonnees().getX(), sys.getCoordonnees().getY()), new Vector2(sys.getCoordonnees().getX() + vect.x, sys.getCoordonnees().getY() + vect.y));
			}
			sys.getBouton().setPosition(sys.getX() - 10, sys.getY() - 10);
//			Project.dynamicStage.getBatch().begin();
//			sys.getBouton().draw(Project.dynamicStage.getBatch(), 1);
//			sys.getBouton().toFront();
//			Project.dynamicStage.getBatch().end();
		}
		shapeRenderer.end();

		for (Actor qteRessource : barreDuHaut.getChildren().items) {
			if (qteRessource instanceof Label) {
				((Label) qteRessource).setText(qteRessource.getName() + " : " + Project.partie.getTJoueur()[0].getTRessource().get(EnumRessource.valueOf(qteRessource.getName())));
			}
		}
	}
}
