package controller.controles.buttons.tech;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.entity.player.Joueur;
import model.entity.player.donnee.EnumTech;
import view.menus.tech.TechContainer;

public class OngletTech extends TextButton {

	public OngletTech(Group container, Joueur player, EnumTech typeTech, Skin skin) {
		super("" + typeTech, skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				if (container.findActor("tech_container") != null) {
					container.findActor("tech_container").remove();
				}
				container.addActor(new TechContainer(container, player, typeTech, skin));
			}
		});
	}
}
