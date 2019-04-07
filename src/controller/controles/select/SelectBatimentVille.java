package controller.controles.select;

import java.io.FileReader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.IntMap.Entry;

import controller.controles.buttons.ville.ButtonAddBatimentVille;
import model.batiment.BatimentVille;
import model.batiment.ListBatiment;
import model.carte.stellaire.Ville;
import model.entity.player.Joueur;
import view.launcher.Project;

public class SelectBatimentVille extends Window {

	public SelectBatimentVille(Ville ville, Skin skin) {
		super("Selection batiment", skin);
		setSize(Project.width / 2, Project.height / 2);
		setPosition(Project.width / 2 - getWidth() / 2, Project.height / 2 - getHeight() / 2);

		ScrollPane container = new ScrollPane(null, skin);
		container.setSize(getWidth(), getHeight() - getTitleLabel().getHeight());
		VerticalGroup batiments = new VerticalGroup();

		//Lecture du fichier des bâtiments et affichage pour sélection
		TextButton annuler = new TextButton("Annuler", skin);
		annuler.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				remove();
			}

		});

		batiments.addActor(annuler);

		if (ville.getJoueur() != null) {

			for (Entry<BatimentVille> batiment : ville.getJoueur().getBuildings().getBatimentsVille().entries()) {
				//Empêche de sélectionner des bâtiments déjà construits ou en construction ainsi que ceux dont la technologie n'est pas débloquée
				if (!ville.presenceBatiment(batiment.value) && (ville.isBuildingUnlocked(batiment.value))) {

					TextButton button = new ButtonAddBatimentVille(this, ville, batiment.value, skin);
					//Le bouton est nommé selon le nom du bâtiment
					button.setName(batiment.value.getNom());

					batiments.addActor(button);
				}
			}
		}

		container.setActor(batiments);

		addActor(container);

		Gdx.input.setCursorCatched(false);

		Project.staticStage.addActor(this);
	}
}
