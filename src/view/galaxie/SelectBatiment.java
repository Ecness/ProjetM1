package view.galaxie;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.badlogic.gdx.utils.Json;

import model.EnumRessource;
import model.batiment.BatimentPlanete;
import model.batiment.ListBatiment;
import model.carte.stellaire.Planete;
import view.launcher.Project;

public class SelectBatiment {
	private VerticalGroup table;
	private ScrollPane panel;
	
	private ListBatiment listeBatiments;
	
	@SuppressWarnings("unlikely-arg-type")
	public SelectBatiment(Planete planete, int emplacement) {
		table = new VerticalGroup();
		
		Json parser = new Json();
		if (planete.getJoueur() != null) {
			try(FileReader file = new FileReader(planete.getJoueur().getNation().getPath() + "/Batiments/Batiments.json")) {

				listeBatiments = parser.fromJson(ListBatiment.class, file);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
				
				TextButton bat = new TextButton(batiment.value.getNom(), Project.skin);

				bat.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (!planete.presenceBatiment(emplacement)) {
							planete.constructionBatiment(batiment.value, emplacement);
						} else {
							planete.deconstructionBatiment(emplacement);
						}
						
						panel.clear();
						planete.setReDraw(true);
					}

				});

				table.addActor(bat);
			}

			panel = new ScrollPane(table);
		}
	}

	public ScrollPane getPanel() {
		return panel;
	}
}
