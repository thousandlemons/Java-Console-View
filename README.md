#### *Download the package [`console-view-2.0.jar`](https://github.com/nathanielove/Java-Console-View/blob/master/console-view-2.0.jar?raw=true) to use this library.*

## Overview

### Introduction

This framework provides a way to quickly construct the View layer of a Java command line app.

The parent class of all classes in the View layer is an abstract class - `AbstractView`. This class has only one abstract public method, `.display()`, to start the console UI defined in this view.

There are two default subclasses:

* **`MenuView`**: ask the user to select from a list of available options 
* **`ActionView`**: executes some custome logic wtih command line UI

### Menu View

The workflow of the `.display()` method in `MenuView`:

1. Print `this.runningTitle`
1. For each `AbstractMenu` object in `this.menuItems`
	* Print index prefix
	* Print description
1. Print `this.selectionMessage`
1. Scan for integer user input

The console output of it will be something like:

```text
Welcome to the taxi booking service center.		// this.runningTitle
1) Book a taxi									// item in this.menuItems
2) Check booking status							// item in this.menuItems
3) Booking history								// item in this.menuItems
4) Quit											// this.backMenuName or this.quitMenuName
Please enter a number to continue: 				// this.selectionMessage
```

If invalid input occurs, there will be an error message.

```text
Invalid input. Please try again: 				// this.errorMessage
```

Once the user enters a valid number, the system will run the corresponding option.

If the current `MenuView` is not the root, the last option will always be `Back` instead of `Quit`. For example, the "booking history" menu will look like this.

```text
Booking History
1) View history
2) Remove all histories
3) Back
```
By entering the number for "Back", the user will go back to the parent menu, which is "Welcome..." in this case.

### Action View

An `ActionView` is what happens after the user selects a specific option, for example, to book a taxi. The console out put of it will be something like:

```text
Booking a Taxi									// this.runningTitle

// ... custom logic here

Press enter to continue...						// this.pauseMessage
```

The custom logic is the part where you need to write the code specific to your app.

After the user presses enter, it will go back and display again the parent menu view.


## User Guide

In this user guide, we will build a simple command line app "Taxi Booking", which prints the same result as the examples above.

### Create "Book Taxi" Action

```java
class BookTaxiAction extends ActionView{

	public BookTaxiAction(){
		super("Booking a Taxi", "Book a taxi");
	}
	
	@override
	public void executeCustomAction() {
		// your custom logic and UI to book a taxi...
	}
}
```

Notice that the first string in the constructor will be printed in the console before your custom logic runs. The second string will be displayed as the option name in the parent menu, if there is any.

### Create "History" Menu

```java
	MenuView historyMenu = new MenuView("Booking History", "Booking history");
	
	// populate menu items
	historyMenu.addMenuItem(new ViewHistoryAction());
	historyMenu.addMenuItem(new RemoveHistoryAction());
```

Similarly, the first string in the constructor is the first line to display when this menu is printed. The second is the option name in the parent menu, if there is any parent menu of this "History" menu.

### Create Root Menu and Run the App

```java
	MenuView rootMenu = new MenuView("Welcome...", "");
	
	// populate menu items
	rootMenu.addMenuItem(new BookTaxiAction());
	rootMenu.addMenuItem(new CheckBookingStatusAction());
	rootMenu.addMenuItem(historyMenu);
	
	// run the app
	rootMenu.display();
```

### Use More Powerful UI Wrappers

To take a quick look, the `AbstractMenu` class provides following instance methods:

```java
	// a wrapper to System.out.print(Object o)
	protected void print(Object o){...}

	// a wrapper to System.out.println()
	protected void println(){...}

	// a wrapper to System.out.println(Object o)
	protected void println(Object o){...}

	// read user input
	protected <T> T read(String message, Class<T> expectedClass){...}

	// read user input with custom validator
	protected <T> T read(String message, Class<T> expectedClass, Validator<T> validator){...}
```

The `ActionMenu` class also provides a useful instance method, which display the pause message `this.pauseMessage` and asks the users to press enter to continue.

```java
	protected void pause(){...}
```

The following example demonstrates a way to use these methods to build a custom `ActionMenu` class, assuming you have a controller class `BookTaxiController`

```java
public class BookTaxiView extends ActionView{

	// controller
	private BookTaxiController controller;

	public BookTaxiView(BookTaxiController controller){
		super("Booking a Taxi", "Book a taxi");
		this.controller = controller;
	}
	
	@Override
	public void executeCustomAction() {
	
		String name = this.read("Please enter your name: ", String.class);
	
		Validator<String> phoneNumberValidator = new Validator<String>(){
			@Override
			public boolean isValid(String t){
				// define your rule to validate phone numbers
				// ...	
			}
		};
		String phone = this.read("Please enter your phone number: ", String.class, phoneNumberValidator);
		
		Validator<Double> bidValidator = this.controller.getCurrentBidValidator();		
		double bid = this.read("Please enter your bid: ", Double.class, bidValidator);
		
		// call your controller
		boolean success = this.controller.book(name, phone, bid);
		
		if(success){
			this.println("Successfully book the following taxi...");
		}
		else{
			this.println("Sorry. We cannot make your booking. Please try again.")
		}
	}
}
```

A few points to notice:

1. The `read()` method uses the system scanneer and provides validation. When the user inputs an invalid value, because the input either doesn't sastify the expected class, or is rejected by the custom `Validator`, the error message (`this.errorMessage`) will be displayed, and the user will be asked to input the value again and again until a valid input is made.

1. The output methods `println` or `print` are just wrappers of the system defaul print methods like `System.out.println()`.

## Further Customization

There are multiple ways to customize this framework.

1. You may choose to pass in any custom attribute in the constructor (e.g. `selectionMessage`) to replace the system defaults (which is "Please enter a number to continue: " in this case).

1. You may implement your own `IndexNumberFormatter` and pass it into the constructor. The default implementation `DefaultIndexNumberFormatter` will render index 0 as `1) `, index 1 as `2) `, so on and so forth. The rendered strings will be printed before the menu item descriptions in the option list.

1. You may implement your own `Validator<T>` to valid user input.

1. You may wish to extend any existing class and override the template methods.

