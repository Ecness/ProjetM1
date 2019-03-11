package view.menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import model.Partie;
import model.carte.stellaire.Carte;
import model.entity.player.EnumNation;
import model.entity.player.Joueur;
import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumRessourceDepart;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTailleMapCombat;
import model.parametre.EnumTypeCarte;
import model.parametre.EnumVictoire;
import model.parametre.Parametre;
import view.launcher.Project;

public class MenuParametre {
	private SplitPane panels;
	
	private Table joueurs;
//	private SelectBox<EnumNation> nation;
//	private SelectBox<String> couleur;
	
	private Table parametres;
	private List<CheckBox> TVictoire;
	
	public MenuParametre(Skin skin) {
//		nation = new SelectBox<EnumNation>(skin);
//		nation.setItems(EnumNation.values());
		
		joueurs = new Table(skin);
		joueurs.setName("tab_players");
		joueurs.align(Align.topLeft);
//		joueurs.setPosition(panels.getOriginX(), panels.getY());
//		joueurs.setSize(panels.getWidth() / 2, panels.getHeight());
		
		for (int i = 1; i <= 2; i++) {
			TextField name = new TextField("Joueur" + i, skin);
			name.setName("player_" + i);
			
			SelectBox<EnumNation> nation = new SelectBox<EnumNation>(skin);
			nation.setName("nation_" + i);
			nation.setItems(EnumNation.values());
			
			SelectBox<String> couleur = new SelectBox<String>(skin);
			couleur.setName("color_" + i);
			couleur.setItems(Colors.getColors().keys().toArray());
			
			joueurs.add(name).expand();
			joueurs.add(nation).expand();
			joueurs.add(couleur);
			joueurs.row();
		}
		
		TextButton retour = new TextButton("Retour", skin);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
			}
		});
//		joueurs.row();
		joueurs.add(retour);
		
		TVictoire = new ArrayList<CheckBox>();
		for (EnumVictoire victoire : EnumVictoire.values()) {
			TVictoire.add(new CheckBox(victoire.toString(), skin));
		}
		
		parametres = new Table(skin);
		parametres.align(Align.left);
		parametres.add("Type de victoire").expand().left();
		for (CheckBox victoire : TVictoire) {
			parametres.add(victoire).expand();
		}
		parametres.row();
		SelectBox<EnumTypeCarte> typeCarte = new SelectBox<>(skin);
		typeCarte.setItems(EnumTypeCarte.values());
		parametres.add("Type de carte").expand().left();
		parametres.add(typeCarte).right();
		parametres.row();
		SelectBox<EnumTailleCarte> tailleCarte = new SelectBox<EnumTailleCarte>(skin);
		tailleCarte.setItems(EnumTailleCarte.values());
		parametres.add("Taille de la carte").expand().left();
		parametres.add(tailleCarte).expand().right();
		parametres.row();
		SelectBox<EnumAbondanceRessource> abondRess = new SelectBox<EnumAbondanceRessource>(skin);
		abondRess.setItems(EnumAbondanceRessource.values());
		parametres.add("Abondance des ressources").expand().left();
		parametres.add(abondRess).expand().right();
		parametres.row();
		SelectBox<Integer> nbJoueur = new SelectBox<Integer>(skin);
		Array<Integer> temp = new Array<Integer>();
		for (int i = 2; i <= 8; i++) {
			temp.add(i);
		}
		nbJoueur.setItems(temp);
		nbJoueur.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				update(skin, nbJoueur.getSelected());
				
			}
		});
		parametres.add("Nombre de joueurs").expand().left();
		parametres.add(nbJoueur).expand().right();
		parametres.row();
		Slider nbPlanMax = new Slider(5, 10, 1, false, skin);
		parametres.add("Nombre de planète maximum par système").expand().left();
		parametres.add(nbPlanMax).expand().right();
		parametres.row();
		//TODO Vérifier la pertinence
		Slider nbAnoMax = new Slider(5, 10, 1, false, skin);
		parametres.add("Nombre d'anomalie maximum par système").expand().left();
		parametres.add(nbAnoMax).expand().right();
		parametres.row();
		SelectBox<EnumTailleMapCombat> tailleCarteCombat = new SelectBox<EnumTailleMapCombat>(skin);
		tailleCarteCombat.setItems(EnumTailleMapCombat.values());
		parametres.add("Taille de la carte de combat").expand().left();
		parametres.add(tailleCarteCombat).expand().right();
		parametres.row();
		SelectBox<EnumRessourceDepart> ressDep = new SelectBox<EnumRessourceDepart>(skin);
		ressDep.setItems(EnumRessourceDepart.values());
		ressDep.setSelected(EnumRessourceDepart.NORMAL);
		parametres.add("Ressources de départ").expand().left();
		parametres.add(ressDep);
		parametres.row();
		
		TextButton valider = new TextButton("Valider", skin);
		valider.addListener(new ClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO Devrait être appelé dans Project
				Project.staticStage.clear();

				//TODO Ajouter les conditions de victoire
				Project.parametre = new Parametre(null, abondRess.getSelected(), typeCarte.getSelected(), tailleCarte.getSelected(),
						nbJoueur.getSelected(), (int)nbAnoMax.getValue(), (int)nbPlanMax.getValue(), tailleCarteCombat.getSelected(), ressDep.getSelected());
				Project.galaxie = new Carte(Project.parametre);
				Joueur[] tabJoueurs = new Joueur[Project.parametre.getNbJoueur()];
				//Récupération des nations et des couleurs
				for (int i = 0; i < Project.parametre.getNbJoueur(); i++) {
					tabJoueurs[i] = new Joueur(	((TextField) joueurs.findActor("player_" + (i + 1))).getText(),
												((SelectBox<EnumNation>) joueurs.findActor("nation_" + (i + 1))).getSelected(),
												Colors.get(((SelectBox<String>) joueurs.findActor("color_" + (i + 1))).getSelected()),
												Project.parametre.getRessourceDepart());
					
					
//					EnumNation valeur;
//					Color couleur;
//					if (i == 0) {
//						valeur = ((SelectBox<EnumNation>) (joueurs.getChildren().get(1))).getSelected();
//						couleur = Colors.get(((SelectBox<String>)(joueurs.getChildren().get(2))).getSelected());
//					} else {
//						valeur = ((SelectBox<EnumNation>) (joueurs.getChildren().get(i*3+1))).getSelected();
//						couleur = Colors.get(((SelectBox<String>)(joueurs.getChildren().get(i*3+2))).getSelected());
//					}
//					tabJoueurs[i] = new Joueur("Joueur " + i, valeur, couleur, Project.parametre.getRessourceDepart());
				}
				
				Project.partie = new Partie(Project.parametre, tabJoueurs);
				
				Project.affichageGalaxie = true;
				Project.cptTours = 1;
			}
		});
		parametres.add(valider);
		
		
		panels = new SplitPane(joueurs, parametres, false, skin);
		panels.setSize(Project.staticStage.getCamera().viewportWidth, Project.staticStage.getCamera().viewportHeight);
		panels.setPosition(0, Project.staticStage.getCamera().viewportHeight - panels.getHeight());
//		panels.setFillParent(true);
		
		
		Project.staticStage.addActor(panels);
	}
	
	public void update(Skin skin, int nbPlayer) {
		joueurs.clear();
		
		for (int i = 1; i <= nbPlayer; i++) {
			TextField name = new TextField("Joueur" + i, skin);
			name.setName("player_" + i);
			
			SelectBox<EnumNation> nation = new SelectBox<EnumNation>(skin);
			nation.setName("nation_" + i);
			nation.setItems(EnumNation.values());
			
			SelectBox<String> couleur = new SelectBox<String>(skin);
			couleur.setName("color_" + i);
			couleur.setItems(Colors.getColors().keys().toArray());
			
			joueurs.add(name).expand();
			joueurs.add(nation).expand();
			joueurs.add(couleur);
			joueurs.row();
		}
		
		TextButton retour = new TextButton("Retour", skin);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
			}
		});
//		joueurs.row();
		joueurs.add(retour);
	}
}
