package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import model.EnumRessource;
import model.entity.player.Joueur;
import view.launcher.Project;

public class AffichageRessources extends HorizontalGroup {

	public AffichageRessources(Joueur joueur, Skin skin) {
		super();
		setName("afficheur_ressources");

		for (EnumRessource ressource : EnumRessource.values()) {
			if (ressource != EnumRessource.PRODUCTION && ressource != EnumRessource.PUISSANCE) {
				//Création d'un groupe contenant image et quantité de la ressource
				HorizontalGroup ensemble = new HorizontalGroup();
				ensemble.setName("group_" + ressource.toString());

				//TODO A remplacer par une image
				Label nom = new Label(ressource.toString() + " : ", skin);
				nom.setAlignment(Align.center);

				String text = "" + joueur.getTRessource().get(ressource);

				if (ressource != EnumRessource.SCIENCE && ressource != EnumRessource.PRODUCTION && ressource != EnumRessource.PUISSANCE) {
					text += " / " + joueur.getTRessourceMax().get(ressource);
				}

				Label quantite = new Label(text, skin);
				quantite.setAlignment(Align.center);
				quantite.setName("quantity_" + ressource.toString());

				ensemble.addActor(nom);
				ensemble.addActor(quantite);

				addActor(ensemble);
			}
		}

		align(Align.center);
		space(Project.staticStage.getCamera().viewportWidth / 16);
	}

	/**
	 * Mise à jour des valeurs des ressources
	 * 
	 * @param joueur	Joueur possédant les ressources à afficher
	 */
	public void update(Joueur joueur) {
		for (EnumRessource ressource : EnumRessource.values()) {
			if (ressource != EnumRessource.PRODUCTION && ressource != EnumRessource.PUISSANCE) {
				HorizontalGroup group = findActor("group_" + ressource.toString());
				Label quantity = group.findActor("quantity_" + ressource.toString());

				String text = "" + joueur.getTRessource().get(ressource);

				if (ressource != EnumRessource.SCIENCE && ressource != EnumRessource.PRODUCTION && ressource != EnumRessource.PUISSANCE) {
					text += " / " + joueur.getTRessourceMax().get(ressource);
				}

				quantity.setText(text);
			}
		}
	}
}
