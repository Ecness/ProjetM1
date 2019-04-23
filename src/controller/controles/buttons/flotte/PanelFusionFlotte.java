package controller.controles.buttons.flotte;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;
import view.launcher.Project;

public class PanelFusionFlotte extends Window {

	public PanelFusionFlotte(Systeme systeme, Skin skin) {
		super("Fusion flottes", skin);
		setSize(Project.width / 2, Project.height / 2);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		
		HorizontalGroup buttons = new HorizontalGroup();
		SelectFlotteToMerge groupSource = new SelectFlotteToMerge(systeme, skin);
		groupSource.setName("group_source");
		SelectFlotteToMerge groupCible = new SelectFlotteToMerge(systeme, skin);
		groupCible.setName("group_cible");
		PanelActions actions = new PanelActions(systeme, groupSource, groupCible, skin);

		//Annulation des modifications
		TextButton cancel = new TextButton("Annuler", skin);
		cancel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				groupSource.clearAllFlotteSelected();
				groupCible.clearAllFlotteSelected();
				remove();
			}
		});
		
		//Validation des modifications
		TextButton valider = new TextButton("Valider", skin);
		valider.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				systeme.getFlottes().clear();
				for (Flotte flotte : SelectFlotteToMerge.getCopyFlotte()) {
					if (!flotte.getTVaisseau().isEmpty()) {
						systeme.getFlottes().add(flotte);
						systeme.getJoueur().getTFlotte().add(flotte);
					}
				}
				groupSource.clearAllFlotteSelected();
				groupCible.clearAllFlotteSelected();
				Project.displayHasChanged = true;
				
				remove();
			}
		});
		
		//Placement et ajout des acteurs
		buttons.addActor(cancel);
		buttons.addActor(valider);
		buttons.setBounds(getOriginX(), getOriginY(), getWidth(), buttons.getPrefHeight());
		buttons.fill();
		addActor(buttons);
		
		groupSource.setBounds(getOriginX(), 
								getOriginY() + buttons.getPrefHeight(), 
								getWidth() / 3, 
								getHeight() - getTitleLabel().getPrefHeight() - buttons.getPrefHeight());
		addActor(groupSource);

		groupCible.setBounds(getOriginX() + getWidth() * (2f/3f), 
				getOriginY() + buttons.getPrefHeight(), 
				getWidth() / 3, 
				getHeight() - getTitleLabel().getPrefHeight() - buttons.getPrefHeight());
		addActor(groupCible);
		
		actions.setBounds(getOriginX() + getWidth() * (1f/3f), 
				getOriginY() + buttons.getPrefHeight(), 
				getWidth() / 3, 
				getHeight() - getTitleLabel().getPrefHeight() - buttons.getPrefHeight());
		addActor(actions);
		
	}
	
	public void update(Systeme systeme, Skin skin) {
		((SelectFlotteToMerge) findActor("group_source")).update(systeme, skin);
		((SelectFlotteToMerge) findActor("group_cible")).update(systeme, skin);
	}
}
