package wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWheelManager {
	WheelManager wheelManager;
	DefaultRules presetRules;
	List<String> listOfDesign;

	@BeforeEach
	void setup() {
		wheelManager = new WheelManager();
		presetRules = new DefaultRules();
		listOfDesign = new ArrayList<>();
		listOfDesign.add("my first design===wheelDiameter: 4.0, hubDiameter: 1.0, numberOfSpokes: 8");
	}

	@Test
	void savedWheelHas3Properties() {
		Wheel wheel = presetRules.createWheel();

		String savedWheel = wheelManager.getPropertiesOfAWheel(wheel);

		assertEquals("wheelDiameter: 4.0, hubDiameter: 1.0, numberOfSpokes: 8", savedWheel);
	}

	@Test
	void saveACustomWheel() {
		Wheel customWheel = presetRules.createWheel();
		customWheel.setDiameter(10);
		customWheel.addASpoke();

		String savedCustomWheel = wheelManager.getPropertiesOfAWheel(customWheel);

		assertEquals("wheelDiameter: 10.0, hubDiameter: 1.0, numberOfSpokes: 9", savedCustomWheel);
	}

	@Test
	void loadAPresetRule() {
		Wheel presetWheel = wheelManager.loadAPresetWheel();

		assertEquals(4, presetWheel.getDiameter());
		assertEquals(1, presetWheel.getHub().getDiameter());
		assertEquals(8, presetWheel.getSpokes().size());
	}

	@Test
	void loadACustomWheel() {
		String savedCustomWheel = "wheelDiameter: 10.0, hubDiameter: 2.0, numberOfSpokes: 20";

		Wheel wheel = wheelManager.loadAWheelFromProperties(savedCustomWheel);

		assertEquals(10, wheel.getDiameter());
		assertEquals(2, wheel.getHub().getDiameter());
		assertEquals(20, wheel.getSpokes().size());
	}

	@Test
	void loadAnotherCustomWheel() {
		String savedCustomWheel = "wheelDiameter: 40.0, hubDiameter: 5.0, numberOfSpokes: 100";

		Wheel anotherWheel = wheelManager.loadAWheelFromProperties(savedCustomWheel);

		assertEquals(40.0, anotherWheel.getDiameter());
		assertEquals(5.0, anotherWheel.getHub().getDiameter());
		assertEquals(100, anotherWheel.getSpokes().size());
	}

	@Test
	void extractDesignNameFromTheDesignRecord() {
		String nameOfTheFirstDesign = wheelManager.getDesignNameFromList(listOfDesign).get(0);

		assertEquals("my first design", nameOfTheFirstDesign);
	}

	@Test
	void extractDesignPropertyFromDesignRecord() {
		String propertyOfTheFirstDesign = wheelManager.getDesignPropertiesFromList(listOfDesign).get(0);

		assertEquals("wheelDiameter: 4.0, hubDiameter: 1.0, numberOfSpokes: 8", propertyOfTheFirstDesign);
	}
}


