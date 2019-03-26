package view.galaxie.systeme.planete;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.galaxie.systeme.AffichageListePlanetes2;
import view.galaxie.systeme.AffichageSysteme;
import view.galaxie.systeme.planete.ville.batiment.AffichageVille;
import view.galaxie.systeme.planete.ville.batiment.AffichageVille2;

public class AffichagePlanete2 extends VerticalGroup {
	
	public AffichagePlanete2(Systeme systeme, Planete planete, Skin skin) {
		super();
		setName("afficheur_planete");
		
		TextButton retour = new TextButton("Retour", skin);
		retour.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				Group parent = getParent();
				parent.clear();
				parent.addActor(new AffichageSysteme(systeme, skin));
			}
			
		});
		addActor(retour);
		
		Table informations = new Table(skin);
		informations.setName("informations");
		
		//Nom de la planète
		informations.add(new Label("" + planete.getId(), skin));
		informations.row();
		//Type de la planète
		informations.add(new Label("Type : " + planete.getTypePlanete(), skin));
		informations.row();
		
		//Ressources
		String text = new String();
		for (EnumRessource ressource : EnumRessource.values()) {
			int value = planete.getVille() != null ? planete.getTRessource().get(ressource) + planete.getVille().getTRessource().get(ressource)
					: planete.getTRessource().get(ressource);
			text += " " + ressource.toString().substring(0, 1) + value;
		}
		Label ressources = new Label(text, skin);
		ressources.setName("ressources");
		informations.add(ressources);
		
		addActor(informations);
		
		if (planete.getVille() != null) {
			addActor(new AffichageVille2(planete.getVille(), skin));
		} else {
			addActor(new AffichageBatimentsPlanete2(planete, skin));
		}
	}
	
	public void update(Planete planete, Skin skin) {
		String text = new String();
		for (EnumRessource ressource : EnumRessource.values()) {
			int value = planete.getVille() != null ? planete.getTRessource().get(ressource) + planete.getVille().getTRessource().get(ressource)
					: planete.getTRessource().get(ressource);
			text += " " + ressource.toString().substring(0, 1) + value;
		}
		((Label) ((Table) findActor("informations")).findActor("ressources")).setText(text);
		
		if (planete.getVille() != null) {
			if (planete.getVille().isReDrawBatiments()) {
				((AffichageVille2) getChildren().peek()).updateBatiments(planete.getVille(), skin);
				planete.getVille().setReDrawBatiments(false);
			}
			if (planete.getVille().isReDrawFilesBatiments() || planete.getVille().isReDrawFilesVaisseaux()) {
				((AffichageVille2) getChildren().peek()).updateFile(planete.getVille(), skin);
			}
		} else {
			((AffichageBatimentsPlanete2) getChildren().peek()).update(planete, skin);
		}
		planete.setReDraw(false);
	}
}
