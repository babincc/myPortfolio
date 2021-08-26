package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is used to store various tools that the program needs to use.
 * Since these tools are used by multiple classes, they are stored in this
 * common file rather than in each files that needs them.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Tools {

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
			throw new IllegalArgumentException("ERROR: min must be smaller than max!");
		}

		// A random number between 0 and [max - min] (inclusive)
		double randDouble = Math.random() * (max - min + 1);

		// Casts that random number to an int for return
		int randInt = (int) randDouble;

		// Returns a random int between min and max (inclusive)
		return randInt + min;

	}
	
	/**
     * This method generates a random number between two decimals (inclusively).
     *
     * @param min - The lowest number that should be considered for the final result
     * @param max - The highest number that should be considered for the final
     *            result
     * @return A random decimal between min and max (inclusive)
     */
    public static double genRand(double min, double max) {

        // Don't let min be greater than max
        if (min > max) {
            throw new IllegalArgumentException("ERROR: min must be smaller than max!");
        }

        // A random number between 0 and [max - min] (inclusive)
        double randDouble = Math.random() * (max - min + 1);

        // Returns a random int between min and max (inclusive)
        return randDouble + min;

    }
	
	/**
	 * This method generates random numbers between two integers (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @param canRepeat  - Tells whether or not a number can be in the list twice
	 * @return A list of random integers between min and max (inclusive)
	 */
	public static int[] genRand(int min, int max, int numOutputs, boolean canRepeat) {

		// If there can be no repeats but there aren't enough numbers in the range to not repeat
		if(!canRepeat && numOutputs > (max - min)+1) {
			throw new IllegalArgumentException("ERROR: The range is not large enough to have no repeats!");
		}
		
		int[] randNums = new int[numOutputs];
		
		for(int i = 0; i < numOutputs; i++) {
			randNums[i] = -1;
		}
		
		int repCount = 0; //  number of times we need to repeat a call because of recurring nums
		
		// This loop fills the list with random numbers
		for(int i = 0; i < numOutputs; i++) {
			int randNum = genRand(min, max);
			
			// make sure there is no repetition if it is not allowed
			if(!canRepeat) {
				boolean mustRestart = false;
				for(int num : randNums) {
					mustRestart = false;
					
					if(randNum == num) {

						// if we repeat too many times, force a unique number to be picked
						if(repCount == 20) {
							randNum = uniqueNumFinder(randNums, min);
							repCount = 0;
							mustRestart = false;
							break;
						}
						
						i--;
						repCount++;
						mustRestart = true;
						break;
					}
				}
				
				if(!mustRestart) {
					repCount = 0;
				}
			}
			
			if(repCount > 0) {
				continue;
			}
			
			randNums[i] = randNum;
		}
		
		return randNums;

	}
	
	/**
	 * This method generates random numbers between two integers (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat. It does all of this by calling to
	 * the genRand(int, int, int, boolean) method with the boolean automatically set
	 * to true. If the user does not specify if a number can or can't repeat, it is
	 * automatically set to be able to have repeating numbers.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @return A list of random integers between min and max (inclusive)
	 */
	public static int[] genRand(int min, int max, int numOutputs) {
		
		return genRand(min, max, numOutputs, true);
		
	}
	
	/**
	 * This method recursively finds a number that is not in the list already.
	 * 
	 * @param compareList - The list of numbers generated so far
	 * @param start - The number to start comparing to the ones in the list
	 * @return A unique number not already in the list
	 */
	private static int uniqueNumFinder(int[] compareList, int start) {
		
		int uniqueNum = -1;
		
		for(int num : compareList) {
			if(num == start) {
				uniqueNum = uniqueNumFinder(compareList, start+1);
			} else {
				uniqueNum = start;
			}
		}
		
		return uniqueNum;
		
	}
	
	/**
	 * This method generates random numbers between two decimals (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @param canRepeat  - Tells whether or not a number can be in the list twice
	 * @return A list of random doubles between min and max (inclusive)
	 */
	public static double[] genRand(double min, double max, int numOutputs, boolean canRepeat) {

		// If there can be no repeats but there aren't enough numbers in the range to not repeat
		if(!canRepeat && numOutputs > (max - min)+1) {
			throw new IllegalArgumentException("ERROR: The range is not large enough to have no repeats!");
		}
		
		double[] randNums = new double[numOutputs];
		
		for(int i = 0; i < numOutputs; i++) {
			randNums[i] = -1;
		}
		
		int repCount = 0; //  number of times we need to repeat a call because of recurring nums
		
		// This loop fills the list with random numbers
		for(int i = 0; i < numOutputs; i++) {
			double randNum = genRand(min, max);
			
			// make sure there is no repetition if it is not allowed
			if(!canRepeat) {
				boolean mustRestart = false;
				for(double num : randNums) {
					mustRestart = false;
					
					if(randNum == num) {

						// if we repeat too many times, force a unique number to be picked
						if(repCount == 20) {
							randNum = uniqueNumFinder(randNums, min);
							repCount = 0;
							mustRestart = false;
							break;
						}
						
						i--;
						repCount++;
						mustRestart = true;
						break;
					}
				}
				
				if(!mustRestart) {
					repCount = 0;
				}
			}
			
			if(repCount > 0) {
				continue;
			}
			
			randNums[i] = randNum;
		}
		
		return randNums;

	}
	
	/**
	 * This method generates random numbers between two decimals (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat. It does all of this by calling to
	 * the genRand(double, double, int, boolean) method with the boolean automatically set
	 * to true. If the user does not specify if a number can or can't repeat, it is
	 * automatically set to be able to have repeating numbers.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @return A list of random doubles between min and max (inclusive)
	 */
	public static double[] genRand(double min, double max, int numOutputs) {
		
		return genRand(min, max, numOutputs, true);
		
	}
	
	/**
	 * This method recursively finds a number that is not in the list already.
	 * 
	 * @param compareList - The list of numbers generated so far
	 * @param start - The number to start comparing to the ones in the list
	 * @return A unique number not already in the list
	 */
	private static double uniqueNumFinder(double[] compareList, double start) {
		
		double uniqueNum = -1.0;
		
		for(double num : compareList) {
			if(num == start) {
				uniqueNum = uniqueNumFinder(compareList, start+1);
			} else {
				uniqueNum = start;
			}
		}
		
		return uniqueNum;
		
	}
	
	/**
     * This method rounds a number to a specified number of places.
     *
     * @param originalNum   - The number submitted to be rounded
     * @param places        - The number of digits past the decimal desired in the final result
     * @return The rounded number
     */
	public static double round(Double originalNum, int places) {

		String ogNum = "" + originalNum;
		String wholeNum = ogNum.substring(0, ogNum.indexOf("."));
		String decimal = ogNum.substring(ogNum.indexOf(".") + 1);

		if (decimal.length() <= places) {
			return originalNum;
		}

		decimal = decimal.substring(0, places);

		// make a string of zeros to see compare with later
		String zeros = "";
		for (int i = 0; i < places; i++) {
			zeros += "0";
		}

		// if the places after the decimal are nothing but zeros, just return the whole
		// number
		if (decimal.equals(zeros)) {
			ogNum = wholeNum + ".0";
		} else {
			ogNum = wholeNum + "." + decimal;
		}

		return Double.parseDouble(ogNum);

	}

	/**
	 * This method writes the generated data entries to a file.
	 * 
	 * @param allData - The generated data entries
	 * @param fileName - The name of the file being written
	 */
	public static void writeLocFile(String allData, String fileName) {
		
		// make the file so it can be written to
		File data_file = new File(fileName);
		FileOutputStream oFile = null;
		try {
			data_file.createNewFile();
			oFile = new FileOutputStream(data_file, false);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File could not be found to write to!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: Could not create file!");
			e.printStackTrace();
		}
		
		// convert the string into a writable format
		byte[] bytesArray = allData.getBytes();

		// write to the file
		try {
			oFile.write(bytesArray);
			oFile.flush();
		} catch (IOException e) {
			System.out.println("ERROR: Could not write to file!");
			e.printStackTrace();
		}
	
	}
	
}
