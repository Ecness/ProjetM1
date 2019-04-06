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

		Json parser = new Json();
		if (ville.getJoueur() != null) {
			try(FileReader file = new FileReader(ville.getJoueur().getNation().getPath() + "/Batiments/Batiments.json")) {

				ListBatiment listeBatiments = parser.fromJson(ListBatiment.class, file);
				
				for (Entry<BatimentVille> batiment : listeBatiments.getBatimentsVille().entries()) {
					//Empêche de sélectionner des bâtiments déjà construits ou en construction ainsi que ceux dont la technologie n'est pas débloquée
					if (!ville.presenceBatiment(batiment.value) && (ville.isBuildingUnlocked(batiment.value))) {
						
						TextButton button = new ButtonAddBatimentVille(this, ville, batiment.value, skin);
						//Le bouton est nommé selon le nom du bâtiment
						button.setName(batiment.value.getNom());

						batiments.addActor(button);
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString() + " dans view/galaxie/SelectBatiment");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
		}

		container.setActor(batiments);
		
		addActor(container);
		
		Gdx.input.setCursorCatched(false);
		
		Project.staticStage.addActor(this);
	}
}
