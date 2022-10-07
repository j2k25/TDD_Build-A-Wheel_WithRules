package wheel.UI;

import javax.swing.*;

public class UtilityButton {
	private final WheelActionListener wheelActionListener;

	UtilityButton(WheelActionListener theWheelActionListener) {
		wheelActionListener = theWheelActionListener;
	}

	public JButton saveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(430, 300, 80, 50);
		saveButton.addActionListener(wheelActionListener);
		saveButton.setActionCommand("Save a wheel");
		return saveButton;
	}

	public JButton resetButton() {
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(650, 300, 80, 50);
		resetButton.addActionListener(wheelActionListener);
		resetButton.setActionCommand("Reset wheel");
		return resetButton;
	}

	public JButton loadButton() {
		JButton loadButton = new JButton("Load");
		loadButton.setBounds(540, 300, 80, 50);
		loadButton.addActionListener(wheelActionListener);
		loadButton.setActionCommand("Load a wheel");
		return loadButton;
	}

	public JButton decreaseButton(String propertyName) {
		JButton decreaseButton = new JButton("<");
		decreaseButton.setBounds(170, 5, 50, 30);
		decreaseButton.addActionListener(wheelActionListener);
		decreaseButton.setActionCommand(propertyName + " decrease");
		return decreaseButton;
	}

	public JButton increaseButton(String propertyName) {
		JButton increaseButton = new JButton(">");
		increaseButton.setBounds(230, 5, 50, 30);
		increaseButton.addActionListener(wheelActionListener);
		increaseButton.setActionCommand(propertyName + " increase");
		return increaseButton;
	}
}
