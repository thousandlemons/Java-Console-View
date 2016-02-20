package io.bretty.console.view;

public class MyAction extends ActionView {
    public MyAction() {
        super("My Action", "My action");
    }

    @Override
    public void executeCustomAction() {

        Validator<Integer> myValidator = new Validator<Integer>() {
            public boolean isValid(Integer integer) {
                return integer > 10;
            }
        };

        int myInt = this.prompt("Please input an integer: ", Integer.class, myValidator);
        this.println("Your integer is: " + myInt);
    }

    public static void main(String[] args) {
        ActionView myAction = new MyAction();
        myAction.display();
    }
}
