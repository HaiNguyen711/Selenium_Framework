package core.element.manager.base.type;

public enum ElementType {
	
	CSS_SELECTOR("cssSelector"), ID("id"), CLASS_NAME("class"), NAME("name"),TAG_NAME("tagName"), XPATH("xpath");
	
	
	private String by;
	
	/**
	 * Element type of locator with given string of by
	 * 
	 * @param by - Type of locator
	 */
	ElementType(String by) {
		this.by = by;
	}
	
	/**
	 * Get value element type
	 * 
	 * @return type of locator
	 */
	public String getValue() {
		return by;
	}

}
