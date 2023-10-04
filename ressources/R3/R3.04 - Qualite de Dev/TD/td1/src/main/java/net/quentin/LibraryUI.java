package net.quentin;

import java.util.Scanner;

public class LibraryUI {

    public boolean mainChoice() {
        int answer = UserInput.displayIntChoice("What do you want ? : ",
                "Create Library", "Access to a library", "exit");

        if (answer == 0) {
            System.out.println("do something");
            return true;
        } else if (answer == 1) {
            System.out.println("do another thing");
            return true;
        } else {
            return false;
        }
    }

    public void launch() {
        while (true) {
            if (!mainChoice()) {
                break;
            }
        }
    }
}
