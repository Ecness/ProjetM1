package model.entity.vaisseau;

import model.module.EnumArme;
import model.module.EnumBlindage;
import model.module.EnumModulVaisseau;

public class Vaisseau {

	private int puissance;
	private String nom;
	private EnumModulVaisseau[] TModuleVaisseau;
	private EnumArme[] TArme;
	private EnumBlindage[] TBlindage;
	private int vitesse;
}
