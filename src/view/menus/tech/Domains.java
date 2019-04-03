package view.menus.tech;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.controles.buttons.tech.OngletTech;
import model.entity.player.Joueur;
import model.entity.player.donnee.EnumTech;

public class Domains extends HorizontalGroup {
	
	public Domains(Group container, Joueur player, Skin skin) {
		super();
		setName("domains");
		setPosition(container.getOriginX(), (container.getOriginY() + container.getHeight()) -  ((Window) container).getTitleLabel().getHeight());
		setWidth(container.getWidth());
		
		addActor(new OngletTech(container, player, EnumTech.BATIMENT, skin));
		addActor(new OngletTech(container, player, EnumTech.MILITAIRE, skin));
		addActor(new OngletTech(container, player, EnumTech.BONUS, skin));
		
		TextButton close = new TextButton("Fermer", skin);
		close.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				container.remove();
				player.setOnMenuTech(false);
			}
			
		});
		addActor(close);
		
		setBounds(0, (container.getOriginY() + container.getHeight()) - ((Window) container).getTitleLabel().getPrefHeight() - getPrefHeight(), container.getWidth(), getPrefHeight());
	}
}
