package view.menus.tech;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import model.EnumRessource;
import model.entity.player.Joueur;
import model.entity.player.Science;

public class DescriptionTech extends Table {

	public DescriptionTech(Group container, Joueur player, Science tech, Skin skin) {
		super(skin);
		setName("description_tech");
		
		setBounds(0, container.findActor("tech_container").getY() - container.getHeight()/2, container.getWidth(), container.getHeight()/2);
		
		//Headers
		Label nameHeader = new Label("Nom", skin);
		nameHeader.setWidth(container.getWidth() * 0.1f);
		
		Label descriptionHeader = new Label("Description", skin);
		descriptionHeader.setWidth(container.getWidth() * 0.8f);
		
		Label turnsHeader = new Label("Tour(s)", skin);
		turnsHeader.setWidth(container.getWidth() * 0.1f);
		
		add(nameHeader, descriptionHeader, turnsHeader);
		
		row();
		//Values
		Label nameValue = new Label(tech.getNom(), skin);
		nameValue.setWidth(container.getWidth() * 0.1f);
		
		Label descriptionValue = new Label(tech.getDescription(), skin);
		descriptionValue.setWidth(container.getWidth() * 0.8f);
		
		Label turnsValue = new Label("" + tech.getCout() / player.getTRessource().get(EnumRessource.SCIENCE), skin);
		turnsValue.setWidth(container.getWidth() * 0.1f);
		
		add(nameValue, descriptionValue, turnsValue);
	}
}
