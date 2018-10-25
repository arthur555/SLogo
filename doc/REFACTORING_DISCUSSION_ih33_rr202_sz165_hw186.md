# Duplication Refactoring

### Fat codes 

``` java
private static final List<Image> ICONS = Collections.unmodifiableList(List.of(
    ImageUtils.getImageFromUrl("new_button.png", BUTTON_SIZE, BUTTON_SIZE),
    ImageUtils.getImageFromUrl("background_button.png", BUTTON_SIZE, BUTTON_SIZE),
    ImageUtils.getImageFromUrl("turtle_image_button.png", BUTTON_SIZE, BUTTON_SIZE),
    ImageUtils.getImageFromUrl("pen_color_button.png", BUTTON_SIZE, BUTTON_SIZE),
    ImageUtils.getImageFromUrl("language_button.png", BUTTON_SIZE, BUTTON_SIZE),
    ImageUtils.getImageFromUrl("help_button.png", BUTTON_SIZE, BUTTON_SIZE)
));

private static final List<String> TOOLTIPS = Collections.unmodifiableList(List.of(
    "Creates a new workspace",
    "Set background color",
    "Set turtle image",
    "Set pen color",
    "Set language",
    "Open documentation"
));
```

* It's not necessarily "redundant", but all these could have been slimmed out loading them from a *.properties* file.

# Checklist Refactoring

### Magic numbers/strings, use of null(s)

```java
Pair<Token, Integer> listStartPair = parseToken(doTimesPair.getValue(), "ListStart");
if (listStartPair.getKey() == null) {
    throw generateSyntaxException("Missing \"[\" symbol after dotimes keyword in a dotimes loop", listStartPair.getValue());
}
Pair<Expression, Integer> variablePair = parseVariable(listStartPair.getValue());
if (variablePair.getKey() == null) {
    throw generateSyntaxException("Illegal variable format after \"[\" in a dotimes loop", variablePair.getValue());
}
Pair<Expression, Integer> limitPair = parseExpression(variablePair.getValue());
if (limitPair.getKey() == null) {
    throw generateSyntaxException("Illegal expression for the upper limit value of the variable in a dotimes loop", limitPair.getValue());
}
Pair<Token, Integer> listEndPair = parseToken(limitPair.getValue(), "ListEnd");
if (listEndPair.getKey() == null) {
    throw generateSyntaxException("Missing \"]\" symbol after the limit value in a dotimes loop", limitPair.getValue());
}
Pair<Expression, Integer> expressionListPair = parseExpressionList(listEndPair.getValue());
if (expressionListPair.getKey() == null) {
    throw generateSyntaxException("Illegal format of a list of commands in a dotimes loop", expressionListPair.getValue());
}
```

# General Refactoring

### Monolithic interface

```java
public interface TurtleManager extends TurtleModel {
    /**
     *  SPECIAL ID to represent ALL TURTLES within this model
     */
    int ALL = 0;

    /**
     * @return id of the LAST turtle within the selected group, always > 0
     */
    int id();

    /**
     * @return id of the LAST turtle within the selected group, always > 0
     */
    List<Integer> selected();

    /**
     * @return total number of turtles created
     */
    int size();

    /**
     * Adds a turtle with the given ID
     * REJECTS ID <= 0
     * @return the ID of the turtle that we just added
     */
    int addTurtle(int id) throws IllegalParameterException;

    /**
     * Returns ObservableMap of (ID, TurtleModel)
     */
    ObservableMap<Integer, TurtleModel> turtleModels();

    /**
     *  Runs ops on every selected turtles
     */
    <T> T foreach(TurtleOperations<T> ops);

    /**
     * Selects all the turtles with given IDs
     * All operations will operate only to these selected turtles
     * @return id()
     */
    int tell(List<Integer> turtleIDs);

    /**
     * Performs the operation given by `ops` to all the turtles with the given IDs
     * It does not alter the previous selection
     * @return result of last command or 0 if there weren't any operations
     */
    <T> T ask(List<Integer> indices, TurtleOperations<T> ops);

    /**
     * Performs the operation given by `ops` to all the turtles that satisfy the predicate
     * It does not alter the previous selection
     * @return result of last command or 0 if there weren't any operations
     */
    <T> T askWith(Predicate<TurtleModel> p, TurtleOperations<T> ops);

    /**
     * @return StateMachine
     */
    StateMachine memory();
    void equipMemory(StateMachine memory);

    /**
     * I honestly feel like this shouldn't be here ... but it's easy to do
     * @blame inchan hwang
     */
    int setBackground(int index);

    /**
     * But these should definitely be here
     */
    int setPenColor(int index);
    int setPenSize(int pixels);
    int setShape(int index);
    int setPalette(int index, int r, int g, int b);
    int penColor();
    int shape();
}
```
Ideally, we should be separating UI-specific (background) / Turtle-specific interrfaces but we are currently using this giant interface.

# Long Methods

The obvious long methods in our code are the methods that actually execute commands. In a previous design of the program, we used reflection, so we created a new class for each type of command that could be input. However, in the current stage of the design, we opted for simple if/else statements. This makes the code quite a bit longer, and possibly harder or easier to understand.  

Long methods are another Code Smell that can reveal more than just centralization of your code — they are often also the source of inflexibility in your code because they are a sign of an object doing too much. By looking beyond simply breaking these methods up into smaller pieces, you may find violations of the Single Responsibility Principle or hard coded assumptions that can be reversed by creating an abstraction to support the Dependency Inversion Principle. Design Patterns can often help given you specific guidance in how to refactor your code to better support these principles.
