package view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ConfirmRetourMenuPrincipal;
import model.util.Sauvegarde;
import view.launcher.Project;

public class MenuPartie extends Window {
	private VerticalGroup group;
	
	public MenuPartie(Skin skin) {
		super("Menu", skin);
		setName("menu_partie");
		setSize(Project.width / 1.5f, Project.height / 1.5f);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		setMovable(false);
		setResizable(false);
		
		group = new VerticalGroup();
		group.setName("group");
		group.grow();
		
		TextButton reprendre = new TextButton("Reprendre", skin);
		reprendre.setName("reprendre");
		MenuPartie menu = this;
		reprendre.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				Project.pause = false;
				getParent().removeActor(menu);
			}

		});

		TextButton enregistrer = new TextButton("Enregistrer", skin);
		enregistrer.setName("enregistrer");
		//TODO Implémenter l'enregistrement
		enregistrer.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				Sauvegarde.sauvegarder();
			}

		});

		TextButton charger = new TextButton("Charger", skin);
		charger.setName("charger");
		//TODO Implémenter le chargement
		charger.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				Sauvegarde.charger();
			}

		});

		TextButton parametres = new TextButton("Paramètres", skin);
		parametres.setName("parametres");
		//TODO Implémenter les paramètres de partie
		parametres.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

			}

		});

		TextButton quitter = new TextButton("Quitter", skin);
		quitter.setName("quitter");
		quitter.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				new ConfirmRetourMenuPrincipal(skin);
			}

		});

		group.addActor(reprendre);
		group.addActor(enregistrer);
		group.addActor(charger);
		group.addActor(parametres);
		group.addActor(quitter);
		
		group.setSize(getWidth() / 2, getHeight() / 2);
		group.setPosition(getWidth() / 2 - group.getWidth() / 2, getHeight() / 2 - group.getHeight() / 2);
		//TODO Revoir l'espacement
		group.space(getHeight() / group.getChildren().size / 2);
		addActor(group);
		
		Gdx.input.setCursorCatched(false);
	}
}
