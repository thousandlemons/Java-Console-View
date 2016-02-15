package menu;

public class CompositeMenuItemTest {


    public static void main(String[] args) {
        ActionMenuItem simpleAction = new SimpleActionMenuItem();
        ActionMenuItem simpleAction1 = new SimpleActionMenuItem();
        CompositeMenuItem compositeMenuItem = new CompositeMenuItem("Welcome!", "");
        CompositeMenuItem compositeMenuItem1 = new CompositeMenuItem("Submenu", "Submenu");

        compositeMenuItem1.addMenuItem(simpleAction1);

        compositeMenuItem.addMenuItem(simpleAction);
        compositeMenuItem.addMenuItem(compositeMenuItem1);

        compositeMenuItem.display();
    }
}
