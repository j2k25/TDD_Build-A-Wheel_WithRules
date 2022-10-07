package wheel.UI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WheelGraphicPanel extends JPanel {
	double _wheelDiameter;
	double _hubDiameter;
	int _numberOfSpokes;

	WheelGraphicPanel() {
		setBackground(null);
		setLayout(null);
	}

	void setWheelProperties(double wheelDiameter, double hubDiameter, int numberOfSpokes) {
		double graphicScale = 25;
		_wheelDiameter = wheelDiameter * graphicScale;
		_hubDiameter = hubDiameter * graphicScale;
		_numberOfSpokes = numberOfSpokes;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		drawTheSpokes(g2d);
		drawTheHub(g2d);
		drawTheWheel(g2d);
	}

	private void drawTheSpokes(Graphics2D g2d) {
		List<double[]> coordinates = generateCoordinates(_numberOfSpokes);

		g2d.setColor(Color.lightGray);
		coordinates.forEach(coordinate -> {
			int[] startCoordinate = translateCoordinate(coordinate[0] * _hubDiameter, coordinate[1] * _hubDiameter);
			int[] endCoordinate = translateCoordinate(coordinate[0] * _wheelDiameter, coordinate[1] * _wheelDiameter);
			g2d.drawLine(startCoordinate[0], startCoordinate[1], endCoordinate[0], endCoordinate[1]);
		});
	}

	private void drawTheWheel(Graphics2D g2d) {
		int wheelDiameterInt = (int) Math.round(_wheelDiameter);
		int[] wheelCoordinate = translateCoordinate(-wheelDiameterInt, wheelDiameterInt);

		g2d.setStroke(new BasicStroke(6f));
		g2d.setColor(Color.ORANGE);
		g2d.drawOval(wheelCoordinate[0], wheelCoordinate[1], wheelDiameterInt * 2, wheelDiameterInt * 2);
	}

	private void drawTheHub(Graphics2D g2d) {
		int hubDiameterInt = (int) Math.round(_hubDiameter);
		int[] hubCoordinate = translateCoordinate(-hubDiameterInt, hubDiameterInt);

		g2d.setStroke(new BasicStroke(3f));
		g2d.setColor(Color.BLACK);
		g2d.drawOval(hubCoordinate[0], hubCoordinate[1], hubDiameterInt * 2, hubDiameterInt * 2);
	}

	private List<double[]> generateCoordinates(int numberOfSpokes) {
		return IntStream.range(0, numberOfSpokes)
			.mapToObj(i -> new double[]{
				Math.cos(2 * Math.PI * i / numberOfSpokes),
				Math.sin(2 * Math.PI * i / numberOfSpokes)
			})
			.collect(Collectors.toList());
	}

	private int[] translateCoordinate(double oldX, double oldY) {
		int newX = (int) Math.round(oldX + 200);
		int newY = (int) Math.round(-oldY + 200);
		return new int[]{newX, newY};
	}
}
