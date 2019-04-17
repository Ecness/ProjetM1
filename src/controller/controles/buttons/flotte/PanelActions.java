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

				if (!source.getVaisseauSelected().isEmpty() && cible.getFlotteSelected() != null) {
					for (Vaisseau vaisseau : source.getVaisseauSelected()) {
						cible.getFlotteSelected().addVaisseau(vaisseau);
						source.getFlotteSelected().removeVaisseau(vaisseau);
					}
					source.getVaisseauSelected().clear();
//					if (source.isNouvelleFlotte()) {
//						source.setNouvelleFlotte(false);
////						systeme.getFlottes().add(source.getFlotteSelected());
//						SelectFlotteToMerge.addFlotte(source.getFlotteSelected());
//					}
					if (cible.isNouvelleFlotte()) {
						cible.setNouvelleFlotte(false);
//						systeme.getFlottes().add(cible.getFlotteSelected());
						SelectFlotteToMerge.addFlotte(cible.getFlotteSelected());
					}
					//TODO A ajouter sur une validation
//					for (Flotte flotte : systeme.getFlottes()) {
//						if (flotte.getTVaisseau().isEmpty()) {
//							systeme.getFlottes().remove(flotte);
//						}
//					}
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

				if (!cible.getVaisseauSelected().isEmpty() && source.getFlotteSelected() != null) {
					for (Vaisseau vaisseau : cible.getVaisseauSelected()) {
						source.getFlotteSelected().addVaisseau(vaisseau);
						cible.getFlotteSelected().removeVaisseau(vaisseau);
					}
					cible.getVaisseauSelected().clear();
					if (source.isNouvelleFlotte()) {
						source.setNouvelleFlotte(false);
//						systeme.getFlottes().add(source.getFlotteSelected());
						SelectFlotteToMerge.addFlotte(source.getFlotteSelected());
					}
//					if (cible.isNouvelleFlotte()) {
//						cible.setNouvelleFlotte(false);
////						systeme.getFlottes().add(cible.getFlotteSelected());
//						SelectFlotteToMerge.addFlotte(cible.getFlotteSelected());
//					}
					//TODO A ajouter sur une validation
//					for (Flotte flotte : systeme.getFlottes()) {
//						if (flotte.getTVaisseau().isEmpty()) {
//							systeme.getFlottes().remove(flotte);
//						}
//					}
					source.update(systeme, skin);
					cible.update(systeme, skin);
				}
			}
		});

		addActor(right);
		addActor(left);
	}
}
