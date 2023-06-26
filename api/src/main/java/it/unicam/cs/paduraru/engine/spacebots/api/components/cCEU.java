package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.Component;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

import java.util.List;

//Code Execution Unit
public class cCEU extends Component{
    List<BotCommand> commands;

    int instructionPointer;
    public cCEU(ERobot parent, List<BotCommand> commands) {
        super(parent);
        instructionPointer = 0;
        this.commands = commands;
    }

    public void executeNextLine(){
        instructionPointer = commands.get(instructionPointer).execute((ERobot) parent, instructionPointer);
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

    private void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }

}
