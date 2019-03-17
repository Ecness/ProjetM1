package controller.boutons.ville;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Ville;

public class ErrorTechNotUnlocked extends TextButton {
	
	public ErrorTechNotUnlocked(Ville ville, Skin skin) {
		super("Technologie non débloquée", skin);
		setColor(Color.RED);
		setFillParent(true);
		addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				ville.setReDrawBatiments(true);
			}
			
		});
	}
}
