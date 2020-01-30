# Object Oriented Programming Project
Small Java-based project, where you can expect:
* Polymorphism 
* Hierarchy
* Jframe based GUI

The version used in this program was Java 8 Update 241

## Lore
This small, GUI-based, project that bases on the current day to organized and schedules task to defined project in a center
Note: because of project restriction it was not allowed to use either getClass() and static methods, therefore some could can look less clear/repetitive but I could not find a better way to do it;
## Flow
    The project is based in 2 packages:
    1. A backend, that contains all the users core functions the project, including all non-GUI classes:
    * Creating and managing the center;
    * Creting and managing tasks(Creating/ Deleting/ Changing status);
    * Managing people (adding and removing them from projects etc);
    * Handling Dates
    2. A GUI package, containing all Jframe action:
    * Each class represents a diferent window
    * Each class relates to the functions to the functions in the backend
    * Bootloading the project from either a txt-formatted file or an .obj file
    
## Additional Notes
As said above the code had some limitations and might not be as clear.
The best example of this is the fact that the loading of the center from an obj file is done from the GUI class. This is of course because static methods where not allowed.


## Known Bugs/Errors
* You can't add task to the main Teacher in a project
* Because of the difference between referencing between Widowns and Unix/MacOS, this might not work in windows
