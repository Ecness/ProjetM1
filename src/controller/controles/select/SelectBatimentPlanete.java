package controller.controles.select;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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

import controller.controles.buttons.planete.ButtonAddBatimentPlanete;
import model.batiment.BatimentPlanete;
import model.batiment.ListBatiment;
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

		Json parser = new Json();
		if (planete.getJoueur() != null) {
			try(FileReader file = new FileReader(planete.getJoueur().getNation().getPath() + "/Batiments/Batiments.json")) {

				ListBatiment listeBatiments = parser.fromJson(ListBatiment.class, file);

				List<String> listeBatimentsPlanete = new ArrayList<String>();
				for (BatimentPlanete batiment : planete.getTBatiment()) {
					if (batiment != null) {
						listeBatimentsPlanete.add(batiment.getNom());
					}
				}

				for (Entry<BatimentPlanete> batiment : listeBatiments.getBatimentsPlanete().entries()) {
					if (!listeBatimentsPlanete.contains(batiment.value.getNom()) && 
							(planete.getJoueur().getTechnology().getScienceBatiment().get(listeBatiments.getBatimentsPlanete().get(batiment.key).getTechNecessaire()).isRechercher())) {

						TextButton button = new ButtonAddBatimentPlanete(this, planete, emplacement, batiment.value, skin);

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
