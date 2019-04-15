package view.galaxie.systeme.flotte;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;

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
				}
				
			});
			
			add(button);
			i++;
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
			
			table.add(button);
		}
	}
}
