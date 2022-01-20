package ie.gmit;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.TournamentController;

public class HelloTest {

	@Test
	public void test() {
		TournamentController lambda = new TournamentController();
		
		System.out.println("Return Val = " + String.valueOf(10));
		System.out.println("Return Val = " + ("V=" + 10));
		//lambda.myHandler(10, context);
		//fail("Not yet implemented");
	}

}
