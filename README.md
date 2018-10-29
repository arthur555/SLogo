# slogo

A development environment that helps users write SLogo programs.

##Contributors

* Rahul Ramesh rr202
* Arthur Zhao sz165
* Inchan Hwang ih33
* Haotian Wang hw186

##Stats/Roles

Started about 10/14
Finished 10/28
Easily 125-150ish hours

Inchan: Front end...everything
Arthur: Some Front end and turtle controlling
Rahul: Commands, interaction with turtle model, memory
Haotian: Syntax interpretation/parsing

##Resources:

https://blog.buildo.io/a-tour-of-abstract-syntax-trees-906c0574a067 - Abstract Syntax Tree

https://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer
-Zooming relative to Mouse Pointer

In-class labs on Lambda Functions



##How to start the project:

1. Go to *File -> Project Structure -> Modules* and press + 
2. add *src/backend* and *src/frontend* **NOT** *backend* and *frontend*
3. press *frontend* module and go to dependencies tab and add *backend* as a dependency
4. set *frontend/resources* as resources folder
5. If you still see errors, run *File -> Invalidate Caches / Restart*

* To run, simply run SlogoApp from src/frontend/src/app/SlogoApp

No data or resource files required.

##Using the program:
* Run program, and look at left for options. Options include help, changing background color, adding turtles, and viewing documentation. Users can also have multiple tabs open for perhaps different simulations going on. They do NOT share memory
* Users can type into the window at the bottom. They can backspace, though they cannot navigate with cursor. Users can paste using ctrl-v on windows
* [] is for multiple expressions in one
* () is for a single expression to be evaluated
* If users want to put in multiple statements at once, please use []
* Users can click on successfully entered commands in the command history window to automatically copy them into the command line.
* To run any command that requires a turtle, at least one must be selected. This can be done either by clicking on the desired turtle, using the tell method, or clicking and dragging to box multiple turtles.
* The user can zoom in and out using the scroll wheel
* The user can change the animation speed, as well as stroke width

##Known Bugs
* Sometimes, when creating "fine lines" such as semicircles, javafx throws an unknown animation exception. At the end of the day, the shape was still drawn correctly, so this error was simply ignored.

##Extra Features
* We'd like to think zooming in/out is an extra feature!
* The ability to have multiple workspaces
* Stroke width



