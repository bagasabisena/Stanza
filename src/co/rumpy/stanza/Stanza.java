package co.rumpy.stanza;

public abstract class Stanza {
	
	//List of message types
	public static final int STREAM = 101;
	public static final int STREAM_INITIATE = 102;
	public static final int STREAM_RESPONSE = 103;
	
	public static final int MESSAGE_CHAT = 201;
	public static final int MESSAGE_RECEIPT_RECEIVED = 202;
	public static final int MESSAGE_RECEIPT_READ = 203;
	
	public static final int MESSAGE_GROUP = 210;
	
	public static final int IQ_PING = 301;
	public static final int IQ_PING_RESULT = 302;
	
	//List of stanza header
	public static final int HEADER_STREAM = 100;
	public static final int HEADER_MESSAGE = 200;
	public static final int HEADER_IQ = 300;
	public static final int HEADER_PRESENCE = 400;
	
	//JSON node name
	protected static final String TAG_FROM = "from";
	protected static final String TAG_TO = "to";
	
	protected String to;
	protected String from;
	
	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	
	public abstract String toJSON();
	public abstract Stanza fromJSON(String JSON);

}
