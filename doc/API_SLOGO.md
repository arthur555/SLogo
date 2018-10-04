SLogo Architecture Design
---
Consider the following questions as you discuss the basic design elements of the SLogo project. Write a high-level description of the different parts of the project and how they can interact without worrying about the implementation details.

1. What is the result of parsing and who receives it?
    The parsing will be an API. It will send commands to the Turtle API, which will receive the commands and move appropriately.
2. When does parsing need to take place and what does it need to start properly?
    Parsing takes place as soon as lines are being entered from the front end of the code. The parser will read these lines, and if it can immediately act it will - e.g. if a simple command fd 50 is given. However, if a more advanced method is given, it will parse differently, for example if a for loop is given.
3. When are errors detected and how are they reported?
What do commands know, when do they know it, and how do they get it?
    Errors will mostly be caught by the command parsing API. They will throw back errors, which will be sent to the "code handling" API (front end), which will output an appropriate message.
4. How is the GUI updated after a command has completed execution?
    assumign command is valid.
    Command Executed (front end) > parser (back end) > turtle code (back end) > turtle visualizer (front end)

Author
---
* rr202 - Rahul Ramesh
* sz165 - Arthur Zhao
* hw186 - Haotian Wang
* ih33  - Inchan Hwang

API
---
* External: between the view (front end) and model (back end) modules
    * All visualization, we don't have any External API planned (yet), since the model is NOT going to call it. Only the other way around.
    * For the backend,
        * We need to think more about this.
* Internal: within each module (i.e., for its future programmers/maintainers)
    * SLogoDisplay: This class handles the Turtle on the Screen
        * Turtle updateTurtleInfo();
        * void moveTurtle(Turtle t);
        * void clear()
        * Grid updateBackGroundInfo();
        * void drawBackGround();
        * Map<String, String> handleUserOperations()

    * SLogoREPL:
        * handleParsingException(ParsingException e)
        * fetchCurrentLine()
    * How you plan to provide paths for extension through interfaces, inheritance, and design patterns for new features you might reasonably expect to be added to the program.
    * What subclasses or implementing classes will be used to extend your part to add new features: making it clear what kind of code someone will be expected to write, what parts of your code you expect to be closed to modification, and what errors may be thrown.
    * Note, while some of these methods may be public, many may be protected or package friendly.