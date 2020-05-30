package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayCommand implements ActionListener {

    private ReplayManager replayManager;

    public ReplayCommand(ReplayManager replayManager) {
        this.setReplayManager(replayManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	replayManager.replay();
    }

    
	public ReplayManager getReplayManager() {
		return replayManager;
	}

	public void setReplayManager(ReplayManager replayManager) {
		this.replayManager = replayManager;
	}
}
