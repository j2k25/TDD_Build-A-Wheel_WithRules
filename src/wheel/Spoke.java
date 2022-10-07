package wheel;

public class Spoke {
	private double _spokeLength;

	Spoke(double spokeLength) {
		_spokeLength = spokeLength;
	}

	public double getLength() {
		return _spokeLength;
	}

	public void setLength(double spokeLength) {
		_spokeLength = spokeLength;
	}
}
