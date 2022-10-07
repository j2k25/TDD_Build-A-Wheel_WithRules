package wheel.UI;

import javax.swing.*;

public class Driver extends JFrame {

	Driver() {
		setSize(800, 400);
		setTitle("Wheel");
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		AppPanel appPanel = new AppPanel(new WheelActionListener());
		setContentPane(appPanel);
	}

	public static void main(String[] args) {
		new Driver();
	}
}
