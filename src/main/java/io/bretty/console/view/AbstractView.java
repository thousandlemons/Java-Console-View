package io.bretty.console.view;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The parent class of all View classes, which defines infrastructure attributes (e.g. {@code parentView} and methods (e.g. {@code prompt()}
 */

public abstract class AbstractView {

    /**
     * the parent view of the current view
     * if the current view is the root view, this field will always be {@code null}
     */
    protected AbstractView parentView;

    /**
     * the string always to be displayed in the first line
     * whenever this view is running
     */
    protected String runningTitle;

    /**
     * the name of this view when displayed in a parent {@link MenuView MenuView}
     */
    protected String nameInParentMenu;


    /**
     * the configuration of default strings in the UI
     */
    protected ViewConfig viewConfig;

    /**
     * the {@code Scanner} to handle user command line input
     */
    protected Scanner keyboard = new Scanner(System.in);


    /**
     * default constructor
     *
     * @param runningTitle     see {@link io.bretty.console.view.AbstractView#runningTitle runningTitle}
     * @param nameInParentMenu see {@link io.bretty.console.view.AbstractView#nameInParentMenu nameInParentView}
     */
    public AbstractView(String runningTitle, String nameInParentMenu) {
        this.runningTitle = runningTitle;
        this.nameInParentMenu = nameInParentMenu;
        this.viewConfig = ViewConfig.DEFAULT;
    }

    /**
     * create a view with your own choice of words
     *
     * @param runningTitle     see {@link io.bretty.console.view.AbstractView#runningTitle runningTitle}
     * @param nameInParentMenu see {@link io.bretty.console.view.AbstractView#nameInParentMenu nameInParentView}
     * @param viewConfig
     */
    public AbstractView(String runningTitle, String nameInParentMenu, ViewConfig viewConfig) {
        this.runningTitle = runningTitle;
        this.nameInParentMenu = nameInParentMenu;
        this.viewConfig = viewConfig;
    }

    /**
     * display this view in the console
     */
    public abstract void display();

    /**
     * Override this method to execute your own logic when the view is going back to the parent view
     * Remember to call "super.onBack()" after your own logic
     */
    protected void onBack() {

    }

    /**
     * Override this method to execute your own logic when the user is quiting (i.e. this view doesn't have any parent view)
     * Remember to call "super.onQuit()" after your own logic
     */
    protected void onQuit() {
        this.println(this.viewConfig.getQuitMessage());
    }

    /**
     * Try to go back to the parent view.
     * if the parent view is null, then print quit message;
     * else if the parent view is a menu, then call the parent {@code .display()}
     * else if the parent view is an action, then just return without printing anything
     */
    protected void goBack() {
        if (this.parentView != null && this.parentView instanceof MenuView) {
            this.onBack();
            this.parentView.display();
        } else if (this.parentView == null) {
            this.onQuit();
        }
    }

    /**
     * print the default pause message, and the user may press enter to continue.
     */
    protected void pause() {
        this.print(this.viewConfig.getPauseMessage());
        this.keyboard.nextLine();
    }

    /**
     * Show confirmation dialog; if confirmed, return; else, print {@code this.viewConfig.actionCanceledMessage}
     *
     * @param warningMessage e.g. "Make a booking now?"
     * @return true if user has confirmed; false otherwise
     */
    protected boolean confirmDialog(String warningMessage) {
        String input = this.prompt(warningMessage + this.viewConfig.getConfirmOption(), String.class);
        input = input.replace("\n", "");
        boolean confirmed = this.viewConfig.getConfirmValidator().isValid(input);
        if (!confirmed) {
            this.actionCanceled();
        }
        return confirmed;
    }

    /**
     * print {@code this.viewConfig.actionCanceledMessage}
     */
    protected void actionCanceled() {
        this.println(this.viewConfig.getActionCanceledMessage());
    }

    /**
     * print {@code this.viewConfig.actionSuccessfulMessage}
     */
    protected void actionSuccessful() {
        this.println(this.viewConfig.getActionSuccessfulMessage());
    }

    /**
     * print {@code this.viewConfig.actionFailedMessage}
     */
    protected void actionFailed() {
        this.println(this.viewConfig.getActionFailedMessage());
    }

    /**
     * A wrapper of {@code System.out.print(Object o)}
     *
     * @param o the object to print
     */
    protected void print(Object o) {
        System.out.print(o);
    }

    /**
     * A wrapper of {@code System.out.println(Object o)}
     *
     * @param o the object to print
     */
    protected void println(Object o) {
        System.out.println(o);
    }

    /**
     * A wrapper of {@code System.out.println()}
     */
    protected void println() {
        System.out.println();
    }

    /**
     * Read an object from command line user input with default type validation.
     * It will repeatedly ask the user for re-try if validation continues to fail.
     *
     * @param message       The message to print asking for user's input
     * @param expectedClass The class of the expected data scanned (e.g. {@code Integer.class}).
     *                      Currently all expected classes as in the {@code .nextInt()},
     *                      {@code .nextDouble()} etc. are supported. Aka when you create a
     *                      {@code Scanner keyboard = new Scanner(System.in);}, all the {@code next...()}
     *                      methods you will invoke on the {@code keyboard} object can be
     *                      replaced by this method
     * @param <T>           Expected class
     * @return the scanned input of the expected type
     */
    protected <T> T prompt(String message, Class<T> expectedClass) {
        return this.prompt(message, expectedClass, null);
    }


    /**
     * Read an object from command line user input with custom validator
     * It will repeatedly ask the user for re-try if validation continues to fail.
     *
     * @param message       The message to print asking for user's input
     * @param expectedClass The class of the expected data scanned (e.g. {@code Integer.class}).
     *                      Currently all expected classes as in the {@code .nextInt()},
     *                      {@code .nextDouble()} etc. are supported. Aka when you create a
     *                      {@code Scanner keyboard = new Scanner(System.in);}, all the {@code next...()}
     *                      methods you will invoke on the {@code keyboard} object can be
     *                      replaced by this method
     * @param validator     A custom validator to validate the user input
     * @param <T>           Expected class
     * @return the scanned input of the expected type
     */
    protected <T> T prompt(String message, Class<T> expectedClass, Validator<T> validator) {
        boolean isValid = false;
        Object input = null;
        T output = null;
        this.print(message);
        while (!isValid) {
            try {
                if (expectedClass == Integer.class) {
                    input = keyboard.nextInt();
                } else if (expectedClass == Double.class) {
                    input = keyboard.nextDouble();
                } else if (expectedClass == String.class) {
                    input = keyboard.nextLine();
                } else if (expectedClass == Byte.class) {
                    input = keyboard.nextByte();
                } else if (expectedClass == BigDecimal.class) {
                    input = keyboard.nextBigDecimal();
                } else if (expectedClass == BigInteger.class) {
                    input = keyboard.nextBigInteger();
                } else if (expectedClass == Boolean.class) {
                    input = keyboard.nextBoolean();
                } else if (expectedClass == Float.class) {
                    input = keyboard.nextFloat();
                } else if (expectedClass == Long.class) {
                    input = keyboard.nextLong();
                } else if (expectedClass == Short.class) {
                    input = keyboard.nextShort();
                }

                output = expectedClass.cast(input);

                isValid = validator == null || validator.isValid(output);
            } catch (InputMismatchException e) {
                this.print(this.viewConfig.getInputErrorMessage());
            } finally {
                if (expectedClass != String.class) {
                    keyboard.nextLine();
                }
            }
        }
        return output;
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

    public ViewConfig getViewConfig() {
        return viewConfig;
    }

    public void setViewConfig(ViewConfig viewConfig) {
        this.viewConfig = viewConfig;
    }
}
