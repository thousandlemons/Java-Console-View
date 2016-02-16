package io.bretty.console.view;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class AbstractView {


    public static final String DEFAULT_ERROR_MESSAGE = "Invalid input. Please try again: ";

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
     * The message to display when the user inputs an invalid value
     */
    protected String inputErrorMessage = DEFAULT_ERROR_MESSAGE;

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
    }

    /**
     * @param runningTitle      see {@link io.bretty.console.view.AbstractView#runningTitle runningTitle}
     * @param nameInParentMenu  see {@link io.bretty.console.view.AbstractView#nameInParentMenu nameInParentView}
     * @param inputErrorMessage see{@link io.bretty.console.view.AbstractView#inputErrorMessage inputErrorMessage}
     */
    public AbstractView(String runningTitle, String nameInParentMenu, String inputErrorMessage) {
        this.runningTitle = runningTitle;
        this.nameInParentMenu = nameInParentMenu;
        this.inputErrorMessage = inputErrorMessage;
    }

    /**
     * display this view in the console
     */
    public abstract void display();

    /**
     * if {@code this.parentView != null}, go back to the parent view;
     * else, quit.
     */
    protected void goBack() {
        if (this.parentView != null) {
            this.parentView.display();
        } else {
            System.out.println();
            System.out.println("Shutting down... ");
        }
    }

    /**
     * A wrapper of {@code System.out.print(Object o);}
     *
     * @param o the object to print
     */
    protected void print(Object o) {
        System.out.print(o);
    }

    /**
     * A wrapper of {@code System.out.println(Object o);}
     *
     * @param o the object to print
     */
    protected void println(Object o) {
        System.out.println(o);
    }

    /**
     * A wrapper of {@code System.out.println(Object o);}
     */
    protected void println() {
        System.out.println();
    }

    /**
     * Read an object from command line user input
     *
     * @param message       The message to print asking for user's input
     * @param expectedClass The expected class of user input
     * @param <T>           Expected class
     * @return the scanned input of the expected type
     */
    protected <T> T read(String message, Class<T> expectedClass) {
        return this.read(message, expectedClass, null);
    }


    /**
     * Read an object from command line user input
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
    protected <T> T read(String message, Class<T> expectedClass, Validator<T> validator) {
        boolean isValid = false;
        Object input = null;
        T output = null;
        while (!isValid) {
            try {
                System.out.print(message);

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
                System.out.print(this.inputErrorMessage);
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
}
