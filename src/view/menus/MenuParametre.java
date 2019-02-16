package view.menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.EnumTypeCarte;
import model.parametre.EnumVictoire;
import model.parametre.Parametre;
import view.launcher.Project;

public class MenuParametre {
	private Stage stage;
	private Skin skin;
	
	private Label TVictoireLabel;
	private CheckBox domination;
	private CheckBox scientifique;
	private CheckBox expansion;
	private HorizontalGroup checkTVictoire;
	
	private Label TCarteLabel;
	private SelectBox<EnumTypeCarte> selectTypeCarte;
	private Array<EnumTypeCarte> typeCarte;
	
	private Label tailleCarteLabel;
	private SelectBox<EnumTailleCarte> selectTailleCarte;
	private Array<EnumTailleCarte> tailleCarte;
	
	private Label abondRessLabel;
	private SelectBox<EnumAbondanceRessource> selectabondRess;
	private Array<EnumAbondanceRessource> abondRess;
	
	private Label nbJoueurLabel;
	private SelectBox<Integer> selectNbJoueur;
	private Array<Integer> nbJoueur;
	
	private Label nbPlanMaxLabel;
	private Slider nbPlanMax;
	
	private Label nbAnoMaxLabel;
	private Slider nbAnoMax;
	
	private Label TCombatLabel;
	private SelectBox<EnumTailleMapCombat> selectTCombat;
	private Array<EnumTailleMapCombat> Tcombat;
	
	private TextButton retour;
	private TextButton valider;
	
	public MenuParametre(final Project projet) {
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		int width = screenWidth/4;
		int height = screenHeight/9;
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		TVictoireLabel = new Label("Types de victoire", skin);
		TVictoireLabel.setSize(width, height);
		TVictoireLabel.setPosition(0, screenHeight-height);
		TCarteLabel = new Label("Type de carte", skin);
		TCarteLabel.setSize(width, height);
		TCarteLabel.setPosition(0, screenHeight-height*2);
		tailleCarteLabel = new Label("Taille de la carte", skin);
		tailleCarteLabel.setSize(width, height);
		tailleCarteLabel.setPosition(0, screenHeight-height*3);
		abondRessLabel = new Label("Abondance des ressources", skin);
		abondRessLabel.setSize(width, height);
		abondRessLabel.setPosition(0, screenHeight-height*4);
		nbJoueurLabel = new Label("Nombre de joueurs", skin);
		nbJoueurLabel.setSize(width, height);
		nbJoueurLabel.setPosition(0, screenHeight-height*5);
		nbPlanMaxLabel = new Label("Nombre de planète maximum par système", skin);
		nbPlanMaxLabel.setSize(width, height);
		nbPlanMaxLabel.setPosition(0, screenHeight-height*6);
		nbAnoMaxLabel = new Label("Nombre d'anomalie maximum par système", skin);
		nbAnoMaxLabel.setSize(width, height);
		nbAnoMaxLabel.setPosition(0, screenHeight-height*7);
		TCombatLabel = new Label("Taille de la carte de combat", skin);
		TCombatLabel.setSize(width, height);
		TCombatLabel.setPosition(0, screenHeight-height*8);
		
		checkTVictoire = new HorizontalGroup();
		checkTVictoire.setSize(screenWidth/2, height);
		checkTVictoire.setPosition(screenWidth/2, TVictoireLabel.getY());
		
		domination = new CheckBox("Domination", skin);
		scientifique = new CheckBox("Scientifique", skin);
		expansion = new CheckBox("Expansion", skin);
		
		checkTVictoire.addActor(domination);
		checkTVictoire.addActor(scientifique);
		checkTVictoire.addActor(expansion);
		
		selectTypeCarte = new SelectBox<EnumTypeCarte>(skin);
		typeCarte = new Array<EnumTypeCarte>(EnumTypeCarte.values().length);
		for (EnumTypeCarte value : EnumTypeCarte.values()) {
			typeCarte.add(value);
		}
		selectTypeCarte.setItems(typeCarte);
		selectTypeCarte.setSize(screenWidth/2, height);
		selectTypeCarte.setPosition(screenWidth/2, screenHeight-height*2);
		
		selectTailleCarte = new SelectBox<EnumTailleCarte>(skin);
		tailleCarte = new Array<EnumTailleCarte>(EnumTailleCarte.values().length);
		for (EnumTailleCarte value : EnumTailleCarte.values()) {
			tailleCarte.add(value);
		}
		selectTailleCarte.setItems(tailleCarte);
		selectTailleCarte.setSelected(EnumTailleCarte.MOYENNE);
		selectTailleCarte.setSize(screenWidth/2, height);
		selectTailleCarte.setPosition(screenWidth/2, screenHeight-height*3);
		
		selectabondRess = new SelectBox<EnumAbondanceRessource>(skin);
		abondRess = new Array<EnumAbondanceRessource>(EnumAbondanceRessource.values().length);
		for (EnumAbondanceRessource value : EnumAbondanceRessource.values()) {
			abondRess.add(value);
		}
		selectabondRess.setItems(abondRess);
		selectabondRess.setSelected(EnumAbondanceRessource.NORMAL);
		selectabondRess.setSize(screenWidth/2, height);
		selectabondRess.setPosition(screenWidth/2, screenHeight-height*4);
		
		selectNbJoueur = new SelectBox<Integer>(skin);
		nbJoueur = new Array<Integer>();
		for (int i = 2; i <= 8; i++) {
			nbJoueur.add(i);
		}
		selectNbJoueur.setItems(nbJoueur);
		selectNbJoueur.setSize(screenWidth/2, height);
		selectNbJoueur.setPosition(screenWidth/2, screenHeight-height*5);
		
		nbAnoMax = new Slider(5, 10, 1, false, skin);
		nbAnoMax.setSize(screenWidth/2, height);
		nbAnoMax.setPosition(screenWidth/2, screenHeight-height*6);
		
		nbPlanMax = new Slider(5, 10, 1, false, skin);
		nbPlanMax.setSize(screenWidth/2, height);
		nbPlanMax.setPosition(screenWidth/2, screenHeight-height*7);
		
		selectTCombat = new SelectBox<EnumTailleMapCombat>(skin);
		Tcombat = new Array<EnumTailleMapCombat>(EnumTailleMapCombat.values().length);
		for (EnumTailleMapCombat value : EnumTailleMapCombat.values()) {
			Tcombat.add(value);
		}
		selectTCombat.setItems(Tcombat);
		selectTCombat.setSelected(EnumTailleMapCombat.MOYENNE);
		selectTCombat.setSize(screenWidth/2, height);
		selectTCombat.setPosition(screenWidth/2, screenHeight-height*8);
		
		retour = new TextButton("Retour", skin);
		retour.setSize(width, height);
		retour.setPosition(0, 0);
		
		valider = new TextButton("Valider", skin);
		valider.setSize(width, height);
		valider.setPosition(screenWidth*0.75f, screenHeight-height*9);
		
		stage = new Stage();
		//Ajout des éléments
		stage.addActor(TVictoireLabel);
		stage.addActor(TCarteLabel);
		stage.addActor(tailleCarteLabel);
		stage.addActor(abondRessLabel);
		stage.addActor(nbJoueurLabel);
		stage.addActor(nbPlanMaxLabel);
		stage.addActor(nbAnoMaxLabel);
		stage.addActor(TCombatLabel);
		stage.addActor(checkTVictoire);
		stage.addActor(selectTypeCarte);
		stage.addActor(selectTailleCarte);
		stage.addActor(selectabondRess);
		stage.addActor(selectNbJoueur);
		stage.addActor(nbAnoMax);
		stage.addActor(nbPlanMax);
		stage.addActor(selectTCombat);
		stage.addActor(retour);
		stage.addActor(valider);
		
		final List<EnumVictoire> tVictoire = new ArrayList<EnumVictoire>();
		for (Actor value : checkTVictoire.getChildren()) {
			if (CheckBox.class.cast(value).isChecked()) {
				 tVictoire.add(EnumVictoire.valueOf(value.getName().toUpperCase()));
			}
		}
		
		retour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
			}
		});
		
		valider.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.parametre = new Parametre(tVictoire, selectabondRess.getSelected(), selectTypeCarte.getSelected(), selectTailleCarte.getSelected(),
						selectNbJoueur.getSelected(), (int)nbAnoMax.getValue(), (int)nbPlanMax.getValue(), selectTCombat.getSelected(), null);
//				Project.galaxie = new AffichageGalaxie();
				Project.change = true;
				Project.menu = 2;
//				Project.affichageGalaxie = true;
			}
		});
	}
	
	public void draw() {
		stage.draw();
	}
	
	public void dispose() {
		stage.dispose();
	}

	public Stage getStage() {
		return stage;
	}
}
