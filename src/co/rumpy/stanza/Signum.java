package co.rumpy.stanza;

public class Signum {
	
	private String user = null;
	private String domain = null;
	private String resource = null;
	
	public static final String DEFAULT_DOMAIN = "rumpy.co";
	
	public Signum() {
		
	}
	
	public Signum(String stringSignum) {
		this.parse(stringSignum);
	}
	
	public String getDomain() {
		return domain;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getResource() {
		return resource;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public void parse(String signum) {
		
		String[] signumArray = signum.split("@");
		String domainAndResource;
		this.user = signumArray[0];
		if (signumArray.length == 1) {
			return;
		} else {
			domainAndResource = signumArray[1];
		}
		
		String[] domainAndResourceArray = domainAndResource.split("/");
		
		if (domainAndResourceArray.length == 1) {
			this.domain = domainAndResourceArray[0];
			return;
		} else {
			this.domain = domainAndResourceArray[0];
			this.resource = domainAndResourceArray[1];
			return;
		}
		
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder(user);
		String signum;
		
		if (domain == null) {
			builder.append("@" + DEFAULT_DOMAIN);
		} else {
			builder.append("@" + domain);
		}
		
		if (resource == null) {
			signum = builder.toString();
		} else {
			builder.append("/" + resource);
			signum = builder.toString();
		}
		
		return signum;
		
	}
	
	public void print() {
		System.out.println(user);
		System.out.println(domain);
		System.out.println(resource);
	}
	
	public String getBareSignum() {
		
		return user + "@" + domain;
	}
	
	public boolean isBare() {
		
		if (resource != null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String normalize(String signum) {
		
		Signum s = new Signum();
		s.parse(signum);
		return s.toString();
		
	}

}
