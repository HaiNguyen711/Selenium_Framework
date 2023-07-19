package dataObjects.Agoda.enums;

public enum UserName {
	
	LOC("LocLy");
	
	private final String text;
	
	UserName(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
