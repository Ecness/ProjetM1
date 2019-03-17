package view.galaxie.systeme.planete.ville;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.IntMap.Entry;

import controller.error.Error;
import model.EnumRessource;
import model.batiment.BatimentVille;
import model.batiment.ListBatiment;
import model.carte.stellaire.Ville;
import view.launcher.Project;

public class SelectBatimentVille extends ScrollPane{

	@SuppressWarnings("unlikely-arg-type")
	public SelectBatimentVille(Ville ville, Skin skin) {
		super(null, skin);
		VerticalGroup batiments = new VerticalGroup();

		//Lecture du fichier des bâtiments et affichage pour sélection
		TextButton annuler = new TextButton("Annuler", skin);
		annuler.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				clear();
				ville.setReDrawBatiments(true);
			}

		});

		batiments.addActor(annuler);

		Json parser = new Json();
		if (ville.getJoueur() != null) {
			try(FileReader file = new FileReader(ville.getJoueur().getNation().getPath() + "/Batiments/Batiments.json")) {

				ListBatiment listeBatiments = parser.fromJson(ListBatiment.class, file);

				//Récupération des bâtiments déjà construits ou en construction
				List<String> listeBatimentsVille = new ArrayList<String>();
				for (BatimentVille batiment : ville.getTBatimentVille()) {
					listeBatimentsVille.add(batiment.getNom());
				}
				for (BatimentVille batiment : ville.getFileDeConstructionBatiment()) {
					listeBatimentsVille.add(batiment.getNom());
				}

				for (Entry<BatimentVille> batiment : listeBatiments.getBatimentsVille().entries()) {
					//Empêche de sélectionner des bâtiments déjà construits ou en construction ainsi que ceux dont la technologie n'est pas débloquée
					if (!listeBatimentsVille.contains(batiment.value.getNom()) && 
							(ville.getJoueur().getTechnology().getScienceBatiment().get(listeBatiments.getBatimentsVille().get(batiment.key).getTechNecessaire()).isRechercher())) {
						//Correction type lecture json (de String vers EnumRessource)
						Map<EnumRessource, Integer> bonus = new HashMap<EnumRessource, Integer>();

						for (EnumRessource ressource : EnumRessource.values()) {
							bonus.put(ressource, batiment.value.getBonus().get(ressource.name()));
						}

						batiment.value.setBonus(bonus);

						String text = batiment.value.getNom() + "\nTour(s) : " + (int)Math.ceil(batiment.value.getCout() / ville.getTRessource().get(EnumRessource.PRODUCTION));
						TextButton button = new TextButton(text, Project.skin);
						//Le bouton est nommé selon sa clé dans la liste des batiments
						button.setName("" + batiment.key);

						button.addListener(new ClickListener() {

							@Override
							public void clicked(InputEvent event, float x, float y) {
								//Test si le joueur a débloqué la technologie nécessaire
								if (ville.getJoueur().getTechnology().getScienceBatiment().get(listeBatiments.getBatimentsVille().get(Integer.parseInt(button.getName())).getTechNecessaire()).isRechercher()) {
									//Récupération du bâtiment selon son nom (clé de la map)
									ville.constructionBatiment(listeBatiments.getBatimentsVille().get(Integer.parseInt(button.getName())));
									clear();
									ville.setReDrawBatiments(true);
								} else {
									//Affichage d'erreur si technologie non débloquée, ne devrait pas apparaître
									new Error("Technologie manquante",
												"Technologie manquante : " + 
													ville.getJoueur().getTechnology().getScienceBatiment().get(listeBatiments.getBatimentsVille().get(Integer.parseInt(button.getName())).getTechNecessaire()).getNom(),
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

		setActor(batiments);
	}
}
