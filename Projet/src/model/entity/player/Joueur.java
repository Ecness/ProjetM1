package model.entity.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.EnumRessource;
import model.carte.stellaire.Ville;
import model.entity.general.General;
import model.entity.vaisseau.Flotte;

public class Joueur {

	
	
	private String name;
	private EnumNation nation;
	private Map<EnumRessource, Integer> TRessource;
	private List<Flotte> TFlotte;
	private List<Ville> TVille;
	private Science science;
	private List<General> TGeneral;
}
