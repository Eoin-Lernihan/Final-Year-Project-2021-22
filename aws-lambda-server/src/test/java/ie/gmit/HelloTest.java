package ie.gmit;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.controller.BaseController;
import ie.gmit.sw.controller.UserController;

public class HelloTest {

	@Test
	public void test() {
		BaseController lambda = new UserController();
		
		System.out.println("Return Val = " + String.valueOf(10));
		System.out.println("Return Val = " + ("V=" + 10));
		//lambda.myHandler(10, context);
		//fail("Not yet implemented");
	}

}
