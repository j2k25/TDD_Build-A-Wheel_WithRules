package wheel;

public interface WheelRules {
	default Wheel createWheel() {
		return new Wheel(this, 4, 1, 8);
	}

	boolean isOKToChangeWheelDiameter(double newDiameter);

	boolean isOKToChangeHubDiameter(double newDiameter);

	boolean isOKToChangeNumberOfSpokes(int newNumberOfSpokes);
}
