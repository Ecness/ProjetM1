package model.util;

import java.util.HashMap;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import model.EnumRessource;

@SuppressWarnings({ "serial" })
public class MapRessource extends HashMap<EnumRessource, Integer> implements Json.Serializable {

	@Override
	public void write (Json json) {
		for (Entry<EnumRessource, Integer> ressource : entrySet()) {
			json.writeValue(ressource.getKey().toString(), ressource.getValue(), null);
		}
	}

	@Override
	public void read (Json json, JsonValue jsonData) {
		for(JsonValue entry = jsonData.child; entry != null; entry = entry.next) {
			System.out.println("\n"+entry.name + ":" + entry.child().next().asInt());
			String nom = entry.name;
			Object value = json.readValue(jsonData.child().next().name, null, jsonData);
			System.out.println(value);
			Integer val = entry.child().next().asInt();
			System.out.println(nom +"+"+val);
			put(EnumRessource.valueOf(nom), val);
		}
	}
}
