package controller.controles.select;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import controller.error.Error;
import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.ListBatiment;
import model.carte.stellaire.Planete;
import view.launcher.Project;

public class SelectBatimentPlanete2 extends Window {
	@SuppressWarnings("unlikely-arg-type")
	public SelectBatimentPlanete2(Planete planete, int emplacement, Skin skin) {
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
							(planete.getJoueur().getTechnology().getScienceBatiment().get(listeBatiments.getBatimentsVille().get(batiment.key).getTechNecessaire()).isRechercher())) {
						//Correction type lecture json (de String vers EnumRessource)
						Map<EnumRessource, Integer> bonus = new HashMap<EnumRessource, Integer>();
						Map<EnumRessource, Integer> cout = new HashMap<EnumRessource, Integer>();

						for (EnumRessource ressource : EnumRessource.values()) {
							bonus.put(ressource, batiment.value.getBonus().get(ressource.name()));
							cout.put(ressource, batiment.value.getCout().get(ressource.name()));
						}

						batiment.value.setBonus(bonus);
						batiment.value.setCout(cout);

						TextButton button = new TextButton(batiment.value.getNom(), Project.skin);
						//Le bouton est nommé selon sa clé dans la liste des batiments
						button.setName("" + batiment.key);

						button.addListener(new ClickListener() {

							@Override
							public void clicked(InputEvent event, float x, float y) {
								//Récupération du bâtiment selon son nom (clé de la map)
								if (planete.verifConstructionBatiment(listeBatiments.getBatimentsPlanete().get(Integer.parseInt(button.getName())), emplacement)) {
									planete.constructionBatiment(listeBatiments.getBatimentsPlanete().get(Integer.parseInt(button.getName())), emplacement);
									remove();
									if (emplacement == 0) {
										planete.setReDrawBuild1(true); 
									} else {
										planete.setReDrawBuild2(true);
									}
									planete.setReDraw(true);
								} else {
									//Gestion du bouton d'erreur en cas de ressources insuffisantes
									new Error("Ressources manquantes", 
												"Pas assez de ressources", 
												skin);
								}
							}

						});

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
