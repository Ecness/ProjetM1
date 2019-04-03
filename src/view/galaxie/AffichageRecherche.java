package view.galaxie;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import controller.controles.buttons.tech.ButtonMenuTech;
import model.EnumRessource;
import model.entity.player.Joueur;
import view.launcher.Project;

public class AffichageRecherche extends HorizontalGroup {

	public AffichageRecherche(Joueur player, Skin skin) {
		super();
		setName("group_tech");
		
		TextButton select = new ButtonMenuTech(player, skin);
		select.setName("menu_tech");
		
		String text = player.getSearchingTech() == null ? "Aucune recherche" : 
						player.getSearchingTech().getNom() + "\nTour(s) : " +
							(int) Math.ceil((double) player.getSearchingTech().getCout() / (double) player.getTRessource().get(EnumRessource.SCIENCE));
		Label tech = new Label(text, skin);
		tech.setName("label_tech");
		
		addActor(select);
		addActor(tech);
	}
	
	public void update(Joueur player, Skin skin) {
		String text = player.getSearchingTech() == null ? "Aucune recherche" : 
			player.getSearchingTech().getNom() + "\nTour(s) : " +
				(int) Math.ceil((double) player.getSearchingTech().getCout() / (double) player.getTRessource().get(EnumRessource.SCIENCE));
		((Label) findActor("label_tech")).setText(text);
	}
}
