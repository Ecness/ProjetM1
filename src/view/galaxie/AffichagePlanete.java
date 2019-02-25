package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichagePlanete {
	private VerticalGroup container;
	private String text;

	public AffichagePlanete() {
		Systeme systeme = Project.systemeSelectionne;

		container = new VerticalGroup();
		
		container.addActor(new Label(systeme.getIdSysteme() + "\n" + systeme.getTypeSysteme().toString(), Project.skin));
		
		for (Planete planete : systeme.getTPlanete()) {
			text = planete.getTypePlanete().toString();
			
			for (EnumRessource ressource : EnumRessource.values()) {
				text += " " + ressource.toString().substring(0, 1) + " : " + Project.partie.getTJoueur()[0].getTRessource().get(ressource);
			}
			
			TextButton bouton = new TextButton(text, Project.skin);
			
			container.addActor(bouton);
			container.space(20);
		}
		
		
		container.center();
	}

	public VerticalGroup getContainer() {
		return container;
	}
}
