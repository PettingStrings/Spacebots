package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.BotCommand;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Code Execution Unit
public class cCEU extends PComponent {
    List<BotCommand> commands;

    int instructionPointer;
    public cCEU(ERobot parent, List<BotCommand> commands) {
        super(parent);
        instructionPointer = 0;
        this.commands = commands;
    }

    protected cCEU(PComponent com, List<BotCommand> commands){
        super(com);
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
    @Override
    public Object deepCopy(){
        cCEU component = new cCEU((PComponent) super.deepCopy(), new ArrayList<>());

        component.commands = this.commands.stream()
                .map(comm -> (BotCommand)comm.deepCopy())
                .collect(Collectors.toList());

        return component;
    }

}
