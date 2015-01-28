package co.rumpy.stanza.presence;

/**
 * Static helper class to help create presence stanza that is commonly used
 * @author Bagas Abisena
 *
 */

public class Presences {
	
	public static Presence subscribedPresence(String from, String to) {
		
		Presence p = new Presence();
		p.setFrom(from);
		p.setTo(to);
		p.setType(Presence.TYPE_SUBSCRIBED);
		
		return p;
	}
	
	public static Presence unsubscribedPresence(String from, String to) {
		
		Presence p = new Presence();
		p.setFrom(from);
		p.setTo(to);
		p.setType(Presence.TYPE_UNSUBSCRIBED);
		
		return p;
	}
	
	

}
