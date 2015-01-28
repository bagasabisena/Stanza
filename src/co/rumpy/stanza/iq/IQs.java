package co.rumpy.stanza.iq;

import co.rumpy.stanza.utils.StringUtils;

/**
 * Static helper class to help create iq stanza that is commonly used
 * @author Bagas Abisena
 *
 */

public class IQs {
	
	public static IQ setRoster (String from, String to, String query) {
		
		IQ iq = new IQ();
		iq.setFrom(from);
		iq.setTo(to);
		iq.setId(StringUtils.randomStringGenerator(6));
		iq.setType(IQ.TYPE_SET);
		iq.setContent(IQ.CONTENT_ROSTER);
		iq.setQuery(query);
		
		return iq;
	}
	
	public static IQ resultRoster(String from, String to, Roster roster) {
		
		IQ iq = new IQ();
		iq.setFrom(from);
		iq.setTo(to);
		iq.setId(StringUtils.randomStringGenerator(6));
		iq.setType(IQ.TYPE_RESULT);
		iq.setContent(IQ.CONTENT_ROSTER);
		iq.setQuery(roster);
		
		return iq;
	}

}
