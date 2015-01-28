package co.rumpy.stanza.stream;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import co.rumpy.stanza.Stanza;

public class Stream extends Stanza {
	
	
	private String id = "";
	private ArrayList<String> features = null;
	private String user_agent = "";
	
	public static final Integer HEADER = Stanza.HEADER_STREAM;
	
	// JSON node name
	private static final String TAG_STREAM = "stream";
	private static final String TAG_ID = "id";
	private static final String TAG_FEATURE = "features";
	private static final String TAG_USER_AGENT = "user_agent";
	
	// Feature list
	public static final String FEATURE_CHAT_BASIC = "chat";
	public static final String FEATURE_RICH_PRESENCE = "rich_presence";
	
	public Stream() {
		
		features = new ArrayList<String>();
	}
	
	public ArrayList<String> getFeatures() {
		return features;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUserAgent() {
		return user_agent;
	}
	
	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setUserAgent(String userAgent) {
		this.user_agent = userAgent;
	}

	@Override
	public String toJSON() {
		
		JSONObject object = new JSONObject();
		String JSON = "";
		
		try {
			object.put(TAG_TO, to);
			object.put(TAG_FROM, from);
			
			if (!id.equals("")) {
				object.put(TAG_ID, id);
			}
			
			JSONObject stanza = new JSONObject();
			stanza.put(TAG_STREAM, object);
			
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
			JSONObject object = stanza.getJSONObject(TAG_STREAM);
			
			to = object.getString(TAG_TO);
			from = object.getString(TAG_FROM);
			id = object.optString(TAG_ID);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
	

}
