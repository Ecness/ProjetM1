package view.galaxie;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.IntMap.Entry;

import controller.boutons.ConfirmationDestructionBatiment;
import controller.boutons.ErrorButtonSelectBatiment;
import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.ListBatiment;
import model.carte.stellaire.Planete;
import view.launcher.Project;

public class SelectBatiment extends ScrollPane{
	private boolean error;

	@SuppressWarnings("unlikely-arg-type")
	public SelectBatiment(Planete planete, int emplacement, Skin skin) {
		super(null, skin);
		VerticalGroup batiments = new VerticalGroup();

		//Confirmation de destruction d'un bâtiment si présent
		if (planete.presenceBatiment(emplacement)) {
			setActor(new ConfirmationDestructionBatiment(planete, emplacement, skin));
		} else {
			//Lecture du fichier des bâtiments et affichage pour sélection
			TextButton annuler = new TextButton("Annuler", skin);
			annuler.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					clear();
					planete.setReDraw(true);
				}

			});

			batiments.addActor(annuler);

			Json parser = new Json();
			if (planete.getJoueur() != null) {
				try(FileReader file = new FileReader(planete.getJoueur().getNation().getPath() + "/Batiments/Batiments.json")) {

					ListBatiment listeBatiments = parser.fromJson(ListBatiment.class, file);

					for (Entry<BatimentPlanete> batiment : listeBatiments.getBatimentsPlanete().entries()) {
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
									clear();
									planete.setReDraw(true);
								} else {
									error = true;
								}

								if (error) {
									//Gestion du bouton d'erreur en cas de ressources insuffisantes
									setActor(new ErrorButtonSelectBatiment(planete, skin));
								}
							}

						});

						batiments.addActor(button);
					}
				} catch (Exception e) {
					System.out.println(e.toString() + " dans view/galaxie/SelectBatiment");
					System.out.println(e.getMessage());
					e.printStackTrace();

				}
			}

			setActor(batiments);
		}
	}
}
