package dataObjects.enums;

public enum RoleName {
	ADMIN("Admin"), PARTNER("Partner"), MEMBER("Member");
	
	private final String text;
	
	RoleName(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
