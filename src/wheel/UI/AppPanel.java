package wheel.UI;

import wheel.Wheel;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
	private final UtilityButton utilityButton;
	private PropertyPanelWithButtons wheelPanel;
	private PropertyPanelWithButtons hubPanel;
	private PropertyPanelWithButtons spokesPanel;
	private PropertyPanel spokeLengthPanel;
	private PropertyPanel spokeAnglePanel;

	AppPanel(WheelActionListener wheelActionListener) {
		setBackground(new Color(153, 159, 173));
		setBounds(0, 0, 800, 400);
		setLayout(null);

		utilityButton = new UtilityButton(wheelActionListener);

		add(utilityButton.saveButton());
		add(utilityButton.loadButton());
		add(utilityButton.resetButton());

		addPropertyPanels();

		WheelGraphicPanel wheelGraphicPanel = createGraphicPanel();
		add(wheelGraphicPanel);

		wheelActionListener.setAppPanel(this);
		wheelActionListener.setWheelGraphicPanel(wheelGraphicPanel);
	}

	private void addPropertyPanels() {
		wheelPanel = new PropertyPanelWithButtons(utilityButton, "Wheel diameter", "4.0");
		wheelPanel.setPosition(430, 30);
		add(wheelPanel);

		hubPanel = new PropertyPanelWithButtons(utilityButton, "Hub diameter", "1.0");
		hubPanel.setPosition(430, 90);
		add(hubPanel);

		spokesPanel = new PropertyPanelWithButtons(utilityButton, "Spokes", "8");
		spokesPanel.setPosition(430, 150);
		add(spokesPanel);

		spokeLengthPanel = new PropertyPanel("Spoke Length", "3");
		spokeLengthPanel.setPosition(430, 190);
		add(spokeLengthPanel);

		spokeAnglePanel = new PropertyPanel("Spoke Angle", "45");
		spokeAnglePanel.setPosition(430, 230);
		add(spokeAnglePanel);
	}

	private WheelGraphicPanel createGraphicPanel() {
		WheelGraphicPanel wheelGraphicPanel = new WheelGraphicPanel();
		wheelGraphicPanel.setWheelProperties(
			4,
			1,
			8
		);
		wheelGraphicPanel.setBounds(0, 0, 400, 400);
		return wheelGraphicPanel;
	}

	public void revalidatePropertyPanels(Wheel wheel) {
		wheelPanel.setValue(wheel.getDiameter());
		hubPanel.setValue(wheel.getHub().getDiameter());
		spokesPanel.setValue(wheel.getSpokes().size());
		spokeLengthPanel.setValue(wheel.computeSpokeLength());
		spokeAnglePanel.setValue(360.0 / wheel.getSpokes().size());

		repaint();
	}
}
