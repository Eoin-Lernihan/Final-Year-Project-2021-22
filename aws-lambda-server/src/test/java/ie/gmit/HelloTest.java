package ie.gmit;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.Tournament;

public class HelloTest {

	@Test
	public void test() {
		Tournament lambda = new Tournament();
		
		System.out.println("Return Val = " + String.valueOf(10));
		System.out.println("Return Val = " + ("V=" + 10));
		//lambda.myHandler(10, context);
		//fail("Not yet implemented");
	}

}
