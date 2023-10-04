package net.quentin;

import java.util.Scanner;

public abstract class UserInput {

    public static Scanner sc = new Scanner(System.in);

    public static int displayIntChoice(String request, String... choices) {
        int cpt = 0;
        for (String choice : choices) {
            System.out.println(cpt + " - " + choice);
            cpt++;
        }
        System.out.println(request);
        try {
            return sc.nextInt();
        } catch (Exception e) {
            throw e;
        }
    }
}
