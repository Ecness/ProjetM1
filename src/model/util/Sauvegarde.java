package model.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.utils.Json;

import model.EnumRessource;

public class Sauvegarde {

	public static void sauvegarder() {
		//TODO Implémenter le mécanisme de sauvegarde
	}
	
	public static void charger() {
		//TODO Implémenter le mécanisme de chargement
	}
	
	/**
	 * Correction d'une erreur liée à la lecture des HashMap<EnumRessource, Integer>
	 * 
	 * @param values	Map à corriger
	 * 
	 * @return	Map<EnumRessource, Integer>
	 */
	public static Map<EnumRessource, Integer> convert(Map<?, Integer> values) {
		Map<EnumRessource, Integer> map = new HashMap<EnumRessource, Integer>();
		
		for (Entry<?, Integer> value : values.entrySet()) {
			map.put(EnumRessource.valueOf((String) value.getKey()), value.getValue());
		}
		
		return map;
	}
	
	/**
	 * Load object's data from a json file
	 * 
	 * @param object	Class of the object to load
	 * @param path		Path of the file to load
	 * 
	 * @return	An object containing the file's data
	 */
	public static Object loadFromFile(Class<?> objectClass, String path) {
		Json parser = new Json();
		Object list = null;
		try(FileReader file = new FileReader(path)) {

			list = parser.fromJson(Class.forName(objectClass.getName()), file);

		} catch (Exception e) {
			System.out.println(e.toString() + "### ERREUR : FILE:\""+path+"\" ###");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		return list;
	}
}
