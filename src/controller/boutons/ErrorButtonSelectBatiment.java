package controller.boutons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Planete;

public class ErrorButtonSelectBatiment extends TextButton {

	public ErrorButtonSelectBatiment(Planete planete, Skin skin) {
		super("Ressources insuffisantes", skin);
		setColor(Color.RED);
		setFillParent(true);
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				planete.setReDraw(true);
			}

		});
	}
	
}
