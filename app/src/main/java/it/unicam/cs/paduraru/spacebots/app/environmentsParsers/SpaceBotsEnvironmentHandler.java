package it.unicam.cs.paduraru.spacebots.app.environmentsParsers;

import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementa i metodi di FollowMeParserHandler.
 */
public class SpaceBotsEnvironmentHandler implements FollowMeParserHandler {
    private int instructionPointer = 0;
    private final List<BotCommand> instructions = new ArrayList<>();
    private final Stack<Integer> loopPointers = new Stack<>();

    public List<BotCommand> getProgram() {
        return instructions;
    }

    @Override
    public void parsingStarted() {instructionPointer = 0;}

    @Override
    public void parsingDone() {}

    @Override
    public void moveCommand(double[] args) {//MOVE x y s
        instructionPointer++;
        instructions.add(new Move(new PVector(args[0], args[1]), args[2]));
    }

    @Override
    public void moveRandomCommand(double[] args) {//MOVE RANDOM x1 x2 y1 y2 s
        instructionPointer++;
        instructions.add(new MoveRandom(args[0], args[1], args[2], args[3], args[4]));
    }

    @Override
    public void signalCommand(String label) {//SIGNAL label
        instructionPointer++;
        instructions.add(new Signal(new PLabel(label)));
    }

    @Override
    public void unsignalCommand(String label) {//UNSIGNAL label
        instructionPointer++;
        instructions.add(new Unsignal(new PLabel(label)));
    }

    @Override
    public void followCommand(String label, double[] args) {//FOLLOW label dist s
        instructionPointer++;
        instructions.add(new Follow(new PLabel(label), args[0], args[1]));
    }

    @Override
    public void stopCommand() {
        instructionPointer++;
        instructions.add(new Stop());
    }

    @Override
    public void continueCommand(int s) {
        instructionPointer++;
        instructions.add(new Continue(s));
    }

    @Override
    public void repeatCommandStart(int n) {
        loopPointers.add(instructionPointer);
        instructionPointer++;
        instructions.add(new RepeatN(n));
    }

    @Override
    public void untilCommandStart(String label) {
        loopPointers.add(instructionPointer);
        instructionPointer++;
        instructions.add(new Until(new PLabel(label)));
    }

    @Override
    public void doForeverStart() {
        loopPointers.add(instructionPointer);
        instructionPointer++;
        instructions.add(new Forever());
    }

    @Override
    public void doneCommand() {
        instructionPointer++;
        Integer loopPointer = loopPointers.pop();
        instructions.add(new Done(loopPointer));
        if(instructions.get(loopPointer) instanceof LoopCommand loop)
            loop.setDoneIp(instructionPointer-1);
    }
}
