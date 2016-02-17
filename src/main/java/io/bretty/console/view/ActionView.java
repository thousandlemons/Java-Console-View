package io.bretty.console.view;

/**
 * The view that is used for a custom leaf functionality implementation.
 * It provides useful features like managed UI wrapper, e.g. {@code read()}
 * to read an input from the user with automatic validation.
 * Workflow of the default implementation:
 * 1) print title;
 * 2) execute custom action;
 * 3) pause
 * 4) go back to parent view
 */

public abstract class ActionView extends AbstractView {


    /**
     * the default message to display when paused
     */
    public static final String DEFAULT_PAUSE_MESSAGE = "Press enter to continue...";

    /**
     * the message to display when paused
     */
    private String pauseMessage = DEFAULT_PAUSE_MESSAGE;

    public ActionView(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);
    }

    public ActionView(String runningTitle, String nameInParentMenu, String inputErrorMessage, String pauseMessage) {
        super(runningTitle, nameInParentMenu, inputErrorMessage);
        this.pauseMessage = pauseMessage;
    }

    /**
     * to create your custom {@code ActionView} subclass, implement this method
     * to execute your custom logic between printing the title and go back to
     * the parent view
     */
    public abstract void executeCustomAction();

    /**
     * print the default pause message, and the user may press enter to continue.
     */
    protected void pause() {
        System.out.println();
        System.out.print(this.pauseMessage);
        this.keyboard.nextLine();
    }

    @Override
    public void display() {
        System.out.println();
        System.out.println(this.runningTitle);
        this.executeCustomAction();
        this.pause();
        this.goBack();
    }
}
