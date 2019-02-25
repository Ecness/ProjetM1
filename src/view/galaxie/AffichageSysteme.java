package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageSysteme {
	private VerticalGroup container;
	private String text;

	public AffichageSysteme() {
		Systeme systeme = Project.systemeSelectionne;

		container = new VerticalGroup();
		
		Label nomSysteme = new Label(systeme.getIdSysteme() + "\n" + systeme.getTypeSysteme().toString(), Project.skin);
		nomSysteme.setAlignment(Align.center);
		container.addActor(nomSysteme);
		
		for (Planete planete : systeme.getTPlanete()) {
			text = "Type : " + planete.getTypePlanete().toString() + "\n";
			
			for (EnumRessource ressource : EnumRessource.values()) {
				text += " " + ressource.toString().substring(0, 1) + " : " + Project.partie.getTJoueur()[0].getTRessource().get(ressource);
			}
			
			TextButton bouton = new TextButton(text, Project.skin);
			bouton.getLabelCell().getActor().setAlignment(Align.left);
			
			container.addActor(bouton);
			container.space(20);
		}
		
		
		container.center();
	}

	public VerticalGroup getContainer() {
		return container;
	}
}
