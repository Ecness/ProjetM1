package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.batiment.ConfirmationAnnulationBatimentVille;
import controller.confirm.ville.vaisseau.ConfirmationAnnulationVaisseau;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;

public class FileDeConstructionVaisseau2 extends HorizontalGroup {

	public FileDeConstructionVaisseau2(Ville ville, Skin skin) {
		super();
		setName("liste_attente_vaisseaux");
		
		int i = 0;
		for (Vaisseau vaisseau : ville.getFileDeConstructionUnite()) {
			TextButton button = new TextButton(vaisseau.getNom() + "\n" + vaisseau.getCout().get(EnumRessource.PRODUCTION) / ville.getTRessource().get(EnumRessource.PRODUCTION), skin);
			button.setName("vaisseau_file_" + i);
			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					new ConfirmationAnnulationVaisseau(ville, vaisseau, skin);
				}
				
			});
			
			i++;
			addActor(button);
		}
		
		TextButton add = new TextButton("+", skin);
		add.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				//TODO Implémenter le chargement du fichier des vaisseaux
//				new SelectVaisseau(ville, skin);
			}
			
		});
		
		addActor(add);
	}
	
	public void update(Ville ville, Skin skin) {
		if (ville.isReDrawFilesVaisseaux()) {
			clear();
			ville.setReDrawFilesVaisseaux(false);
			
			TextButton add = new TextButton("+", skin);
			add.setName("add");
			add.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					//TODO Implémenter le chargement du fichier des vaisseaux
//					new SelectVaisseau(ville, skin);
				}
				
			});
			
			addActor(add);
		}
		
		for (int i = 0; i < ville.getFileDeConstructionUnite().size(); i++) {
			Vaisseau vaisseau = ville.getFileDeConstructionUnite().get(i);
			String text = vaisseau.getNom() + "\nTour(s) : " + (int) Math.ceil((double) vaisseau.getCout().get(EnumRessource.PRODUCTION) / (double) ville.getTRessource().get(EnumRessource.PRODUCTION));
			if (findActor("vaisseau_file_" + i) != null) {
				((TextButton) findActor("vaisseau_file_" + i)).setText(text);
			} else {
				TextButton button = new TextButton(text, skin);
				button.setName("vaisseau_file_" + i);
				
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						new ConfirmationAnnulationVaisseau(ville, vaisseau, skin);
					}
					
				});
				
				addActorBefore(button, findActor("add"));
			}

		}
	}
}
