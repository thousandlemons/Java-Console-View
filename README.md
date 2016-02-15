# 

## Overview

### Introduction

This framework provides a way to quickly construct the View layer of a Java command line app.

There are two types of view classes:

* **Composite**: ask the user to select from a list of available options 
* **Action**: executes some custome logic wtih command line I/O

### Composite 

This is represented by the class `CompositeMenuItem`. The console output of it will be something like this.

```text
Welcome to the taxi booking service center.
1) Book a taxi
2) Check booking status
3) Booking history
4) Quit
Please enter a number to continue: 

```

As you can see from the example above, `CompositMenuItem` displays a list of available options. It asks for the user to input an integer. If invalid input occurs, there will be an error message.

```text
Invalid input. Please try again: 
```

Once the user enters a valid number, the system will run the corresponding option.

If the current `CompositeMenuItem` is not the root, the last option will always be `Back` instead of `Quit`. For example, the "booking history" menu will look like this.

```text
Booking History
1) View history
2) Remove all histories
3) Back
```
By entering the number for "Back", the user will go back to the parent menu, which is "Welcome..." in this case.

### Action

An action is what happens after the user selects a specific, for example, to book a taxi. The console out put of it will be something like:

```text
Booking a Taxi

// custom logic here

Press enter to continue...

```

The custom logic is the part where you need to write the code specific to your app.

After the user presses enter, it will go to the parent composite menu item view.


## User Guide

### Extend `ActionMenuItem`to Create a Custom Action

```java
class BookTaxiAction extends ActionMenuItem{

	public ActionMenuItem("Booking a Taxi", "Book a taxi");
	
	@override
	public void executeCustomAction() {
		// your custom logic and user IO here...
	}
}
```

Notice that the first string in the constructor will be printed in the console before your custom logic runs. The second string will be displayed as the option name in the parent menu, if there is any.

### Create a `CompositeMenuItem` object as History menu

```java
	CompositeMenuItem historyMenu = new CompositeMenuItem("Booking History", "Booking history");
	historyMenu.addMenuItem(new ViewHistoryAction());
	historyMenu.addMenuItem(new RemoveHistoryAction());
```

Similarly, the first string in the constructor is the first line to display when this menu is printed. The second is the option name in the parent menu, if there is any.

### Create a `CompositeMenuItem` object as the root menu

```java
	CompositeMenuItem rootMenu = new CompositeMenuItem("Welcome", "");
	
	rootMenu.addMenuItem(new BookTaxiAction());
	rootMenu.addMenuItem(new CheckBookingStatusAction());
	rootMenu.addMenuItem(historyMenu);
	
	rootMenu.display();
```
