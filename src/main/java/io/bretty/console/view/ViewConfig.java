package io.bretty.console.view;

/**
 * Configuration of default strings in the UI
 */

public class ViewConfig {

    public static final ViewConfig DEFAULT = new ViewConfig();

    public static final Validator<String> DEFAULT_CONFIRM_VALIDATOR = new Validator<String>() {
        @Override
        public boolean isValid(String s) {
            return s.equals("y") || s.equals("Y");
        }
    };

    public static final String DEFAULT_INPUT_ERROR_MESSAGE = "Invalid input. Please try again: ";
    public static final String DEFAULT_MENU_SELECTION_MESSAGE = "Please enter a number to continue: ";
    public static final String DEFAULT_PAUSE_MESSAGE = "Press enter to continue...";
    public static final String DEFAULT_QUIT_MESSAGE = "Shutting down...";
    public static final String DEFAULT_BACK_MENU_NAME = "Back";
    public static final String DEFAULT_QUIT_MENU_NAME = "Quit";
    public static final String DEFAULT_CONFIRM_OPTION = " (y/N) ";
    public static final String DEFAULT_ACTION_CANCELED_MESSAGE = "Action canceled.";
    public static final String DEFAULT_ACTION_SUCCESSFUL_MESSAGE = "Action successful!";
    public static final String DEFAULT_ACTION_FAILED_MESSAGE = "Action failed.";

    private String inputErrorMessage = DEFAULT_INPUT_ERROR_MESSAGE;
    private String menuSelectionMessage = DEFAULT_MENU_SELECTION_MESSAGE;
    private String pauseMessage = DEFAULT_PAUSE_MESSAGE;
    private String quitMessage = DEFAULT_QUIT_MESSAGE;
    private String backMenuName = DEFAULT_BACK_MENU_NAME;
    private String quitMenuName = DEFAULT_QUIT_MENU_NAME;
    private String confirmOption = DEFAULT_CONFIRM_OPTION;
    private String actionCanceledMessage = DEFAULT_ACTION_CANCELED_MESSAGE;
    private String actionSuccessfulMessage = DEFAULT_ACTION_SUCCESSFUL_MESSAGE;
    private String actionFailedMessage = DEFAULT_ACTION_FAILED_MESSAGE;

    private IndexNumberFormatter indexNumberFormatter = DefaultIndexNumberFormatter.INSTANCE;

    private Validator<String> confirmValidator = DEFAULT_CONFIRM_VALIDATOR;

    private ViewConfig() {

    }

    public String getInputErrorMessage() {
        return inputErrorMessage;
    }

    public String getMenuSelectionMessage() {
        return menuSelectionMessage;
    }

    public String getPauseMessage() {
        return pauseMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public String getBackMenuName() {
        return backMenuName;
    }

    public String getQuitMenuName() {
        return quitMenuName;
    }

    public String getConfirmOption() {
        return confirmOption;
    }

    public String getActionCanceledMessage() {
        return actionCanceledMessage;
    }

    public String getActionSuccessfulMessage() {
        return actionSuccessfulMessage;
    }

    public String getActionFailedMessage() {
        return actionFailedMessage;
    }

    public IndexNumberFormatter getIndexNumberFormatter() {
        return indexNumberFormatter;
    }

    public Validator<String> getConfirmValidator() {
        return confirmValidator;
    }

    public ViewConfig copy(){
        ViewConfig viewConfig = new ViewConfig();
        viewConfig.inputErrorMessage = this.inputErrorMessage;
        viewConfig.menuSelectionMessage = this.menuSelectionMessage;
        viewConfig.pauseMessage = this.pauseMessage;
        viewConfig.quitMessage = this.quitMessage;
        viewConfig.backMenuName = this.backMenuName;
        viewConfig.quitMenuName = this.quitMenuName;
        viewConfig.confirmOption = this.confirmOption;
        viewConfig.actionCanceledMessage = this.actionCanceledMessage;
        viewConfig.actionSuccessfulMessage = this.actionSuccessfulMessage;
        viewConfig.actionFailedMessage = this.actionFailedMessage;
        viewConfig.indexNumberFormatter = this.indexNumberFormatter;
        viewConfig.confirmValidator = this.confirmValidator;
        return viewConfig;
    }

    /**
     * A builder class to create custom {@link io.bretty.console.view.ViewConfig ViewConfig} objects
     */
    public static class Builder{
        private ViewConfig viewConfig;

        public Builder() {
            this.viewConfig = new ViewConfig();
        }

        public Builder setMenuSelectionMessage(String menuSelectionMessage){
            this.viewConfig.menuSelectionMessage = menuSelectionMessage;
            return this;
        }

        public Builder setPauseMessage(String pauseMessage){
            this.viewConfig.pauseMessage = pauseMessage;
            return this;
        }

        public Builder setQuitMessage(String quitMessage){
            this.viewConfig.quitMessage = quitMessage;
            return this;
        }

        public Builder setBackMenuName(String backMenuName){
            this.viewConfig.backMenuName = backMenuName;
            return this;
        }

        public Builder setQuitMenuName(String quitMenuName){
            this.viewConfig.quitMenuName = quitMenuName;
            return this;
        }

        public Builder setConfirm(String confirmOption, Validator<String> confirmValidator){
            this.viewConfig.confirmOption = confirmOption;
            this.viewConfig.confirmValidator = confirmValidator;
            return this;
        }

        public Builder setActionCanceledMessage(String actionCanceledMessage){
            this.viewConfig.actionCanceledMessage = actionCanceledMessage;
            return this;
        }

        public Builder setActionSuccessfulMessage(String actionSuccessfulMessage){
            this.viewConfig.actionSuccessfulMessage = actionSuccessfulMessage;
            return this;
        }

        public Builder setActionFailedMessage(String actionFailedMessage){
            this.viewConfig.actionFailedMessage = actionFailedMessage;
            return this;
        }

        public Builder setIndexNumberFormatter(IndexNumberFormatter indexNumberFormatter){
            this.viewConfig.indexNumberFormatter = indexNumberFormatter;
            return this;
        }

        public ViewConfig build(){
            return this.viewConfig.copy();
        }
    }
}
