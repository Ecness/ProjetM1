package view.galaxie.systeme.planete.ville.batiment;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import controller.confirm.ville.batiment.ConfirmationDestructionBatimentVille;
import model.batiment.BatimentVille;
import model.carte.stellaire.Ville;

public class AffichageBatimentsVille2 extends ScrollPane {
	
	public AffichageBatimentsVille2(Ville ville, Skin skin) {
		super(null, skin);
		setName("liste_batiments");
		
		Table table = new Table(skin);
		table.setName("tab_batiments");
		for (BatimentVille batiment : ville.getTBatimentVille()) {
			Label nom = new Label(batiment.getNom(), skin);
//			Label description = new Label(batiment.getDescription(), skin);
			TextButton delete = new TextButton("X", skin);
			delete.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					new ConfirmationDestructionBatimentVille(ville, batiment, skin);
				}
				
			});
			
			table.add(nom/*, description*/, delete);
			table.row();
		}
		
		setActor(table);
	}
	
	//TODO Différencier les cas d'ajout de bâtiment (une ligne en plus) et de destruction (clear)
	public void update(Ville ville, Skin skin) {
		Table table = ((Table) findActor("tab_batiments"));
		table.clear();
		
		for (BatimentVille batiment : ville.getTBatimentVille()) {
			Label nom = new Label(batiment.getNom(), skin);
//			Label description = new Label(batiment.getDescription(), skin);
			TextButton delete = new TextButton("X", skin);
			delete.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					new ConfirmationDestructionBatimentVille(ville, batiment, skin);
				}
				
			});
			
			table.add(nom/*, description*/, delete);
			table.row();
		}
	}
}
