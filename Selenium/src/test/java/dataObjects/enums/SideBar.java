package dataObjects.enums;

public enum SideBar {

	USERINFO("user-info"), PARTNERS("partners"), ACCOUNTS("accounts");
	
	private final String text;
	
	SideBar(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
