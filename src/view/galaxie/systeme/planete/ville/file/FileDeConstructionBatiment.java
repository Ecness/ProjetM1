package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.batiment.ConfirmationAnnulationBatimentVille;
import controller.controles.buttons.ville.ButtonSelectBatimentVille;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;
import view.launcher.Project;

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
		
		TextButton add = new ButtonSelectBatimentVille(ville, skin);
		add.setName("add");
		
		addActor(add);
	}
	
	public void update(Ville ville, Skin skin) {
		if (ville.isBatimentTermine()) {
			//TODO Créer une alerte
			clear();
			ville.setBatimentTermine(false);
		}
		if (ville.isBatimentAnnule()) {
			//TODO Créer une alerte
			clear();
			ville.setBatimentAnnule(false);
		}
		
		for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
			String text = batiment.getNom() + "\nTour(s) : " + (int) Math.ceil((double) batiment.getCout() / (double) ville.getTRessource().get(EnumRessource.PRODUCTION));
			if (findActor("batiment_file_" + batiment.getNom()) != null) {
				//Si le bâtiment est dans la file, on met à jour son affichage
				((TextButton) findActor("batiment_file_" + batiment.getNom())).setText(text);
			} else {
				//Sinon, on ajoute son affichage
				TextButton button = new TextButton(text, skin);
				button.setName("batiment_file_" + batiment.getNom());
				
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						new ConfirmationAnnulationBatimentVille(ville, batiment, skin);
					}
					
				});
				
				//Ajout du bâtiment avant le bouton d'ajout (si présent)
				if (findActor("add") != null) {
					addActorBefore(findActor("add"), button);
				} else {
					addActor(button);
				}
			}
		}

		//Affichage ou retrait du bouton d'ajout en fonction du nombre de construction
		if (findActor("add") != null && ville.getFileDeConstructionBatiment().size() == 5) {
			findActor("add").remove();
		} else if (findActor("add") == null && ville.getFileDeConstructionBatiment().size() < 5) {
			TextButton add = new ButtonSelectBatimentVille(ville, skin);
			add.setName("add");
			addActor(add);
		}
		
		Project.displayHasChanged = true;
	}
}
