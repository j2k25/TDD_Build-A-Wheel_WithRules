package wheel.UI;

public class PropertyPanelWithButtons extends PropertyPanel {

	PropertyPanelWithButtons(UtilityButton utilityButton, String propertyName, String defaultValue) {
		super(propertyName, defaultValue);

		add(utilityButton.decreaseButton(_propertyName));
		add(utilityButton.increaseButton(_propertyName));
	}
}
