package io.bretty.console.view;

import java.util.Scanner;


public abstract class AbstractView {


    /**
     * the parent view of the current view
     * if the current view is the root view, this field will always be {@code null}
     */
    protected AbstractView parentView;

    /**
     * {@code runningTitle} is always to be displayed in the first line
     * whenever this view is running
     */
    protected String runningTitle;

    /**
     * {@code nameInParent} is the name of this view when displayed
     * in a parent {@link MenuView {@code MenuView}}
     */
    protected String nameInParentMenu;

    /**
     * the {@code Scanner} to handle user command line input
     */
    protected Scanner keyboard = new Scanner(System.in);


    /**
     * default constructor
     *
     * @param runningTitle     see {@link io.bretty.console.view.AbstractView#runningTitle {@code runningTitle}}
     * @param nameInParentMenu see {@link io.bretty.console.view.AbstractView#nameInParentMenu {@code nameInParentView}}
     */
    public AbstractView(String runningTitle, String nameInParentMenu) {
        this.runningTitle = runningTitle;
        this.nameInParentMenu = nameInParentMenu;
    }

    /**
     * display this view in the console
     */
    public abstract void display();

    /**
     * if {@code this.parentView != null}, go back to the parent view;
     * else, quit
     */
    protected void goBack() {
        if (this.parentView != null) {
            this.parentView.display();
        } else {
            System.out.println();
            System.out.println("Shutting down... ");
        }
    }


    public AbstractView getParentView() {
        return parentView;
    }

    public void setParentView(AbstractView parentView) {
        this.parentView = parentView;
    }

    public String getRunningTitle() {
        return runningTitle;
    }

    public void setRunningTitle(String runningTitle) {
        this.runningTitle = runningTitle;
    }

    public String getNameInParentMenu() {
        return nameInParentMenu;
    }

    public void setNameInParentMenu(String nameInParentMenu) {
        this.nameInParentMenu = nameInParentMenu;
    }
}
