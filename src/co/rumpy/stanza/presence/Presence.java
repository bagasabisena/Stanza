package co.rumpy.stanza.presence;

import org.json.JSONException;
import org.json.JSONObject;

import co.rumpy.stanza.Stanza;
import co.rumpy.stanza.iq.Roster;

public class Presence extends Stanza {
	
	public String type = "";
	private Roster roster = null;
	
	private static final String TAG_PRESENCE = "presence";
	private static final String TAG_TYPE = "type";
	private static final String TAG_ROSTER = "roster";
	
	public static final String TYPE_SUBSCRIBE = "subscribe";
	public static final String TYPE_UNSUBSCRIBE = "unsubscribe";
	public static final String TYPE_SUBSCRIBED = "subscribed";
	public static final String TYPE_UNSUBSCRIBED = "unsubscribed";
	
	public Presence() {
		
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Roster getRoster() {
		return roster;
	}
	
	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@Override
	public String toJSON() {
		
		JSONObject object = new JSONObject();
		String JSON = "";
		
		try {
			object.put(TAG_TO, to);
			object.put(TAG_FROM, from);
			object.put(TAG_TYPE, type);
			
			if (roster != null) {
				JSONObject jRoster = roster.toJSONObject();
				object.put(TAG_ROSTER, jRoster);
			}
			
			JSONObject stanza = new JSONObject();
			stanza.put(TAG_PRESENCE, object);
			
			JSON = stanza.toString();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSON;
	}

	@Override
	public Stanza fromJSON(String JSON) {
		
		JSONObject stanza;
		try {
			stanza = new JSONObject(JSON);
			JSONObject object = stanza.getJSONObject(TAG_PRESENCE);
			
			to = object.getString(TAG_TO);
			from = object.getString(TAG_FROM);
			type = object.getString(TAG_TYPE);
			
			JSONObject jRoster = object.optJSONObject(TAG_ROSTER);
			if (jRoster != null) {
				Roster roster = new Roster();
				roster.fromJSONObject(jRoster);
				this.roster = roster;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return this;
		
	}
	
	
	
	
	

}
