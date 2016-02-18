package io.bretty.console.view;

public class CustomizationTest {

    public static void main(String[] args) {

        String confirmOption = "(yes/no)";

        Validator<String> confirmValidator = new Validator<String>() {
            @Override
            public boolean isValid(String s) {
                return s.toLowerCase().equals("yes");
            }
        };

        ViewConfig viewConfig = new ViewConfig.Builder()
                .setConfirm(confirmOption, confirmValidator)
                .build();
    }
}
