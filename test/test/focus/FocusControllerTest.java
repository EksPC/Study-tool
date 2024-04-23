package test.focus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.focus.FocusController;

class FocusControllerTest {

	@Test
	void testBindElements() {
		FocusController controller = new FocusController(null);
		assertNotEquals(controller.getUserInterface(), null);
	}

}
