package io.bretty.console.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * The View that displays a list of options with index numbers, and asks the user to select one to proceed. It provides validation of user input.
 */

public class MenuView extends AbstractView {

    /**
     * a list of {@code AbstractView} objects to be displayed in the menu as available options
     */
    protected List<AbstractView> menuItems = new ArrayList<>();

    public MenuView(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);
    }

    public MenuView(String runningTitle, String nameInParentMenu, ViewConfig viewConfig) {
        super(runningTitle, nameInParentMenu, viewConfig);
    }

    /**
     * Add an entry to the menu; similar to remove, setter and getter
     *
     * @param menuItem to be appended to the last
     */
    public void addMenuItem(AbstractView menuItem) {
        menuItem.parentView = this;
        this.menuItems.add(menuItem);
    }

    public void removeMenuItem(int index) {
        this.menuItems.remove(index);
    }

    public void removeMenuItem(AbstractView menuItem){
        this.menuItems.remove(menuItem);
    }

    public List<AbstractView> getMenuItems() {
        return new ArrayList<>(this.menuItems);
    }

    public void setMenuItems(List<AbstractView> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= this.menuItems.size() + 1;
    }

    @Override
    public void display() {

        this.println();

        // print running title (e.g. "Create Item")
        this.println(this.runningTitle);

        // print all menu items
        // e.g.
        // 1) Create Item
        // 2) View Item
        // 3) ...

        for (int i = 0; i < this.menuItems.size(); ++i) {
            this.println(this.viewConfig.getIndexNumberFormatter().format(i) + this.menuItems.get(i).nameInParentMenu);
        }

        String backOrQuit = this.parentView == null ? this.viewConfig.getQuitMenuName() : this.viewConfig.getBackMenuName();

        // 4) Back/quit; always the last index
        this.println(this.viewConfig.getIndexNumberFormatter().format(this.menuItems.size()) + backOrQuit);


        // get a valid index number
        Validator<Integer> indexNumberValidator = new Validator<Integer>() {
            @Override
            public boolean isValid(Integer index) {
                return isValidIndex(index);
            }
        };

        int selection = this.prompt(this.viewConfig.getMenuSelectionMessage(), Integer.class, indexNumberValidator);

        // go parentView
        if (selection == this.menuItems.size() + 1) {
            this.goBack();
        } else {
            this.menuItems.get(selection - 1).display();
        }
    }

}
