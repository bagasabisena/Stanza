package co.rumpy.stanza;

import org.json.JSONException;
import org.json.JSONObject;

public class Media {
	
	private String type = "";
	private String url = "";
	private String data = "";
	
	// JSON Node Name
	private static final String TAG_TYPE = "type";
	private static final String TAG_URL = "url";
	private static final String TAG_DATA = "data";
	
	// Media type
	public static final String TYPE_IMAGE_JPG = "image/jpeg";
	public static final String TYPE_IMAGE_PNG = "image/png";
	public static final String TYPE_AUDIO_AMR = "audio/amr";
	
	public String getData() {
		return data;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public JSONObject toJSONObject() {
		
		JSONObject object = new JSONObject();
		
		try {
			object.put(TAG_TYPE, type);
			object.put(TAG_URL, url);
			object.put(TAG_DATA, data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
		
		
	}
	

}
