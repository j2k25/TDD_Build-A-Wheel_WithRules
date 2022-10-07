package wheel.UI;

import javax.swing.*;
import java.awt.*;

public class PropertyPanel extends JPanel {
	final JLabel propertyLabel;
	final String _propertyName;

	PropertyPanel(String propertyName, String defaultValue) {
		setLayout(null);
		setBackground(new Color(250, 250, 250));
		_propertyName = propertyName;

		propertyLabel = new JLabel(String.format("%s: %s", propertyName, defaultValue));
		propertyLabel.setBounds(10, 0, 150, 40);
		propertyLabel.setForeground(new Color(41, 61, 102));
		add(propertyLabel);
	}

	public void setPosition(int x, int y) {
		setBounds(x, y, 300, 40);
	}

	public void setValue(double value) {
		if (_propertyName.equals("Spokes")) {
			propertyLabel.setText(String.format("%s: %d", _propertyName, (int) value));
		} else {
			propertyLabel.setText(String.format("%s: %.1f", _propertyName, value));
		}
	}
}
