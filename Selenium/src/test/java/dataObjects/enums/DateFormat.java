package dataObjects.enums;

public enum DateFormat {
	
	FORMAT_1("M/d/yyyy"), FORMAT_2("MM/dd/yyyy"), FORMAT_3("yyyy-MM-dd");
	
	private final String text;
	
	DateFormat(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
