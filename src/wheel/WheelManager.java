package wheel;

import java.util.*;
import java.util.stream.Collectors;

public class WheelManager {
	private final DefaultRules wheelRules;

	public WheelManager() {
		wheelRules = new DefaultRules();
	}

	public String getPropertiesOfAWheel(Wheel wheel) {
		StringJoiner wheelProperties = new StringJoiner(", ");

		wheelProperties.add("wheelDiameter: " + wheel.getDiameter());
		wheelProperties.add("hubDiameter: " + wheel.getHub().getDiameter());
		wheelProperties.add("numberOfSpokes: " + wheel.getSpokes().size());

		return wheelProperties.toString();
	}

	public Wheel loadAWheelFromProperties(String wheelProperties) {
		String[] propertyArray = wheelProperties.split(", ");

		List<Double> properties = Arrays.stream(propertyArray)
			.map(property -> property.substring(property.indexOf(": ") + 1))
			.map(Double::valueOf)
			.collect(Collectors.toList());

		double wheelDiameter = properties.get(0);
		double hubDiameter = properties.get(1);
		int numberOfSpokes = properties.get(2).intValue();

		return wheelRules.createCustomWheel(wheelDiameter, hubDiameter, numberOfSpokes);
	}

	public Wheel loadAPresetWheel() {
		return wheelRules.createWheel();
	}

	public List<String> getDesignNameFromList(List<String> listOfDesign) {
		return listOfDesign
			.stream()
			.map(design -> design.substring(0, design.indexOf("===")))
			.collect(Collectors.toList());
	}

	public List<String> getDesignPropertiesFromList(List<String> listOfDesign) {
		return listOfDesign
			.stream()
			.map(design -> design.substring(design.indexOf("===") + 3))
			.collect(Collectors.toList());
	}
}
