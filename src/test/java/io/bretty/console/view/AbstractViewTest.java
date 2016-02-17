package io.bretty.console.view;

import java.util.Date;

import static org.junit.Assert.*;

public class AbstractViewTest {

    public static void main(String[] args) {
        System.out.println(new Date());
        ViewConfig viewConfig = new ViewConfig.Builder()
                .setActionCanceledMessage("Uh oh~ Action canceled")
                .setActionSuccessfulMessage("Congrats! You did it!")
                .setPauseMessage("Wait... If you wanna continue, you gotta press enter.")
                .build();

        ActionView actionView = new ActionView("Test", "") {
            @Override
            public void executeCustomAction() {
                if(this.confirmDialog("Are you sure you wanna do this?")){
                    println("Confirmed");
                }
            }
        };
        actionView.display();
    }

}