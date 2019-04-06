package view.galaxie.systeme.planete.ville.file;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.vaisseau.ConfirmationAnnulationVaisseau;
import controller.controles.buttons.vaisseau.ButtonSelectVaisseau;
import controller.controles.select.SelectVaisseau;
import model.EnumRessource;
import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;
import view.launcher.Project;

public class FileDeConstructionVaisseau extends VerticalGroup {

	public FileDeConstructionVaisseau(Ville ville, Skin skin) {
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

				new SelectVaisseau(ville, skin);
			}

		});

		addActor(add);
	}

	public void update(Ville ville, Skin skin) {
		if (ville.isVaisseauTermine()) {
			//TODO Créer une alerte
			clear();
			ville.setVaisseauTermine(false);
		}
		if (ville.isVaisseauAnnule()) {
			//TODO Créer une alerte
			clear();
			ville.setVaisseauAnnule(false);
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

				//Ajout du vaisseau avant le bouton d'ajout (si présent)
				if (findActor("add") != null) {
					addActorBefore(findActor("add"), button);
				} else {
					addActor(button);
				}
			}
		}

		//Affichage ou retrait du bouton d'ajout en fonction du nombre de construction
		if (findActor("add") != null && ville.getFileDeConstructionUnite().size() == 5) {
			findActor("add").remove();
		} else if (findActor("add") == null && ville.getFileDeConstructionUnite().size() < 5) {
			TextButton add = new ButtonSelectVaisseau(ville, skin);
			add.setName("add");
			addActor(add);
		}

		Project.displayHasChanged = true;
	}
}
