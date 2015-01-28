package co.rumpy.stanza.test;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import co.rumpy.stanza.Signum;
import co.rumpy.stanza.Stanza;
import co.rumpy.stanza.iq.IQ;
import co.rumpy.stanza.iq.IQs;
import co.rumpy.stanza.iq.Roster;
import co.rumpy.stanza.message.Message;
import co.rumpy.stanza.message.Message.Type;
import co.rumpy.stanza.presence.Presence;
import co.rumpy.stanza.stream.Stream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*IQ iq = new IQ();
		iq.setTo("server.rumpy.co");
		iq.setFrom("bagas@rumpy.co");
		iq.setType("get");
		iq.setId("123abc");
		iq.setContent("roster");
		
		ArrayList<Roster> rosters = new ArrayList<Roster>();
		rosters.add(new Roster("bagas@rumpy.co", "Bagas Abisena", "bagas.jpg", "hello"));
		rosters.add(new Roster("adiskaf@rumpy.co", "Adiska Fardani", "adiskaf.jpg", "hihi"));
		
		iq.setQuery(rosters);
		
		String JSON = iq.toJSON();
		
		System.out.println(JSON);
		
		IQ iq2 = (IQ) iq.fromJSON(JSON);
		System.out.println(iq2.getTo());
		System.out.println(iq2.getFrom());
		System.out.println(iq2.getId());
		System.out.println(iq2.getType());
		System.out.println(iq2.getContent());
		
		@SuppressWarnings("unchecked")
		ArrayList<Roster> rosters2 = (ArrayList<Roster>) iq2.getQuery();
		Roster roster = rosters2.get(0);
		System.out.println("ROSTER " + roster.getFullname()); */
		
		
		
		//-------------------------
		
		/*String JSON = "{\"iq\":{\"content\":\"roster\",\"id\":\"123abc\",\"to\":\"server.rumpy.co\",\"query\":[{\"signum\":\"bagas@rumpy.co\",\"fullname\":\"Bagas Abisena\"}," +
				"{\"signum\":\"adiskaf@rumpy.co\",\"fullname\":\"Adiska Fardani\"}],\"from\":\"bagas@rumpy.co\",\"type\":\"get\"}}";
		
		Stanza stanza = null;
		
		try {
			JSONObject jStanza = new JSONObject(JSON);
			if (jStanza.has("stream")) {
				// stream stanza received
				stanza = new Stream();
				stanza = stanza.fromJSON(JSON);
			} else if (jStanza.has("message")) {
				stanza = new Message();
				stanza = stanza.fromJSON(JSON);
			} else if (jStanza.has("iq")) {
				stanza = new IQ();
				stanza = stanza.fromJSON(JSON); 
			} else if (jStanza.has("presence")) {
				//stanza = new Presence();
				//stanza = stanza.fromJSON(JSON);
			} else {
				// ERROR
				System.out.println("ERROR in JSON decoding process");
			}
			
			IQ iq = (IQ) stanza;
			System.out.println(iq.getId()); 
			
		} catch (JSONException e) {
			e.printStackTrace();
		} */
		
		// -----------------------------------
		
		
		/*Message message = new Message();
		message.setTo("adiskaf@rumpy.co");
		message.setFrom("bagas@rumpy.co");
		message.setId("123abc");
		ArrayList<String> readIDs = new ArrayList<String>();
		readIDs.add("asdfgh");
		//readIDs.add("zxcvbn");
		//readIDs.add("qwerty");
		message.setReceivedReceipt(readIDs);
		
		String JSON = message.toJSON();
		System.out.println(JSON);
		 
		
		Message message2 = new Message();
		message2.fromJSON(JSON);
		
		ArrayList<String> readIDs2 = message2.getReceivedReceipt();
		
		for (String s : readIDs2) {
			System.out.println(s);
		}
		
		Message deliveredReceipt = new Message();
		deliveredReceipt.setFrom(message.getTo());
		deliveredReceipt.setTo(message.getFrom());
		deliveredReceipt.setId("123456");
		ArrayList<String> receivedIDs = new ArrayList<String>();
		receivedIDs.add(message.getId());
		deliveredReceipt.setReceivedReceipt(receivedIDs);*/
		
		// -----------------------------------------------
		
		/*Message atob = new Message();
		atob.setTo("bagas@rumpy.co");
		atob.setFrom("adiskaf@rumpy.co");
		atob.setId("atob");
		atob.setType(Type.CHAT);
		atob.setBody("Halo B dari A");
		atob.setReceiptRequested(true);
		String jAtob = atob.toJSON();
		//System.out.println(jAtob);
		
		send(atob);
		
		Message breca = new Message();
		breca.fromJSON(jAtob);
		
		if (breca.isReceiptRequested()) {
			
			//logger("message ask for deliveries receipt. Sending receipt");
			
			Message deliveredReceipt = new Message();
			deliveredReceipt.setFrom(breca.getTo());
			deliveredReceipt.setTo(breca.getFrom());
			deliveredReceipt.setId("123");
			ArrayList<String> receivedIDs = new ArrayList<String>();
			receivedIDs.add(breca.getId());
			deliveredReceipt.setReceivedReceipt(receivedIDs);
			send(deliveredReceipt);
		} */
		
		//----------------------
		
		/*Message weird = new Message();
		weird.setFrom("bagas@rumpy.co");
		weird.setTo("adiskaf@rumpy.co");
		weird.setId("qwerty");
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("zxcvb");
		ids.add("qwerty");
		weird.setReceivedReceipt(ids);
		//Log.d("4", weird.toJSON());
		System.out.println(weird.toJSON());*/
		
		// ----------------------------
		
		Roster roster = new Roster("bagas@rumpy.co", "Bagas Abisena", "bagas.jpg", "hello!");
		ArrayList<Roster> rosters = new ArrayList<Roster>();
		rosters.add(roster);
		
		IQ iq = new IQ();
		iq.setTo("bagas@rumpy.co");
		iq.setFrom("server.rumpy.co");
		iq.setId("123456");
		iq.setType("result");
		iq.setContent("roster");
		iq.setQuery(rosters);
		
		System.out.println(iq.toJSON());
		
		IQ iq2 = new IQ();
		iq2.fromJSON(iq.toJSON());
		System.out.println(iq2.getContent());
		
		ArrayList<Roster> rosters2 = (ArrayList<Roster>) iq2.getQuery();
		for (Roster r : rosters2) {
			r.print();
		}
		//Roster roster2 = (Roster) iq2.getQuery();
		//roster2.print();
		
		//------------------------------
		
		/*Presence presence = new Presence();
		
		presence.setTo("bagas@rumpy.co");
		presence.setFrom("adiskaf@rumpy.co");
		presence.setType(Presence.TYPE_SUBSCRIBE);
		
		String jPresence = presence.toJSON();
		
		System.out.println(jPresence);
		
		Presence presence2 = new Presence();
		presence2.fromJSON(jPresence);
		
		System.out.println(presence2.getTo());
		System.out.println(presence2.getFrom());
		System.out.println(presence2.getType());*/
		
		// --------------------------------
		
		/*Presence presence1 = new Presence();
		presence1.setTo("bagas@rumpy.co");
		presence1.setFrom("adiskaf@rumpy.co");
		presence1.setType(Presence.TYPE_SUBSCRIBE);
		presence1.setRoster(new Roster("adiskaf@rumpy.co", "Adiska Fardani", "adiskaf.jpg", "NoLimit!"));
		
		String jPresence = presence1.toJSON();
		System.out.println(jPresence);
		
		Presence presence2 = new Presence();
		presence2.fromJSON(jPresence);
		
		Roster r = presence2.getRoster();
		r.print();*/
		
		// ----------------------------------
		
		/*String fullSignum = "bagas@rumpy.co/android";
		
		System.out.println(new Signum(fullSignum).getBareSignum());*/
		
		//--------------------------------------------
		
		/*IQ iqSet = IQs.setRoster("bagas@rumpy.co", "server.rumpy.co", "adiskaf@rumpy.co");
		String jSet = iqSet.toJSON();
		
		System.out.println(jSet);
		
		IQ iqSet2 = new IQ();
		iqSet2.fromJSON(jSet);
		
		System.out.println(iqSet2.getQuery());*/
		
		// -------------------------------------
		
		IQ getUserIQ = new IQ();
		getUserIQ.setTo("server.rumpy.co");
		getUserIQ.setFrom("bagas@rumpy.co");
		getUserIQ.setId("123abc");
		getUserIQ.setType("get");
		getUserIQ.setContent("user");
		getUserIQ.setQuery("adiskaf@rumpy.co");
		
		System.out.println(getUserIQ.toJSON());
		
		IQ resultUserIQ = new IQ();
		resultUserIQ.setFrom("server.rumpy.co");
		resultUserIQ.setTo("bagas@rumpy.co");
		resultUserIQ.setId("123abc");
		resultUserIQ.setType(IQ.TYPE_RESULT);
		resultUserIQ.setContent("user");
		resultUserIQ.setQuery(new Roster("adiskaf@rumpy.co", "Adiska Fardani", "adiskaf.jpg", "nolimit hore"));
		
		System.out.println(resultUserIQ.toJSON());
		
	}
	
	private static void send(Stanza stanza) {
		String jStanza = stanza.toJSON();
		System.out.println(jStanza);
	}

}
