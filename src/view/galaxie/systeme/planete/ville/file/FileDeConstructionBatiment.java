package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.batiment.ConfirmationAnnulationBatimentVille;
import controller.controles.select.SelectBatimentVille;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class FileDeConstructionBatiment extends VerticalGroup {

	public FileDeConstructionBatiment(Ville ville, Skin skin) {
		super();
		setName("liste_attente_batiment");
		
		for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
			TextButton bat = new TextButton(batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION)), skin);
			bat.setName("batiment_file_" + batiment.getNom());
			bat.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					new ConfirmationAnnulationBatimentVille(ville, batiment, skin);
				}
				
			});
			
			addActor(bat);
		}
		
		TextButton add = new TextButton("+", skin);
		add.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				new SelectBatimentVille(ville, skin);
			}
			
		});
		
		addActor(add);
	}
	
	public void update(Ville ville, Skin skin) {
		if (ville.isReDrawFilesBatiments()) {
			clear();
			ville.setReDrawFilesBatiments(false);
		}
		
		for (int i = 0; i < ville.getFileDeConstructionBatiment().size(); i++) {
			BatimentVille batiment = ville.getFileDeConstructionBatiment().get(i);
			String text = batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION));
			if (findActor("batiment_file_" + batiment.getNom()) != null) {
				((TextButton) findActor("batiment_file_" + batiment.getNom())).setText(text);
			} else {
				TextButton button = new TextButton(text, skin);
				button.setName("batiment_file_" + batiment.getNom());
				
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						new ConfirmationAnnulationBatimentVille(ville, batiment, skin);
					}
					
				});
				
				addActor(button);
			}

		}
		
		TextButton add = new TextButton("+", skin);
		add.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				new SelectBatimentVille(ville, skin);
			}
			
		});
		
		addActor(add);
	}
}
