package main.utils.userInputFetcher;

import java.util.Scanner;

/**
 * This class creates a Singleton instance of the System.in scanner. This
 * way, we don't run into any issues that occur when using multiple scanners
 * on the System thread.
 *
 * @author Christian Babin
 * @version 1.0.0
 * @since 1.0.0
 */
class SingletonSysInScanner {
    private static SingletonSysInScanner instance = null;
    private final Scanner userIn;

    private SingletonSysInScanner() {
        userIn = new Scanner(System.in);
    }

    public static SingletonSysInScanner getInstance() {
        if(instance == null) {
            instance = new SingletonSysInScanner();
        }

        return instance;
    }

    public Scanner getUserIn() {
        return userIn;
    }
}
