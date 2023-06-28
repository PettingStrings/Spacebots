package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;

import java.util.List;
import java.util.stream.Collectors;

//Code Execution Unit
public class PCEU extends PComponent {
    List<BotCommand> commands;
    int instructionPointer;
    public PCEU(ERobot parent, List<BotCommand> commands) {
        super(parent);
        instructionPointer = 0;
        this.commands = commands;
    }

    protected PCEU(PComponent com, List<BotCommand> commands){
        super(com);
        instructionPointer = 0;
        this.commands = commands;
    }
    public void executeNextLine(){
        if(instructionPointer >= commands.size()) return;
        instructionPointer = commands.get(instructionPointer).execute((ERobot) parent, instructionPointer);
    }
    public int getInstructionPointer() {
        return instructionPointer;
    }

    private void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }
    @Override
    public Object deepCopy(){
        PCEU component = new PCEU((PComponent) super.deepCopy(),
                this.commands.stream()
                        .map(comm -> (BotCommand)comm.deepCopy())
                        .collect(Collectors.toList()));

        return component;
    }

}