package engine.api;

import engine.commands.Command;
import model.TurtleModel;

public class FakeEngineAPI implements EngineAPI {
    TurtleModel turtleModel;

    public static final String[] COMMAND_NAMES = new String[]{
            "Backward",
            "Forward",
            "ClearScreen",
            "Home",
            "HideTurtle",
            "PenUp",
            "PenDown",
            "SetHeading",
            "SetPosition",
            "ShowTurtle"
    };

    public FakeEngineAPI(TurtleModel turtleModel) {
        this.turtleModel = turtleModel;
    }

    @Override
    public void processString(String str) {
        // lexer -> parser -> interpreter
        var tokens = str.split("\\s");
        for(var cmd : COMMAND_NAMES) {
            if(tokens[0].toLowerCase().equals(cmd.toLowerCase())) {
                try {
                    var command =
                            (Command<TurtleModel>) Class
                                    .forName("engine.commands."+cmd)
                                    .getConstructor(int.class)
                                    .newInstance(Integer.parseInt(tokens[1]));
                    command.update(turtleModel);
                } catch (Exception e) {
                    Command<TurtleModel> command = null;
                    try {
                        command = (Command<TurtleModel>) Class
                                .forName("engine.commands."+cmd)
                                .getConstructor(double.class)
                                .newInstance(Double.parseDouble(tokens[1]));
                        command.update(turtleModel);
                    } catch (Exception e1) {
                        try {
                            command = (Command<TurtleModel>) Class
                                    .forName("engine.commands." + cmd)
                                    .getConstructor()
                                    .newInstance();
                            command.update(turtleModel);
                        } catch (Exception e2) {}

                    }
                }
            }
        }
    }
}
