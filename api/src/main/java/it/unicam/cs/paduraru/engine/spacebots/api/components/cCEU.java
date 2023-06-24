package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Component;
import it.unicam.cs.paduraru.engine.Entity;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;

import java.util.List;

//Code Execution Unit
public class cCEU extends Component{
    List<BotCommand> commands;
    int instructionPointer;
    public cCEU(Entity parent, List<BotCommand> commands) {
        super(parent);
        instructionPointer = 0;
        this.commands = commands;
    }

    public void executeNextLine(){
        instructionPointer = commands.get(instructionPointer).execute(parent, instructionPointer);
    }

}
