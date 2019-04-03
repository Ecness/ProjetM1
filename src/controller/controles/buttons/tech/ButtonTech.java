package controller.controles.buttons.tech;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.entity.player.Joueur;
import model.entity.player.Science;
import model.entity.player.donnee.EnumTech;
import view.launcher.Project;
import view.menus.tech.DescriptionTech;

public class ButtonTech extends TextButton {
	private boolean clickable;

	public ButtonTech(Group parentContainer, Joueur player, EnumTech typeTech, Science tech, Skin skin) {
		super(tech.getNom(), skin);
		setName("button_tech_" + tech.getNom());
		
		clickable = true;

		if (tech.isRechercher()) {
			clickable = false;
			setColor(Color.GREEN);
		} else if (typeTech == EnumTech.BATIMENT && !player.isBatimentTechUnlockable(tech)
				|| typeTech == EnumTech.MILITAIRE && !player.isMilitaireTechUnlockable(tech)
				|| typeTech == EnumTech.BONUS && !player.isBonusTechUnlockable(tech)) {
			clickable = false;
			setColor(Color.RED);
		}
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (clickable) {
					player.setSearchingTech(tech);
					player.setTechHasChanged(true);
					Project.displayHasChanged = true;
				}
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);

				parentContainer.addActor(new DescriptionTech(parentContainer, player, tech, skin));
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				super.exit(event, x, y, pointer, toActor);

				parentContainer.findActor("description_tech").remove();
			}

		});
		
	}
}
