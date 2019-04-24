package view.galaxie.systeme.flotte;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.controles.buttons.flotte.PanelChoixActionFlotte;
import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;
import view.galaxie.systeme.flotte.combat.FenetreChoixCombat;
import view.launcher.Project;

public class AffichageFlottes extends Table {

	public AffichageFlottes(ScrollPane container, Systeme systeme, Skin skin) {
		super(skin);
		setName("table_afficheur_flottes");
		
		int i = 0;
		for (Flotte flotte : systeme.getFlottes()) {
			if (i == 3) {
				row();
				i = 0;
			}
			
			TextButton button = new TextButton(flotte.getNom(), skin);
			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					remove();
					container.setActor(new AffichageDetailFlotte(container, flotte, systeme, skin));
					Project.flotteSelectionnee = flotte;
					Project.displayHasChanged = true;
				}
				
			});
			button.addListener(new ClickListener(Buttons.RIGHT) {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					if (Project.flotteSelectionnee != null && !flotte.getJoueur().equals(Project.flotteSelectionnee.getJoueur())) {
						new FenetreChoixCombat(systeme, Project.flotteSelectionnee, flotte, skin);
					}
//					remove();
//					container.setActor(new AffichageDetailFlotte(container, flotte, systeme, skin));
//					Project.flotteSelectionnee = flotte;
				}
				
			});
			
			add(button);
			i++;
		}
		
		if (!systeme.getFlottes().isEmpty()) {
			row();
			TextButton option = new TextButton("+", skin);
			option.addListener(new ClickListener() {
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					Gdx.input.setCursorCatched(false);
					Project.staticStage.addActor(new PanelChoixActionFlotte(systeme, skin));
					Project.displayHasChanged = true;
				}
				
			});
			add(option);
		}
	}
	
	public void update(ScrollPane container, Systeme systeme, Skin skin) {
		Table table = ((Table) findActor("table_afficheur_flottes"));
		table.reset();
		
		int i = 1;
		for (Flotte flotte : systeme.getFlottes()) {
			if (i == 3) {
				table.row();
				i = 1;
			}
			
			TextButton button = new TextButton(flotte.getNom(), skin);
			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					remove();
					container.setActor(new AffichageDetailFlotte(container, flotte, systeme, skin));
				}
				
			});
			button.addListener(new ClickListener(Buttons.RIGHT) {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					
					if (Project.flotteSelectionnee != null && !flotte.getJoueur().equals(Project.flotteSelectionnee.getJoueur())) {
						new FenetreChoixCombat(systeme, Project.flotteSelectionnee, flotte, skin);
					}
//					remove();
//					container.setActor(new AffichageDetailFlotte(container, flotte, systeme, skin));
//					Project.flotteSelectionnee = flotte;
				}
				
			});
			
			table.add(button);
		}
	}
}
