package wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestSpokes {
	WheelRules wheelRules;
	Wheel wheel;

	@BeforeEach
	void setup() {
		wheelRules = spy(WheelRules.class);
		wheel = wheelRules.createWheel();
	}

	@Test
	void theWheelCreatedByWheelRulesHas8Spokes() {
		assertEquals(8, wheel.getSpokes().size());
	}

	@Test
	void lengthOfSpokesAreCorrectedUponCreation() {
		assertEquals(3, wheel.getSpokes().get(0).getLength());
	}

	@Test
	void theSpokesLengthsAdjustWhenWheelDiameterChanges() {
		when(wheelRules.isOKToChangeWheelDiameter(5)).thenReturn(true);

		wheel.setDiameter(5);

		assertEquals(4, wheel.getSpokes().get(0).getLength());
	}

	@Test
	void theSpokesLengthAdjustsWhenHubDiameterChanges() {
		when(wheelRules.isOKToChangeHubDiameter(0.5)).thenReturn(true);

		wheel.getHub().setDiameter(0.5);

		assertEquals(3.5, wheel.getSpokes().get(0).getLength());
	}

	@Test
	void addASpokeToTheNumberOfSpokes() {
		when(wheelRules.isOKToChangeNumberOfSpokes(9)).thenReturn(true);

		wheel.addASpoke();

		assertEquals(9, wheel.getSpokes().size());
	}

	@Test
	void RemoveASpokeFromTheNumberOfSpokes() {
		when(wheelRules.isOKToChangeNumberOfSpokes(7)).thenReturn(true);

		wheel.removeASpoke();

		assertEquals(7, wheel.getSpokes().size());
	}

	@Test
	void cannotRemoveASpokeIfThereAreOnlyEight() {
		when(wheelRules.isOKToChangeNumberOfSpokes(7)).thenReturn(false);

		wheel.removeASpoke();

		assertEquals(8, wheel.getSpokes().size());
	}

	@Test
	void cannotAddASpokeIfAllSpokesCannotBeSpaced() {
		int numberOfSpokesThatMakesAllSpokesCannotBeSpaced = 9;
		when(wheelRules.isOKToChangeNumberOfSpokes(numberOfSpokesThatMakesAllSpokesCannotBeSpaced)).thenReturn(false);

		wheel.addASpoke();

		assertEquals(8, wheel.getSpokes().size());
	}

	@Test
	void propagateExceptionWhenNumberOfSpokesCantBeChanged() {
		when(wheelRules.isOKToChangeNumberOfSpokes(7)).thenThrow(
			new IllegalArgumentException("invalid number of spokes")
		);

		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.removeASpoke());
		assertEquals("invalid number of spokes", thrownException.getMessage());
		assertEquals(8, wheel.getSpokes().size());
	}
}
