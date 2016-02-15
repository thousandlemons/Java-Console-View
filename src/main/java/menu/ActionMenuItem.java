package menu;

public abstract class ActionMenuItem extends MenuItem {

    public static final String DEFAULT_PAUSE_MESSAGE = "Press enter to continue...";

    private String pauseMessage = DEFAULT_PAUSE_MESSAGE;

    public ActionMenuItem(String runningTitle, String nameInMenu) {
        super(runningTitle, nameInMenu);
    }

    public ActionMenuItem(String runningTitle, String nameInMenu, String pauseMessage) {
        super(runningTitle, nameInMenu);
        this.pauseMessage = pauseMessage;
    }

    public abstract void executeCustomAction();

    private void pause(){
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
