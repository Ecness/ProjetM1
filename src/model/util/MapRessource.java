package model.util;

import java.util.HashMap;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import model.EnumRessource;

@SuppressWarnings({ "rawtypes", "serial" })
public class MapRessource extends HashMap<EnumRessource, Integer> implements Json.Serializer<MapRessource> {

	@Override
	public void write(Json json, MapRessource object, Class knownType) {
		json.writeObjectStart();
		for (Entry<EnumRessource, Integer> value : object.entrySet()) {
			json.writeValue("" + value.getKey(), value.getValue(), knownType);
		}
		json.writeObjectEnd();
	}

	@Override
	public MapRessource read(Json json, JsonValue jsonData, Class type) {
		MapRessource ressource = new MapRessource();
		
		ressource.put(EnumRessource.valueOf(jsonData.child().name), jsonData.child().asInt());

		return ressource;
	}

}
