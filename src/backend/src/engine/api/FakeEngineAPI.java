package engine.api;

import model.TurtleModel;

public class FakeEngineAPI implements EngineAPI {
    TurtleModel turtleModel;

    public FakeEngineAPI(TurtleModel turtleModel) {
        this.turtleModel = turtleModel;
    }

    @Override
    public void processString(String str) {
        // lexer -> parser -> interpreter
        System.out.println("proc");
        turtleModel.setX(100);
        turtleModel.move(true);
        turtleModel.setY(100);
        turtleModel.move(false);
    }
}
