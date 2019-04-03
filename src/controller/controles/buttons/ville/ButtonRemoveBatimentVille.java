package controller.controles.buttons.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.batiment.ConfirmationDestructionBatimentVille;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class ButtonRemoveBatimentVille extends TextButton {

	public ButtonRemoveBatimentVille(Ville ville, BatimentVille batiment, Skin skin) {
		super("X", skin);
		
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				new ConfirmationDestructionBatimentVille(ville, batiment, skin);
			}
			
		});
	}
}
