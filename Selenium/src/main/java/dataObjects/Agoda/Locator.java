package dataObjects.Agoda;

public class Locator {
	private String _sControlType;
	private String _sLocatorType;
	private String _sLocator;

	public Locator(String sControlType, String sLocatorType, String sLocator) {
		this._sControlType = sControlType;
		this._sLocatorType = sLocatorType;
		this._sLocator = sLocator;
	}

	public String getsControlType() {
		return _sControlType;
	}

	public void setsControlType(String sControlType) {
		this._sControlType = sControlType;
	}
	public String getsLocatorType() {
		return _sLocatorType;
	}

	public void setsLocatorType(String sLocatorType) {
		this._sLocatorType = sLocatorType;
	}
	public String getsLocator() {
		return _sLocator;
	}

	public void setsLocator(String sLocator) {
		this._sLocator = sLocator;
	}
}
