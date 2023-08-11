package dataObjects.enums;

public enum UserInfor {
	
	FIRSTNAME("firstName"), LASTNAME("lastName"), USERNAME("username"), PHONE("phone");
	
	private final String text;
	UserInfor(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
