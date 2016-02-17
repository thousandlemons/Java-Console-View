package io.bretty.console.view;

class SimpleAction extends ActionView {

    SimpleAction() {
        super("Running Simple Action", "Simple Action");
    }

    @Override
    public void executeCustomAction() {
        String line = this.prompt("Please input a line: ", String.class);
        this.println(line);

        Validator<Double> bidValidator = new Validator<Double>() {
            @Override
            public boolean isValid(Double input) {
                return Double.compare(input, 0) >= 0 && Double.compare(input, 50) <= 0 ;
            }
        };

        MenuView menuView = new MenuView("Deeper" + line, "deeper" + line);
        menuView.addMenuItem(new ActionView("Deeper Action" + line, "Deeper action" + line) {
            @Override
            public void executeCustomAction() {
                int input = prompt("Please enter an integer", Integer.class);
                System.out.println("your input is: " + input);
            }
        });
        menuView.setParentView(this);
        menuView.display();

    }
}
