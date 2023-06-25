package it.unicam.cs.paduraru.engine.spacebots.api.commands;

import it.unicam.cs.paduraru.engine.spacebots.api.Label;
import it.unicam.cs.paduraru.engine.spacebots.api.entities.ERobot;
/*
*Ripete delle istruzioni fino a quando una certa condizione non è percepita nell’ambiente
* (ossia il robot è all’interno di un’area con la label richiesta):
 */
public class Until extends BotCommand{
    int doneIp;
    Label label;

    public Until(Label label){
        this.label = label;
    }
    @Override
    public int execute(ERobot target, int instructionPointer) {
       if(target.getCurrentLabels().contains(label))
           return doneIp+1;

       return instructionPointer+1;
    }

    @Override
    public void initialize() {

    }

    public int getDoneIp() {
        return doneIp;
    }

    public void setDoneIp(int doneIp) {
        this.doneIp = doneIp;
    }
}
