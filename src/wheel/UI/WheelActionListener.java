package wheel.UI;

import wheel.Wheel;
import wheel.WheelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class WheelActionListener implements ActionListener {
	private final double changeDerivative = 0.1;
	private final WheelManager wheelManager;
	private AppPanel appPanel;
	private Wheel wheel;
	private WheelGraphicPanel wheelGraphicPanel;

	WheelActionListener() {
		wheelManager = new WheelManager();
		wheel = wheelManager.loadAPresetWheel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
				case "Wheel diameter increase" -> handleIncreaseWheelDiameter();
				case "Wheel diameter decrease" -> handleDecreaseWheelDiameter();
				case "Hub diameter increase" -> handleIncreaseHubDiameter();
				case "Hub diameter decrease" -> handleDecreaseHubDiameter();
				case "Spokes increase" -> handleIncreaseSpokes();
				case "Spokes decrease" -> handleDecreaseSpokes();
				case "Save a wheel" -> handleSaveAWheel();
				case "Load a wheel" -> handleLoadAWheel();
				case "Reset wheel" -> handleReset();
			}
		} catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(appPanel, exception.getMessage());
		}

		wheelGraphicPanel.setWheelProperties(
			wheel.getDiameter(),
			wheel.getHub().getDiameter(),
			wheel.getSpokes().size()
		);

		wheelGraphicPanel.repaint();
		appPanel.revalidatePropertyPanels(wheel);
	}

	private void handleSaveAWheel() {
		String nameOfTheDesign = JOptionPane.showInputDialog(
			appPanel,
			"Please enter a name for the design"
		);

		if (nameOfTheDesign != null) {
			if (nameOfTheDesign.trim().equals("")) {
				nameOfTheDesign = Long.toString(System.currentTimeMillis());
			}

			String designProperties = wheelManager.getPropertiesOfAWheel(wheel);
			MemoryManager.saveDesignToMemory(designProperties, nameOfTheDesign);
		}
	}

	private void handleLoadAWheel() {
		try {
			List<String> listOfSavedDesign = MemoryManager.readDesignsFromMemory();

			if (listOfSavedDesign.size() == 0) {
				JOptionPane.showMessageDialog(appPanel, "No saved design found");
			} else {
				List<String> listOfDesignName = wheelManager.getDesignNameFromList(listOfSavedDesign);
				List<String> listOfDesignProperty = wheelManager.getDesignPropertiesFromList(listOfSavedDesign);

				String selectedDesignName = getUserSelectionFromUI(listOfDesignName);

				int selectedDesignIndex = listOfDesignName.indexOf(selectedDesignName);
				if (selectedDesignIndex != -1) {
					String selectedDesignProperties = listOfDesignProperty.get(selectedDesignIndex);
					wheel = wheelManager.loadAWheelFromProperties(selectedDesignProperties);
				}
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(appPanel, "No saved design found");
		}
	}

	private void handleReset() {
		wheel = wheelManager.loadAPresetWheel();
	}

	private void handleIncreaseWheelDiameter() {
		double newWheelDiameter = roundDouble(wheel.getDiameter() + changeDerivative);
		wheel.setDiameter(newWheelDiameter);
	}

	private void handleDecreaseWheelDiameter() {
		double newWheelDiameter = roundDouble(wheel.getDiameter() - changeDerivative);
		wheel.setDiameter(newWheelDiameter);
	}

	private void handleIncreaseHubDiameter() {
		double newHubDiameter = roundDouble(wheel.getHub().getDiameter() + changeDerivative);
		wheel.getHub().setDiameter(newHubDiameter);
	}

	private void handleDecreaseHubDiameter() {
		double newHubDiameter = roundDouble(wheel.getHub().getDiameter() - changeDerivative);
		wheel.getHub().setDiameter(newHubDiameter);
	}

	private void handleIncreaseSpokes() {
		wheel.addASpoke();
	}

	private void handleDecreaseSpokes() {
		wheel.removeASpoke();
	}

	private String getUserSelectionFromUI(List<String> listOfDesignName) {
		return (String) JOptionPane.showInputDialog(
			null,
			"Please select a design",
			"Load a design",
			JOptionPane.PLAIN_MESSAGE,
			null,
			listOfDesignName.toArray(),
			listOfDesignName.get(0)
		);
	}

	private double roundDouble(double value) {
		BigDecimal bigDecimal = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
		return bigDecimal.doubleValue();
	}

	public void setWheelGraphicPanel(WheelGraphicPanel graphicPanel) {
		wheelGraphicPanel = graphicPanel;
	}

	public void setAppPanel(AppPanel theAppPanel) {
		appPanel = theAppPanel;
	}
}
