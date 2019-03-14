package view.galaxie.planete.ville;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.boutons.ville.ConfirmationDestructionBatimentVille;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class AffichageBatimentsVille extends ScrollPane {
	private VerticalGroup group;

	public AffichageBatimentsVille(Ville ville, Skin skin) {
		super(null, skin);
		this.setFadeScrollBars(false);
		setName("liste_batiments");
		
		group = new VerticalGroup();
		
		for (BatimentVille batiment : ville.getTBatimentVille()) {
			TextButton bat = new TextButton(batiment.getNom(), skin);
			bat.setName(batiment.getNom());
			bat.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					setActor(new ConfirmationDestructionBatimentVille(ville, batiment, skin));
				}
				
			});
			
			group.addActor(bat);
		}
		
		TextButton add = new TextButton("+", skin);
		add.setName("add_batimentVille");
		add.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				setActor(new SelectBatimentVille(ville, skin));
			}
			
		});
		
		group.addActor(add);
		
		setActor(group);
	}
	
	public void update(Ville ville, Skin skin) {
		group.clear();
		
		for (BatimentVille batiment : ville.getTBatimentVille()) {
			TextButton bat = new TextButton(batiment.getNom(), skin);
			bat.setName(batiment.getNom());
			bat.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					setActor(new ConfirmationDestructionBatimentVille(ville, batiment, skin));
				}
				
			});
			
			group.addActor(bat);
		}
		
		TextButton add = new TextButton("+", skin);
		add.setName("add_batimentVille");
		add.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				setActor(new SelectBatimentVille(ville, skin));
			}
			
		});
		
		group.addActor(add);
		
		setActor(group);
	}
}
