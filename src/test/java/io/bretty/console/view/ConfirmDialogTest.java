package io.bretty.console.view;

public class ConfirmDialogTest {

    public static void main(String[] args) {
        ActionView actionView = new ActionView("ConfirmDialogTest", "") {
            @Override
            public void executeCustomAction() {
                this.confirmDialog("Sure?");
            }
        };

        actionView.display();

    }
}
