package io.bretty.console.view;

/**
 * Configuration of default strings in the UI
 */

public class ViewConfig {

    public static final ViewConfig DEFAULT = new ViewConfig();

    public static final String DEFAULT_INPUT_ERROR_MESSAGE = "Invalid input. Please try again: ";
    public static final String DEFAULT_MENU_SELECTION_MESSAGE = "Please enter a number to continue: ";
    public static final String DEFAULT_PAUSE_MESSAGE = "\nPress enter to continue...";
    public static final String DEFAULT_QUIT_MESSAGE = "\nShutting down...";
    public static final String DEFAULT_BACK_MENU_NAME = "Back";
    public static final String DEFAULT_QUIT_MENU_NAME = "Quit";
    public static final String DEFAULT_CONFIRM_OPTION = " (y/N) ";
    public static final String DEFAULT_ACTION_CANCELED_MESSAGE = "Action canceled.";
    public static final String DEFAULT_ACTION_SUCCESSFUL_MESSAGE = "Action successful!";
    public static final String DEFAULT_ACTION_FAILED_MESSAGE = "Action failed.";

    private static class DefaultIndexNumberFormatter implements IndexNumberFormatter{

        static final IndexNumberFormatter INSTANCE = new DefaultIndexNumberFormatter();

        private DefaultIndexNumberFormatter(){

        }

        @Override
        public String format(int index) {
            return (index + 1) + ") ";
        }
    }

    private static class DefaultConfirmValidator implements Validator<String>{

        static final DefaultConfirmValidator INSTANCE = new DefaultConfirmValidator();

        private DefaultConfirmValidator(){

        }

        @Override
        public boolean isValid(String s) {
            return s.equals("y") || s.equals("Y");
        }
    }

    private String inputErrorMessage;
    private String menuSelectionMessage;
    private String pauseMessage;
    private String quitMessage;
    private String backMenuName;
    private String quitMenuName;
    private String confirmOption;
    private String actionCanceledMessage;
    private String actionSuccessfulMessage;
    private String actionFailedMessage;

    private IndexNumberFormatter indexNumberFormatter;
    private Validator<String> confirmValidator;

    private ViewConfig() {
        this.inputErrorMessage = DEFAULT_INPUT_ERROR_MESSAGE;
        this.menuSelectionMessage = DEFAULT_MENU_SELECTION_MESSAGE;
        this.pauseMessage = DEFAULT_PAUSE_MESSAGE;
        this.quitMessage = DEFAULT_QUIT_MESSAGE;
        this.backMenuName = DEFAULT_BACK_MENU_NAME;
        this.quitMenuName = DEFAULT_QUIT_MENU_NAME;
        this.confirmOption = DEFAULT_CONFIRM_OPTION;
        this.actionCanceledMessage = DEFAULT_ACTION_CANCELED_MESSAGE;
        this.actionSuccessfulMessage = DEFAULT_ACTION_SUCCESSFUL_MESSAGE;
        this.actionFailedMessage = DEFAULT_ACTION_FAILED_MESSAGE;
        this.indexNumberFormatter = DefaultIndexNumberFormatter.INSTANCE;
        this.confirmValidator = DefaultConfirmValidator.INSTANCE;
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

        public Builder setInputErrorMessage(String inputErrorMessage) {
            this.viewConfig.inputErrorMessage = inputErrorMessage;
            return this;
        }

        public ViewConfig build(){
            return this.viewConfig.copy();
        }
    }
}
