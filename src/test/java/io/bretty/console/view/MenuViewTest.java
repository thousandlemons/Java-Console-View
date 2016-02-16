package io.bretty.console.view;

public class MenuViewTest {


    public static void main(String[] args) {
        ActionView simpleAction = new SimpleActionViewTest();
        ActionView simpleAction1 = new SimpleActionViewTest();
        MenuView menuView = new MenuView("Welcome!", "");
        MenuView menuView1 = new MenuView("Submenu", "Submenu");

        menuView1.addMenuItem(simpleAction1);

        menuView.addMenuItem(simpleAction);
        menuView.addMenuItem(menuView1);

        menuView.display();
    }
}
