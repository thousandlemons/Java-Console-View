package menu;

import java.util.Scanner;

public abstract class MenuItem {

    protected MenuItem backMenu;
    protected String runningTitle;
    protected String nameInMenu;

    protected Scanner keyboard = new Scanner(System.in);


    public MenuItem(String runningTitle, String nameInMenu) {
        this.runningTitle = runningTitle;
        this.nameInMenu = nameInMenu;
    }

    public abstract void display();

    protected void goBack(){
        if(this.backMenu != null){
            this.backMenu.display();
        }
        else{
            System.out.println();
            System.out.println("Shutting down... ");
        }
    }

    public MenuItem getBackMenu() {
        return backMenu;
    }

    public void setBackMenu(MenuItem backMenu) {
        this.backMenu = backMenu;
    }

    public String getRunningTitle() {
        return runningTitle;
    }

    public void setRunningTitle(String runningTitle) {
        this.runningTitle = runningTitle;
    }

    public String getNameInMenu() {
        return nameInMenu;
    }

    public void setNameInMenu(String nameInMenu) {
        this.nameInMenu = nameInMenu;
    }
}
