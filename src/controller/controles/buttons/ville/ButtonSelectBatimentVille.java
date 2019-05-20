package controller.controles.buttons.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.controles.select.SelectBatimentVille;
import model.carte.stellaire.Ville;

public class ButtonSelectBatimentVille extends TextButton {

		public ButtonSelectBatimentVille(Ville ville, Skin skin) {
			super("+", skin);
			
			addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					new SelectBatimentVille(ville, skin);
				}
				
			});
		}
}
