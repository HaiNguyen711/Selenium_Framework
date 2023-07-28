package dataObjects.Agoda.enums;

public enum ControlType {
	
	BUTTON("Button"), CHECKBOX("CheckBox"), COMBOBOX("ComboBox"), LABEL("Label"), LINK("link"), RADIOBUTTON("RadioButton"),TAB("Tab"), TEXTBOX("TextBox");
	
	private final String text;
	
	ControlType(String text) {
		this.text = text;
	}
	
	public String getElementType() {
		return text;
	}

}
