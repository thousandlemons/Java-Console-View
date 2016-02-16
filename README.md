### *Download the package [`ConsoleView-1.0.jar`]() to use this library.* 

## Overview

### Introduction

This framework provides a way to quickly construct the View layer of a Java command line app.

The parent class of all classes in the View layer is an abstract class - `ConsoleView`. This class has only one abstract public method, `.display()`, to start the console UI defined in this view.

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

In this user guide, we will build a simple command line app to "Taxi Booking", which prints the same result as the examples above.

### Create "Book Taxi" Action

```java
class BookTaxiAction extends ActionView{

	public ActionView(){
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
	
	historyMenu.addMenuItem(new ViewHistoryAction());
	historyMenu.addMenuItem(new RemoveHistoryAction());
```

Similarly, the first string in the constructor is the first line to display when this menu is printed. The second is the option name in the parent menu, if there is any parent menu of this "History" menu.

### Create Root Menu

```java
	MenuView rootMenu = new MenuView("Welcome...", "");
	
	rootMenu.addMenuItem(new BookTaxiAction());
	rootMenu.addMenuItem(new CheckBookingStatusAction());
	rootMenu.addMenuItem(historyMenu);
	
	rootMenu.display();
```

## Customization

There are multiple ways to customize this framework.

First, you can choose to pass in custom attributes (e.g. `selectionMessage`) in the constructor to replace the system defaults (which is "Please enter a number to continue: " in this case).

Second, you can implement your own `IndexNumberFormatter` and pass it into the constructor. The default implementation `DefaultIndexNumberFormatter` will render index 0 as `1) `, index 1 as `2) `, so on and so forth. The rendered strings will be printed before the menu item descriptions in the option list.

Finally, you may wish to extend any existing class and override the template methods.
