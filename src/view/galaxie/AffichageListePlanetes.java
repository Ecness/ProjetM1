package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageListePlanetes extends VerticalGroup {
	
	public AffichageListePlanetes(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_liste_planetes");
		
		for (Planete planete : systeme.getTPlanete()) {
			String text = "Type : " + planete.getTypePlanete() + "\n";
			
			for (EnumRessource ressource : EnumRessource.values()) {
				int value = planete.getVille() != null ? planete.getTRessource().get(ressource) + planete.getVille().getTRessource().get(ressource)
													: planete.getTRessource().get(ressource);
				text += " " + ressource.toString().substring(0, 1) + value;
			}
			
			TextButton bouton = new TextButton(text, skin);
			bouton.setName("planete_" + planete.getId());
			bouton.center();
			bouton.getLabel().setAlignment(Align.left);
			bouton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Project.planeteSelectionne = planete;
					Project.changePlanete = true;
				}
			});
			
			addActor(bouton);
		}
		
		align(Align.center);
		grow();
	}
	
	public void update(Systeme systeme) {
		for (Planete planete : systeme.getTPlanete()) {
			String text = "Type : " + planete.getTypePlanete() + "\n";

			for (EnumRessource ressource : EnumRessource.values()) {
				int value = planete.getVille() != null ? planete.getTRessource().get(ressource) + planete.getVille().getTRessource().get(ressource)
													: planete.getTRessource().get(ressource);
				text += " " + ressource.toString().substring(0, 1) + value;
			}
			
			((TextButton) findActor("planete_" + planete.getId())).setText(text);
		}
	}
}
