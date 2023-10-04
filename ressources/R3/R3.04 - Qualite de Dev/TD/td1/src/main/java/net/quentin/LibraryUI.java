package net.quentin;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryUI {

    private ArrayList<Library> libraries;

    public LibraryUI(ArrayList<Library> libraries) {
        this.libraries = libraries;
    }

    public boolean mainChoice() {
        int answer = UserInput.displayIntChoice("What do you want ? : ",
                "Create Library", "Access to a library", "exit");

        if (answer == 0) {
            createLibrary();
            return true;
        } else if (answer == 1) {
            System.out.println("do another thing");
            return true;
        } else {
            return false;
        }
    }

    public void createLibrary() {
        System.out.println("Choose the name of the library : ");
        String name = UserInput.sc.next();
        System.out.println("Choose the address of the library : ");
        String address = UserInput.sc.next();
        libraries.add(new Library(name, address));
    }

    public void accessToALibrary() {

    }

    public void launch() {
        while (true) {
            if (!mainChoice()) {
                break;
            }
        }
    }
}
