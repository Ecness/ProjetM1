package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import model.EnumRessource;
import model.carte.stellaire.Planete;
import model.carte.stellaire.Systeme;
import view.launcher.Project;

public class AffichageSysteme extends VerticalGroup {
	
	public AffichageSysteme(Systeme systeme, Skin skin) {
		super();
		setName("afficheur_systeme");
		
		VerticalGroup planetes = new VerticalGroup();
		planetes.setName("planetes");
		
		//TODO Remplacer l'identifiant par un nom
		Label nomSysteme = new Label("" + systeme.getIdSysteme(), skin);
		if (systeme.getJoueur() != null) {
			nomSysteme.setText(nomSysteme.getText() + " : " + systeme.getJoueur().getName());
		} else {
			nomSysteme.setText(nomSysteme.getText() + " : libre");
		}
		nomSysteme.setAlignment(Align.center);
		
		Label typeSysteme = new Label(systeme.getTypeSysteme().toString(), skin);
		typeSysteme.setAlignment(Align.center);
		
		for (Planete planete : systeme.getTPlanete()) {
			String text = "Type : " + planete.getTypePlanete() + "\n";
			
			for (EnumRessource ressource : EnumRessource.values()) {
				text += " " + ressource.toString().substring(0, 1) + planete.getTRessource().get(ressource);
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
			
			planetes.addActor(bouton);
		}
		
		planetes.align(Align.center);
		planetes.grow();
		
		addActor(nomSysteme);
		addActor(typeSysteme);
		addActor(planetes);
		
		align(Align.center);
		grow();
	}

	public void update(Systeme systeme) {
		VerticalGroup planetes = findActor("planetes");
		
		for (Planete planete : systeme.getTPlanete()) {
			String text = "Type : " + planete.getTypePlanete() + "\n";

			for (EnumRessource ressource : EnumRessource.values()) {
				text += " " + ressource.toString().substring(0, 1) + planete.getTRessource().get(ressource);
			}
			
			((TextButton) planetes.findActor("planete_" + planete.getId())).setText(text);
		}
	}
}
