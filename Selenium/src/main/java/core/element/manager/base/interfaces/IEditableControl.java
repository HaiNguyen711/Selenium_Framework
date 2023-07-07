package core.element.manager.base.interfaces;

public interface IEditableControl extends IClickableControl {
	
	 void enter(String value);
	
	 void type(String value);
	 
	 void clear();
}
