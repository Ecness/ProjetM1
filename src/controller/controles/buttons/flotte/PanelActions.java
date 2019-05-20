package controller.controles.buttons.flotte;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Vaisseau;

public class PanelActions extends VerticalGroup {

	public PanelActions(Systeme systeme, SelectFlotteToMerge source, SelectFlotteToMerge cible, Skin skin) {
		super();

		TextButton right = new TextButton("->", skin);
		right.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				//Vérification si vaisseaux sélectionnés et flotte cible sélectionnée
				if (!source.getVaisseauSelected().isEmpty() && cible.getFlotteSelected() != null) {
					//Transfert des vaisseaux
					for (Vaisseau vaisseau : source.getVaisseauSelected()) {
						cible.getFlotteSelected().addVaisseau(vaisseau);
						source.getFlotteSelected().removeVaisseau(vaisseau);
					}
					//Vidage de la liste des vaisseaux sélectionnés
					source.getVaisseauSelected().clear();
					//Si la cible est une nouvelle flotte, on la crée
					if (cible.isNouvelleFlotte()) {
						cible.setNouvelleFlotte(false);
						SelectFlotteToMerge.addFlotte(cible.getFlotteSelected());
					}
					//Mise à jour des afficheurs flottes
					source.update(systeme, skin);
					cible.update(systeme, skin);
				}
			}
		});

		TextButton left = new TextButton("<-", skin);
		left.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				//Vérification si vaisseaux sélectionnés et flotte source sélectionnée
				if (!cible.getVaisseauSelected().isEmpty() && source.getFlotteSelected() != null) {
					//Transfert des vaisseaux
					for (Vaisseau vaisseau : cible.getVaisseauSelected()) {
						source.getFlotteSelected().addVaisseau(vaisseau);
						cible.getFlotteSelected().removeVaisseau(vaisseau);
					}
					//Vidage de la liste des vaisseaux sélectionnés
					cible.getVaisseauSelected().clear();
					//Si la source est une nouvelle flotte, on la crée
					if (source.isNouvelleFlotte()) {
						source.setNouvelleFlotte(false);
						SelectFlotteToMerge.addFlotte(source.getFlotteSelected());
					}
					//Mise à jour des afficheurs flottes
					source.update(systeme, skin);
					cible.update(systeme, skin);
				}
			}
		});

		addActor(right);
		addActor(left);
	}
}
