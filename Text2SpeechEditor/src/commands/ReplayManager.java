package commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager  {

    ArrayList<ActionListener> commandsList;

    public ReplayManager() {
    	commandsList  = new ArrayList<>();
    }

    public void replay() {
    	for (ActionListener command : commandsList) {
    		command.actionPerformed(null);
    	}
    	
    }

    public void addCommand(ActionListener command) {
    		commandsList.add(command);
    }

    public ArrayList<ActionListener> getHistory() {
        return this.commandsList;
    }

}
