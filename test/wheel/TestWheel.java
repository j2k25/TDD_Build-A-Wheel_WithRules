package wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestWheel {
	WheelRules wheelRules;
	Wheel wheel;

	@BeforeEach
	void setUp() {
		wheelRules = spy(WheelRules.class);
		wheel = wheelRules.createWheel();
	}

	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void wheelRulesCreatesWheelDiameter4() {
		assertEquals(4, wheel.getDiameter());
	}

	@Test
	void wheelDiameterChangedWhenWheelRuleApproves() {
		when(wheelRules.isOKToChangeWheelDiameter(5)).thenReturn(true);

		wheel.setDiameter(5);

		assertEquals(5, wheel.getDiameter());
	}

	@Test
	void wheelDiameterDoesNotChangedWhenWheelRuleDisapproves() {
		when(wheelRules.isOKToChangeWheelDiameter(3)).thenReturn(false);

		wheel.setDiameter(3);

		assertEquals(4, wheel.getDiameter());
	}

	@Test
	void propagateExceptionWhenWheelDiameterCantBeChanged() {
		when(wheelRules.isOKToChangeWheelDiameter(3)).thenThrow(
			new IllegalArgumentException("invalid wheel diameter")
		);

		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.setDiameter(3));
		assertEquals("invalid wheel diameter", thrownException.getMessage());
		assertEquals(4, wheel.getDiameter());
	}
}
