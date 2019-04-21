package controller.controles.buttons.vaisseau;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import view.galaxie.systeme.flotte.AffichageFlottes;
import view.launcher.Project;

public class ButtonReturnToFlotte extends TextButton {

	public ButtonReturnToFlotte(ScrollPane container, Systeme systeme, Skin skin) {
		super("Retour", skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				container.clear();
				container.setActor(new AffichageFlottes(container, systeme, skin));
				Project.flotteSelectionnee = null;
			}
			
		});
	}

}
