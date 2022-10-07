package wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestHub {
	WheelRules wheelRules;
	Wheel wheel;

	@BeforeEach
	void setup() {
		wheelRules = spy(WheelRules.class);
		wheel = wheelRules.createWheel();
	}

	@Test
	void wheelCreatedByWheelRulesHasAHubWithDiameter1() {
		assertEquals(1, wheel.getHub().getDiameter());
	}

	@Test
	void hubDiameterChangesWhenTheWheelRulesApproves() {
		when(wheelRules.isOKToChangeHubDiameter(0.5)).thenReturn(true);

		wheel.getHub().setDiameter(0.5);

		assertEquals(0.5, wheel.getHub().getDiameter());
	}

	@Test
	void hubDiameterDoesNotChangeWhenWheelRulesDisapproves() {
		when(wheelRules.isOKToChangeHubDiameter(2)).thenReturn(false);

		wheel.getHub().setDiameter(2);

		assertEquals(1, wheel.getHub().getDiameter());
	}

	@Test
	void propagateExceptionWhenHubDiameterCantBeChanged() {
		when(wheelRules.isOKToChangeHubDiameter(2)).thenThrow(
			new IllegalArgumentException("invalid hub diameter")
		);

		var thrownException
			= assertThrows(IllegalArgumentException.class, () -> wheel.getHub().setDiameter(2));
		assertEquals("invalid hub diameter", thrownException.getMessage());
		assertEquals(1, wheel.getHub().getDiameter());
	}
}
