package wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestWheelRules {
	WheelRules wheelRules;
	Wheel wheel;

	@BeforeEach
	void setup() {
		wheelRules = new DefaultRules();
		wheel = wheelRules.createWheel();
	}

	@Test
	void atLeast8SpokesAreRequired() {
		assertThrows(IllegalArgumentException.class, () -> wheel.removeASpoke());
	}

	@Test
	void NotAllowSpokeAdditionIfThereAreTooManySpokesToFit() {
		IntStream.range(0, 9991).forEach(i -> wheel.addASpoke());

		assertThrows(IllegalArgumentException.class, () -> wheel.addASpoke());
	}

	@Test
	void allowsSpokeRemovalIfThereAreAtLeast8() {
		wheel.addASpoke();

		assertEquals(9, wheel.getSpokes().size());
		assertTrue(wheelRules.isOKToChangeNumberOfSpokes(8));

		wheel.removeASpoke();

		assertEquals(8, wheel.getSpokes().size());
	}

	@Test
	void notAllowWheelDiameterToBeSmallerThan4xHubDiameter() {
		assertEquals(4, wheel.getDiameter());
		assertThrows(IllegalArgumentException.class, () -> wheel.setDiameter(3.5));
	}

	@Test
	void allowChangingWheelDiameterAsLongAsBiggerThan4xHubDiameter() {
		assertEquals(1, wheel.getHub().getDiameter());
		assertTrue(wheelRules.isOKToChangeWheelDiameter(4.5));
	}

	@Test
	void notAllowHubDiameterToBeBiggerThanQuarterOfWheelDiameter() {
		assertEquals(4, wheel.getDiameter());
		assertThrows(IllegalArgumentException.class, () -> wheel.getHub().setDiameter(2));
	}

	@Test
	void notAllowHubDiameterToBe0() {
		assertThrows(IllegalArgumentException.class, () -> wheel.getHub().setDiameter(0));
	}

	@Test
	void allowChangingHubDiameterAsLongAsSmallerThanQuarterOfWheelDiameter() {
		assertEquals(4, wheel.getDiameter());
		assertTrue(wheelRules.isOKToChangeHubDiameter(0.5));

		wheel.getHub().setDiameter(0.5);

		assertEquals(0.5, wheel.getHub().getDiameter());
	}

	@Test
	void propagateExceptionWhenWheelDiameterCantBeChanged() {
		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.setDiameter(3));

		assertEquals("Invalid wheel diameter", thrownException.getMessage());
		assertEquals(4, wheel.getDiameter());
	}

	@Test
	void propagateExceptionWhenHubDiameterCantBeChanged() {
		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.getHub().setDiameter(2));

		assertEquals("Invalid hub diameter", thrownException.getMessage());
		assertEquals(1, wheel.getHub().getDiameter());
	}

	@Test
	void propagateExceptionWhenNumberOfSpokesCantBeChanged() {
		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.removeASpoke());

		assertEquals("Invalid number of spokes", thrownException.getMessage());
		assertEquals(8, wheel.getSpokes().size());
	}
}
