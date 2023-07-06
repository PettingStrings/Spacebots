package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.PLabel;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.PRobot;
/*
*Ripete delle istruzioni fino a quando una certa condizione non è percepita nell’ambiente
* (ossia il robot è all’interno di un’area con la label richiesta):
 */
public class Until extends LoopCommand{
    int doneIp;
    PLabel label;

    public Until(PLabel label){
        this.label = label;
    }
    @Override
    public int execute(PRobot target, int instructionPointer) {
       if(target.getCurrentLabels().contains(label))
           return doneIp+1;

       return instructionPointer+1;
    }

    public int getDoneIp() {
        return doneIp;
    }

    public void setDoneIp(int doneIp) {
        this.doneIp = doneIp;
    }

    @Override
    public Object deepCopy() {
        Until command = new Until((PLabel) this.label.deepCopy());
        command.doneIp = this.doneIp;
        return command;
    }

    @Override
    public String convertToString() {
        return null;
    }
}
