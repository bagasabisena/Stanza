package co.rumpy.stanza.message;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.rumpy.stanza.Stanza;

public class Message extends Stanza {
	
	//JSON Node Name
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_ID = "id";
	private static final String TAG_TYPE = "type";
	private static final String TAG_BODY = "body";
	private static final String TAG_REQUEST = "request";
	private static final String TAG_RECEIVED = "received";
	private static final String TAG_READ = "read";
	
	public static final Integer HEADER = Stanza.HEADER_MESSAGE;
	
	private Integer stanzaType = 0;
	
	private String id = "";
	private Type type = null;
	private String body = "";
	private String originalJSON = "";
	private boolean isReceiptRequested = false;
	private ArrayList<String> receivedReceipt = null;
	private ArrayList<String> readReceipt = null;
	
	public enum Type {
		CHAT, GROUPCHAT, HEADLINE, NORMAL, ERROR;
		
		@Override
		public String toString() {
			String s = super.toString();
			s.toLowerCase();
			return s;
		}
	}
	
	// Getter and Setter
	
	public String getBody() {
		return body;
	}
	
	public String getId() {
		return id;
	}
	
	public String getOriginalJSON() {
		return originalJSON;
	}
	
	public ArrayList<String> getReadReceipt() {
		return readReceipt;
	}
	
	public ArrayList<String> getReceivedReceipt() {
		return receivedReceipt;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isReceiptRequested() {
		return isReceiptRequested;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setOriginalJSON(String originalJSON) {
		this.originalJSON = originalJSON;
	}
	
	public void setReadReceipt(ArrayList<String> readReceipt) {
		this.readReceipt = readReceipt;
	}
	
	public void setReceiptRequested(boolean isReceiptRequested) {
		this.isReceiptRequested = isReceiptRequested;
	}
	
	public void setReceivedReceipt(ArrayList<String> receivedReceipt) {
		this.receivedReceipt = receivedReceipt;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Integer getStanzaType() {
		return stanzaType;
	}
	
	public void setStanzaType(Integer stanzaType) {
		this.stanzaType = stanzaType;
	}
	
	

	@Override
	public String toJSON() {
		
		JSONObject object = new JSONObject();
		String JSON = "";
		try {
			object.put(TAG_TO, to);
			object.put(TAG_FROM, from);
			object.put(TAG_ID, id);
			
			if (type != null) {
				object.put(TAG_TYPE, type.toString().toLowerCase());
			}
			
			if (!body.equals("")) {
				object.put(TAG_BODY, body);
			}
			
			if (isReceiptRequested) {
				object.put(TAG_REQUEST, isReceiptRequested);
			}
			
			if (receivedReceipt != null) {
				object.put(TAG_RECEIVED, new JSONArray(receivedReceipt));
			}
			
			if (readReceipt != null) {
				object.put(TAG_READ, new JSONArray(readReceipt));
			}
			
			JSONObject stanza = new JSONObject();
			stanza.put(TAG_MESSAGE, object);
			
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
			JSONObject object = stanza.getJSONObject("message");
			
			id = object.getString(TAG_ID);
			to = object.getString(TAG_TO);
			from = object.getString(TAG_FROM);
			body = object.optString(TAG_BODY);
			isReceiptRequested = object.optBoolean(TAG_REQUEST);
			
			String typeInString = object.optString(TAG_TYPE);
			if (typeInString.equals("chat")) {
				type = Type.CHAT;
			} else if (typeInString.equals("groupchat")) {
				type = Type.GROUPCHAT;
			} else if (typeInString.equals("headline")) {
				type = Type.HEADLINE;
			} else if (typeInString.equals("error")) {
				type = Type.ERROR;
			} else if (typeInString.equals("normal")) {
				type = Type.NORMAL;
			} else if (typeInString.equals("")) {
				type = null;
			}
			
			JSONArray jReceivedReceipt = object.optJSONArray(TAG_RECEIVED);
			if (jReceivedReceipt != null) {
				receivedReceipt = new ArrayList<String>();
				for (int i=0; i<jReceivedReceipt.length(); i++) {
					receivedReceipt.add(jReceivedReceipt.getString(i));
				}
			}
			
			JSONArray jReadReceipt = object.optJSONArray(TAG_READ);
			if (jReadReceipt != null) {
				readReceipt = new ArrayList<String>();
				for (int i=0; i<jReadReceipt.length(); i++) {
					readReceipt.add(jReadReceipt.getString(i));
				}
			}
			
			//receivedReceipt = object.optString(TAG_RECEIVED);
			//readReceipt = object.optString(TAG_READ);
			
			if (type == Type.CHAT) {
				stanzaType = Stanza.MESSAGE_CHAT;
			} else if (type == Type.GROUPCHAT) {
				stanzaType = Stanza.MESSAGE_GROUP;
			} else if (receivedReceipt != null) {
				stanzaType = Stanza.MESSAGE_RECEIPT_RECEIVED;
			} else if (readReceipt != null) {
				stanzaType = Stanza.MESSAGE_RECEIPT_READ;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return this;
	}

}
