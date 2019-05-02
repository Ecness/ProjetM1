package view.galaxie.systeme.flotte.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.combat.PhaseCombat;
import model.entity.vaisseau.Vaisseau;
import view.galaxie.systeme.flotte.ButtonVaisseau;
import view.launcher.Project;

public class RapportCombat extends Window {

	public RapportCombat(PhaseCombat combat, Skin skin) {
		super("Rapport", skin);
		setName("rapport_combat");
		setSize(Project.width / 1.5f, Project.height / 1.5f);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);
		
		//Affichage des vaisseaux détruits par flottes
		Label joueur1 = new Label("" + combat.getMapCombat().getJoueur1(), skin);
		Label joueur2 = new Label("" + combat.getMapCombat().getJoueur2(), skin);
		HorizontalGroup vaisseauxDetruitsFlotte1 = new HorizontalGroup();
		HorizontalGroup vaisseauxDetruitsFlotte2 = new HorizontalGroup();
		for (Vaisseau vaisseau : combat.getDetailCombat().getVaisseauDetruit()) {
			if (vaisseau.getJoueur().equals(combat.getMapCombat().getJoueur1())) {
				vaisseauxDetruitsFlotte1.addActor(new ButtonVaisseau(vaisseau, skin));
			} else {
				vaisseauxDetruitsFlotte2.addActor(new ButtonVaisseau(vaisseau, skin));
			}
		}
		VerticalGroup group1 = new VerticalGroup();
		group1.addActor(joueur1);
		if (vaisseauxDetruitsFlotte1.getChildren().isEmpty()) {
			group1.addActor(new Label("Aucune perte", skin));
		} else {
			group1.addActor(vaisseauxDetruitsFlotte1);
		}
//		group1.setSize(getWidth(), joueur1.getPrefHeight() + vaisseauxDetruitsFlotte1.getPrefHeight());
//		group1.setPosition(getOriginX(), getOriginY() + getHeight() - getTitleLabel().getPrefHeight() - group1.getHeight());
		group1.setBounds(getOriginX(), getOriginY() + getHeight() - getTitleLabel().getPrefHeight() - group1.getPrefHeight(), getWidth(), group1.getPrefHeight());
		
		VerticalGroup group2 = new VerticalGroup();
		group2.addActor(joueur2);
		if (vaisseauxDetruitsFlotte2.getChildren().isEmpty()) {
			group2.addActor(new Label("Aucune perte", skin));
		} else {
			group2.addActor(vaisseauxDetruitsFlotte2);
		}
		group2.setSize(getWidth(), joueur2.getPrefHeight() + vaisseauxDetruitsFlotte2.getPrefHeight());
		group2.setPosition(getOriginX(), group1.getY() - group2.getHeight());
		group2.setBounds(getOriginX(), group1.getY() - group2.getPrefHeight(), getWidth(), group2.getPrefHeight());
		
		addActor(group1);
		addActor(group2);
		
		//Bouton pour fermer le rapport
		TextButton fermer = new TextButton("Fermer", skin);
		fermer.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				remove();
			}
			
		});
		fermer.setBounds(getWidth() / 2 - fermer.getPrefWidth() / 2, 
							getOriginY(), 
							fermer.getPrefWidth(), 
							fermer.getPrefHeight());
		addActor(fermer);
		
		//Affichage du rapport de combat
		ScrollPane rapport = new ScrollPane(new Label("" + combat.getDetailCombat(), skin), skin);
		rapport.setBounds(getOriginX(), 
							fermer.getY() + fermer.getPrefHeight(), 
							getWidth(), 
							getHeight() - group1.getPrefHeight() - group2.getPrefHeight() - getTitleLabel().getPrefHeight() - fermer.getPrefHeight());
		addActor(rapport);
		
		Gdx.input.setCursorCatched(false);
		
		Project.staticStage.addActor(this);
	}
}
