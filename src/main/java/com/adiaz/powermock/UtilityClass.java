package com.adiaz.powermock;

/**
 * Created by toni on 20/10/2017.
 */
public class UtilityClass {
	static int staticMethod(long value) {
		// Some complex logic is done here...
		throw new RuntimeException("I dont want to be executed. I will anyway be mocked out.");
	}
}
