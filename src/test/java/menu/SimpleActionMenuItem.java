package menu;

import java.util.Scanner;

public class SimpleActionMenuItem extends ActionMenuItem {

    public SimpleActionMenuItem() {
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
