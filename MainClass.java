package edu.ilstu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class drives the morse code program.
 */
public class MainClass {
    private static MorseTree ansKey;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        buildTree();

        while(true) {
            System.out.print("Please enter encoded message or stop to exit: ");
            Scanner input = new Scanner(System.in);

            String s = input.nextLine();
            if (stopCheck(s)) System.exit(0);

            while (verify(s)) {
                System.out.println("Please enter a valid Morse Code!");
                System.out.println("Please enter encoded message or stop to exit: ");
                input = new Scanner(System.in);
                s = input.nextLine();

                if (stopCheck(s)) System.exit(0);
            }

            System.out.println("The decoded message is: " + decode(s));
        }
    }

    /**
     * Checks is the string is equal to stop.
     *
     * @param s - the string to check
     * @return boolean - true it is stop false otherwise
     */
    private static boolean stopCheck(String s) {
        return s.equals("stop");
    }

    /**
     * Verifies if the given string contains only morse code characters
     *
     * @param s - the morse code to check
     * @return boolean - true if is morse code false otherwise
     */
    private static boolean verify(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(!(s.charAt(i) == '•' || s.charAt(i) == '-' || s.charAt(i) == ' ')) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds the tree using the file that contains the letters and their codes.
     * code.txt is the target file.
     */
    private static void buildTree() {
        ansKey = new MorseTree();

        // Read code.txt into a tree
        try (Scanner input = new Scanner(new File("code.txt"))) {
            String arr;
            while(input.hasNextLine()) {
                arr = input.nextLine();
                ansKey.addChar(arr.charAt(0), arr.substring(2));
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }

    /**
     * Decodes the message entered by the user
     * @return
     */
    private static String decode(String code) {
        return ansKey.decode(code);
    }
}
