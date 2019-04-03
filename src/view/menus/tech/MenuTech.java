package view.menus.tech;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import model.entity.player.Joueur;
import view.launcher.Project;

public class MenuTech extends Window {

	public MenuTech(Joueur player, Skin skin) {
		super("Technologies", skin);
		setName("menu_tech");
		
		setSize(Project.width * 0.8f, Project.height * 0.8f);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);

		addActor(new Domains(this, player, skin));

		Gdx.input.setCursorCatched(false);

		Project.staticStage.addActor(this);
	}
}
