package dataObjects.enums;

public enum Users {
	
	ADMIN01("Admin01"), ADMIN("Admin");
	
	private final String text;
	
	Users(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
