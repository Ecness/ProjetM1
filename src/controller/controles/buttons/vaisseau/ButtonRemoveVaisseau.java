package controller.controles.buttons.vaisseau;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;

public class ButtonRemoveVaisseau extends TextButton {

	public ButtonRemoveVaisseau(Ville ville, Vaisseau vaisseau, Skin skin) {
		super("X", skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
//				new ConfirmationDestructionBatimentVille(ville, batiment, skin);
			}
			
		});
	}
}
