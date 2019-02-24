package view.menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

public class MenuParametre2 {
	private SplitPane panels;
	
	private Table joueurs;
	private SelectBox<EnumNation> nation;
	private SelectBox<String> couleur;
	
	private Table parametres;
	private List<CheckBox> TVictoire;
	
	public MenuParametre2() {
		nation = new SelectBox<EnumNation>(Project.skin);
		nation.setItems(EnumNation.values());
		
		joueurs = new Table(Project.skin);
		joueurs.align(Align.topLeft);
//		joueurs.setPosition(panels.getOriginX(), panels.getY());
//		joueurs.setSize(panels.getWidth() / 2, panels.getHeight());
		joueurs.add("Joueur").expand();
		joueurs.add(nation).expand();
		couleur = new SelectBox<String>(Project.skin);
		couleur.setItems(Colors.getColors().keys().toArray());
		joueurs.add(couleur).expand();
		for (int i = 1; i <= 8; i++) {
			joueurs.row();
			joueurs.add("Ordinateur").expand();
			SelectBox<EnumNation> nation = new SelectBox<EnumNation>(Project.skin);
			nation.setItems(EnumNation.values());
			joueurs.add(nation).expand();
			SelectBox<String> couleur = new SelectBox<String>(Project.skin);
			couleur = new SelectBox<String>(Project.skin);
			couleur.setItems(Colors.getColors().keys().toArray());
			joueurs.add(couleur).expand();
		}
		
		TextButton retour = new TextButton("Retour", Project.skin);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Project.change = true;
				Project.menu = 0;
			}
		});
		joueurs.row();
		joueurs.add(retour);
		
		TVictoire = new ArrayList<CheckBox>();
		for (EnumVictoire victoire : EnumVictoire.values()) {
			TVictoire.add(new CheckBox(victoire.toString(), Project.skin));
		}
		
		parametres = new Table(Project.skin);
		parametres.align(Align.left);
		parametres.add("Type de victoire").expand().left();
		for (CheckBox victoire : TVictoire) {
			parametres.add(victoire).expand();
		}
		parametres.row();
		SelectBox<EnumTypeCarte> typeCarte = new SelectBox<>(Project.skin);
		typeCarte.setItems(EnumTypeCarte.values());
		parametres.add("Type de carte").expand().left();
		parametres.add(typeCarte).right();
		parametres.row();
		SelectBox<EnumTailleCarte> tailleCarte = new SelectBox<EnumTailleCarte>(Project.skin);
		tailleCarte.setItems(EnumTailleCarte.values());
		parametres.add("Taille de la carte").expand().left();
		parametres.add(tailleCarte).expand().right();
		parametres.row();
		SelectBox<EnumAbondanceRessource> abondRess = new SelectBox<EnumAbondanceRessource>(Project.skin);
		abondRess.setItems(EnumAbondanceRessource.values());
		parametres.add("Abondance des ressources").expand().left();
		parametres.add(abondRess).expand().right();
		parametres.row();
		SelectBox<Integer> nbJoueur = new SelectBox<Integer>(Project.skin);
		Array<Integer> temp = new Array<Integer>();
		for (int i = 2; i <= 8; i++) {
			temp.add(i);
		}
		nbJoueur.setItems(temp);
//		nbJoueur.addAction(new Action() {
//			@Override
//			public boolean act(float delta) {
//				int i = 0;
//				
//				Array<Cell> cells = joueurs.getCells();
//				while (i < 8) {
//					if (i > nbJoueur.getSelected()) {
//						((SelectBox<Integer>) cells[(i+1)*2]).setDisabled(true);
//					}
//				}
//				
//				
////				parametres.invalidate();
////				joueurs.clearChildren();
////				joueurs.add("Joueur").expand();
////				joueurs.add(nation).expand();
//				for (int i = 1; i <= 8; i++) {
//					joueurs.get
//					joueurs.row();
//					joueurs.add("Ordinateur").expand();
//					SelectBox<EnumNation> nation = new SelectBox<EnumNation>(Project.skin);
//					nation.setItems(EnumNation.values());
//					Cell<SelectBox<EnumNation>> cell = joueurs.add(nation).expand();
//					if (i > nbJoueur.getSelected()) {
//						cell.getActor().setDisabled(true);
//					}
//				}
//				
////				parametres.validate();
//				return false;
//			}
//		});
		parametres.add("Nombre de joueurs").expand().left();
		parametres.add(nbJoueur).expand().right();
		parametres.row();
		Slider nbPlanMax = new Slider(5, 10, 1, false, Project.skin);
		parametres.add("Nombre de planète maximum par système").expand().left();
		parametres.add(nbPlanMax).expand().right();
		parametres.row();
		//TODO Vérifier la pertinence
		Slider nbAnoMax = new Slider(5, 10, 1, false, Project.skin);
		parametres.add("Nombre d'anomalie maximum par système").expand().left();
		parametres.add(nbAnoMax).expand().right();
		parametres.row();
		SelectBox<EnumTailleMapCombat> tailleCarteCombat = new SelectBox<EnumTailleMapCombat>(Project.skin);
		tailleCarteCombat.setItems(EnumTailleMapCombat.values());
		parametres.add("Taille de la carte de combat").expand().left();
		parametres.add(tailleCarteCombat).expand().right();
		parametres.row();
		SelectBox<EnumRessourceDepart> ressDep = new SelectBox<EnumRessourceDepart>(Project.skin);
		ressDep.setItems(EnumRessourceDepart.values());
		parametres.add("Ressources de départ").expand().left();
		parametres.add(ressDep);
		parametres.row();
		
		TextButton valider = new TextButton("Valider", Project.skin);
		valider.addListener(new ClickListener() {
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
					EnumNation valeur;
					Color couleur;
					if (i == 0) {
						valeur = ((SelectBox<EnumNation>) (joueurs.getChildren().get(1))).getSelected();
						couleur = Colors.get(((SelectBox<String>)(joueurs.getChildren().get(2))).getSelected());
					} else {
						valeur = ((SelectBox<EnumNation>) (joueurs.getChildren().get(i*3+1))).getSelected();
						couleur = Colors.get(((SelectBox<String>)(joueurs.getChildren().get(i*3+2))).getSelected());
					}
					tabJoueurs[i] = new Joueur("Joueur " + i, valeur, couleur, Project.parametre.getRessourceDepart());
				}
				
				Project.partie = new Partie(Project.parametre, tabJoueurs);
				
				Project.affichageGalaxie = true;
			}
		});
		parametres.add(valider);
		
		
		panels = new SplitPane(joueurs, parametres, false, Project.skin);
		panels.setSize(Project.staticStage.getCamera().viewportWidth, Project.staticStage.getCamera().viewportHeight);
		panels.setPosition(0, Project.staticStage.getCamera().viewportHeight - panels.getHeight());
//		panels.setFillParent(true);
		
		
		Project.staticStage.addActor(panels);
	}
}
