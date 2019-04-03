package view.menus.tech;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Entry;

import controller.controles.buttons.tech.ButtonTech;
import model.entity.player.Joueur;
import model.entity.player.Science;
import model.entity.player.donnee.EnumTech;

public class TechContainer extends ScrollPane {

	public TechContainer(Group parent, Joueur player, EnumTech typeTech, Skin skin) {
		super(null, skin);
		setName("tech_container");
		
		setBounds(0, parent.findActor("domains").getY() - parent.getHeight()/2, parent.getWidth(), parent.getHeight()/2);

		HorizontalGroup container = new HorizontalGroup();


		IntMap<Science> tech = null;

		switch(typeTech) {
		case MILITAIRE:
			tech = player.getTechnology().getScienceMillitaire();
			break;
		case BATIMENT:
			tech = player.getTechnology().getScienceBatiment();
			break;
		case BONUS:
			tech = player.getTechnology().getScienceBonus();
			break;
		default:
			//TODO Renvoyer une erreur
			break;
		}

		VerticalGroup tier0 = new VerticalGroup();
		tier0.setName("tier_0");
		tier0.addActor(new ButtonTech(parent, player, typeTech, tech.get(0), skin));
		container.addActor(tier0);

		for (int i = 1; i < tech.size; i++) {
			VerticalGroup tier = new VerticalGroup();
			tier.setName("tier_" + i);
			for (Entry<Science> science : tech.entries()) {
				if (science.value.getMaxDependance() == i-1) {
					tier.addActor(new ButtonTech(parent, player, typeTech, science.value, skin));
				}
			}

			container.addActor(tier);
		}
		
		setActor(container);
	}
}
