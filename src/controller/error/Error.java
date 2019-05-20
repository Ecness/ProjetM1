package controller.error;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import view.launcher.Project;

public class Error extends Dialog {

	public Error(String title, String content, Skin skin) {
		super(title, skin);

		setSize(Project.width / 4, Project.height / 4);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		setColor(Color.RED);
		
		text(content);
		
		TextButton close = new TextButton("Fermer", skin);
		close.setName("close");
		close.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				getParent().remove();
			}
			
		});
		
		button(close);
		
		Gdx.input.setCursorCatched(false);
		
		Project.staticStage.addActor(this);
	}
}
