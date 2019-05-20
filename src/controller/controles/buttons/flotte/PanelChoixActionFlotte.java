package controller.controles.buttons.flotte;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class PanelChoixActionFlotte extends Window {

	public PanelChoixActionFlotte(Systeme systeme, Skin skin) {
		super("Action flotte", skin);
		setSize(Project.width / 4, Project.height / 4);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		
		HorizontalGroup group = new HorizontalGroup();
		group.setBounds(getOriginX(), 
						getOriginY() + getHeight() / 2, 
						getWidth(), 
						group.getPrefHeight());
		
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				remove();
			}
			
		});
		
		TextButton fusion = new TextButton("Fusionner", skin);
		fusion.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				Gdx.input.setCursorCatched(false);
				Project.staticStage.addActor(new PanelFusionFlotte(systeme, skin));
			}
			
		});
		
		group.addActor(cancel);
		group.addActor(fusion);
		
		addActor(group);
	}
}
