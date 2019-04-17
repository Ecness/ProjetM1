package controller.controles.buttons.flotte;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import model.carte.stellaire.Systeme;
import model.entity.vaisseau.Flotte;
import model.entity.vaisseau.Vaisseau;

public class SelectFlotteToMerge extends ScrollPane {
	/**Ensemble des flottes sélectionnées pour fusion**/
	private static List<Flotte> allFlotteSelected;
	/**Copie des flottes du système pour modification**/
	private static List<Flotte> copyFlotte;
	/**Indique si une nouvelle flotte est en cours de création**/
	private static boolean nouvelleFlotte;

	/**Flotte sélectionnée pour fusion**/
	private Flotte flotteSelected;
	/**Liste des vaisseaux sélectionnés pour transfert**/
	private List<Vaisseau> vaisseauSelected;

	public SelectFlotteToMerge(Systeme systeme, Skin skin) {
		super(null, skin);
		setName("source");
		//Initialisations
		if (allFlotteSelected == null) {
			allFlotteSelected = new ArrayList<Flotte>();
		}
		if (copyFlotte == null) {
			copyFlotte = new ArrayList<Flotte>();
		}
		//Copie des flottes du système pour modification
		if (copyFlotte.isEmpty()) {
			for (Flotte flotte : systeme.getFlottes()) {
				copyFlotte.add(new Flotte(flotte));
			}
		}
		vaisseauSelected = new ArrayList<Vaisseau>();
		
		//Groupe contenant les boutons
		VerticalGroup group = new VerticalGroup();
		group.setName("group");
		group.clear();

		//Affichage des flottes si aucune sélectionnée
		if (flotteSelected == null) {
			for (Flotte flotte : copyFlotte) {
				if (!allFlotteSelected.contains(flotte)) {
					TextButton button = new TextButton(flotte.getNom(), skin);
					button.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event, x, y);

							flotteSelected = flotte;
							allFlotteSelected.add(flotte);
							((PanelFusionFlotte) getParent()).update(systeme, skin);
						}

					});
					group.addActor(button);
				}
				
			}
			//Affichage d'un bouton de création d'une nouvelle flotte si pas déjà enclenché (contexte static)
			if (!nouvelleFlotte) {
				TextButton buttonNewFlotte = new TextButton("Nouvelle Flotte", skin);
				buttonNewFlotte.setName("nouvelle_flotte");
				buttonNewFlotte.addListener(new ClickListener() {
					
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						
						nouvelleFlotte = true;
						flotteSelected = new Flotte();
						allFlotteSelected.add(flotteSelected);
						((PanelFusionFlotte) getParent()).update(systeme, skin);
					}
					
				});
				group.addActor(buttonNewFlotte);
			}
		} else {
			//Si flotte sélectionnée, affichage des vaisseaux
			for (Vaisseau vaisseau : flotteSelected.getTVaisseau()) {
				TextButton button = new TextButton(vaisseau.getNom(), skin);
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);

						vaisseauSelected.add(vaisseau);
					}

				});
				group.addActor(button);
			}
			
			//Retour à la sélection des flottes
			TextButton retour = new TextButton("Retour", skin);
			retour.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);

					vaisseauSelected.clear();
					flotteSelected = null;
					((PanelFusionFlotte) getParent()).update(systeme, skin);
				}

			});
			group.addActor(retour);
		}

		setActor(group);
	}

	public void update(Systeme systeme, Skin skin) {
		//Groupe contenant les boutons
		VerticalGroup group = findActor("group");
		group.clear();
		//Affichage des flottes si aucune sélectionnée
		if (flotteSelected == null) {
			for (Flotte flotte : copyFlotte) {
				if (!allFlotteSelected.contains(flotte)) {
					TextButton button = new TextButton(flotte.getNom(), skin);
					button.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event, x, y);

							flotteSelected = flotte;
							allFlotteSelected.add(flotte);
							((PanelFusionFlotte) getParent()).update(systeme, skin);
						}

					});
					group.addActor(button);
				}
			}
			//Affichage d'un bouton de création d'une nouvelle flotte si pas déjà enclenché (contexte static)
			if (!nouvelleFlotte) {
				TextButton buttonNewFlotte = new TextButton("Nouvelle Flotte", skin);
				buttonNewFlotte.setName("nouvelle_flotte");
				buttonNewFlotte.addListener(new ClickListener() {
					
					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);
						
						nouvelleFlotte = true;
						flotteSelected = new Flotte();
						allFlotteSelected.add(flotteSelected);
						((PanelFusionFlotte) getParent()).update(systeme, skin);
					}
					
				});
				group.addActor(buttonNewFlotte);
			}
		} else {
			//Si flotte sélectionnée, affichage des vaisseaux
			vaisseauSelected = new ArrayList<Vaisseau>();
			for (Vaisseau vaisseau : flotteSelected.getTVaisseau()) {
				TextButton button = new TextButton(vaisseau.getNom(), skin);
				button.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						super.clicked(event, x, y);

						vaisseauSelected.add(vaisseau);
					}

				});
				group.addActor(button);
			}
			
			//Retour à la sélection des flottes
			TextButton retour = new TextButton("Retour", skin);
			retour.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);

					vaisseauSelected.clear();
					allFlotteSelected.remove(flotteSelected);
					flotteSelected = null;
					nouvelleFlotte = false;
					((PanelFusionFlotte) getParent()).update(systeme, skin);
				}

			});
			group.addActor(retour);
		}

		setActor(group);
	}

	public Flotte getFlotteSelected() {
		return flotteSelected;
	}

	public void setFlotteSelected(Flotte flotteSelected) {
		this.flotteSelected = flotteSelected;
	}

	public List<Vaisseau> getVaisseauSelected() {
		return vaisseauSelected;
	}

	public void setVaisseauSelected(List<Vaisseau> vaisseauSelected) {
		this.vaisseauSelected = vaisseauSelected;
	}
	
	public boolean isNouvelleFlotte() {
		return nouvelleFlotte;
	}

	public void setNouvelleFlotte(boolean nouvelleFlotte) {
		SelectFlotteToMerge.nouvelleFlotte = nouvelleFlotte;
	}

	public void clearAllFlotteSelected() {
		flotteSelected = null;
		vaisseauSelected.clear();
		allFlotteSelected.clear();
		copyFlotte.clear();
	}
	
	public static void addFlotte(Flotte flotte) {
		copyFlotte.add(flotte);
	}
	
	public static List<Flotte> getCopyFlotte() {
		return copyFlotte;
	}
}
