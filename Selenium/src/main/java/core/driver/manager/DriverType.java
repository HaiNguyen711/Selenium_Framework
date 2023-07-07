package core.driver.manager;

public enum DriverType {
	Chrome("Chrome"), Firefox("Firefox"), Edge("Edge");
	
	private final String value;
	
	DriverType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
