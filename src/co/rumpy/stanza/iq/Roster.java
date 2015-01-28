package co.rumpy.stanza.iq;

import org.json.JSONException;
import org.json.JSONObject;

public class Roster {
	
	private String signum;
	private String fullname;
	private String image;
	private String presence;
	
	//JSON Tag
	public static final String TAG_SIGNUM = "signum";
	public static final String TAG_FULLNAME = "fullname";
	public static final String TAG_IMAGE = "image";
	public static final String TAG_PRESENCE = "presence";
	
	public Roster(String signum, String fullname, String image, String presence) {
		this.signum = signum;
		this.fullname = fullname;
		this.image = image;
		this.presence = presence;
	}
	
	public Roster() {
		
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public String getSignum() {
		return signum;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getPresence() {
		return presence;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public void setSignum(String signum) {
		this.signum = signum;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setPresence(String presence) {
		this.presence = presence;
	}
	
	public JSONObject toJSONObject() {
		
		JSONObject jRoster = new JSONObject();
		
		try {
			jRoster.put(TAG_SIGNUM, signum);
			jRoster.put(TAG_FULLNAME, fullname);
			jRoster.put(TAG_IMAGE, image);
			jRoster.put(TAG_PRESENCE, presence);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jRoster;
		
	}
	
	public Roster fromJSONObject(JSONObject jRoster) {
		
		try {
			signum = jRoster.getString(TAG_SIGNUM);
			fullname = jRoster.getString(TAG_FULLNAME);
			image = jRoster.getString(TAG_IMAGE);
			presence = jRoster.getString(TAG_PRESENCE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
		
	}
	
	public void print() {
		System.out.println("Signum: " + signum);
		System.out.println("Fullname: " + fullname);
		System.out.println("Image: " + image);
		System.out.println("Presence: " + presence);
	}

}
