package es.caser.spring.mvc.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		assertEquals("index", controller.sayHello(new ExtendedModelMap()));
	}
}
