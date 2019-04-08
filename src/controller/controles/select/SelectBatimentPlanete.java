package controller.controles.select;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntMap.Entry;

import controller.controles.buttons.planete.ButtonAddBatimentPlanete;
import model.batiment.BatimentPlanete;
import model.carte.stellaire.Planete;
import view.launcher.Project;

public class SelectBatimentPlanete extends Window {

	public SelectBatimentPlanete(Planete planete, int emplacement, Skin skin) {
		super("Selection Batiment", skin);
		setSize(Project.width / 4, Project.height / 4);
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

		if (planete.getJoueur() != null) {
			for (Entry<BatimentPlanete> batiment : planete.getJoueur().getBuildings().getBatimentsPlanete().entries()) {
				//Empêche de sélectionner des bâtiments déjà construitsainsi que ceux dont la technologie n'est pas débloquée
				if (!batiment.value.isConstruit() && (planete.isBuildingUnlocked(batiment.value))) {

					TextButton button = new ButtonAddBatimentPlanete(this, planete, emplacement, batiment.value, skin);
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
