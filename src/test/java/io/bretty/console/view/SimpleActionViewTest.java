package io.bretty.console.view;

import java.util.Scanner;

public class SimpleActionViewTest extends ActionView {

    public SimpleActionViewTest() {
        super("Running Simple Action", "Simple Action");
    }

    @Override
    public void executeCustomAction() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input a line");
        String input = keyboard.nextLine();
        System.out.println("The line you input is \"" + input + "\"");
    }
}
