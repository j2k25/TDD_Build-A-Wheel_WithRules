package wheel;

public class DefaultRules implements WheelRules {
	Wheel wheel;

	DefaultRules() {
		wheel = new Wheel(this, 4, 1, 8);
	}

	public Wheel createWheel() {
		wheel = new Wheel(this, 4, 1, 8);
		return wheel;
	}

	public Wheel createCustomWheel(double wheelDiameter, double hubDiameter, int numberOfSpokes) {
		wheel = new Wheel(this, wheelDiameter, hubDiameter, numberOfSpokes);
		return wheel;
	}

	@Override
	public boolean isOKToChangeWheelDiameter(double newDiameter) {
		if (newDiameter >= wheel.getHub().getDiameter() * 4) {
			return true;
		}
		throw new IllegalArgumentException("Invalid wheel diameter");
	}

	@Override
	public boolean isOKToChangeHubDiameter(double newDiameter) {
		if (newDiameter > 0 && newDiameter * 4 <= wheel.getDiameter()) {
			return true;
		}
		throw new IllegalArgumentException("Invalid hub diameter");
	}

	@Override
	public boolean isOKToChangeNumberOfSpokes(int newNumberOfSpokes) {
		if (newNumberOfSpokes >= 8 && newNumberOfSpokes < 10000) {
			return true;
		}
		throw new IllegalArgumentException("Invalid number of spokes");
	}
}
