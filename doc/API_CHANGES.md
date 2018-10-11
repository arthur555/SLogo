> Design_Plan.md

* Introduction
    * This program aims to build a working interpreter to parse/execute SLogo programming language to control the SLogo Turtles on the screen.
    * The primary design goal for this project is to make the set of instructions flexible; Also, to maximize flexibility on how SLogo Turtles are shown on the screen, we will be utilizing MVC pattern, decoupling Turtle's Model and View. We will aim for a interpreter that can actually handle infinite loops/recursions without overflowing the stack.

* Design Overview

    * There are four key components of this program: The editor, the interpreter, the Turtle Model and View.
        * Editor receives new commands / displays history of commands.
        * Editor's controller runs the interpreter with the given command, reporting syntax errors on the Editor view if there is one
        * The Editor Controller subscribes to the Interpreter for new TurtleCommands, and feeds it to Turtle model if it gets one.
        * The interpreter is a composition of two functions, and it resides on the model layer.
            * `parse: String -> AST`
            * `execute: (StateMachine, AST) -> (StateMachine, List[TurtleCommands])`
        * The interpreter executes the parsed AST step-by-step; once it reaches a point where a TurtleCommand is produced, it stops and notifies the EditorController of it, and resumes every tick(). The period of this tick() should be made very carefully or dynamically depending on the context of the computation within a separate controller.
        * The `TurtleModel` receives a `TurtleCommands` and processes it, and notifies the TurtleController of the new position/angle pair.
        * The `TurtleController`, subscribed to new position/angle pair, fetches it when notified; It then interpolates the intermediate position/angle pairs between (the lastest pair -> new pair) and updates the view accordingly on every tick(). This tick() is fed by the javaFX loop, and the update interval should be handled by another controller (although not shown on the diagram).

    * Diagram
    <a href="https://ibb.co/jUQOEU"><img src="https://preview.ibb.co/muJzop/SLogo.png" alt="SLogo" border="0"></a>

* User Interface

    * The user will have a textbox in the bottom right to type in any command they wish to input. Once they hit the enter button, the turtle will execute the given command.
    * After any command is entered and executed, the command will appear in a history log right above the command input pane.
    * However, if the user wants to input more complicated program structures such as a loop or function definition, the code will not execute until the entire advanced body is read in.
    *
    * The turtle will live inside a grid of boxes, and when commands are given, the turle will move from box to box, either leaving or not leaving  a trail behind (depending on the user input).
    * The user will also be able to load in files that contain scripts of Slogo. The user will then hit a "run" button, which will execute the script.
    * Any time a syntax error is discovered, the user will receive appropriate feedback. For example, if the user inputs a string when the system is expecting a number, the user will receive a note mentioning the error
This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.)

* API Details
    *
This section describes each API introduced in the Overview in detail. Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose.

* API Example Code
* User types fd 50 in the editor, and presses enter -> `EditorController`'s `submitCommand()` is executed
* `submitCommand` feeds the input to the `Interpreter`, which parses the input and puts it on to the execution queue within the `Interpreter`.
* The `Interpreter`, on every clock cycle, either resumes the processing of the current command or fetches a new cmmand to keep processing.
* Once the command fd 50 is executed, it gets translated into `TurtleMovements`, representing the movement of a turtle. The interpreter calls runTurtleCommands of the `TurtleModel` with the list of `TurtleMovements` as an argument.
* For each `TurtleMovements`, the model keeps a Cons queue of intermediate positions, and `TurtleController`, whenever the queue is filled, consumes those


It is especially important in helping others understand how to use your APIs to provide example code. It should be clear from this code which objects are responsible for completing each part of the task, but you do not have to implement the called functions.
Show an actual "sequence of code" that implements the following use case using only methods described in your APIs:
The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
Note, clearly show the flow of calls to public methods needed to complete this example, indicating which class contains each method called. It is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.
Additionally, each member of the team should create two use cases of their own (and example code) for the part of the project for which they intend to take responsibility. These can still be done as a group, but should represent a variety of areas of the overall project.

* Design Considerations

    One major issue we had to address was how to set up our code design overall (i.e. whether we should have a model-view-controller hierarchy, or whether we should not have the controller). One concern with building a separate, unique controller class is that it could potentially add unnecessary bulk to the project. While it does help to keep the code overall more separate and more modular, much of the functions can be included in the front end and back end classes.


This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.

* Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

Front end - Inchan and Arthur
Back end - Rahul, Haotian, and some Inchan