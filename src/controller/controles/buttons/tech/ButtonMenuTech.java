package controller.controles.buttons.tech;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.entity.player.Joueur;
import view.menus.tech.MenuTech;

public class ButtonMenuTech extends TextButton {

	public ButtonMenuTech(Joueur player, Skin skin) {
		super("Recherche", skin);
		setName("button_recherche");

		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (!player.isOnMenuTech()) {
					new MenuTech(player, skin);
					player.setOnMenuTech(true);
				}
			}

		});
	}
}
