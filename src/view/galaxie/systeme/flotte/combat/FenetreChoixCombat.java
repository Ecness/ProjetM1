package view.galaxie.systeme.flotte.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;
import view.launcher.Project;

public class FenetreChoixCombat extends Window {

	public FenetreChoixCombat(Systeme systeme, Flotte flotte1, Flotte flotte2, Skin skin) {
		super("Combat", skin);
		setName("fenetre_combat");
		setSize(Project.width / 2, Project.height / 2);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		
		SplitPane conteneurFlottes = new SplitPane(new FlotteCombat(flotte1, skin), new FlotteCombat(flotte2, skin), false, skin);
		conteneurFlottes.setBounds(getOriginX(), 
									getOriginY() + getHeight() / 2 - getTitleLabel().getHeight(), 
									getWidth(), 
									getHeight() / 2);
		conteneurFlottes.setName("conteneur_flottes");
		addActor(conteneurFlottes);
		
		Label text = new Label("Lancer le combat ?", skin);
		text.setPosition(getOriginX() + getWidth() / 2 - text.getPrefWidth() / 2,
							conteneurFlottes.getY() - text.getPrefHeight());
		addActor(text);
		
		TextButton annuler = new TextButton("Annuler", skin);
		annuler.setPosition(getOriginX(), getOriginY());
		annuler.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				remove();
			}
			
		});
		
		TextButton confirmer = new TextButton("Confirmer", skin);
		confirmer.setPosition(getOriginX() + getWidth() - confirmer.getPrefWidth(), getOriginY());
		confirmer.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				//TODO Intégrer le combat
			}
			
		});
		addActor(annuler);
		addActor(confirmer);
		
		Gdx.input.setCursorCatched(false);
		
		Project.staticStage.addActor(this);
	}
}
