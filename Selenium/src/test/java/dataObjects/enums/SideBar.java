package dataObjects.enums;

public enum SideBar {

	USERINFOR("Hi,"), PARTNERS("Partners"), ACCOUNTS("Accounts"), LOGOUT("Logout");
	
	private final String text;
	
	SideBar(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
