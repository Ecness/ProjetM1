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

import controller.controles.buttons.vaisseau.ButtonAddVaisseau;

import com.badlogic.gdx.utils.IntMap.Entry;

import model.carte.stellaire.Ville;
import model.entity.vaisseau.ListVaisseaux;
import model.entity.vaisseau.Vaisseau;
import model.util.Sauvegarde;
import view.launcher.Project;

public class SelectVaisseau extends Window {

	public SelectVaisseau(Ville ville, Skin skin) {
		super("Selection vaisseau", skin);
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
			try(FileReader file = new FileReader(ville.getJoueur().getNation().getPath() + "/Vaisseaux/Vaisseaux.json")) {

				ListVaisseaux listeVaisseaux = parser.fromJson(ListVaisseaux.class, file);

				for (Entry<Vaisseau> vaisseau : listeVaisseaux.getVaisseaux().entries()) {
					if (ville.isVaisseauDebloquer(vaisseau.value)) {
//						vaisseau.value.setCout(Sauvegarde.convert(vaisseau.value.getCout()));

						TextButton button = new ButtonAddVaisseau(this, ville, vaisseau.value, skin);
						//Le bouton est nommé selon sa clé dans la liste des batiments
						button.setName("" + vaisseau.key);

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
