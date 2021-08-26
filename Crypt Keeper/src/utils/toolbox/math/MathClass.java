package utils.toolbox.math;

/**
 * This class is a mashup of common mathematical methods that are needed
 * throughout the program.
 * 
 * @author Christian Babin
 * @version 1.0.2
 * @since 1.0.0
 */
public class MathClass {
	
	/**
	 * This method generates a random number between two integers (inclusively).
	 * 
	 * @param min - The lowest number that should be considered for the final result
	 * @param max - The highest number that should be considered for the final
	 *            result
	 * @return A random integer between min and max (inclusive)
	 */
	public static int genRand(int min, int max) {

		// Don't let min be greater than max
		if (min > max) {
			throw new IllegalArgumentException("Error Code: RGMSM6");
		}

		// A random number between 0 and [max - min] (inclusive)
		double randDouble = Math.random() * (max - min + 1);

		// Casts that random number to an int for return
		int randInt = (int) randDouble;

		// Returns a random int between min and max (inclusive)
		return randInt + min;

	}

}
