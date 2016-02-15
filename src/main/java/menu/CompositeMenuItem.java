package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class CompositeMenuItem extends MenuItem {

    public static final String DEFAULT_SELECTION_MESSAGE = "Please enter a number to continue: ";
    public static final String DEFAULT_ERROR_MESSAGE = "Invalid input. Please try again: ";
    public static final String DEFAULT_BACK_MENU_NAME = "Back";
    public static final String DEFAULT_QUIT_MENU_NAME = "Quit";

    private String selectionMessage = DEFAULT_SELECTION_MESSAGE;
    private String inputErrorMessage = DEFAULT_ERROR_MESSAGE;
    private String backMenuName = DEFAULT_BACK_MENU_NAME;
    private String quitMenuName = DEFAULT_QUIT_MENU_NAME;
    private IndexNumberFormatter indexNumberFormatter = DefaultIndexNumberFormatter.INSTANCE;

    private List<MenuItem> menuItems = new ArrayList<>();

    public CompositeMenuItem(String runningTitle, String nameInMenu) {
        super(runningTitle, nameInMenu);
    }

    public CompositeMenuItem(String runningTitle, String nameInMenu, String selectionMessage, String inputErrorMessage, String backMenuName, String quitMenuName, IndexNumberFormatter indexNumberFormatter) {
        super(runningTitle, nameInMenu);
        this.selectionMessage = selectionMessage;
        this.inputErrorMessage = inputErrorMessage;
        this.backMenuName = backMenuName;
        this.quitMenuName = quitMenuName;
        this.indexNumberFormatter = indexNumberFormatter;
    }

    public void addMenuItem(MenuItem menu){
        menu.backMenu = this;
        this.menuItems.add(menu);
    }

    public void removeMenuItem(int index){
        this.menuItems.remove(index);
    }

    public List<MenuItem> getMenuItems(){
        return new ArrayList<>(this.menuItems);
    }

    private boolean isValidIndex(int index){
        return index >= 1 && index <= this.menuItems.size() + 1;
    }

    private int readSelection(){

        int selection;

        try{
            selection = keyboard.nextInt();

        }
        catch (InputMismatchException e){
            selection = -1;
        }
        finally {
            keyboard.nextLine();
        }

        return selection;
    }

    @Override
    public void display() {

        System.out.println();

        // print running title (e.g. "Create Item")
        System.out.println(this.runningTitle);

        // print all menu items
        // e.g.
        // 1) Create Item
        // 2) View Item
        // 3) ...

        for(int i = 0; i < this.menuItems.size(); ++i){
            System.out.println(this.indexNumberFormatter.format(i + 1) + this.menuItems.get(i).nameInMenu);
        }

        String backOrQuit = this.backMenu == null? this.quitMenuName : this.backMenuName;

        // 4) Back/quit
        System.out.println(this.indexNumberFormatter.format(this.menuItems.size() + 1) + backOrQuit);

        // prompt for selection
        System.out.print(this.selectionMessage);

        // get a valid integer
        int selection = this.readSelection();
        while(!isValidIndex(selection)){
            System.out.print(this.inputErrorMessage);
            selection = this.readSelection();
        }

        // go backMenu
        if(selection == this.menuItems.size() + 1){
            this.goBack();
        }

        else{
            this.menuItems.get(selection - 1).display();
        }
    }

}
