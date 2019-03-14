package view.galaxie.planete.ville;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import model.EnumRessource;
import model.carte.stellaire.Ville;
import model.entity.vaisseau.Vaisseau;

public class FileDeConstructionVaisseau extends VerticalGroup {
	
	public FileDeConstructionVaisseau(Ville ville, Skin skin) {
		super();
		setName("liste_attente_vaisseau");
		
		int i = 0;
		for (Vaisseau vaisseau : ville.getFileDeConstructionUnite()) {
			TextButton vai = new TextButton(vaisseau.getNom() + "\n" + vaisseau.getCout().get(EnumRessource.PRODUCTION) / ville.getTRessource().get(EnumRessource.PRODUCTION), skin);
			vai.setName("vaisseau_file_" + i);
			i++;
			//TODO Ajouter mécanique d'annulation
			addActor(vai);
		}
	}
	
	public void update(Ville ville) {
		List<Actor> newList = new ArrayList<Actor>();
		
		for (int i = 0; i < ville.getFileDeConstructionUnite().size(); i++) {
			Vaisseau vaisseau = ville.getFileDeConstructionUnite().get(i);
			String text = vaisseau.getNom() + "\n" + vaisseau.getCout().get(EnumRessource.PRODUCTION) / ville.getTRessource().get(EnumRessource.PRODUCTION);
			((TextButton) findActor("vaisseau_file_" + i)).setText(text);
			
			if (vaisseau.getCout().get(EnumRessource.PRODUCTION) <= 0) {
				ville.getFileDeConstructionUnite().remove(vaisseau);
				newList.add(findActor("vaisseau_file_" + i));
			}
		}
		
		for (Actor button : newList) {
			removeActor(button);
		}
		
		int i = 0;
		for (Actor button : getChildren()) {
			
			button.setName("vaisseau_file_" + i);
			i++;
		}
	}
}
