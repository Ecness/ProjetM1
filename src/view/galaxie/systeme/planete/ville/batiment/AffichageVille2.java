package view.galaxie.systeme.planete.ville.batiment;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Ville;
import view.galaxie.systeme.planete.AffichagePlanete2;
import view.galaxie.systeme.planete.ville.file.FileDeConstruction;
import view.galaxie.systeme.planete.ville.file.FileDeConstruction2;

public class AffichageVille2 extends VerticalGroup {

	public AffichageVille2(Ville ville, Skin skin) {
		super();
		setName("afficheur_ville");
		
		AffichageBatimentsVille2 batiments = new AffichageBatimentsVille2(ville, skin);
		batiments.setName("batiments_ville");
		
		FileDeConstruction2 listeAttente = new FileDeConstruction2(ville, skin);
		listeAttente.setName("liste_attente");
		
		addActor(batiments);
		addActor(listeAttente);
	}
	
	public void updateBatiments(Ville ville, Skin skin) {
		((AffichageBatimentsVille2) findActor("batiments_ville")).update(ville, skin);
	}
	
	public void updateFile(Ville ville, Skin skin) {
		((FileDeConstruction2) findActor("liste_attente")).update(ville, skin);
	}
}
