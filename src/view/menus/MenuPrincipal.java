package view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import view.launcher.Project;

public class MenuPrincipal {
	private Skin skin;
	private Stage stage;
	
	final Button nouvellePartie;
	final Button chargerPartie;
	final Button parametres;
	final Button quitter;
	
	public MenuPrincipal(final Project projet) {
		skin = projet.skin;
		stage = projet.stage;
		
		nouvellePartie = new TextButton("Nouvelle Partie", skin);
		nouvellePartie.setSize(Project.width/4, Project.height/10);
		nouvellePartie.setPosition(Project.width/2-200, (3*Project.height/8)+15);
		
		chargerPartie = new TextButton("Charger Partie", skin);
		chargerPartie.setSize(Project.width/4, Project.height/10);
		chargerPartie.setPosition(Project.width/2-200, (2*Project.height/8)+15);
		
		parametres = new TextButton("Paramètres", skin);
		parametres.setSize(Project.width/4, Project.height/10);
		parametres.setPosition(Project.width/2-200, (1*Project.height/8)+15);
		
		quitter = new TextButton("Quitter", skin);
		quitter.setSize(Project.width/4, Project.height/10);
		quitter.setPosition(Project.width/2-200, (0*Project.height/8)+15);
		
		nouvellePartie.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 1;
			}
		});
		
		quitter.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		stage.addActor(nouvellePartie);
		stage.addActor(chargerPartie);
		stage.addActor(parametres);
		stage.addActor(quitter);
	}

	public Stage getStage() {
		return stage;
	}
}
