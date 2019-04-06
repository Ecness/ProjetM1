package model.util;

import java.util.HashMap;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import model.EnumRessource;

@SuppressWarnings({ "serial" })
public class MapRessource extends HashMap<EnumRessource, Integer> implements Json.Serializable {

	@Override
	public void write (Json json) {
		//	      json.writeObjectStart("value", MapRessource.class, HashMap.class);
//			      for (Entry<EnumRessource, Integer> ressource : entrySet()) {
//			    	  json.writeValue(ressource.getKey().toString(), ressource.getValue());
//			      }
		//	      json.writeObjectEnd();

//		json.writeType(this.getClass());
		for (Entry<EnumRessource, Integer> ressource : entrySet()) {
			json.writeValue(ressource.getKey().toString(), ressource.getValue(), null);
		}
//		json.writeFields(this);
	}

	@Override
	public void read (Json json, JsonValue jsonData) {
//		MapRessource values = new MapRessource();
//		for (Entry<EnumRessource, Integer> ressource : entrySet()) {
			//			json.writeValue(ressource.getKey().toString(), ressource.getValue());
		for(JsonValue entry = jsonData.child; entry != null; entry = entry.next) {
//          intMap.put(Integer.parseInt(entry.name), json.readValue(entry.name, null, jsonData));
//			System.out.println("\n" + entry.name + "\n");
//			json.setTypeName(entry.name);
//			jsonData.next();
			System.out.println("\n"+entry.name + ":" + entry.child().next().asInt());
			String nom = entry.name;
			Object value = json.readValue(jsonData.child().next().name, null, jsonData);
			System.out.println(value);
			Integer val = entry.child().next().asInt();
			System.out.println(nom +"+"+val);
			put(EnumRessource.valueOf(nom), val);
      }
//			System.out.println(""+ressource.getKey().getClass()+ressource.getKey());
//		}
//		for(JsonValue entry = jsonData.child; entry != null; entry = entry.next) {
//			values.put(Integer.parseInt(entry.name), json.readValue(entry.name, null, jsonData));
//		}
		//		number.put(EnumRessource.valueOf(jsonData.child().name()), jsonData.child().asInt());
	}

}
