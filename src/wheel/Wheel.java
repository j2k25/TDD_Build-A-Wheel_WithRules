package wheel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Wheel {
	private final WheelRules wheelRules;
	private final Hub hub;
	private final List<Spoke> spokes;
	private double diameter;

	public Wheel(WheelRules theWheelRules, double theDiameter, double hubDiameter, int numberOfSpokes) {
		wheelRules = theWheelRules;
		diameter = theDiameter;

		hub = createHub(wheelRules, hubDiameter);

		spokes = createSpokes(numberOfSpokes);
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double newDiameter) {
		if (wheelRules.isOKToChangeWheelDiameter(newDiameter)) {
			diameter = newDiameter;
			spokes.forEach(spoke -> spoke.setLength(computeSpokeLength()));
		}
	}

	private List<Spoke> createSpokes(int numberOfSpokes) {
		return IntStream.range(0, numberOfSpokes)
			.mapToObj(ignore ->
				new Spoke(computeSpokeLength()))
			.collect(Collectors.toList());
	}

	public List<Spoke> getSpokes() {
		return spokes;
	}

	public double computeSpokeLength() {
		return diameter - getHub().getDiameter();
	}

	public void addASpoke() {
		if (wheelRules.isOKToChangeNumberOfSpokes(spokes.size() + 1)) {
			spokes.add(new Spoke(computeSpokeLength()));
		}
	}

	public void removeASpoke() {
		if (wheelRules.isOKToChangeNumberOfSpokes(spokes.size() - 1)) {
			spokes.remove(0);
		}
	}

	Hub createHub(WheelRules wheelRules, double hubDiameter) {
		return new Hub() {
			private double _diameter = hubDiameter;

			@Override
			public double getDiameter() {
				return _diameter;
			}

			@Override
			public void setDiameter(double diameter1) {
				if (wheelRules.isOKToChangeHubDiameter(diameter1)) {
					_diameter = diameter1;
					getSpokes().forEach(spoke -> spoke.setLength(computeSpokeLength()));
				}
			}
		};
	}

	public Hub getHub() {
		return hub;
	}
}

