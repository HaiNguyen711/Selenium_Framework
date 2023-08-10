package dataObjects.enums;

public enum Users {
	
	TESTER1("Tester1"), TESTER2("Tester2");
	
	private final String text;
	
	Users(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
