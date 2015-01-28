package co.rumpy.stanza.iq;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.rumpy.stanza.Stanza;

public class IQ extends Stanza {
	
	String type = "";
	String content = "";
	String id = "";
	Object query = null;
	
	public static final String TYPE_SET = "set";
	public static final String TYPE_GET = "get";
	public static final String TYPE_RESULT = "result";
	public static final String TYPE_ERROR = "error";
	
	public static final String CONTENT_ROSTER = "roster";
	public static final String CONTENT_USER = "user";

	public static final Integer HEADER = Stanza.HEADER_IQ;
	
	private static final String TAG_ID = "id";
	private static final String TAG_TYPE = "type";
	private static final String TAG_CONTENT = "content";
	private static final String TAG_QUERY = "query";
	private static final String TAG_IQ = "iq";
	
	public String getContent() {
		return content;
	}
	
	public String getId() {
		return id;
	}
	
	public Object getQuery() {
		return query;
	}
	
	public String getType() {
		return type;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setQuery(Object query) {
		this.query = query;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toJSON() {
		
		JSONObject object = new JSONObject();
		String JSON = "";
		
		try {
			object.put(TAG_TO, to);
			object.put(TAG_FROM, from);
			object.put(TAG_ID, id);
			object.put(TAG_TYPE, type);
			object.put(TAG_CONTENT, content);
			
			if (query != null) {
				if (type.equals("result") && content.equals("user")) {
					
					Roster roster = (Roster) query;
					JSONObject jRoster = roster.toJSONObject();
					
					object.put(TAG_QUERY, jRoster);
					
				} else if ((type.equals("set") || type.equals("get")) && (content.equals("user") || content.equals("roster"))) {
					
					String signum = (String) query;
					object.put(TAG_QUERY, signum);
					
				} else if (type.equals("result") && content.equals("roster")) {
					
					ArrayList<Roster> rosters = (ArrayList<Roster>) query;
					JSONArray jRosters = new JSONArray();
					
					for (Roster r : rosters) {
						JSONObject jRoster = r.toJSONObject();
						jRosters.put(jRoster);
					}
					
					object.put(TAG_QUERY, jRosters);
					
				} else if (type.equals("result") && content.equals("user")) {
					
					Roster r = (Roster) query;
					JSONObject jRoster = r.toJSONObject();
					object.put(TAG_QUERY, jRoster);
				}
			}
			
			JSONObject stanza = new JSONObject();
			stanza.put(TAG_IQ, object);
			
			JSON = stanza.toString();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	

	@Override
	public Stanza fromJSON(String JSON) {
	
		try {
			JSONObject stanza = new JSONObject(JSON);
			JSONObject object = stanza.getJSONObject(TAG_IQ);
			
			id = object.getString(TAG_ID);
			to = object.getString(TAG_TO);
			from = object.getString(TAG_FROM);
			type = object.getString(TAG_TYPE);
			content = object.getString(TAG_CONTENT);
			//query = object.opt(TAG_QUERY);
			
			if (content.equals("roster") && type.equals("result")) {
				JSONArray array = object.getJSONArray(TAG_QUERY);
				ArrayList<Roster> rosters = new ArrayList<Roster>();
				for (int i=0;i<array.length();i++) {
					JSONObject jRoster = array.getJSONObject(i);
					String signum = jRoster.getString(Roster.TAG_SIGNUM);
					String fullname = jRoster.getString(Roster.TAG_FULLNAME);
					String image = jRoster.getString(Roster.TAG_IMAGE);
					String presence = jRoster.getString(Roster.TAG_PRESENCE);
					rosters.add(new Roster(signum, fullname, image, presence));
				}
				
				query = rosters;
			} else if ((content.equals("user") || content.equals("roster")) && (type.equals("get") || type.equals("set"))) {
				query = object.getString(TAG_QUERY);
			} else if (content.equals("user") && type.equals("result")) {
				JSONObject jRoster = object.getJSONObject(TAG_QUERY);
				Roster roster = new Roster();
				roster.fromJSONObject(jRoster);
				query = roster;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	

}
