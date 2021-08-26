package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h3>
 * CREDIT: This class takes heavily (almost an exact copy) from Yong Mook Kim
 * https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * </h3>
 *
 * <p>
 * This class is used to read in an unusually formatted .csv file.
 * </p>
 * 
 * @author Yong Mook Kim (https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/)
 * @author Christian Babin
 * @version 1.0.0
 */
public class CSV_Reader {
	
	private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    /**
	 * This method gets each line read and placed into a list. The lines get broken
	 * into their elements.
	 * 
	 * @param fileName - the name of the .csv file being read in
	 * @return A list of every line from the .csv file after it has been properly
	 *         separated.
	 */
    public static ArrayList<List<String>> read(String fileName) {

    	// make the file from the file name
        String csvFile = fileName;

        // make the scanner
        Scanner scanner = null;
		try {
			scanner = new Scanner(new File(csvFile));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: \"" + fileName + "\" could not be found!");
			e.printStackTrace();
		}
		
		// the list of each line of the .csv file
		ArrayList<List<String>> allLines = new ArrayList<>();
		
		// go through the file line by line
		scanner.nextLine(); // skip the header line
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            allLines.add(line);
        }
        
        scanner.close();
        
        return allLines;

    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
	
}
